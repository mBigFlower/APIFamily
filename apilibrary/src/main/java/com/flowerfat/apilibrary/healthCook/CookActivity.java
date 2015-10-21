package com.flowerfat.apilibrary.healthCook;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.flowerfat.apilibrary.R;

public class CookActivity extends AppCompatActivity {
    private final static String TAG = "CookActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);

    }

    public void cookOnclick(View v){
        if(v.getId() == R.id.cook_name){
            startActivity(new Intent(CookActivity.this, NameActivity.class));
        } else if(v.getId() == R.id.cook_details){
            startActivity(new Intent(CookActivity.this, DetailsActivity.class));
        } else if(v.getId() == R.id.cook_categery){

        } else if(v.getId() == R.id.cook_list){

        }
    }

}
