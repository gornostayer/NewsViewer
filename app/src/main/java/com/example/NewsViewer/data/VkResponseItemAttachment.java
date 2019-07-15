package com.example.NewsViewer.data;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class VkResponseItemAttachment {
    @SerializedName("type")
    private String type;

    @SerializedName("photo")
    private AttachmentPhoto photo;

    @SerializedName("album")
    private AttachmentAlbum album;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public AttachmentPhoto getPhoto() {
        return photo;
    }

    public void setPhoto(AttachmentPhoto photo) {
        this.photo = photo;
    }

    public AttachmentAlbum getAlbum() {
        return album;
    }

    public void setAlbum(AttachmentAlbum album) {
        this.album = album;
    }
}

