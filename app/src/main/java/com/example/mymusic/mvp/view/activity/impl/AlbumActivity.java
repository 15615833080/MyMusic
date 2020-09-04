package com.example.mymusic.mvp.view.activity.impl;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.example.mymusic.R;
import com.example.mymusic.adapter.AlbumFragmentAdapter;
import com.example.mymusic.adapter.FragmentAdapter;
import com.example.mymusic.base.BaseInternetActivity;
import com.example.mymusic.mvp.model.MusicSourceModel;
import com.example.mymusic.mvp.presenter.impl.DataHandlerInternetPresenterImpl;
import com.example.mymusic.mvp.view.fragment.DataMusicFragment;
import com.example.mymusic.utils.LogUtils;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static androidx.fragment.app.FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT;

public class AlbumActivity extends BaseInternetActivity {

    private static final String TAG = "AlbumActivity";
    @BindView(R.id.album_tabs)
    TabLayout albumTabs;
    @BindView(R.id.view_pager2)
    ViewPager2 viewPager2;
    private MusicSourceModel musicSourceModel;
    private List<Fragment> fragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album);
        ButterKnife.bind(this);
        initView();
        initData();
        initViewPager();
    }

    private void initData() {
        DataHandlerInternetPresenterImpl.getInstance().initShowDataView(this);
        DataHandlerInternetPresenterImpl.getInstance().get();
    }

    @Override
    public void passMusicSource(MusicSourceModel musicSourceModel) {
        this.musicSourceModel = musicSourceModel;
        LogUtils.d(TAG, "" + musicSourceModel);
    }

    private void initViewPager() {
        List<String> titles = new ArrayList<>();
        titles.add("伤感华语");
        titles.add("网抑云");
        titles.add("情感地带");
        titles.add("当前热歌");
        titles.add("情感归属");

        /*for (int i = 0; i < titles.size(); i++) {
            albumTabs.addTab(albumTabs.newTab().setText(titles.get(i)));
        }*/
        fragments = new ArrayList<>();
        for (int i = 0; i < titles.size(); i++) {
            fragments.add(DataMusicFragment.newInstance(musicSourceModel, this));
        }
/*        FragmentAdapter mFragmentAdapteradapter = new FragmentAdapter(getSupportFragmentManager(), BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        mFragmentAdapteradapter.setData(titles, fragments);
        //给ViewPager设置适配器
        viewPager2.setAdapter(mFragmentAdapteradapter);
        //将TabLayout和ViewPager关联起来。
        albumTabs.setupWithViewPager(viewPager2);
        viewPager2*/
        AlbumFragmentAdapter albumFragmentAdapter = new AlbumFragmentAdapter(this);
        albumFragmentAdapter.setData(titles,fragments);
        viewPager2.setAdapter(albumFragmentAdapter);
        viewPager2.setUserInputEnabled(false);
        new TabLayoutMediator(albumTabs, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setText(titles.get(position));
            }
        }).attach();
    }

    private void initView() {
        initNavBar(true, "音乐排行榜", true);
    }
}
