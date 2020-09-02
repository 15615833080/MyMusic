package com.example.mymusic.greendao;

import android.content.Context;

public class DaoManager {

    //创建数据库的名字
    private static final String DB_NAME = "greendao.db";
    //创建数据库的工具
    private static DaoMaster.DevOpenHelper mHelper;//获取Helper对象
    //它里面实际上是保存的数据库的对象
    private static DaoMaster mDaoMaster;
    //管理gen里生成的所有的Dao对象里面带有基本的增删改查的方法；
    private static DaoSession mDaoSession;
    //上下文
    private Context mContext;
    //多线程中要被共享的使用volatile关键字修饰，GreenDao管理类
    private volatile static DaoManager mInstance;
    /**
     * 获取单例
     */
    public static DaoManager getInstance() {
        if (mInstance == null) {
            synchronized (DaoManager.class) {
                if (mInstance == null) {
                    mInstance = new DaoManager();
                }
            }

        }
        return mInstance;
    }

    private DaoManager(){

    }
    /**
     * 初始化上下文创建数据库的时候使用
     */
    public void init(Context context){
        this.mContext = context;
    }
    /**
     * 判断是否存在数据库，如果没有则创建
     */
    public DaoMaster getDaoMaster(){
        if(mDaoMaster == null){
            mHelper = new DaoMaster.DevOpenHelper(mContext, DB_NAME, null);
            mDaoMaster = new DaoMaster(mHelper.getWritableDatabase());
        }
        return mDaoMaster;
    }
    /**
     * 完成对数据库的增删改查操作
     */
    public DaoSession getDaoSession(){
        if(mDaoSession == null){
            if(mDaoMaster == null){
                mDaoMaster = getDaoMaster();
            }
            mDaoSession = mDaoMaster.newSession();
        }
        return mDaoSession;
    }
    /**
     * 关闭所有的操作，数据库开启后，使用完成要关闭
     */
    public void closeConnection(){
        if(mHelper != null){
            mHelper.close();
            mHelper = null;
        }
        if(mDaoSession != null){
            mDaoSession.clear();
            mDaoSession = null;
        }
    }

}