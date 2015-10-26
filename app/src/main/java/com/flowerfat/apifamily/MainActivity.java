package com.flowerfat.apifamily;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.flowerfat.apilibrary.healthCook.CookActivity;
import com.flowerfat.apilibrary.indentification.IdActivity;
import com.flowerfat.apilibrary.oilPrice.OilActivity;
import com.flowerfat.apilibrary.phone.Phone2PlaceActivity;


public class MainActivity extends AppCompatActivity {

    private ListView mListView ;
    private ArrayAdapter<String> mAdapter ;

    private String[] apiTitles = {"健康菜谱", "健康知识(尚未添加)", "彩票(尚未添加)", "手机号归属地", "天气预报(尚未添加)", "油价", "身份证", "药品知识(尚未添加)"};

    private Context mContext ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this ;

        initListView();
    }


    private void initListView(){
        mListView = (ListView)findViewById(R.id.main_listview);

        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, apiTitles);
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                listViewOnclick(position);
            }
        });
    }

    private void listViewOnclick(int position){
        switch (position){
            case 0:
                startActivity(new Intent(mContext, CookActivity.class));
                break;
            case 2:
                ;
                break;
            case 3:
                startActivity(new Intent(mContext, Phone2PlaceActivity.class));
                break;
            case 5:
                startActivity(new Intent(mContext, OilActivity.class));
                break;
            case 6:
                startActivity(new Intent(mContext, IdActivity.class));
                break;
            default:
//                startActivity(new Intent(mContext, TestWebView.class));
                break;
        }
    }





}
