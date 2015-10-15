package com.flowerfat.apifamily;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.flowerfat.apilibrary.main.Util;
import com.flowerfat.apilibrary.phone.Phone2PlaceActivity;


public class MainActivity extends AppCompatActivity {

    private ListView mListView ;
    private ArrayAdapter<String> mAdapter ;

    private String[] apiTitles = {"健康菜谱", "健康知识", "彩票", "手机号归属地", "天气预报", "油价", "身份证", "药品知识"};

    private Context mContext ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this ;

        initListView();
        test();
    }

    private void test(){
        String text = "\u5e7f\u5c9b\u4e4b\u604b.mp3";
        long beginTime1 = System.currentTimeMillis() ;
        Log.i("方法一", "开始");
        for (double i=0; i<25000;i++){
            Util.decodeUnicode(text);
        }
        Log.i("方法一", "共耗时："+(System.currentTimeMillis()-beginTime1));

        long beginTime2 = System.currentTimeMillis() ;
        Log.i("方法二", "开始");
        for (double i=0; i<25000;i++){
            Util.unicode2Utf8(text);
        }
        Log.i("方法二", "共耗时：" + (System.currentTimeMillis() - beginTime2));
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
            case 2:
                Toast.makeText(mContext, "马上搞定它！", Toast.LENGTH_LONG).show();
                break;
            case 3:
                startActivity(new Intent(mContext, Phone2PlaceActivity.class));
                break;
            default:
                Toast.makeText(MainActivity.this, "尚未添加该api，敬请期待~", Toast.LENGTH_LONG).show();
                break;
        }
    }


}
