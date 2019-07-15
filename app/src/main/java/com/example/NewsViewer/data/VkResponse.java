package com.example.NewsViewer.data;

import com.google.gson.annotations.SerializedName;

public class VkResponse {
    @SerializedName("response")
    private VkResponseResponse response;

    public VkResponseResponse getResponse() {
        return response;
    }

    public void setResponse(VkResponseResponse response) {
        this.response = response;
    }
}