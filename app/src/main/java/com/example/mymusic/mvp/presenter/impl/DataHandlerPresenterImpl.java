package com.example.mymusic.mvp.presenter.impl;

import android.content.Context;

import com.example.mymusic.bean.AlbumBean;
import com.example.mymusic.bean.MusicBean;
import com.example.mymusic.bean.MusicSourceBean;
import com.example.mymusic.helps.RealmHelp;
import com.example.mymusic.mvp.presenter.DataHandlerPresenter;
import com.example.mymusic.mvp.view.activity.ShowDataView;
import com.example.mymusic.utils.LogUtils;

public class DataHandlerPresenterImpl implements DataHandlerPresenter {
    private static final String TAG = "DataHandlerPresenterImp";
    private Context mContext;
    private ShowDataView showDataView;
    private RealmHelp realmHelp = new RealmHelp();
    private MusicSourceBean mMusicSourceBean;
    private AlbumBean mAlbumBean;
    private MusicBean musicBean;

    public DataHandlerPresenterImpl(Context context, ShowDataView showDataView) {
        mContext = context;
        this.showDataView = showDataView;
    }

    @Override
    public void getAlbum() {
        mMusicSourceBean = realmHelp.getMusicSource();
        showDataView.updateAlbum(mMusicSourceBean.getAlbum());
    }

    @Override
    public void getAlbum(String albumId, boolean isAlbum, boolean isPlayList, boolean isMusic) {
        mAlbumBean = realmHelp.getAlbum(albumId);
        if (isAlbum && !isPlayList && !isMusic) {
            showDataView.updateIntro(mAlbumBean, null, null);
            LogUtils.d(TAG, "getAlbumMusicList" + mAlbumBean.getList());
            showDataView.updateMusic(mAlbumBean.getList());
        }
    }

    @Override
    public void getHotData() {
        mMusicSourceBean = realmHelp.getMusicSource();
        showDataView.updateHot(mMusicSourceBean.getHot());
    }
    @Override
    public void getMusicData(String musicId) {
        musicBean = realmHelp.getMusic(musicId);
        showDataView.updateIntro(null,null, musicBean);
    }

    @Override
    public void getPlayData() {

    }



    @Override
    public void close() {
        realmHelp.close();
    }
}
