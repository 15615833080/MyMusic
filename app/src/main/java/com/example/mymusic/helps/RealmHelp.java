package com.example.mymusic.helps;

import android.content.Context;

import com.example.mymusic.bean.AlbumBean;
import com.example.mymusic.bean.MusicBean;
import com.example.mymusic.bean.MusicSourceBean;
import com.example.mymusic.bean.PlayListBean;
import com.example.mymusic.migration.Migration;
import com.example.mymusic.mvp.model.UserModel;
import com.example.mymusic.utils.DataUtils;

import java.io.FileNotFoundException;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmQuery;
import io.realm.RealmResults;

public class RealmHelp {
    private Realm mRealm;

    public RealmHelp() {
        mRealm = Realm.getDefaultInstance();
    }
    /**
     * Realm数据库发生结构性变化要进行迁移（模型或者模型中的字段有修改、增添或者删除）
     */
    /**
     * 告诉Realm数据需要迁移，并且为Realm设置最新配置
     */
    public static void migraiton(){
        RealmConfiguration configuration = getRealmConfiguration();

        //Realm设置最新的配置
        Realm.setDefaultConfiguration(configuration);
        //告诉Realm数据需要迁移
        try {
            Realm.migrateRealm(configuration);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    /**
     * 获取RealmConfiguration
     * @return
     */
    public static RealmConfiguration getRealmConfiguration() {
        return new RealmConfiguration.Builder()
                .schemaVersion(1)
                .migration(new Migration())
                .build();
    }

    /**
     * 保存用户信息
     */
    public void saveUser(UserModel userModel) {
        mRealm.beginTransaction();
        mRealm.insert(userModel);
        //mRealm.insertOrUpdate(userModel);
        mRealm.commitTransaction();
    }

    /**
     * 返回所有用户
     */
    public List<UserModel> getAllUser() {
        RealmQuery<UserModel> query = mRealm.where(UserModel.class);
        RealmResults<UserModel> realmResults = query.findAll();
        return realmResults;
    }

    /**
     * 验证用户信息
     */
    public boolean validateUser(String phone, String password) {
        boolean result = false;
        RealmQuery<UserModel> query = mRealm.where(UserModel.class);
        query.equalTo("phone", phone).equalTo("password", password);
        UserModel userModel = query.findFirst();
        if (userModel != null) {
            result = true;
        }
        return result;
    }

    /**
     * 获取当前登录用户的用户模型
     */
    public UserModel getUser() {
        RealmQuery<UserModel> query = mRealm.where(UserModel.class);
        query.equalTo("phone", UserHelp.getInstance().getPhone());
        UserModel userModel = query.findFirst();
        return userModel;
    }

    /**
     * 修改密码
     */
    public boolean updataPassword(String password) {
        UserModel userModel = getUser();
        mRealm.beginTransaction();
        userModel.setPassword(password);
        mRealm.commitTransaction();
        return true;

    }

    /**
     * 用户登录后存放数据
     * 用户退出后删除数据
     */
    /**
     * 保存音乐源数据
     */
    public void savaMusicModel(Context context) {
        String dataJson = DataUtils.getJsonFromAssets(context, "DataSource.json");
        mRealm.beginTransaction();
        mRealm.createObjectFromJson(MusicSourceBean.class, dataJson);
        mRealm.commitTransaction();
    }

    /**
     * 删除音乐源数据
     * 1.RealmResult delete
     * 2.Realm delete 删除这个模型下所有的数据
     */
    public void removeMusicModel(Context context) {
        mRealm.beginTransaction();
        mRealm.delete(MusicSourceBean.class);
        mRealm.delete(AlbumBean.class);
        mRealm.delete(PlayListBean.class);
        mRealm.delete(MusicBean.class);
        mRealm.commitTransaction();
    }
    /**
     * 获得MusicSourceBean
     */
    public MusicSourceBean getMusicSource(){
        return mRealm.where(MusicSourceBean.class).findFirst();
    }
    /**
     * 获得Album歌单
     */
    public AlbumBean getAlbum(String albumId){
        return mRealm.where(AlbumBean.class).equalTo("albumId", albumId).findFirst();
    }
    /**
     * 获得音乐
     */
    public MusicBean getMusic(String musicId){
        return mRealm.where(MusicBean.class).equalTo("musicId", musicId).findFirst();
    }

    /**
     * 关闭数据库
     */
    public void close() {
        if (mRealm != null && !mRealm.isClosed()) {
            mRealm.close();
        }
    }

}
