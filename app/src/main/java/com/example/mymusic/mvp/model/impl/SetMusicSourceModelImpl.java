package com.example.mymusic.mvp.model.impl;

import com.example.mymusic.mvp.model.MusicSourceModel;
import com.example.mymusic.mvp.model.SetMusicSourceModel;
import com.example.mymusic.mvp.presenter.DataHandlerInternetPresenter;
import com.example.mymusic.network.NetWork;
import com.example.mymusic.utils.LogUtils;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class SetMusicSourceModelImpl implements SetMusicSourceModel {
    private static final String TAG = "SetMusicSourceModelImpl";
    private Disposable disposable;
    private DataHandlerInternetPresenter dataHandlerInternetPresenter;
    private MusicSourceModel mMusicSourceModel;
    public SetMusicSourceModelImpl(DataHandlerInternetPresenter dataHandlerInternetPresenter){
        this.dataHandlerInternetPresenter = dataHandlerInternetPresenter;
    }

    public MusicSourceModel fetchMusicSourceModel() {
        NetWork instance = NetWork.getInstance();
        instance.getMusicApi()
                .getMusicSource()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<MusicSourceModel>() {
                    @Override
                    public void accept(MusicSourceModel musicSourceModel) throws Exception {
                        mMusicSourceModel = musicSourceModel;
                        LogUtils.d(TAG, "getMusicSourceInternet" + musicSourceModel);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        LogUtils.d(TAG, "getMusicSourceInternet error");
                    }
                });
        return mMusicSourceModel;
    }
    @Override
    public void getMusicSourceInternet() {
        NetWork instance = NetWork.getInstance();
        if(disposable == null){
            disposable = instance.getMusicApi()
                    .getMusicSource()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<MusicSourceModel>() {
                        @Override
                        public void accept(MusicSourceModel musicSourceModel) throws Exception {
                            LogUtils.d(TAG, "getMusicSourceInternet" + musicSourceModel);
                            dataHandlerInternetPresenter.setMusicSourceInternet(musicSourceModel);
                        }
                    }, new Consumer<Throwable>() {
                        @Override
                        public void accept(Throwable throwable) throws Exception {
                            LogUtils.d(TAG, "getMusicSourceInternet error");
                        }
                    });
        }
    }

    @Override
    public void destroy() {
        disposable.dispose();
    }
}
