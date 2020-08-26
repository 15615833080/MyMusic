package com.example.mymusic.mvp.presenter;

import android.content.Context;

public interface PlayPresenter {
    /**
     * 开始播放
     */
    void playMusic(String path);

    /**
     * 停止播放
     * @param path
     */
    void pauseMusic(String path);

}
