package com.example.mymusic.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.ToastUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.util.LogTime;
import com.example.mymusic.R;
import com.example.mymusic.bean.RecordBean;
import com.example.mymusic.mvp.view.views.SlidingButtonView;
import com.example.mymusic.utils.LogUtils;
import com.example.mymusic.utils.UtilsHelper;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemLongClick;

public class RecordListAdapter extends RecyclerView.Adapter<RecordListAdapter.ViewHolder> implements SlidingButtonView.IonSlidingButtonListener {
    private static final String TAG = "RecordListAdapter";
    private View mItemView;
    private Context mContext;
    private List<RecordBean> mRecordList;
    private IonSlidingViewClickListener mIDeleteBtnClickListener;
    private SlidingButtonView mMenu = null;


    public RecordListAdapter(Context context) {
        this.mContext = context;
        mIDeleteBtnClickListener = (IonSlidingViewClickListener) context;
    }

    public void update(List<RecordBean> recordBeanList) {
        mRecordList = recordBeanList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mRecordList.size();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mItemView = LayoutInflater.from(mContext).inflate(R.layout.record_item, parent, false);
        return new ViewHolder(mItemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (mRecordList != null) {
            final RecordBean recordBean = mRecordList.get(position);
            Glide.with(mContext)
                    .load(recordBean.getPoster())
                    .into(holder.ivImg);
            holder.tvName.setText(recordBean.getName());
            holder.tvNewsTypeName.setText(recordBean.getAuthor());
            holder.layoutContent.getLayoutParams().width = UtilsHelper.getScreenWidth(
                    mContext);
            holder.layoutContent.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    if(menuIsOpen()){
                        closeMenu();
                    }else {
                        int n = holder.getLayoutPosition();
                        mIDeleteBtnClickListener.onItemLongClick(view, n);
                    }
                    return true;
                }
            });
            /*holder.layoutContent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //判断是否有删除菜单打开
                    if (menuIsOpen()) {
                        closeMenu();//关闭菜单
                    } else {
                        int n = holder.getLayoutPosition();
                        mIDeleteBtnClickListener.onItemLongClick(v, n);
                    }
                }
            });*/
            holder.tvDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int n = holder.getAdapterPosition();
                    LogUtils.d(TAG, v + "NIHAO" + n + mIDeleteBtnClickListener);
                    mIDeleteBtnClickListener.onDeleteBtnCilck(v, n);
                }
            });
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_delete)
        TextView tvDelete;
        @BindView(R.id.iv_img)
        ImageView ivImg;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_newsType_name)
        TextView tvNewsTypeName;
        @BindView(R.id.layout_content)
        RelativeLayout layoutContent;
        View itemView;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.itemView = itemView;
            ((SlidingButtonView) itemView).setSlidingButtonListener(RecordListAdapter.this);
        }
    }

    /**
     * 删除菜单打开信息接收
     *
     * @param view
     */
    @Override
    public void onMenuIsOpen(View view) {
        mMenu = (SlidingButtonView) view;
    }

    /**
     * 滑动或者点击了Item监听
     */
    @Override
    public void onDownOrMove(SlidingButtonView slidingButtonView) {
        if (menuIsOpen()) {
            if (mMenu != slidingButtonView) {
                closeMenu();
            }
        }
    }

    /**
     * 关闭菜单
     */
    public void closeMenu() {
        mMenu.closeMenu();
        mMenu = null;
    }

    /**
     * 判断是否有菜单打开
     */
    public Boolean menuIsOpen() {
        if (mMenu != null) {
            return true;
        }
        return false;
    }

    public void removeData(int position, String musicId) {
        mRecordList.remove(position);
        notifyItemRemoved(position);
    }
    public void reMoveDataAll(){
        mRecordList.clear();
        notifyDataSetChanged();
    }

    public interface IonSlidingViewClickListener {
        void onItemLongClick(View view, int position);
        void onDeleteBtnCilck(View view, int position);
    }

}
