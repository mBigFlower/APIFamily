package com.flowerfat.apilibrary.indentification;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.flowerfat.apilibrary.R;
import com.flowerfat.apilibrary.phone.ApiPhone;

/**
 * 身份证查询
 */
public class IdActivity extends AppCompatActivity {
    private final static String TAG = "IdActivity";

    private EditText mEditText ;
    private TextView mTextView ;

    private String id ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_id);

        findView();
    }

    private void findView(){
        mEditText = (EditText)findViewById(R.id.id_edit);
        mTextView = (TextView)findViewById(R.id.id_resultText);
    }

    public void SearchOnclick(View v){
        id = mEditText.getText().toString().trim();
        if(id.length() == 18){
            MyTask myTask = new MyTask();
            myTask.execute();
        } else {
            Toast.makeText(IdActivity.this, "请输入正确身份证号", Toast.LENGTH_LONG).show();
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
            return new ApiIndentify().id2Details(id);
        }

        //onProgressUpdate方法用于更新进度信息
        @Override
        protected void onProgressUpdate(Integer... progresses) {
        }

        //onPostExecute方法用于在执行完后台任务后更新UI,显示结果
        @Override
        protected void onPostExecute(String result) {
            mTextView.setText(result);

            Toast.makeText(IdActivity.this, result, Toast.LENGTH_LONG).show();
        }

        //onCancelled方法用于在取消执行中的任务时更改UI
        @Override
        protected void onCancelled() {
            Log.i(TAG, "onCancelled() called");
        }
    }

}
