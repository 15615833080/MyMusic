package com.example.mymusic.helps;

import com.example.mymusic.bean.RecordBean;
import com.example.mymusic.greendao.DaoManager;
import com.example.mymusic.greendao.RecordBeanDao;

import java.util.Collections;
import java.util.List;

public class RecordHelper {
    //多线程中要被共享的使用volatile关键字修饰，GreenDao管理类
    private volatile static RecordHelper mInstance;
    private RecordBeanDao recordBeanDao = DaoManager.getInstance().getDaoSession().getRecordBeanDao();

    /**
     * 获取单例
     */
    public static RecordHelper getInstance() {
        if (mInstance == null) {
            synchronized (RecordHelper.class) {
                if (mInstance == null) {
                    mInstance = new RecordHelper();
                }
            }

        }
        return mInstance;
    }

    private RecordHelper() {
    }

    /**
     * 会自动判定是插入还是替换
     */
    public void insert(RecordBean recordBean) {
        recordBeanDao.insertOrReplace(recordBean);
    }

    /**
     * 查询所有数据
     */
    public List<RecordBean> searchAll() {
        List<RecordBean> recordBeanList = recordBeanDao.queryBuilder().orderDesc().list();
        Collections.reverse(recordBeanList);
        return recordBeanList;
    }

    /**
     * 指定id删除数据
     */
    public void delete(String musicId) {
        recordBeanDao.queryBuilder().where(RecordBeanDao.Properties.MusicId.eq(musicId)).buildDelete().executeDeleteWithoutDetachingEntities();

    }

}
