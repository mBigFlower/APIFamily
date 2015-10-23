package com.flowerfat.apilibrary.healthCook;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.flowerfat.apilibrary.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class NameActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private CookAdapter myAdapter;
    private EditText mEditText;

    private String cookName;
    private List<CookName> cookList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name);

        findView();
        initRecyclerView();
    }

    private void findView() {
        mEditText = (EditText) findViewById(R.id.cookName_edit);
        mRecyclerView = (RecyclerView) findViewById(R.id.cookName_recyclerView);

    }

    private void initRecyclerView() {
        // 设置LinearLayoutManager
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        // 设置ItemAnimator
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        // 设置固定大小
        mRecyclerView.setHasFixedSize(true);
        // 初始化自定义的适配器
        myAdapter = new CookAdapter();
        // 为mRecyclerView设置适配器
        mRecyclerView.setAdapter(myAdapter);


        myAdapter.setOnItemClickListener(new CookAdapter.onItemClickListener() {
            @Override
            public void onClick(View v, int position) {
                Intent intent = new Intent(NameActivity.this, DetailsActivity.class);
                intent.putExtra("id", cookList.get(position).getId());
                startActivity(intent);
            }
        });
    }

    private void setRecyclerView(List<CookName> cooks) {
        myAdapter.makeDatas(cooks);
    }

    public void SearchOnclick(View v) {
        cookName = mEditText.getText().toString().trim();
        if (!"".equals(cookName)) {
            new MyTask().execute();
        } else {
            Toast.makeText(NameActivity.this, "请输入美食名称", Toast.LENGTH_LONG).show();
        }
    }



    private class MyTask extends AsyncTask<String, Integer, String> {
        //onPreExecute方法用于在执行后台任务前做一些UI操作
        @Override
        protected void onPreExecute() {

        }

        //doInBackground方法内部执行后台任务,不可在此方法内修改UI
        @Override
        protected String doInBackground(String... params) {
            return new ApiCook().getByName(cookName);
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
                if(jsonObject.getBoolean("success")){
                    cookList = new Gson().fromJson(jsonObject.getString("yi18"), new TypeToken<List<CookName>>() {
                    }.getType());
                    if (cookList.size() > 0) {
                        setRecyclerView(cookList);
                    } else {
                        Toast.makeText(NameActivity.this, "未搜索到相关菜谱", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(NameActivity.this, "什么鬼？请联系bigflower", Toast.LENGTH_LONG).show();
                }

            } catch (Exception e) {
                Log.e("菜谱名称获取","果然不行:"+e.getMessage());
            }
        }
        //onCancelled方法用于在取消执行中的任务时更改UI
        @Override
        protected void onCancelled() {
        }
    }


}
