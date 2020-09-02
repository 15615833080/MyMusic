package com.example.mymusic.greendao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.example.mymusic.bean.RecordBean;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "RECORD_BEAN".
*/
public class RecordBeanDao extends AbstractDao<RecordBean, Long> {

    public static final String TABLENAME = "RECORD_BEAN";

    /**
     * Properties of entity RecordBean.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property MusicId = new Property(1, String.class, "musicId", false, "music_id");
        public final static Property Name = new Property(2, String.class, "name", false, "NAME");
        public final static Property Poster = new Property(3, String.class, "poster", false, "POSTER");
        public final static Property Path = new Property(4, String.class, "path", false, "PATH");
        public final static Property Author = new Property(5, String.class, "author", false, "AUTHOR");
    }


    public RecordBeanDao(DaoConfig config) {
        super(config);
    }
    
    public RecordBeanDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"RECORD_BEAN\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"music_id\" TEXT," + // 1: musicId
                "\"NAME\" TEXT," + // 2: name
                "\"POSTER\" TEXT," + // 3: poster
                "\"PATH\" TEXT," + // 4: path
                "\"AUTHOR\" TEXT);"); // 5: author
        // Add Indexes
        db.execSQL("CREATE UNIQUE INDEX " + constraint + "IDX_RECORD_BEAN_music_id ON \"RECORD_BEAN\"" +
                " (\"music_id\" ASC);");
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"RECORD_BEAN\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, RecordBean entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String musicId = entity.getMusicId();
        if (musicId != null) {
            stmt.bindString(2, musicId);
        }
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(3, name);
        }
 
        String poster = entity.getPoster();
        if (poster != null) {
            stmt.bindString(4, poster);
        }
 
        String path = entity.getPath();
        if (path != null) {
            stmt.bindString(5, path);
        }
 
        String author = entity.getAuthor();
        if (author != null) {
            stmt.bindString(6, author);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, RecordBean entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String musicId = entity.getMusicId();
        if (musicId != null) {
            stmt.bindString(2, musicId);
        }
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(3, name);
        }
 
        String poster = entity.getPoster();
        if (poster != null) {
            stmt.bindString(4, poster);
        }
 
        String path = entity.getPath();
        if (path != null) {
            stmt.bindString(5, path);
        }
 
        String author = entity.getAuthor();
        if (author != null) {
            stmt.bindString(6, author);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public RecordBean readEntity(Cursor cursor, int offset) {
        RecordBean entity = new RecordBean( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // musicId
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // name
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // poster
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // path
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5) // author
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, RecordBean entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setMusicId(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setName(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setPoster(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setPath(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setAuthor(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(RecordBean entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(RecordBean entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(RecordBean entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
