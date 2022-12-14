package com.escobargames.soccernews.data.remote;

import com.escobargames.soccernews.domain.News;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;


public interface SoccerNewsApi {
    @GET("news.json")
    Call<List<News>> getNews();
}
