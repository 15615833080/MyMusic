package com.example.mymusic.mvp.presenter;

public interface DataHandlerPresenter {
    void getAlbum();
    void getAlbum(String albumId, boolean isAlbum, boolean isPlayList, boolean isMusic);
    void getHotData();
    void getPlayData();
    void getMusicData(String musicId);
    void close();
}
