package com.example.mymusic.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.ActivityUtils;
import com.bumptech.glide.Glide;
import com.example.mymusic.R;
import com.example.mymusic.bean.AlbumBean;
import com.example.mymusic.bean.MusicSourceBean;
import com.example.mymusic.mvp.view.activity.AlbumListActivity;
import com.example.mymusic.mvp.view.views.MyGridView;
import com.example.mymusic.utils.Constant;
import com.example.mymusic.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MusicGridAdapter extends RecyclerView.Adapter<MusicGridAdapter.ViewHolder> {

    private static final String TAG = "MusicGridAdapter";
    private Context mContext;
    private List<AlbumBean> albumBeanList;

    public MusicGridAdapter(Context context) {
        LogUtils.d(TAG, "MusicGridAdapter");
        mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_grid_music, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if(albumBeanList != null){
            AlbumBean albumBean = albumBeanList.get(position);
            Glide.with(mContext)
                    .load(albumBean.getPoster())
                    .into(holder.ivIcon);
            holder.tvName.setText(albumBean.getName());
            holder.tvPlayNum.setText(albumBean.getPlayNum());
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(mContext, AlbumListActivity.class);
                    intent.putExtra(Constant.ALBUM_ID, albumBean.getAlbumId());
                    intent.putExtra(Constant.IS_ALBUM, true);
                    intent.putExtra(Constant.IS_PLAYLIST, false);
                    intent.putExtra(Constant.IS_MUSIC, false);
                    mContext.startActivity(intent);
                }
            });
        }

    }

    public void update(List<AlbumBean> albumBeanList){
        LogUtils.d(TAG, "albumBeanList" + albumBeanList);
        this.albumBeanList = albumBeanList;
    }

    @Override
    public int getItemCount() {
        return albumBeanList.size();
    }

    static
    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_icon)
        MyGridView ivIcon;
        @BindView(R.id.tv_play_num)
        TextView tvPlayNum;
        @BindView(R.id.tv_name)
        TextView tvName;
        View itemView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.itemView = itemView;
        }
    }

}
