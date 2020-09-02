package com.example.mymusic.mvp.view.activity.impl;

import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.mymusic.R;
import com.example.mymusic.base.BaseActivity;
import com.example.mymusic.base.BaseInternetActivity;
import com.example.mymusic.bean.AlbumBean;
import com.example.mymusic.bean.MusicBean;
import com.example.mymusic.bean.PlayListBean;
import com.example.mymusic.bean.RecordBean;
import com.example.mymusic.helps.RecordHelper;
import com.example.mymusic.mvp.model.MusicSourceModel;
import com.example.mymusic.mvp.presenter.DataHandlerInternetPresenter;
import com.example.mymusic.mvp.presenter.DataHandlerPresenter;
import com.example.mymusic.mvp.presenter.impl.DataHandlerInternetPresenterImpl;
import com.example.mymusic.mvp.presenter.impl.DataHandlerPresenterImpl;
import com.example.mymusic.mvp.view.views.PlayMusicView;
import com.example.mymusic.utils.Constant;
import com.example.mymusic.utils.LogUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jp.wasabeef.glide.transformations.BlurTransformation;

public class PlayMusicActivity extends BaseInternetActivity {

    private static final String TAG = "PlayMusicActivity";
    @BindView(R.id.iv_bg)
    ImageView ivBg;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_author)
    TextView tvAuthor;
    @BindView(R.id.play_music_view)
    PlayMusicView playMusicView;
    @BindView(R.id.pl_back)
    ImageView plBack;
    private String musicId;
    private int hotPosition;
    private DataHandlerPresenter dataHandlerPresenter;
    private DataHandlerInternetPresenter dataHandlerInternetPresenter;
    private MusicSourceModel.AlbumModel.ListBeanX mMusicBean;
    private MusicSourceModel.HotModel mHotModel;
    private RecordHelper mRecordHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_music);
        ButterKnife.bind(this);
        initData();
        initView();

    }

    private void initData() {
        //dataHandlerPresenter = new DataHandlerPresenterImpl(this, this);
        dataHandlerInternetPresenter = new DataHandlerInternetPresenterImpl(this, this);
        hotPosition = getIntent().getIntExtra(Constant.HOT_POSTION, -1);
        isAlbum = getIntent().getBooleanExtra(Constant.IS_ALBUM, false);
        isPlayList = getIntent().getBooleanExtra(Constant.IS_PLAYLIST, false);
        isHot = getIntent().getBooleanExtra(Constant.IS_MUSIC, false);
        mRecordHelper = RecordHelper.getInstance();
        LogUtils.d(TAG, "" + hotPosition + isAlbum + isHot);

        //dataHandlerInternetPresenter.getAlbumMusicInternet(hotPosition, isAlbum, isHot);
        //dataHandlerPresenter.getMusicData(musicId);
        if (isAlbum) {
            mMusicBean = getIntent().getBundleExtra(Constant.INTENT_ALBUM_MUSIC).getParcelable(Constant.BUNDLE_ALBUM_MUSIC);
            LogUtils.d(TAG, "" + hotPosition + isAlbum + isHot + "   " + mMusicBean);
            setData();
        } else if (isHot) {
            mHotModel = getIntent().getBundleExtra(Constant.INTENT_HOT_MUSIC).getParcelable(Constant.BUNDLE_HOT_MUSIC);
            LogUtils.d(TAG, "" + hotPosition + isAlbum + isHot + "   " + mHotModel);
            setHotData();
        }
    }

    private void setHotData() {
        if (mHotModel != null) {
            insert();
            Glide.with(this)
                    .load(mHotModel.getPoster())
                    .apply(RequestOptions.bitmapTransform(new BlurTransformation(25, 10)))
                    .into(ivBg);
            tvAuthor.setText(mHotModel.getAuthor());
            tvName.setText(mHotModel.getName());
            playMusicView.init(this);
            playMusicView.setMusicHot(mHotModel);
            playMusicView.playMusic();
        }
    }

    private void setData() {
        if (mMusicBean != null) {
            insert();
            Glide.with(this)
                    .load(mMusicBean.getPoster())
                    .apply(RequestOptions.bitmapTransform(new BlurTransformation(25, 10)))
                    .into(ivBg);
            tvAuthor.setText(mMusicBean.getAuthor());
            tvName.setText(mMusicBean.getName());
            playMusicView.init(this);
            playMusicView.setMusic(mMusicBean);
            playMusicView.playMusic();

        }
    }

    private void insert() {
        RecordBean recordBean = new RecordBean();
        recordBean.setId(null);
        recordBean.setMusicId(isHot ? mHotModel.getMusicId() : mMusicBean.getMusicId());
        recordBean.setAuthor(isHot ? mHotModel.getAuthor() : mMusicBean.getAuthor());
        recordBean.setName(isHot ? mHotModel.getName() : mMusicBean.getName());
        recordBean.setPath(isHot ? mHotModel.getPath() : mMusicBean.getPath());
        recordBean.setPoster(isHot ? mHotModel.getPoster() : mMusicBean.getPoster());
        mRecordHelper.insert(recordBean);
    }



    private void initView() {
        //        隐藏statusBar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN, WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
        /*Glide.with(this)
                .load("http://res.lgdsunday.club/poster-1.png")
                .apply(RequestOptions.bitmapTransform(new BlurTransformation(25, 10)))
                .into(ivBg);
        playMusicView.init(this);
        playMusicView.setMusicIcon("http://res.lgdsunday.club/poster-1.png");
        playMusicView.playMusic("http://music.163.com/song/media/outer/url?id=481853043.mp3");
        tvAuthor.setText("author");
        tvName.setText("musicName");*/

    }

    @OnClick(R.id.pl_back)
    public void onViewClicked() {
        onBackPressed();
    }

    @Override
    public void updataInternetAlbumMusicIntro(MusicSourceModel.AlbumModel.ListBeanX albumMusicIntro) {
        if (albumMusicIntro != null) {
            LogUtils.d(TAG, "musicBean" + albumMusicIntro);
            Glide.with(this)
                    .load(albumMusicIntro.getPoster())
                    .apply(RequestOptions.bitmapTransform(new BlurTransformation(25, 10)))
                    .into(ivBg);
            tvAuthor.setText(albumMusicIntro.getAuthor());
            tvName.setText(albumMusicIntro.getName());
            playMusicView.init(this);
            playMusicView.setMusic(albumMusicIntro);
            playMusicView.playMusic();

        }
    }

   /* @Override
    public void updateInterentAlbumIntro(MusicSourceModel.AlbumModel albumModel) {
        if (hotModel != null) {
            LogUtils.d(TAG, "musicBean" + hotModel);
            Glide.with(this)
                    .load(hotModel.getPoster())
                    .apply(RequestOptions.bitmapTransform(new BlurTransformation(25, 10)))
                    .into(ivBg);
            tvAuthor.setText(hotModel.getAuthor());
            tvName.setText(hotModel.getName());
            *//*playMusicView.init(this);
            playMusicView.setMusic(hotModel);
            playMusicView.playMusic();*//*

        }
    }*/

    /*@Override
    public void updateIntro(AlbumBean albumBean, PlayListBean playListBean, MusicBean musicBean) {
        if(musicBean != null){
            LogUtils.d(TAG, "musicBean" + musicBean);
            Glide.with(this)
                    .load(musicBean.getPoster())
                    .apply(RequestOptions.bitmapTransform(new BlurTransformation(25, 10)))
                    .into(ivBg);
            tvAuthor.setText(musicBean.getAuthor());
            tvName.setText(musicBean.getName());
            playMusicView.init(this);
            playMusicView.setMusic(musicBean);
            playMusicView.playMusic();

        }
    }*/

    @Override
    protected void onDestroy() {
        super.onDestroy();
        playMusicView.destroy();
        //dataHandlerPresenter.close();
    }
}
