package com.example.mymusic.mvp.view.activity;

import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.mymusic.R;
import com.example.mymusic.base.BaseActivity;
import com.example.mymusic.bean.AlbumBean;
import com.example.mymusic.bean.MusicBean;
import com.example.mymusic.bean.PlayListBean;
import com.example.mymusic.mvp.presenter.DataHandlerPresenter;
import com.example.mymusic.mvp.presenter.DataHandlerPresenterImpl;
import com.example.mymusic.mvp.view.activity.impl.ShowDataView;
import com.example.mymusic.mvp.view.views.PlayMusicView;
import com.example.mymusic.utils.Constant;
import com.example.mymusic.utils.LogUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jp.wasabeef.glide.transformations.BlurTransformation;

public class PlayMusicActivity extends BaseActivity {

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
    private DataHandlerPresenter dataHandlerPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_music);
        ButterKnife.bind(this);
        initData();
        initView();

    }

    private void initData() {
        dataHandlerPresenter = new DataHandlerPresenterImpl(this, this);
        musicId = getIntent().getStringExtra(Constant.MUSIC_ID);
        dataHandlerPresenter.getMusicData(musicId);
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
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        playMusicView.destroy();
        dataHandlerPresenter.close();
    }
}
