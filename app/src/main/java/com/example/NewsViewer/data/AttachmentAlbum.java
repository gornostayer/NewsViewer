package com.example.NewsViewer.data;

import com.google.gson.annotations.SerializedName;

public class AttachmentAlbum {
    @SerializedName("thumb")
    private AlbumThumb thumb;

    public AlbumThumb getThumb() {
        return thumb;
    }

    public void setThumb(AlbumThumb thumb) {
        this.thumb = thumb;
    }
}
