package com.example.mymusic.mvp.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mymusic.R;
import com.example.mymusic.adapter.MusicListAdapter;
import com.example.mymusic.mvp.model.MusicSourceModel;
import com.example.mymusic.utils.Constant;
import com.example.mymusic.utils.LogUtils;

import butterknife.BindView;

public class DataMusicFragment extends Fragment {

    private static final String TAG = "DataMusicFragment";
    RecyclerView recyclerView;
    private MusicListAdapter mMusicListAdapter;
    private Context mContext;
    private MusicSourceModel musicSourceModel;

    public DataMusicFragment(Context context) {
        mContext = context;
    }

    public static DataMusicFragment newInstance(MusicSourceModel musicSourceModel, Context context) {
        LogUtils.d(TAG, "musicSourceModel" + musicSourceModel);
        DataMusicFragment fragment = new DataMusicFragment(context);
        Bundle bundle = new Bundle();
        bundle.putParcelable(Constant.BUNDLE_MUSIC_SOURCE, musicSourceModel);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            LogUtils.d(TAG, "musicSourceModel" + musicSourceModel);
            musicSourceModel = getArguments().getParcelable(Constant.BUNDLE_MUSIC_SOURCE);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_music_rank, container, false);
        recyclerView = view.findViewById(R.id.recycler_view);
        initViews();
        initData();
        return view;
    }

    private void initData() {
        mMusicListAdapter.updateInternet(musicSourceModel.getHot());
    }

    private void initViews() {
        mMusicListAdapter = new MusicListAdapter(mContext, null);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(mMusicListAdapter);
    }

}
