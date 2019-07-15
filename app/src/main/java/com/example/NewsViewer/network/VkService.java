package com.example.NewsViewer.network;

import com.example.NewsViewer.data.VkResponse;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface VkService {
    @GET("wall.get")
    Call<VkResponse> getNews(@QueryMap HashMap<String, String> map);
}
