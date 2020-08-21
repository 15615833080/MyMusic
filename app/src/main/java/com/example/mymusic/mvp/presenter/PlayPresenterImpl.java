package com.example.mymusic.mvp.presenter;

import android.content.Context;
import android.media.MediaPlayer;

import com.example.mymusic.helps.MediaPlayerHelp;
import com.example.mymusic.utils.LogUtils;

public class PlayPresenterImpl implements PlayPresenter {

    private Context mContext;
    private static final String TAG = "PlayPresenterImpl";
    private MediaPlayerHelp mMediaPlayerHelp;

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
            });
        }
    }

    @Override
    public void pauseMusic(String path) {
        mMediaPlayerHelp.pause();
    }
}
