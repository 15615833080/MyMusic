package com.example.mymusic.network.api;

import com.example.mymusic.mvp.model.MusicSourceModel;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface MusicApi {
    @GET("DataSource.json")
    Observable<MusicSourceModel> getMusicSource();
}
