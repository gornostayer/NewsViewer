package com.example.NewsViewer.data;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class VkResponseItem {
    @SerializedName("text")
    private String newsText;
    @SerializedName("attachments")
    private ArrayList<VkResponseItemAttachment> attachments;

    public String getNewsText() {
        return newsText;
    }

    public void setNewsText(String newsText) {
        this.newsText = newsText;
    }

    public ArrayList<VkResponseItemAttachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(ArrayList<VkResponseItemAttachment> attachments) {
        this.attachments = attachments;
    }
}

