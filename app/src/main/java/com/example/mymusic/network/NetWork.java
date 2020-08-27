package com.example.mymusic.network;

import com.example.mymusic.network.api.MusicApi;
import com.example.mymusic.utils.LogUtils;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.mymusic.utils.Constant.WEB_SITE;

public class NetWork {
    private static final String TAG = "NetWork";
    private Retrofit mRetrofit;
    private OkHttpClient mOkHttpClient;
    private MusicApi mMusicApi;
    private volatile static NetWork instance = null;

    private NetWork() {
        mOkHttpClient = new OkHttpClient();
    }

    public static NetWork getInstance() {
        if (instance == null) {
            synchronized (NetWork.class) {
                if (instance == null) {
                    instance = new NetWork();
                }
            }
        }
        return instance;
    }

    public MusicApi getMusicApi() {
        if (mMusicApi == null) {
            mRetrofit = new Retrofit.Builder()
                    .baseUrl(WEB_SITE)
                    .client(mOkHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
            mMusicApi = mRetrofit.create(MusicApi.class);
        }
        LogUtils.d(TAG, "api" + mMusicApi);
        return mMusicApi;
    }

    /*public Observable<MusicSourceModel> fetchMusicSourceBean() {
        if (mMusicApi == null) {
            return Observable.create(new ObservableOnSubscribe<MusicSourceModel>() {
                @Override
                public void subscribe(ObservableEmitter<MusicSourceModel> emitter) throws Exception {
                    emitter.onError(new Throwable("MainAPI is null"));

                }
            });
        }
        return mMusicApi.getMusicSourceData();
    }*/
}
