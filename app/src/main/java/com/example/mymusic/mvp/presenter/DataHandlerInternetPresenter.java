package com.example.mymusic.mvp.presenter;

import com.example.mymusic.mvp.model.MusicSourceModel;

public interface DataHandlerInternetPresenter {
    void setMusicSourceInternet(MusicSourceModel musicSourceModel);
    void getAlbumInternet(int currentPosition, boolean isAlbum, boolean isHot);
    void getAlbumMusicInternet(int currentPostion, boolean isAlbum, boolean isHot);
    void get();
    void dsetroy();
}
