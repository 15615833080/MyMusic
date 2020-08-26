package com.example.mymusic.migration;

import com.example.mymusic.bean.PlayListBean;

import javax.xml.validation.Schema;

import io.realm.DynamicRealm;
import io.realm.RealmMigration;
import io.realm.RealmSchema;

public class Migration implements RealmMigration {
    @Override
    public void migrate(DynamicRealm realm, long oldVersion, long newVersion) {
        RealmSchema schema = realm.getSchema();
        /**
         * 第一次数据迁移
         */
        if(oldVersion == 0){
            schema.create("MusicBean")
                    .addField("musicId", String.class)
                    .addField("name", String.class)
                    .addField("poster", String.class)
                    .addField("path", String.class)
                    .addField("author", String.class);

            schema.create("AlbumBean")
                    .addField("albumId", String.class)
                    .addField("name", String.class)
                    .addField("title", String.class)
                    .addField("intro", String.class)
                    .addField("poster", String.class)
                    .addField("playNum", String.class)
                    .addRealmListField("list", schema.get("MusicBean"));

            schema.create("PlayListBean")
                    .addField("playListId", String.class)
                    .addField("name", String.class)
                    .addField("title", String.class)
                    .addField("intro", String.class)
                    .addField("poster", String.class)
                    .addField("playNum", String.class)
                    .addRealmListField("list", schema.get("MusicBean"));

            schema.create("MusicSourceBean")
                    .addRealmListField("album", schema.get("AlbumBean"))
                    .addRealmListField("playList", schema.get("PlayListBean"))
                    .addRealmListField("hot", schema.get("MusicBean"));

            oldVersion = newVersion;
        }
        /*if(oldVersion == 1){
            schema.get("PlayListBean").renameField("list", "playMusicList");
            schema.get("AlbumBean").renameField("list", "albumMusicList");
            oldVersion = newVersion;
        }
        if(oldVersion == 2){
            schema.get("PlayListBean").renameField("playMusicList", "list");
            schema.get("AlbumBean").renameField("albumMusicList", "list");
            oldVersion = newVersion;
        }*/
    }
}
