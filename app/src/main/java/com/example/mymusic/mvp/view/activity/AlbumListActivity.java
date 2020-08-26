package com.example.mymusic.mvp.view.activity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.mymusic.R;
import com.example.mymusic.adapter.MusicListAdapter;
import com.example.mymusic.base.BaseActivity;
import com.example.mymusic.bean.AlbumBean;
import com.example.mymusic.bean.MusicBean;
import com.example.mymusic.bean.PlayListBean;
import com.example.mymusic.helps.RealmHelp;
import com.example.mymusic.mvp.presenter.DataHandlerPresenter;
import com.example.mymusic.mvp.presenter.DataHandlerPresenterImpl;
import com.example.mymusic.mvp.view.activity.impl.ShowDataView;
import com.example.mymusic.utils.Constant;
import com.example.mymusic.utils.LogUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.glide.transformations.BlurTransformation;

public class AlbumListActivity extends BaseActivity{

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
    private MusicListAdapter musicListAdapter;
    private AlbumBean albumBean;
    private String mAlbumId;
    private RealmHelp realmHelp;
    private DataHandlerPresenter dataHandlerPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album_list);
        ButterKnife.bind(this);
        initData();
        initView();
    }

    private void initData() {
        dataHandlerPresenter = new DataHandlerPresenterImpl(this, this);
        mAlbumId = getIntent().getStringExtra(Constant.ALBUM_ID);
        isAlbum = getIntent().getBooleanExtra(Constant.IS_ALBUM, false);
        isPlayList = getIntent().getBooleanExtra(Constant.IS_PLAYLIST, false);
        isMusic = getIntent().getBooleanExtra(Constant.IS_MUSIC, false);
    }

    private void initView() {
        initNavBar(true, "音乐专辑", true);

        musicListAdapter = new MusicListAdapter(this, null);
        dataHandlerPresenter.getAlbum(mAlbumId, isAlbum, isPlayList, isMusic);
        rvList.setLayoutManager(new LinearLayoutManager(this));
        rvList.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        rvList.setAdapter(musicListAdapter);
    }

    @Override
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
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dataHandlerPresenter.close();
    }
}
