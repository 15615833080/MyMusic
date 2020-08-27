package com.example.mymusic.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mymusic.R;
import com.example.mymusic.bean.AlbumBean;
import com.example.mymusic.mvp.model.MusicSourceModel;
import com.example.mymusic.mvp.view.activity.impl.AlbumListActivity;
import com.example.mymusic.mvp.view.views.MyGridView;
import com.example.mymusic.utils.Constant;
import com.example.mymusic.utils.LogUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MusicGridAdapter extends RecyclerView.Adapter<MusicGridAdapter.ViewHolder> {

    private static final String TAG = "MusicGridAdapter";
    private Context mContext;
    private List<AlbumBean> albumBeanList;
    private List<MusicSourceModel.AlbumModel> albumModelList;

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
        if(albumModelList != null){
            MusicSourceModel.AlbumModel albumBean = albumModelList.get(position);
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
                    intent.putExtra(Constant.ALBUM_POSTION, position);
                    mContext.startActivity(intent);
                }
            });
        }

    }

    /*public void update(List<AlbumBean> albumBeanList){
        LogUtils.d(TAG, "albumBeanList" + albumBeanList);
        this.albumBeanList = albumBeanList;
    }*/
    public void updateInternet(List<MusicSourceModel.AlbumModel> albumModelList){
        this.albumModelList = albumModelList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if(albumModelList != null){
            return albumModelList.size();
        }else {
            return 6;
        }
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
