package com.example.mymusic.mvp.view.activity.impl;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.mymusic.R;
import com.example.mymusic.adapter.AlbumMusicListAdapter;
import com.example.mymusic.adapter.MusicListAdapter;
import com.example.mymusic.base.BaseInternetActivity;
import com.example.mymusic.mvp.model.MusicSourceModel;
import com.example.mymusic.mvp.presenter.DataHandlerInternetPresenter;
import com.example.mymusic.mvp.presenter.DataHandlerPresenter;
import com.example.mymusic.mvp.presenter.impl.DataHandlerInternetPresenterImpl;
import com.example.mymusic.utils.Constant;
import com.example.mymusic.utils.LogUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.glide.transformations.BlurTransformation;

public class AlbumListActivity extends BaseInternetActivity {

    private static final String TAG = "AlbumListActivity";
    @BindView(R.id.iv_icon)
    ImageView ivIcon;
    @BindView(R.id.al_title)
    TextView alTitle;
    @BindView(R.id.tv_info)
    TextView tvInfo;
    @BindView(R.id.ml_name)
    TextView mlName;
    @BindView(R.id.rv_list)
    RecyclerView rvList;
    @BindView(R.id.bg_icon)
    ImageView bgIcon;
    //private MusicListAdapter musicListAdapter;
    private AlbumMusicListAdapter albumMusicListAdapter;
    private String mAlbumId;
    private DataHandlerPresenter dataHandlerPresenter;
    private DataHandlerInternetPresenter dataHandlerInternetPresenter;
    private int currentPosition;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album_list);
        ButterKnife.bind(this);
        initData();
        initView();
    }

    private void initData() {
        //dataHandlerPresenter = new DataHandlerPresenterImpl(this, this);
        dataHandlerInternetPresenter = new DataHandlerInternetPresenterImpl(this, this);
        mAlbumId = getIntent().getStringExtra(Constant.ALBUM_ID);
        isAlbum = getIntent().getBooleanExtra(Constant.IS_ALBUM, false);
        isPlayList = getIntent().getBooleanExtra(Constant.IS_PLAYLIST, false);
        isHot = getIntent().getBooleanExtra(Constant.IS_MUSIC, false);
        currentPosition = getIntent().getIntExtra(Constant.ALBUM_POSTION, 0);
        LogUtils.d(TAG, ""+ currentPosition + isAlbum + isHot);


    }

    private void initView() {
        initNavBar(true, "音乐专辑", true);

        albumMusicListAdapter = new AlbumMusicListAdapter(this, null);
        //musicListAdapter = new MusicListAdapter(this, null);
        dataHandlerInternetPresenter.getAlbumInternet(currentPosition, isAlbum, isHot);
        //dataHandlerPresenter.getAlbum(mAlbumId, isAlbum, isPlayList, isHot);
        rvList.setLayoutManager(new LinearLayoutManager(this));
        rvList.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        rvList.setAdapter(albumMusicListAdapter);
    }

    @Override
    public void updateInterentAlbumIntro(MusicSourceModel.AlbumModel albumModel) {
        if (albumModel != null) {
            Glide.with(this)
                    .load(albumModel.getPoster())
                    .into(ivIcon);
            Glide.with(this)
                    .load(albumModel.getPoster())
                    .apply(RequestOptions.bitmapTransform(new BlurTransformation(25, 10)))
                    .into(bgIcon);
            alTitle.setText(albumModel.getTitle());
            tvInfo.setText(albumModel.getIntro());
        }
    }

    @Override
    public void updateInternetAlbumMusic(List<MusicSourceModel.AlbumModel.ListBeanX> albumMusicList) {
        LogUtils.d(TAG, "mGridAdapter" + albumMusicList);
        if (albumMusicListAdapter != null && albumMusicList != null) {
            albumMusicListAdapter.updateInternetAlbumMusic(albumMusicList);
        }
    }

    /*@Override
    public void updateIntro(AlbumBean albumBean, PlayListBean playListBean, MusicBean musicBean) {
        if (albumBean != null) {
            Glide.with(this)
                    .load(albumBean.getPoster())
                    .into(ivIcon);
            Glide.with(this)
                    .load(albumBean.getPoster())
                    .apply(RequestOptions.bitmapTransform(new BlurTransformation(25, 10)))
                    .into(bgIcon);
            alTitle.setText(albumBean.getTitle());
            tvInfo.setText(albumBean.getIntro());
        }
    }

    @Override
    public void updateMusic(List<MusicBean> musicBeanList) {
        LogUtils.d(TAG, "mGridAdapter" + musicBeanList);
        if (musicListAdapter != null && musicBeanList != null) {
            musicListAdapter.update(musicBeanList);
        }
    }*/
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
