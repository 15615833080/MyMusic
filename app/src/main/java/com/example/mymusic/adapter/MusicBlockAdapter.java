package com.example.mymusic.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mymusic.R;
import com.example.mymusic.mvp.model.MusicSourceModel;
import com.example.mymusic.mvp.view.activity.impl.RankActivity;
import com.example.mymusic.utils.Constant;
import com.example.mymusic.utils.LogUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MusicBlockAdapter extends RecyclerView.Adapter<MusicBlockAdapter.ViewHolder> {

    private static final String TAG = "MusicBlockAdapter";
    private List<MusicSourceModel.HotModel> mHotModelList;
    private Context mContext;
    private String[] mBlockTitle;

    public MusicBlockAdapter(Context context) {
        mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_block, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (mBlockTitle != null) {
            holder.tvName.setText(mBlockTitle[position]);
            if(position == 2){
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        LogUtils.d(TAG, "111");
                        Intent intent = new Intent(mContext, RankActivity.class);
                        mContext.startActivity(intent);
                    }
                });
            }
        }
    }

    @Override
    public int getItemCount() {
        if (mBlockTitle != null) {
            return mBlockTitle.length;
        } else {
            return 4;
        }
    }

    public void setData(String[] blockTitle, List<MusicSourceModel.HotModel> hotModelList) {
        mBlockTitle = blockTitle;
        mHotModelList = hotModelList;
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_name)
        TextView tvName;
        private View itemView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;
            ButterKnife.bind(this, itemView);
        }
    }

}
