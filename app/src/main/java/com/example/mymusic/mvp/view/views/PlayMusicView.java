package com.example.mymusic.mvp.view.views;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.IBinder;
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
import com.example.mymusic.bean.MusicBean;
import com.example.mymusic.mvp.model.MusicSourceModel;
import com.example.mymusic.mvp.presenter.PlayPresenter;
import com.example.mymusic.mvp.presenter.impl.PlayPresenterImpl;
import com.example.mymusic.service.MusicService;
import com.example.mymusic.utils.LogUtils;

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
    private Intent musicIntent;
    private MusicSourceModel.AlbumModel.ListBeanX mMusicBean;
    private MusicService.MusicBindr mBinder;
    private Animation mPlayMusicAnim, mPlayNeedleAnim, mStopNeedleAnim;
    private boolean isPlaying, isBinding;
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
     * 获取当前的musicBean
     * @param musicBean
     */
    public void setMusic(MusicSourceModel.AlbumModel.ListBeanX musicBean){
        LogUtils.d(TAG,"setMusic" + musicBean);
        mMusicBean = musicBean;
        setMusicIcon();
    }
    /**
     * 光盘中音乐封面图片
     */
    public void setMusicIcon() {
        Glide.with(mContext)
                .load(mMusicBean.getPoster())
                .into(ivIcon);
    }

    @OnClick(R.id.fl_play_music)
    public void onViewClicked() {
        trigger();
    }
    /**
     * 播放状态
     */
    public void trigger() {
        if (isPlaying) {
            stopMusic();
        } else {
            playMusic();
        }

    }
    /**
     * 播放音乐
     */
    public void playMusic() {
        isPlaying = true;
        ivPlay.setVisibility(GONE);
        flPlayMusic.startAnimation(mPlayMusicAnim);
        ivNeedle.startAnimation(mPlayNeedleAnim);
        LogUtils.d(TAG,"playMusic" + isPlaying);
        startMusicService();
        //mPlayPresenter.playMusic(mPath);

    }

    /**
     * 停止播放
     */
    public void stopMusic() {
        isPlaying = false;
        ivPlay.setVisibility(VISIBLE);
        flPlayMusic.clearAnimation();
        ivNeedle.startAnimation(mStopNeedleAnim);
        if(mBinder != null){
            mBinder.pauseMusic();

        }
        //mPlayPresenter.pauseMusic(mPath);
    }


    /**
     * 启动播放音乐服务
     */
    public void startMusicService() {
        if (musicIntent == null) {
            LogUtils.d(TAG,"startMusicService" + musicIntent);
            musicIntent = new Intent(mContext, MusicService.class);
            mContext.startService(musicIntent);
        }else{
            mBinder.playMusic();
        }
        //绑定service
        if(!isBinding){
            isBinding = true;
            LogUtils.d(TAG,"isBinding" + isBinding);
            mContext.bindService(musicIntent, conn, Context.BIND_AUTO_CREATE);
        }
    }

    /**
     * 解除绑定
     */
    public void destroy(){
        if(isBinding){
            isBinding = false;
            mContext.unbindService(conn);
        }
    }

    private ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            mBinder = (MusicService.MusicBindr) iBinder;
            LogUtils.d(TAG,"onServiceConnected" + mBinder);
            mBinder.setMusic(mMusicBean);
            mBinder.playMusic();
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };

}
