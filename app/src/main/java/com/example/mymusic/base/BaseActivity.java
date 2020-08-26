package com.example.mymusic.base;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mymusic.R;
import com.example.mymusic.bean.AlbumBean;
import com.example.mymusic.bean.MusicBean;
import com.example.mymusic.bean.PlayListBean;
import com.example.mymusic.mvp.presenter.InputPresenter;
import com.example.mymusic.mvp.presenter.InputPresenterImpl;
import com.example.mymusic.mvp.view.activity.MeActivity;
import com.example.mymusic.mvp.view.activity.impl.ShowDataView;

import java.util.List;


public class BaseActivity extends AppCompatActivity implements View.OnClickListener, ShowDataView {

    private ImageView navBack, navMe;
    private TextView navTitle;
    public InputPresenter inputPresenter;
    public boolean isAlbum;
    public boolean isPlayList;
    public boolean isMusic;

    /**
     * 初始化navBar
     * @param isShowBack
     * @param title
     * @param isShowMe
     */
    public void initNavBar(boolean isShowBack, String title, boolean isShowMe){
        navBack = findViewById(R.id.iv_back);
        navTitle = findViewById(R.id.tv_title);
        navMe = findViewById(R.id.iv_me);
        navBack.setVisibility(isShowBack ? View.VISIBLE : View.GONE);
        navMe.setVisibility(isShowMe ? View.VISIBLE : View.GONE);
        navTitle.setText(title);
        navBack.setOnClickListener(this);
        navMe.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_back:
                onBackPressed();
                break;
            case R.id.iv_me:
                Intent intent = new Intent(this, MeActivity.class);
                startActivity(intent);
        }

    }

    @Override
    public void updateAlbum(List<AlbumBean> albumBeanList) {

    }

    @Override
    public void updateHot(List<MusicBean> musicBeanList) {

    }

    @Override
    public void updatePlayList(List<PlayListBean> playListBeanList) {

    }

    @Override
    public void updateMusic(List<MusicBean> musicBeanList) {

    }

    @Override
    public void updateIntro(AlbumBean albumBean, PlayListBean playListBean, MusicBean musicBean) {

    }

}