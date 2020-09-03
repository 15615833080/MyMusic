package com.example.mymusic.mvp.view.activity.impl;

import android.content.Context;
import android.os.Bundle;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mymusic.R;
import com.example.mymusic.adapter.MusicBlockAdapter;
import com.example.mymusic.adapter.MusicGridAdapter;
import com.example.mymusic.adapter.MusicListAdapter;
import com.example.mymusic.base.BaseInternetActivity;
import com.example.mymusic.mvp.model.MusicSourceModel;
import com.example.mymusic.mvp.presenter.DataHandlerInternetPresenter;
import com.example.mymusic.mvp.presenter.DataHandlerPresenter;
import com.example.mymusic.mvp.presenter.impl.DataHandlerInternetPresenterImpl;
import com.example.mymusic.mvp.view.views.GridSpaceItemDecoration;
import com.example.mymusic.utils.LogUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseInternetActivity {

    private static final String TAG = "MainActivity";
    @BindView(R.id.rv_block)
    RecyclerView rvBlock;
    @BindView(R.id.rv_grid)
    RecyclerView rvGrid;
    @BindView(R.id.rv_list)
    RecyclerView rvList;
    private MusicGridAdapter mGridAdapter;
    private MusicListAdapter mListAdapter;
    private DataHandlerPresenter dataHandlerPresenter;
    private MusicBlockAdapter mMusicBlockAdapter;
    private List<MusicSourceModel.HotModel> mHotModelList;
    private String[] blockTitle ={"每日推荐", "歌单", "排行榜", "电台", "直播"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    private void initData() {
        //dataHandlerPresenter = new DataHandlerPresenterImpl(this, this);
        //dataHandlerInternetPresenter = new DataHandlerInternetPresenterImpl(this, this, 1);
        DataHandlerInternetPresenterImpl.getInstance().initShowDataView(this);
        mMusicBlockAdapter.setData(blockTitle, mHotModelList);
        //dataHandlerPresenter.getAlbum();
        //dataHandlerPresenter.getHotData();
//        dataHandlerInternetPresenter.getHotInternet();
    }

    private void initView() {
        initNavBar(false, "主页", true);
        //rvblock
        mMusicBlockAdapter = new MusicBlockAdapter(this);
        //线性布局禁止滑动
/*        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false){
            @Override
            public boolean canScrollHorizontally() {
                return false;
            }
        };
        rvBlock.setLayoutManager(linearLayoutManager);*/
        rvBlock.setLayoutManager(new GridLayoutManager(this, blockTitle.length));
        rvBlock.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL));
        rvBlock.setNestedScrollingEnabled(false);
        rvBlock.setAdapter(mMusicBlockAdapter);

        //推荐歌单
        mGridAdapter = new MusicGridAdapter(this);
        rvGrid.setLayoutManager(new GridLayoutManager(this, 3));
        rvGrid.addItemDecoration(new GridSpaceItemDecoration(getResources().getDimensionPixelSize(R.dimen.albumMarginSize), rvGrid));
        rvGrid.setNestedScrollingEnabled(false);
        rvGrid.setAdapter(mGridAdapter);

        //最热音乐
        mListAdapter = new MusicListAdapter(this, rvList);
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
            mHotModelList = hotModelList;
            mListAdapter.updateInternet(hotModelList);
        }
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
