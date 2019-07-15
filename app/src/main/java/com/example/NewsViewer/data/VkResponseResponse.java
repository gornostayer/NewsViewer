package com.example.NewsViewer.data;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class VkResponseResponse {

    @SerializedName("items")
    private ArrayList<VkResponseItem> news;


    public ArrayList<VkResponseItem> getNews() {
        return news;
    }

    public void setNews(ArrayList<VkResponseItem> news) {
        this.news = news;
    }
}
