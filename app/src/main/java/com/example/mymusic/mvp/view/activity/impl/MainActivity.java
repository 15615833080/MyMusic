package com.example.mymusic.mvp.view.activity.impl;

import android.os.Bundle;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mymusic.R;
import com.example.mymusic.adapter.MusicGridAdapter;
import com.example.mymusic.adapter.MusicListAdapter;
import com.example.mymusic.base.BaseActivity;
import com.example.mymusic.base.BaseInternetActivity;
import com.example.mymusic.bean.AlbumBean;
import com.example.mymusic.bean.MusicBean;
import com.example.mymusic.bean.MusicSourceBean;
import com.example.mymusic.helps.RealmHelp;
import com.example.mymusic.mvp.model.MusicSourceModel;
import com.example.mymusic.mvp.presenter.DataHandlerInternetPresenter;
import com.example.mymusic.mvp.presenter.DataHandlerPresenter;
import com.example.mymusic.mvp.presenter.impl.DataHandlerInternetPresenterImpl;
import com.example.mymusic.mvp.presenter.impl.DataHandlerPresenterImpl;
import com.example.mymusic.mvp.view.views.GridSpaceItemDecoration;
import com.example.mymusic.utils.LogUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseInternetActivity {

    private static final String TAG = "MainActivity";
    private MusicGridAdapter mGridAdapter;
    private MusicListAdapter mListAdapter;
    private DataHandlerPresenter dataHandlerPresenter;
    private DataHandlerInternetPresenter dataHandlerInternetPresenter;
    @BindView(R.id.rv_grid)
    RecyclerView rvGrid;
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
        //dataHandlerPresenter = new DataHandlerPresenterImpl(this, this);
        dataHandlerInternetPresenter = new DataHandlerInternetPresenterImpl(this,this, 1);
        //推荐歌单
        mGridAdapter = new MusicGridAdapter(this);
        //dataHandlerPresenter.getAlbum();
        rvGrid.setLayoutManager(new GridLayoutManager(this, 3));
        rvGrid.addItemDecoration(new GridSpaceItemDecoration(getResources().getDimensionPixelSize(R.dimen.albumMarginSize), rvGrid));
        rvGrid.setAdapter(mGridAdapter);

        //最热音乐
        mListAdapter = new MusicListAdapter(this, rvList);
        //dataHandlerPresenter.getHotData();
//        dataHandlerInternetPresenter.getHotInternet();
        rvList.setLayoutManager(new LinearLayoutManager(this));
        rvList.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        rvList.setNestedScrollingEnabled(false);
        rvList.setAdapter(mListAdapter);
    }

    @Override
    public void updateInternetAlbum(List<MusicSourceModel.AlbumModel> albumModelList) {
        LogUtils.d(TAG, "mGridAdapter" + mGridAdapter + albumModelList);
        if (mGridAdapter != null && albumModelList != null) {
            mGridAdapter.updateInternet(albumModelList);
        }
    }

    @Override
    public void updateInternetHot(List<MusicSourceModel.HotModel> hotModelList) {
        LogUtils.d(TAG, "mGridAdapter" + hotModelList);
        if (mListAdapter != null && hotModelList != null) {
            mListAdapter.updateInternet(hotModelList);
        }
    }

    @Override
    public void passMusicSource(MusicSourceModel musicSourceModel) {
        super.passMusicSource(musicSourceModel);
    }

    /*@Override
    public void updateAlbum(List<AlbumBean> albumBeanList) {
        LogUtils.d(TAG, "mGridAdapter" + mGridAdapter + albumBeanList);
        if (mGridAdapter != null && albumBeanList != null) {
            mGridAdapter.update(albumBeanList);
        }
    }

    @Override
    public void updateHot(List<MusicBean> musicBeanList) {
        LogUtils.d(TAG, "mGridAdapter" + musicBeanList);
        if (mListAdapter != null && musicBeanList != null) {
            mListAdapter.update(musicBeanList);
        }
    }*/

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dataHandlerInternetPresenter.dsetroy();
    }

}
