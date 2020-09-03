package com.example.mymusic.mvp.view.activity.impl;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.util.LogTime;
import com.example.mymusic.R;
import com.example.mymusic.adapter.FragmentAdapter;
import com.example.mymusic.base.BaseInternetActivity;
import com.example.mymusic.mvp.model.MusicSourceModel;
import com.example.mymusic.mvp.presenter.impl.DataHandlerInternetPresenterImpl;
import com.example.mymusic.mvp.view.fragment.DataMusicFragment;
import com.example.mymusic.mvp.view.views.CustomViewPager;
import com.example.mymusic.utils.LogUtils;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static androidx.fragment.app.FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT;

public class RankActivity extends BaseInternetActivity {

    private static final String TAG = "RankActivity";
    @BindView(R.id.tabs)
    TabLayout mTabs;
    @BindView(R.id.view_pager)
    CustomViewPager mViewPager;
    private List<Fragment> fragments;
    private MusicSourceModel musicSourceModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rank);
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
        titles.add("日榜");
        titles.add("周榜");
        titles.add("月榜");
        titles.add("年榜");
        titles.add("更多");

        for (int i = 0; i < titles.size(); i++) {
            mTabs.addTab(mTabs.newTab().setText(titles.get(i)));
        }
        fragments = new ArrayList<>();
        for (int i = 0; i < titles.size(); i++) {
            fragments.add(DataMusicFragment.newInstance(musicSourceModel, this));
        }
        FragmentAdapter mFragmentAdapteradapter = new FragmentAdapter(getSupportFragmentManager(),BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        mFragmentAdapteradapter.setData(titles, fragments);
        //给ViewPager设置适配器
        mViewPager.setAdapter(mFragmentAdapteradapter);
        //将TabLayout和ViewPager关联起来。
        mTabs.setupWithViewPager(mViewPager);
        mViewPager.setSlidingEnable(false);

        //给TabLayout设置适配器(一般不需要)
//        mTabs.setTabsFromPagerAdapter(mFragmentAdapteradapter);
    }
    private void initView() {
        initNavBar(true, "音乐排行榜", true);
    }
}
