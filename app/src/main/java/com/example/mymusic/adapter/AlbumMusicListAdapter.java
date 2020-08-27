package com.example.mymusic.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mymusic.R;
import com.example.mymusic.bean.AlbumBean;
import com.example.mymusic.bean.MusicBean;
import com.example.mymusic.mvp.model.MusicSourceModel;
import com.example.mymusic.mvp.view.activity.impl.PlayMusicActivity;
import com.example.mymusic.utils.Constant;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AlbumMusicListAdapter extends RecyclerView.Adapter<AlbumMusicListAdapter.ViewHolder> {

    private Context mContext;
    private View mItemView;
    private RecyclerView mRv;
    private List<MusicBean> musicBeanList;
    private AlbumBean albumBean;
    private boolean isCalcaulationRvHeight;
    private List<MusicSourceModel.AlbumModel.ListBeanX> albumModelMusicList;

    public AlbumMusicListAdapter(Context context, RecyclerView recyclerView) {
        mContext = context;
        mRv = recyclerView;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mItemView = LayoutInflater.from(mContext).inflate(R.layout.item_list_music, parent, false);
        return new ViewHolder(mItemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        setRecyclerViewHeight();
        if (albumModelMusicList != null) {
            final MusicSourceModel.AlbumModel.ListBeanX musicBean = albumModelMusicList.get(position);
            Glide.with(mContext)
                    .load(musicBean.getPoster())
                    .into(holder.ivIcon);
            holder.tvName.setText(musicBean.getName());
            holder.tvAuthor.setText(musicBean.getAuthor());
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(mContext, PlayMusicActivity.class);
                    intent.putExtra(Constant.MUSIC_ID, musicBean.getMusicId());
                    intent.putExtra(Constant.HOT_POSTION, position);
                    intent.putExtra(Constant.IS_ALBUM, false);
                    intent.putExtra(Constant.IS_MUSIC, true);
                    mContext.startActivity(intent);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        if(albumModelMusicList != null){
            return albumModelMusicList.size();
        }else {
            return 10;
        }
    }

    public void update(List<MusicBean> musicBeanList) {
        if (musicBeanList != null) {
            this.musicBeanList = musicBeanList;
        }
    }

    public void updateInternetAlbumMusic(List<MusicSourceModel.AlbumModel.ListBeanX> albumModelMusicList){
        this.albumModelMusicList = albumModelMusicList;
        notifyDataSetChanged();
    }


/*    public void updateAlbum(AlbumBean albumBean) {
        if (albumBean != null) {
            this.albumBean = albumBean;
        }
    }*/

    /**
     * 1、获取ItemView的高度
     * 2、itemView的数量
     * 3、使用 itemViewHeight * itemViewNum = RecyclerView的高度
     */
    private void setRecyclerViewHeight() {
        if (isCalcaulationRvHeight || mRv == null) {
            return;
        }
        isCalcaulationRvHeight = true;

//        获取ItemView的高度
        RecyclerView.LayoutParams itemViewLp = (RecyclerView.LayoutParams) mItemView.getLayoutParams();
//        itemView的数量
        int itemCount = getItemCount();
//        使用 itemViewHeight * itemViewNum = RecyclerView的高度
        int recyclerViewHeight = itemViewLp.height * itemCount;
//        设置RecyclerView高度
        LinearLayout.LayoutParams rvLp = (LinearLayout.LayoutParams) mRv.getLayoutParams();
        rvLp.height = recyclerViewHeight;
        mRv.setLayoutParams(rvLp);
    }

    static
    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_icon)
        ImageView ivIcon;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_author)
        TextView tvAuthor;
        View itemView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.itemView = itemView;
        }
    }

}
