package com.example.mymusic.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class RecordBean {
    @Id(autoincrement = true)
    private Long id;
    @Index(unique = true)
    @Property(nameInDb = "music_id")  //指定在数据库中的名字
    private String musicId;
    private String name;
    private String poster;
    private String path;
    private String author;
    @Generated(hash = 1441236520)
    public RecordBean(Long id, String musicId, String name, String poster,
            String path, String author) {
        this.id = id;
        this.musicId = musicId;
        this.name = name;
        this.poster = poster;
        this.path = path;
        this.author = author;
    }
    @Generated(hash = 96196931)
    public RecordBean() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getMusicId() {
        return this.musicId;
    }
    public void setMusicId(String musicId) {
        this.musicId = musicId;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPoster() {
        return this.poster;
    }
    public void setPoster(String poster) {
        this.poster = poster;
    }
    public String getPath() {
        return this.path;
    }
    public void setPath(String path) {
        this.path = path;
    }
    public String getAuthor() {
        return this.author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "RecordBean{" +
                "id=" + id +
                ", musicId='" + musicId + '\'' +
                ", name='" + name + '\'' +
                ", poster='" + poster + '\'' +
                ", path='" + path + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}
