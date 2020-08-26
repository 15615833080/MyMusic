package com.example.mymusic.helps;

import android.content.ComponentName;
import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;

import com.example.mymusic.utils.LogUtils;

import java.io.IOException;

public class MediaPlayerHelp {
    private static final String TAG = "MediaPlayerHelp";
    private Context mContext;
    private MediaPlayer mMediaPlayer;
    private String mPath;
    private static MediaPlayerHelp instance;
    private OnMediaPlayerHelperListener onMediaPlayerHelperListener;

    public void setOnMediaPlayerHelperListener(OnMediaPlayerHelperListener onMediaPlayerHelperListener) {
        LogUtils.d(TAG,"setOnMediaPlayerHelperListener");
        this.onMediaPlayerHelperListener = onMediaPlayerHelperListener;
    }

    public static MediaPlayerHelp getInstance(Context context) {
        if (instance == null) {
            synchronized (MediaPlayerHelp.class) {
                if (instance == null) {
                    instance = new MediaPlayerHelp(context);
                }
            }
        }
        return instance;
    }

    private MediaPlayerHelp(Context context) {
        mContext = context;
        mMediaPlayer = new MediaPlayer();
    }

    /**
     * 1.setPath：播放音乐的路径
     * 2.start：开始播放音乐
     * 3.pause：暂停播放音乐
     */
    //1.设置播放地址
    public void setPath(String path) {
        /**
         * 1.音乐正在播放，重置音乐播放状态
         * 2.设置meidaplayer的播放路径
         * 3.准备播放
         */
        LogUtils.d(TAG,"当前播放地址" + path);
        //1.音乐正在播放，重置音乐播放状态.音乐暂停播放，播放新的音乐
        if (mMediaPlayer.isPlaying() || !path.equals(mPath)) {
            mMediaPlayer.reset();
        }
        mPath = path;

        //2.设置meidaplayer的播放路径
        try {
            mMediaPlayer.setDataSource(mContext, Uri.parse(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //3.准备播放
        mMediaPlayer.prepareAsync();
        //监听准备完成
        mMediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                if (onMediaPlayerHelperListener != null) {
                    LogUtils.d(TAG, "mediaPlayer");
                    onMediaPlayerHelperListener.onPrepared(mediaPlayer);
                }else {
                    LogUtils.d(TAG, "mediaPlayer" + mediaPlayer);
                }
            }
        });
        //监听播放完成
        mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                if(onMediaPlayerHelperListener != null){
                    LogUtils.d(TAG, "mediaPlayer");
                    onMediaPlayerHelperListener.onCompletion(mediaPlayer);
                }else {
                    LogUtils.d(TAG, "mediaPlayer" + mediaPlayer);
                }
            }
        });

    }

    public String getPath() {
        return mPath;
    }

    //2.播放音乐
    public void start() {
        if (mMediaPlayer.isPlaying()) {
            return;
        } else {
            mMediaPlayer.start();
        }
    }

    //3.暂停播放
    public void pause() {
        if (mMediaPlayer.isPlaying() && mMediaPlayer != null) {
            mMediaPlayer.pause();
        }
    }


    //定义接口，将异步加载完成的通知外放出去
    public interface OnMediaPlayerHelperListener {
        void onPrepared(MediaPlayer mediaPlayer);
        void onCompletion(MediaPlayer mediaPlayer);
    }
}
