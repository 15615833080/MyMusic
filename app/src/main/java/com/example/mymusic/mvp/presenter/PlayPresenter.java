package com.example.mymusic.mvp.presenter;

import android.content.Context;

public interface PlayPresenter {
    /**
     * 播放状态
     */
    void playMusic(String path);
    void pauseMusic(String path);
}
