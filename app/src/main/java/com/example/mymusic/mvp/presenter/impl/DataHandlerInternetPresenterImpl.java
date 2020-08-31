package com.example.mymusic.mvp.presenter.impl;

import android.content.Context;

import com.example.mymusic.mvp.model.MusicSourceModel;
import com.example.mymusic.mvp.model.SetMusicSourceModel;
import com.example.mymusic.mvp.model.impl.SetMusicSourceModelImpl;
import com.example.mymusic.mvp.presenter.DataHandlerInternetPresenter;
import com.example.mymusic.mvp.view.activity.ShowInternetDataView;
import com.example.mymusic.utils.LogUtils;

public class DataHandlerInternetPresenterImpl implements DataHandlerInternetPresenter {

    private static final String TAG = "DataHandlerInternetPres";
    private Context mContext;
    private ShowInternetDataView showInternetDataView;
    private SetMusicSourceModel setMusicSourceModel = new SetMusicSourceModelImpl(this);
    private MusicSourceModel mMusicSourceModel;

    public DataHandlerInternetPresenterImpl(Context context, ShowInternetDataView showInternetDataView, int tag) {
        mContext = context;
        this.showInternetDataView = showInternetDataView;
        setMusicSourceModel.getMusicSourceInternet();
    }

    public DataHandlerInternetPresenterImpl(Context context, ShowInternetDataView showInternetDataView) {
        mContext = context;
        this.showInternetDataView = showInternetDataView;
    }

    @Override
    public void setMusicSourceInternet(MusicSourceModel musicSourceModel) {
        LogUtils.d(TAG, musicSourceModel + "");
        mMusicSourceModel = musicSourceModel;
        if (mMusicSourceModel != null) {
            showInternetDataView.updateInternetAlbum(mMusicSourceModel.getAlbum());
            showInternetDataView.updateInternetHot(mMusicSourceModel.getHot());
            showInternetDataView.passMusicSource(musicSourceModel);
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
    public void dsetroy() {
        setMusicSourceModel.destroy();
    }
}
