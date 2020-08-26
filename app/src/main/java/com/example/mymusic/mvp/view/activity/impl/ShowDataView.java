package com.example.mymusic.mvp.view.activity.impl;

import com.example.mymusic.bean.AlbumBean;
import com.example.mymusic.bean.MusicBean;
import com.example.mymusic.bean.PlayListBean;

import java.util.List;

public interface ShowDataView {
    void updateAlbum(List<AlbumBean> albumBeanList);
    void updateHot(List<MusicBean> musicBeanList);
    void updatePlayList(List<PlayListBean> playListBeanList);
    void updateMusic(List<MusicBean> musicBeanList);
    void updateIntro(AlbumBean albumBean, PlayListBean playListBean, MusicBean musicBean);
}
