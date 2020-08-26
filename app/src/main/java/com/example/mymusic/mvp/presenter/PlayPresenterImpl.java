package com.example.mymusic.mvp.presenter;

import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.media.MediaPlayer;
import android.os.IBinder;

import com.example.mymusic.bean.MusicBean;
import com.example.mymusic.helps.MediaPlayerHelp;
import com.example.mymusic.service.MusicService;
import com.example.mymusic.utils.LogUtils;

import java.security.Provider;

public class PlayPresenterImpl implements PlayPresenter {

    private Context mContext;
    private static final String TAG = "PlayPresenterImpl";
    private MediaPlayerHelp mMediaPlayerHelp;
    private MusicService.MusicBindr mMusicBindr;
    private Intent mMusicIntent;
    private boolean isBinding;
    private MusicBean mMusicBean;

    public PlayPresenterImpl(Context context){
        mContext = context;
        mMediaPlayerHelp = MediaPlayerHelp.getInstance(mContext);
    }

    /**
     * 1.判断当前音乐是否正在进行播放
     * 2.如果当前音乐正在播放，执行start方法
     * 3.如果不是正在播放的音乐，重置播放的状态。
     * @param path
     */
    @Override
    public void playMusic(String path) {
        if(mMediaPlayerHelp.getPath()!= null && mMediaPlayerHelp.getPath().equals(path)){
            LogUtils.d(TAG,"当前音乐正在播放，播放地址" + path);
            mMediaPlayerHelp.start();
        }else {
            LogUtils.d(TAG,"播放新的音乐，播放地址" + path);
            mMediaPlayerHelp.setPath(path);
            LogUtils.d(TAG,"进入" + path);
            mMediaPlayerHelp.setOnMediaPlayerHelperListener(new MediaPlayerHelp.OnMediaPlayerHelperListener() {
                @Override
                public void onPrepared(MediaPlayer mediaPlayer) {
                    LogUtils.d(TAG, "setOnMediaPlayerHelperListener");
                    mMediaPlayerHelp.start();
                }

                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    LogUtils.d(TAG, "播放完成之后，停止播放");
                    ((Service)mContext).stopSelf();
                }
            });
        }
    }

    @Override
    public void pauseMusic(String path) {
        mMediaPlayerHelp.pause();
    }

  /*  *//**
     * 启动播放音乐服务
     *//*
    public void startMusicService() {
        if (mMusicIntent == null) {
            mMusicIntent = new Intent(mContext, MusicService.class);
            mContext.startService(mMusicIntent);
        }
        //绑定service
        if(!isBinding){
            isBinding = true;
            mContext.bindService(mMusicIntent, conn, Context.BIND_AUTO_CREATE);
        }
    }

    *//**
     * 解除绑定
     *//*
    public void destroy(){
        if(isBinding){
            isBinding = false;
            mContext.unbindService(conn);
        }
    }

    private ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            mMusicBindr = (MusicService.MusicBindr) iBinder;
            mMusicBindr.setMusic(mMusicBean);
            mMusicBindr.playMusic();
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };*/
}
