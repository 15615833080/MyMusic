package com.example.mymusic.mvp.view.activity;

import com.example.mymusic.mvp.model.MusicSourceModel;

import java.util.List;

public interface ShowInternetDataView {

    void updateInternetAlbum(List<MusicSourceModel.AlbumModel> albumModelList);
    void updateInternetHot(List<MusicSourceModel.HotModel> hotModelList);
    void updateInterentAlbumIntro(MusicSourceModel.AlbumModel albumModel);
    void updateInternetAlbumMusic(List<MusicSourceModel.AlbumModel.ListBeanX> albumMusicList);
    void updataInternetAlbumMusicIntro(MusicSourceModel.AlbumModel.ListBeanX AlbumMusicIntro);
    void updateInternetHotMusicIntro(MusicSourceModel.HotModel hotModel);
    void passMusicSource(MusicSourceModel musicSourceModel);
}
