package com.example.mymusic;

import android.app.Application;

import com.blankj.utilcode.util.Utils;
import com.example.mymusic.greendao.DaoManager;
import com.example.mymusic.helps.RealmHelp;
import com.example.mymusic.mvp.presenter.impl.DataHandlerInternetPresenterImpl;

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
        DaoManager.getInstance().init(this);
        DaoManager.getInstance().getDaoMaster();
        //RealmHelp.migraiton();
    }
    public void initData(){
        DataHandlerInternetPresenterImpl.getInstance().init();
    }
}
