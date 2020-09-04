package com.example.mymusic.mvp.view.activity.impl;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mymusic.R;
import com.example.mymusic.adapter.RecordListAdapter;
import com.example.mymusic.base.BaseInternetActivity;
import com.example.mymusic.bean.RecordBean;
import com.example.mymusic.helps.RecordHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PlayRecordActivity extends BaseInternetActivity implements RecordListAdapter.IonSlidingViewClickListener {

    private static final String TAG = "PlayRecordActivity";
    @BindView(R.id.tv_record)
    TextView mTvRecord;
    @BindView(R.id.rv_record)
    RecyclerView mRvRecord;
    @BindView(R.id.floatingActionButton)
    FloatingActionButton floatingActionButton;
    @BindView(R.id.iv_me)
    ImageView ivMe;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.linearLayout)
    LinearLayout linearLayout;
    private RecordListAdapter recordListAdapter;
    private RecordHelper mRecordHelper;
    private List<RecordBean> mRecordBeanList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_record);
        ButterKnife.bind(this);
        mRecordHelper = RecordHelper.getInstance();
        mRecordBeanList = mRecordHelper.searchAll();
        initView();
    }

    private void initView() {
        initNavBar(true, "播放记录", false);
        recordListAdapter = new RecordListAdapter(this);
        mRvRecord.setLayoutManager(new LinearLayoutManager(this));
        mRvRecord.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mRvRecord.setAdapter(recordListAdapter);
        recordListAdapter.update(mRecordBeanList);
    }

   /* private void initData() {
        if(recordListAdapter != null && mRecordBeanList != null){
            mRecordBeanList = mRecordHelper.searchAll();
            recordListAdapter.update(mRecordBeanList);
        }
    }*/


    @Override
    public void onItemLongClick(View view, int position) {
        floatingActionButton.setVisibility(View.VISIBLE);
        initNavBar1(true, "播放记录", false, true);
    }

    @Override
    public void onDeleteBtnCilck(View view, int position) {
        String musicId = mRecordBeanList.get(position).getMusicId();
        recordListAdapter.removeData(position, musicId);
        //从数据库中删除
        mRecordHelper.delete(musicId);
    }

    @OnClick({R.id.tv_record, R.id.floatingActionButton})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_record:
                floatingActionButton.setVisibility(View.GONE);
                mTvRecord.setVisibility(View.GONE);
                break;
            case R.id.floatingActionButton:
                recordListAdapter.reMoveDataAll();
                mRecordHelper.deleteAll();
                floatingActionButton.setVisibility(View.GONE);
                mTvRecord.setVisibility(View.GONE);
                break;
        }
    }
}
