package com.example.mymusic.mvp.view.views;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.IBinder;
import android.util.AttributeSet;
import android.util.Property;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.bumptech.glide.Glide;
import com.example.mymusic.R;
import com.example.mymusic.bean.MusicBean;
import com.example.mymusic.mvp.model.MusicSourceModel;
import com.example.mymusic.mvp.model.MusicSourceModel.AlbumModel.ListBeanX;
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
    private ListBeanX mMusicBean;
    private MusicSourceModel.HotModel mHotModel;
    private MusicService.MusicBindr mBinder;
    private Animation mPlayMusicAnim, mPlayNeedleAnim, mStopNeedleAnim;
    Animator mPlayMusicAnimtor, mPlayNeedleAnimtor, mStopNeedleAnimtor;
    private boolean isPlaying, isBinding;
    PlayPresenter mPlayPresenter;
    private ObjectAnimator mObjectPlayMusicAnimtor, mObjectPlayNeedleAnimtor, mObjectStopNeedleAnimtor;

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
        /**补间动画
         * 1、定义所需要执行的动画
         *      1、光盘转动的动画
         *      2、指针指向光盘的动画
         *      3、指针离开光盘的动画
         * 2、startAnimation
         */
        /*mPlayMusicAnim = AnimationUtils.loadAnimation(mContext, R.anim.play_music_anim);
        mPlayNeedleAnim = AnimationUtils.loadAnimation(mContext, R.anim.play_needle_anim);
        mStopNeedleAnim = AnimationUtils.loadAnimation(mContext, R.anim.stop_needle_anim);*/
        /**
         * 属性动画 --- xml方式
         */
        /*mPlayMusicAnimtor = AnimatorInflater.loadAnimator(mContext, R.animator.play_music_animator);
        mPlayNeedleAnimtor = AnimatorInflater.loadAnimator(mContext, R.animator.play_needle_animator);
        mStopNeedleAnimtor = AnimatorInflater.loadAnimator(mContext, R.animator.stop_needle_animator);*/
        /**
         * 属性动画  --- java代码方式
         */
        setObjectMusicAnimtor();
        addView(mView);
        mPlayPresenter = new PlayPresenterImpl(mContext);
    }

    private void setObjectMusicAnimtor() {
        mObjectPlayMusicAnimtor = ObjectAnimator.ofFloat(flPlayMusic, "rotation", 0f, 360f);
        mObjectPlayMusicAnimtor.setInterpolator(new LinearInterpolator());
        mObjectPlayMusicAnimtor.setDuration(10000);
        mObjectPlayMusicAnimtor.setRepeatCount(ValueAnimator.INFINITE);

        ivNeedle.setPivotY(0);
        ivNeedle.setPivotX(0);
        mObjectPlayNeedleAnimtor = ObjectAnimator.ofFloat(ivNeedle, "rotation", -20f, 0);
        mObjectPlayNeedleAnimtor.setInterpolator(new LinearInterpolator());
        mObjectPlayNeedleAnimtor.setDuration(800);
        mObjectPlayNeedleAnimtor.setupEndValues();

        mObjectStopNeedleAnimtor = ObjectAnimator.ofFloat(ivNeedle, "rotation", 0, -20);
        mObjectStopNeedleAnimtor.setDuration(1200);
        mObjectStopNeedleAnimtor.setInterpolator(new LinearInterpolator());
        mObjectStopNeedleAnimtor.setupStartValues();

    }

    /**
     * 获取当前的musicBean
     *
     * @param musicBean
     */
    public void setMusic(ListBeanX musicBean) {
        LogUtils.d(TAG, "setMusic" + musicBean);
        mMusicBean = musicBean;
        setMusicIcon();
    }

    public void setMusicHot(MusicSourceModel.HotModel mHotModel) {
        LogUtils.d(TAG, "setMusic" + mHotModel);
        this.mHotModel = mHotModel;
        setMusicIcon();
    }

    /**
     * 光盘中音乐封面图片
     */
    public void setMusicIcon() {
        if (mMusicBean != null) {
            Glide.with(mContext)
                    .load(mMusicBean.getPoster())
                    .into(ivIcon);
        } else if (mHotModel != null) {
            Glide.with(mContext)
                    .load(mHotModel.getPoster())
                    .into(ivIcon);
        }
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
        /*mPlayMusicAnimtor.setTarget(flPlayMusic);
        mPlayMusicAnimtor.start();*/
        mObjectPlayMusicAnimtor.start();
        mObjectPlayNeedleAnimtor.start();
        /*mPlayNeedleAnimtor.setTarget(ivNeedle);
        ivNeedle.setPivotX(0);
        ivNeedle.setPivotY(0);
        mPlayNeedleAnimtor.start();*/
        //flPlayMusic.startAnimation(mPlayMusicAnim);
        //ivNeedle.startAnimation(mPlayNeedleAnim);
        LogUtils.d(TAG, "playMusic" + isPlaying);
        startMusicService();
        //mPlayPresenter.playMusic(mPath);
    }

    /**
     * 停止播放
     */
    public void stopMusic() {
        isPlaying = false;
        ivPlay.setVisibility(VISIBLE);
        mObjectPlayMusicAnimtor.end();
        mObjectStopNeedleAnimtor.start();
        //mPlayMusicAnimtor.end();
       /* mObjectPlayMusicAnimtor.end();
        mStopNeedleAnimtor.setTarget(ivNeedle);
        mStopNeedleAnimtor.start();*/
        //flPlayMusic.clearAnimation();
        //ivNeedle.startAnimation(mStopNeedleAnim);
        if (mBinder != null) {
            mBinder.pauseMusic();

        }
        //mPlayPresenter.pauseMusic(mPath);
    }


    /**
     * 启动播放音乐服务
     */
    public void startMusicService() {
        if (musicIntent == null) {
            LogUtils.d(TAG, "startMusicService" + musicIntent);
            musicIntent = new Intent(mContext, MusicService.class);
            mContext.startService(musicIntent);
        } else {
            mBinder.playMusic();
        }
        //绑定service
        if (!isBinding) {
            isBinding = true;
            LogUtils.d(TAG, "isBinding" + isBinding);
            mContext.bindService(musicIntent, conn, Context.BIND_AUTO_CREATE);
        }
    }

    /**
     * 解除绑定
     */
    public void destroy() {
        if (isBinding) {
            isBinding = false;
            mContext.unbindService(conn);
        }
    }

    private ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            mBinder = (MusicService.MusicBindr) iBinder;
            LogUtils.d(TAG, "onServiceConnected" + mBinder);
            mBinder.setMusic(mMusicBean, mHotModel);
            mBinder.playMusic();
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };

}
