package com.example.mymusic.mvp.view.activity;

import android.os.Bundle;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mymusic.R;
import com.example.mymusic.adapter.MusicListAdapter;
import com.example.mymusic.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AlbumListActivity extends BaseActivity {

    private MusicListAdapter musicListAdapter;
    @BindView(R.id.rv_list_album)
    RecyclerView rvListAlbum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album_list);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        initNavBar(true, "音乐专辑", true);

        musicListAdapter = new MusicListAdapter(this, null);
        rvListAlbum.setLayoutManager(new LinearLayoutManager(this));
        rvListAlbum.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        rvListAlbum.setAdapter(musicListAdapter);

    }
}
