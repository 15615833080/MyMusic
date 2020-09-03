package com.example.mymusic.mvp.presenter.impl;

import android.content.Context;

import com.example.mymusic.greendao.DaoManager;
import com.example.mymusic.greendao.RecordBeanDao;
import com.example.mymusic.helps.RecordHelper;
import com.example.mymusic.mvp.model.MusicSourceModel;
import com.example.mymusic.mvp.model.SetMusicSourceModel;
import com.example.mymusic.mvp.model.impl.SetMusicSourceModelImpl;
import com.example.mymusic.mvp.presenter.DataHandlerInternetPresenter;
import com.example.mymusic.mvp.view.activity.ShowInternetDataView;
import com.example.mymusic.utils.LogUtils;

import java.security.PublicKey;

public class DataHandlerInternetPresenterImpl implements DataHandlerInternetPresenter {

    private static final String TAG = "DataHandlerInternetPres";
    private Context mContext;
    private ShowInternetDataView showInternetDataView;
    private SetMusicSourceModel setMusicSourceModel = new SetMusicSourceModelImpl(this);
    private MusicSourceModel mMusicSourceModel;
    //多线程中要被共享的使用volatile关键字修饰
    private static DataHandlerInternetPresenterImpl mInstance;
    /**
     * 获取单例
     */
    public static DataHandlerInternetPresenterImpl getInstance() {
        if (mInstance == null) {
            synchronized (RecordHelper.class) {
                if (mInstance == null) {
                    mInstance = new DataHandlerInternetPresenterImpl();
                }
            }

        }
        return mInstance;
    }
    private DataHandlerInternetPresenterImpl() {
    }

    public void init(Context context){
        mContext = context;
        setMusicSourceModel.getMusicSourceInternet();
    }
    public void initShowDataView(ShowInternetDataView showInternetDataView){
        this.showInternetDataView = showInternetDataView;
    }
/*    public DataHandlerInternetPresenterImpl(Context context, ShowInternetDataView showInternetDataView) {
        mContext = context;
        this.showInternetDataView = showInternetDataView;
    }*/

    @Override
    public void setMusicSourceInternet(MusicSourceModel musicSourceModel) {
        LogUtils.d(TAG, musicSourceModel + "");
        mMusicSourceModel = musicSourceModel;
        LogUtils.d(TAG, mMusicSourceModel + "");
        if (mMusicSourceModel != null) {
            showInternetDataView.updateInternetAlbum(mMusicSourceModel.getAlbum());
            showInternetDataView.updateInternetHot(mMusicSourceModel.getHot());
        }
    }

    @Override
    public void getAlbumInternet(int currentPosition, boolean isAlbum, boolean isHot) {
        if (mMusicSourceModel != null && isAlbum) {
            showInternetDataView.updateInterentAlbumIntro(mMusicSourceModel.getAlbum().get(currentPosition));
            showInternetDataView.updateInternetAlbumMusic(mMusicSourceModel.getAlbum().get(currentPosition).getList());
        }
    }

    @Override
    public void getAlbumMusicInternet(int currentPostion, boolean isAlbum, boolean isHot) {
        showInternetDataView.updataInternetAlbumMusicIntro(mMusicSourceModel.getAlbum().get(1).getList().get(currentPostion));
    }

    @Override
    public void get() {
        LogUtils.d(TAG, "mMusicSourceModel");
        showInternetDataView.passMusicSource(mMusicSourceModel);
    }


    @Override
    public void dsetroy() {
        setMusicSourceModel.destroy();
    }
}
