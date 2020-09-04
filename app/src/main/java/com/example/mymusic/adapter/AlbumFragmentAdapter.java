package com.example.mymusic.adapter;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.List;

public class AlbumFragmentAdapter extends FragmentStateAdapter {
    private Context mContext;
    private List<String> mTitles;
    private List<Fragment> mFragments;

    public AlbumFragmentAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
        mContext = fragmentActivity;
    }

    public void setData(List<String> titles, List<Fragment> fragments){
        mTitles = titles;
        mFragments = fragments;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getItemCount() {
        return mFragments.size();
    }

}
