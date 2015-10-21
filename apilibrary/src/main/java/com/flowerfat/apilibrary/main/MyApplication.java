package com.flowerfat.apilibrary.main;

import android.app.Application;
import android.util.Log;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by DELL on 2015/7/29.
 */
public class MyApplication extends Application {

    private static MyApplication myApplication = null;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("MyApplication", "application begin");

        myApplication = this; // 单例

        Fresco.initialize(this);
    }


    public static MyApplication getInstance() {
        return myApplication;
    }
}
