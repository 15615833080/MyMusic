package com.example.mymusic.mvp.view.activity.impl;

import androidx.annotation.NonNull;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.widget.TextView;

import com.example.mymusic.R;
import com.example.mymusic.base.BaseActivity;
import com.example.mymusic.base.BaseInternetActivity;
import com.example.mymusic.utils.LogUtils;
import com.example.mymusic.utils.UserUtils;

import java.lang.ref.WeakReference;


public class SplashActivity extends BaseActivity {

    private static final String TAG = "SplashActivity";
    //倒计时标记
    public static final int CODE = 10001;
    //倒计时最大值
    public static final int MAX_COUNT = 1000;
    //倒计时时间间隔
    public static final int DELAY_MILLIS = 1000;
    //倒计时结束标志
    public static final int ARG_2 = 10003;
    protected Handler myHandler;
    private TextView timeText;
    private MyCountDownTimer myCountDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        timeText = findViewById(R.id.time_text);
        //intData1();
        initData2();
        //initData3();
    }

    private void initData3() {

    }

    private void initData2() {
        final Handler countDownTimerHandler = new CountDownTimerHandler(Looper.myLooper(),this);
        Message msg = Message.obtain();
        msg.what = CODE;
        msg.arg1 = MAX_COUNT;
        countDownTimerHandler.sendMessageDelayed(msg, DELAY_MILLIS);
    }

    public static class CountDownTimerHandler extends Handler{
        final WeakReference<SplashActivity> mWeakReference;
        private SplashActivity activity;

        public CountDownTimerHandler(@NonNull Looper looper, SplashActivity activity) {
            super(looper);
            this.mWeakReference = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            activity = mWeakReference.get();
            switch (msg.what){
                case CODE:
                    int time = msg.arg1;
                    activity.timeText.setText(String.valueOf(time/1000));
                    //循环发的消息控制
                    Message message = Message.obtain();
                    message.what = CODE;
                    message.arg1 = time - 1000;
                    if(time > 0){
                        sendMessageDelayed(message, 1000);
                    }else {
                        final boolean isLogin = UserUtils.validateUserLogin(activity);
                        if(isLogin){
                            toMian();
                        }else {
                            toLogin();
                        }
                    }
                    break;
            }
        }

        private void toLogin() {
            Intent intent = new Intent(activity, LoginActivity.class);
            activity.startActivity(intent);
            activity.finish();
        }

        private void toMian() {
            Intent intent = new Intent(activity, MainActivity.class);
            activity.startActivity(intent);
            activity.finish();
        }

    }



    private void intData1() {
        myHandler = new MyHandler(Looper.myLooper());
        myCountDownTimer = new MyCountDownTimer(3000, 1000);
        myCountDownTimer.start();
        myHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        }, 3000);

        /**
         * handler+runable实现加载
         */
       /* handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
            }
        },2000);*/
    }



    public static class MyHandler extends Handler{
        public MyHandler(@NonNull Looper looper) {
            super(looper);
        }

        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
        }
    }

    class MyCountDownTimer extends CountDownTimer {
        /**
         * @param millisInFuture
         *      表示以毫秒为单位 倒计时的总数
         *      例如 millisInFuture=1000 表示1秒
         * @param countDownInterval
         *      表示 间隔 多少微秒 调用一次 onTick 方法
         *      例如: countDownInterval =1000 ; 表示每1000毫秒调用一次onTick()
         */
        public MyCountDownTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }
        public void onFinish() {
            timeText.setText("正在跳转");
        }

        public void onTick(long millisUntilFinished) {
            timeText.setText("倒计时(" + millisUntilFinished / 1000 + ")");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //闪屏页销毁时将消息对象从消息队列移除并结束倒计时
        //myHandler.removeCallbacksAndMessages(1);
        //myCountDownTimer.cancel();
        LogUtils.d("tag","destory");
    }
}
