package com.example.mymusic.mvp.view.activity;

import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mymusic.R;
import com.example.mymusic.adapter.MusicGridAdapter;
import com.example.mymusic.adapter.MusicListAdapter;
import com.example.mymusic.base.BaseActivity;
import com.example.mymusic.mvp.view.views.GridSpaceItemDecoration;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    private MusicGridAdapter mGridAdapter;
    private MusicListAdapter mListAdapter;
    @BindView(R.id.rv_block)
    RecyclerView rvBlock;
    @BindView(R.id.rv_grid)
    RecyclerView rvGrid;
    @BindView(R.id.rv_album)
    RecyclerView rvAlbum;
    @BindView(R.id.rv_list)
    RecyclerView rvList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        initNavBar(false, "主页", true);

        //推荐歌单
        mGridAdapter = new MusicGridAdapter(this);
        rvGrid.setLayoutManager(new GridLayoutManager(this, 3));
        rvGrid.addItemDecoration(new GridSpaceItemDecoration(getResources().getDimensionPixelSize(R.dimen.albumMarginSize), rvGrid));
        rvGrid.setAdapter(mGridAdapter);

        //最热音乐
        mListAdapter = new MusicListAdapter(this, rvList);
        rvList.setLayoutManager(new LinearLayoutManager(this));
        rvList.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        rvList.setNestedScrollingEnabled(false);
        rvList.setAdapter(mListAdapter);
    }

    @OnClick({R.id.rv_block, R.id.rv_grid, R.id.rv_album, R.id.rv_list})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rv_block:
                break;
            case R.id.rv_grid:
                break;
            case R.id.rv_album:
                break;
            case R.id.rv_list:
                break;
        }
    }
}
