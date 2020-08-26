package com.example.mymusic.bean;

import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;

public class MusicSourceBean extends RealmObject {

    private RealmList<PlayListBean> playList;
    private RealmList<AlbumBean> album;
    private RealmList<MusicBean> hot;

    public RealmList<PlayListBean> getPlayList() {
        return playList;
    }

    public void setPlayList(RealmList<PlayListBean> playList) {
        this.playList = playList;
    }

    public RealmList<AlbumBean> getAlbum() {
        return album;
    }

    public void setAlbum(RealmList<AlbumBean> album) {
        this.album = album;
    }

    public RealmList<MusicBean> getHot() {
        return hot;
    }

    public void setHot(RealmList<MusicBean> hot) {
        this.hot = hot;
    }
}
