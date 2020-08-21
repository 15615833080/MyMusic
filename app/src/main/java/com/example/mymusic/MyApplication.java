package com.example.mymusic;

import android.app.Application;

import com.blankj.utilcode.util.Utils;

import io.realm.Realm;

public class MyApplication extends Application {

    private static MyApplication myApplication;
    public static MyApplication getInstance() {
        return myApplication ;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        myApplication = this;
        Utils.init(this);
        Realm.init(this);
    }
}
