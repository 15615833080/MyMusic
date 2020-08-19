package com.example.mymusic.mvp.view.activity;

import androidx.annotation.NonNull;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.widget.TextView;

import com.example.mymusic.R;
import com.example.mymusic.base.BaseActivity;
import com.example.mymusic.utils.LogUtils;

import java.lang.ref.WeakReference;


public class SplashActivity extends BaseActivity {

    private static final String TAG = "SplashActivity";
    //倒计时标记
    public static final int WHAT = 10001;

    //倒计时最大值
    public static final int MAX_COUNT = 3;

    //倒计时时间间隔
    public static final int DELAY_MILLIS = 1000;
    protected Handler myHandler;
    private TextView timeText;
    private MyCountDownTimer myCountDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        timeText = findViewById(R.id.time_text);

        //init
        //intData1();
        initData2();
    }

    private void initData2() {
        CountDownTimerHandler countDownTimerHandler = new CountDownTimerHandler(Looper.myLooper(),SplashActivity.this);
        Message msg = Message.obtain();
        msg.what = WHAT;
        msg.arg1 = MAX_COUNT;
        countDownTimerHandler.sendMessageDelayed(msg, DELAY_MILLIS);

    }

    private void intData1() {
        myHandler = new MyHandler(Looper.myLooper());
        myCountDownTimer = new MyCountDownTimer(5000, 2000);
        myCountDownTimer.start();
        myHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
            }
        }, 5000);

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

    public static class CountDownTimerHandler extends Handler{
        final WeakReference<SplashActivity> mWeakReference;

        public CountDownTimerHandler(@NonNull Looper looper, SplashActivity activity) {
            super(looper);
            this.mWeakReference = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            SplashActivity activity = mWeakReference.get();
            switch (msg.what){
                case WHAT:
                    int value = msg.arg1;
                    activity.timeText.setText(String.valueOf(value --));
                    //循环发的消息控制
                    if (value >= 0) {
                        Message message = Message.obtain();
                        message.what = WHAT;
                        message.arg1 = value;
                        sendMessageDelayed(message, DELAY_MILLIS);
                    }else {
                        Intent intent = new Intent(activity, LoginActivity.class);
                        activity.startActivity(intent);
                    }
                    break;
            }
        }

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
            Log.i("tag","倒计时"+millisUntilFinished / 1000);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //闪屏页销毁时将消息对象从消息队列移除并结束倒计时
        myHandler.removeCallbacksAndMessages(null);
        myCountDownTimer.cancel();
        LogUtils.d("tag","destory");
    }
}
