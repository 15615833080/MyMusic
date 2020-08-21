package com.example.mymusic.mvp.view.views;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.bumptech.glide.Glide;
import com.example.mymusic.R;
import com.example.mymusic.helps.MediaPlayerHelp;
import com.example.mymusic.mvp.presenter.PlayPresenter;
import com.example.mymusic.mvp.presenter.PlayPresenterImpl;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class PlayMusicView extends FrameLayout {

    @BindView(R.id.iv_icon)
    CircleImageView ivIcon;
    @BindView(R.id.iv_play)
    ImageView ivPlay;
    @BindView(R.id.fl_play_music)
    FrameLayout flPlayMusic;
    @BindView(R.id.iv_needle)
    ImageView ivNeedle;
    private static final String TAG = "PlayMusicView";
    private Context mContext;
    private View mView;
    private Animation mPlayMusicAnim, mPlayNeedleAnim, mStopNeedleAnim;
    private boolean isPlaying;
    private MediaPlayerHelp mMediaPlayerHelp;
    private String mPath;
    PlayPresenter mPlayPresenter;

    public PlayMusicView(@NonNull Context context) {
        super(context);
        init(context);
    }

    public PlayMusicView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public PlayMusicView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public PlayMusicView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    public void init(Context context) {
        mContext = context;
        // 绑定layout布局
        mView = LayoutInflater.from(mContext).inflate(R.layout.play_music_view, this, false);
        ButterKnife.bind(this, mView);
        //布局关联属性
        /**
         * 1、定义所需要执行的动画
         *      1、光盘转动的动画
         *      2、指针指向光盘的动画
         *      3、指针离开光盘的动画
         * 2、startAnimation
         */
        mPlayMusicAnim = AnimationUtils.loadAnimation(mContext, R.anim.play_music_anim);
        mPlayNeedleAnim = AnimationUtils.loadAnimation(mContext, R.anim.play_needle_anim);
        mStopNeedleAnim = AnimationUtils.loadAnimation(mContext, R.anim.stop_needle_anim);
        addView(mView);
        mPlayPresenter = new PlayPresenterImpl(mContext);
    }

    /**
     * 播放状态
     */
    public void trigger(){
        if(isPlaying){
            stopMusic();
        }else {
            playMusic(mPath);
        }

    }
    /**
     * 播放音乐
     */
    public void playMusic(String path){
        isPlaying = true;
        mPath = path;
        ivPlay.setVisibility(GONE);
        flPlayMusic.startAnimation(mPlayMusicAnim);
        ivNeedle.startAnimation(mPlayNeedleAnim);

        mPlayPresenter.playMusic(mPath);

    }
    /**
     * 停止播放
     */
    public void stopMusic(){
        isPlaying = false;
        ivPlay.setVisibility(VISIBLE);
        flPlayMusic.clearAnimation();
        ivNeedle.startAnimation(mStopNeedleAnim);
        mPlayPresenter.pauseMusic(mPath);
    }

    /**
     * 光盘中音乐封面图片
     *
     * @param adress
     */
    public void setMusicIcon(String adress) {
        Glide.with(mContext)
                .load(adress)
                .into(ivIcon);
    }

    @OnClick(R.id.fl_play_music)
    public void onViewClicked() {
       trigger();
    }
}
