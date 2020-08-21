package com.example.mymusic.mvp.view.activity;

import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.mymusic.R;
import com.example.mymusic.mvp.view.views.PlayMusicView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jp.wasabeef.glide.transformations.BlurTransformation;

public class PlayMusicActivity extends AppCompatActivity {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_music);
        ButterKnife.bind(this);

        init();
    }

    private void init() {
        //        隐藏statusBar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN, WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
        Glide.with(this)
                .load("http://res.lgdsunday.club/poster-1.png")
                .apply(RequestOptions.bitmapTransform(new BlurTransformation(25, 10)))
                .into(ivBg);
        playMusicView.init(this);
        playMusicView.setMusicIcon("http://res.lgdsunday.club/poster-1.png");
        playMusicView.playMusic("http://music.163.com/song/media/outer/url?id=481853043.mp3");
    }

    @OnClick(R.id.pl_back)
    public void onViewClicked() {
        onBackPressed();
    }
}
