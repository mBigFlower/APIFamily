package com.flowerfat.apilibrary.healthCook;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.flowerfat.apilibrary.R;
import com.flowerfat.apilibrary.main.Util;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class CategoryActivity extends AppCompatActivity {
    private final static String TAG = "CategoryActivity";

    private RecyclerView mRecyclerView;
    private ListView categoryLV1, categoryLV2;
    private CookListAdapter mAdapter;

    private List<CookDetail> cookList = new ArrayList<>();
    private CookConstant mCookConstant = new CookConstant();

    private int categoryId ;
    private int listId ;
    private List<CookConstant.Big> xixiList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        findView();
        initToolbar();
        initListRecyclerView();

        init();

    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        toolbar.setTitle("标题");
        setSupportActionBar(toolbar);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                if (menuItem.getItemId() == R.id.action_search) {
                    Toast.makeText(CategoryActivity.this, "action_search", Toast.LENGTH_LONG).show();
                } else if (menuItem.getItemId() == R.id.action_filter) {
                    Toast.makeText(CategoryActivity.this, "action_filter", Toast.LENGTH_LONG).show();
                }
                return true;
            }
        });
    }

    /**
     * 初始化
     * 若，之前有过筛选，就按照上次的来，
     * 若，之前没有筛选，就显示Category列表
     */
    private void init() {
        SharedPreferences sp = getSharedPreferences("USER", MODE_PRIVATE);
        listId = sp.getInt("", 0);
//        listId = 37;
        if(listId == 0){
            // 展开Category列表
            initCategory();
        } else {
            // 显示上次的
            new MyTask().execute();
        }
    }

    private void initCategory(){
        categoryLV1 = (ListView) findViewById(R.id.categeryList1);
        categoryLV2 = (ListView) findViewById(R.id.categeryList2);
        categoryLV1.setVisibility(View.VISIBLE);
        categoryLV2.setVisibility(View.VISIBLE);

        String data = Util.getTextFromAssets(getApplicationContext(), "cookCategory.json");
        xixiList = new Gson().fromJson(data, new TypeToken<List<CookConstant.Big>>() {
        }.getType());

        final ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, CookConstant.categoryName);

        categoryLV1.setAdapter(adapter1);
        categoryLV1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                categoryId = position ;
                String[] ss = xixiList.get(categoryId).getChildrenName();
                ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(CategoryActivity.this, android.R.layout.simple_list_item_1, ss);

                categoryLV2.setAdapter(adapter2);
            }
        });

        categoryLV2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                listId = xixiList.get(categoryId).getChildrenId()[position];
                new MyTask().execute();
                categoryLV1.setVisibility(View.GONE);
                categoryLV2.setVisibility(View.GONE);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_category, menu);
        return true;
    }


    private void findView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        mCookConstant.initData();
    }

    private void initListRecyclerView() {
        // 设置LinearLayoutManager
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        // 设置ItemAnimator
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        // 设置固定大小
        mRecyclerView.setHasFixedSize(true);
        // 初始化自定义的适配器
        mAdapter = new CookListAdapter();
        // 为mRecyclerView设置适配器
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new CookListAdapter.onItemClickListener() {
            @Override
            public void onClick(View v, int position) {
                Intent intent = new Intent(CategoryActivity.this, DetailsActivity.class);
                intent.putExtra("id", cookList.get(position).getId());
                startActivity(intent);
            }
        });
    }

    private void setRecyclerView(List<CookDetail> cooks) {
        mAdapter.makeDatas(cooks);
    }

    private class MyTask extends AsyncTask<String, Integer, String> {
        //onPreExecute方法用于在执行后台任务前做一些UI操作
        @Override
        protected void onPreExecute() {

        }

        //doInBackground方法内部执行后台任务,不可在此方法内修改UI
        @Override
        protected String doInBackground(String... params) {

            return new ApiCook().getList(listId);
        }

        //onProgressUpdate方法用于更新进度信息
        @Override
        protected void onProgressUpdate(Integer... progresses) {
        }

        //onPostExecute方法用于在执行完后台任务后更新UI,显示结果
        @Override
        protected void onPostExecute(String result) {
            try {
                JSONObject jsonObject = new JSONObject(result);
                if (jsonObject.getBoolean("success")) {

                    cookList = new Gson().fromJson(jsonObject.getString("yi18"), new TypeToken<List<CookDetail>>() {
                    }.getType());
                    if (cookList.size() > 0) {
                        setRecyclerView(cookList);
                    } else {
                        Toast.makeText(CategoryActivity.this, "未搜索到相关菜谱", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(CategoryActivity.this, "什么鬼？请联系bigflower", Toast.LENGTH_LONG).show();
                }

            } catch (Exception e) {
                Log.e("菜谱名称获取", "果然不行:" + e.getMessage());
            }
        }

        //onCancelled方法用于在取消执行中的任务时更改UI
        @Override
        protected void onCancelled() {
        }
    }


}
