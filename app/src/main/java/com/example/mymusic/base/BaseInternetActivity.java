package com.example.mymusic.base;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mymusic.R;
import com.example.mymusic.bean.AlbumBean;
import com.example.mymusic.bean.MusicBean;
import com.example.mymusic.bean.PlayListBean;
import com.example.mymusic.mvp.model.MusicSourceModel;
import com.example.mymusic.mvp.presenter.DataHandlerInternetPresenter;
import com.example.mymusic.mvp.presenter.InputPresenter;
import com.example.mymusic.mvp.presenter.impl.DataHandlerInternetPresenterImpl;
import com.example.mymusic.mvp.view.activity.ShowDataView;
import com.example.mymusic.mvp.view.activity.ShowInternetDataView;
import com.example.mymusic.mvp.view.activity.impl.MeActivity;
import com.example.mymusic.utils.LogUtils;

import java.util.List;


public class BaseInternetActivity extends AppCompatActivity implements View.OnClickListener, ShowInternetDataView {

    private static final String TAG = "BaseInternetActivity";
    private ImageView navBack, navMe;
    private TextView navTitle, navRecord;
    public InputPresenter inputPresenter;
    public boolean isAlbum;
    public boolean isPlayList;
    public boolean isHot;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

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
    public void initNavBar1(boolean isShowBack, String title, boolean isShowMe, boolean isRecord){
        navBack = findViewById(R.id.iv_back);
        navTitle = findViewById(R.id.tv_title);
        navMe = findViewById(R.id.iv_me);
        navRecord = findViewById(R.id.tv_record);
        navBack.setVisibility(isShowBack ? View.VISIBLE : View.GONE);
        navMe.setVisibility((isShowMe && !isRecord) ? View.VISIBLE : View.GONE);
        navRecord.setVisibility((isRecord && !isShowMe) ? View.VISIBLE : View.GONE);
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
    public void updateInternetAlbum(List<MusicSourceModel.AlbumModel> albumModelList) {

    }

    @Override
    public void updateInternetHot(List<MusicSourceModel.HotModel> hotModelList) {

    }

    @Override
    public void updateInterentAlbumIntro(MusicSourceModel.AlbumModel albumModel) {

    }

    @Override
    public void updateInternetAlbumMusic(List<MusicSourceModel.AlbumModel.ListBeanX> albumMusicList) {

    }

    @Override
    public void updataInternetAlbumMusicIntro(MusicSourceModel.AlbumModel.ListBeanX AlbumMusicIntro) {

    }

    @Override
    public void updateInternetHotMusicIntro(MusicSourceModel.HotModel hotModel) {

    }

    @Override
    public void passMusicSource(MusicSourceModel musicSourceModel) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}