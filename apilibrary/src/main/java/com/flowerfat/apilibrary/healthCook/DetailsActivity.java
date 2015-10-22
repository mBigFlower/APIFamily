package com.flowerfat.apilibrary.healthCook;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.flowerfat.apilibrary.R;

public class DetailsActivity extends AppCompatActivity {

    // 菜的id
    private int id ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        getData();

    }

    private void getData() {

        id = getIntent().getIntExtra("id", -1);
        if(-1 == id){
            Toast.makeText(DetailsActivity.this, "出现异常。未提供菜谱的id ！", Toast.LENGTH_SHORT).show();
        } else {
            new MyTask().execute();
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
            return new ApiCook().getById(id).getMessage();
        }

        //onProgressUpdate方法用于更新进度信息
        @Override
        protected void onProgressUpdate(Integer... progresses) {
        }

        //onPostExecute方法用于在执行完后台任务后更新UI,显示结果
        @Override
        protected void onPostExecute(String result) {
            Log.i("result", result);
            TextView textView = (TextView)findViewById(R.id.details_text);
            textView.setMovementMethod(ScrollingMovementMethod.getInstance());
            textView.setText(Html.fromHtml(result));
        }

        //onCancelled方法用于在取消执行中的任务时更改UI
        @Override
        protected void onCancelled(){
        }
    }
}
