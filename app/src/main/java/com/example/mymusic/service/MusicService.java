package com.example.mymusic.service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

import com.example.mymusic.R;
import com.example.mymusic.bean.MusicBean;
import com.example.mymusic.helps.MediaPlayerHelp;
import com.example.mymusic.mvp.model.MusicSourceModel;
import com.example.mymusic.mvp.presenter.PlayPresenter;
import com.example.mymusic.mvp.presenter.impl.PlayPresenterImpl;
import com.example.mymusic.mvp.view.activity.impl.LoginActivity;
import com.example.mymusic.mvp.view.activity.impl.PlayMusicActivity;
import com.example.mymusic.mvp.view.activity.impl.SplashActivity;
import com.example.mymusic.mvp.view.views.PlayMusicView;
import com.example.mymusic.utils.LogUtils;

/**
 * 1、通过Service 连接 PlayMusicView 和 MediaPlayHelper
 * 2、PlayMusicView -- Service：
 * 1、播放音乐、暂停音乐
 * 2、启动Service、绑定Service、解除绑定Service
 * 3、MediaPlayHelper -- Service：
 * 1、播放音乐、暂停音乐
 * 2、监听音乐播放完成，停止 Service
 */
public class MusicService extends Service {
    private static final String TAG = "MusicService";
    private PlayMusicView playMusicView;
    private MediaPlayerHelp mMediaPlayerHelp;
    private MusicSourceModel.AlbumModel.ListBeanX mMusicBean;
    private MusicSourceModel.HotModel mHotModel;
    private PlayPresenter playPresenter;
    public static final int NOTIFICATION_ID = 1;

    public MusicService() {
    }

    public class MusicBindr extends Binder {
        /**
         * 对于service来说，需要知道播放哪个音乐
         *
         * @param musicBean
         */
        public void setMusic(MusicSourceModel.AlbumModel.ListBeanX musicBean, MusicSourceModel.HotModel hotModel) {
            mMusicBean = musicBean;
            mHotModel = hotModel;
            startForeground();
        }

        /**
         * 播放音乐
         */
        public void playMusic() {
            LogUtils.d(TAG, "playMusicService");
            if (mMusicBean != null) {
                playPresenter.playMusic(mMusicBean.getPath());
            } else if (mHotModel != null) {
                playPresenter.playMusic(mHotModel.getPath());
            }
        }

        /**
         * 暂停播放
         */
        public void pauseMusic() {
            LogUtils.d(TAG, "pauseMusicService");
            if (mMusicBean != null) {
                playPresenter.pauseMusic(mMusicBean.getPath());
            } else if (mHotModel != null) {
                playPresenter.pauseMusic(mHotModel.getPath());
            }
            //playPresenter.pauseMusic(mHotModel.getPath());
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        LogUtils.d(TAG, "onBindService");
        return new MusicBindr();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        LogUtils.d(TAG, "onCreateService");

        mMediaPlayerHelp = MediaPlayerHelp.getInstance(this);
        playPresenter = new PlayPresenterImpl(this);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        LogUtils.d(TAG, "onStartCommandService");
        return super.onStartCommand(intent, flags, startId);
    }

    /**
     * 不可见的service不能在后台进行，可以使用notifcation；
     */
    /**
     * 设置服务在前台可见
     */
    public void startForeground() {

        /**
         * 通知栏点击跳转的intent
         */
        Intent intent = new Intent(this, SplashActivity.class);
        PendingIntent pendingIntent = PendingIntent
                .getActivity(this, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);

        String channelId = "playMusicService";
        String channelName = "音乐服务";
        String description = "MyMusic";
        int importance = NotificationManager.IMPORTANCE_HIGH;
        Notification notification = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O && mMusicBean != null) {
            NotificationChannel channel = createNotificationChannel(channelId, channelName, importance, description);
            notification = new NotificationCompat.Builder(this, channelId)
                    .setContentTitle(mMusicBean.getName())
                    .setContentText(mMusicBean.getAuthor())
                    .setSmallIcon(R.mipmap.welcome_icon)
                    .setContentIntent(pendingIntent)
                    .build();
            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(channel);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O && mHotModel != null) {
            NotificationChannel channel = createNotificationChannel(channelId, channelName, importance, description);
            notification = new NotificationCompat.Builder(this, channelId)
                    .setContentTitle(mHotModel.getName())
                    .setContentText(mHotModel.getAuthor())
                    .setSmallIcon(R.mipmap.welcome_icon)
                    .setContentIntent(pendingIntent)
                    .build();
            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(channel);
        } else if (mMusicBean != null) {
            notification = new NotificationCompat.Builder(this)
                    .setContentTitle(mMusicBean.getName())
                    .setContentText(mMusicBean.getAuthor())
                    .setSmallIcon(R.mipmap.welcome_icon)
                    .setContentIntent(pendingIntent)
                    .build();
        }else if(mHotModel != null){
            notification = new NotificationCompat.Builder(this)
                    .setContentTitle(mHotModel.getName())
                    .setContentText(mHotModel.getAuthor())
                    .setSmallIcon(R.mipmap.welcome_icon)
                    .setContentIntent(pendingIntent)
                    .build();
        }
        /**
         * 设置 notification 在前台展示
         */
        startForeground(NOTIFICATION_ID, notification);
    }

    /**
     * 创建通知渠道
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    private NotificationChannel createNotificationChannel(String channelId, String channelName, int importance, String description) {
        NotificationChannel channel = new NotificationChannel(channelId, channelName, importance);
        channel.setDescription(description);
        channel.enableLights(true);
        channel.setLightColor(Color.RED);
        channel.setLightColor(Color.RED);
        channel.enableVibration(true);
        channel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
        channel.setShowBadge(false);
        return channel;
    }

}
