package com.flowerfat.apilibrary.phone;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.flowerfat.apilibrary.R;


public class Phone2PlaceActivity extends AppCompatActivity {

    private final static String TAG = "PhonePlaceActivity";

    private EditText mEditText ;
    private TextView mTextView ;

    private String tel ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_place);

        findView();

    }

    private void findView(){
        mEditText = (EditText)findViewById(R.id.phone_edit);
        mTextView = (TextView)findViewById(R.id.phone_resultText);
    }


    public void SearchOnclick(View v){
        tel = mEditText.getText().toString().trim();
        if(tel.length() == 11){
            MyTask myTask = new MyTask();
            myTask.execute();
        } else {
            Toast.makeText(Phone2PlaceActivity.this, "请输入正确手机号", Toast.LENGTH_LONG).show();
        }
    }

    private class MyTask extends AsyncTask<String, Integer, String> {
        //onPreExecute方法用于在执行后台任务前做一些UI操作
        @Override
        protected void onPreExecute() {
            Log.i(TAG, "onPreExecute() called");
        }

        //doInBackground方法内部执行后台任务,不可在此方法内修改UI
        @Override
        protected String doInBackground(String... params) {
            Log.i(TAG, "doInBackground(Params... params) called");
            return new ApiPhone().phone2Place(tel);
        }

        //onProgressUpdate方法用于更新进度信息
        @Override
        protected void onProgressUpdate(Integer... progresses) {
            Log.i(TAG, "onProgressUpdate(Progress... progresses) called");
        }

        //onPostExecute方法用于在执行完后台任务后更新UI,显示结果
        @Override
        protected void onPostExecute(String result) {
            Log.i(TAG, "onPostExecute(Result result) called");
            mTextView.setText(result);

            Toast.makeText(Phone2PlaceActivity.this, result, Toast.LENGTH_LONG).show();
        }

        //onCancelled方法用于在取消执行中的任务时更改UI
        @Override
        protected void onCancelled() {
            Log.i(TAG, "onCancelled() called");
        }
    }

}
