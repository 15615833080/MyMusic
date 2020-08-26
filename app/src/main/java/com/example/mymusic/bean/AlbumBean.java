package com.example.mymusic.bean;

import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.RealmField;

public class AlbumBean extends RealmObject {
    /**
     * albumId : 1
     * name : Nostalgic
     * title : 『伤感华语』关于我们，你遗憾吗
     * intro : 最最害怕隔着屏幕说分手
     连最后抱抱你的机会都没有
     明明一丁点的小事...
     * poster : http://res.lgdsunday.club/poster-9.png
     * playNum : 203.3万
     * list : [{"musicId":"101","name":"Nostalgic Piano","poster":"http://res.lgdsunday.club/poster-1.png","path":"http://res.lgdsunday.club/Nostalgic%20Piano.mp3","author":"Rafael Krux"},{"musicId":"102","name":"La Citadelle","poster":"http://res.lgdsunday.club/poster-2.png","path":"http://res.lgdsunday.club/La%20Citadelle.mp3","author":"Komiku"},{"musicId":"103","name":"Champ de tournesol","poster":"http://res.lgdsunday.club/poster-3.png","path":"http://res.lgdsunday.club/Champ%20de%20tournesol.mp3","author":"Komiku"},{"musicId":"104","name":"Horizon Flare","poster":"http://res.lgdsunday.club/poster-4.png","path":"http://res.lgdsunday.club/Horizon%20Flare.mp3","author":"Alexander Nakarada"},{"musicId":"105","name":"Lovely Piano Song","poster":"http://res.lgdsunday.club/poster-5.png","path":"http://res.lgdsunday.club/Lovely%20Piano%20Song.mp3","author":"Rafael Krux"},{"musicId":"106","name":"Romantic Inspiration","poster":"http://res.lgdsunday.club/poster-6.png","path":"http://res.lgdsunday.club/Romantic%20Inspiration.mp3","author":"Rafael Krux"},{"musicId":"107","name":"Brothers Unite","poster":"http://res.lgdsunday.club/poster-7.png","path":"http://res.lgdsunday.club/Brothers%20Unite.mp3","author":"Alexander Nakarada"},{"musicId":"108","name":"Amazing Grace","poster":"http://res.lgdsunday.club/poster-8.png","path":"http://res.lgdsunday.club/Amazing%20Grace.mp3","author":"Kevin MacLeod"}]
     */

    private String albumId;
    private String name;
    private String title;
    private String intro;
    private String poster;
    private String playNum;
    private RealmList<MusicBean> list;

    public RealmList<MusicBean> getList() {
        return list;
    }

    public void setList(RealmList<MusicBean> list) {
        this.list = list;
    }


    public String getAlbumId() {
        return albumId;
    }

    public void setAlbumId(String albumId) {
        this.albumId = albumId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getPlayNum() {
        return playNum;
    }

    public void setPlayNum(String playNum) {
        this.playNum = playNum;
    }


}
