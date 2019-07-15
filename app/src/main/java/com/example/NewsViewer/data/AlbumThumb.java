package com.example.NewsViewer.data;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class AlbumThumb {
    @SerializedName("sizes")
    private ArrayList<PhotoSize> sizes;

    public ArrayList<PhotoSize> getSizes() {
        return sizes;
    }

    public void setSizes(ArrayList<PhotoSize> sizes) {
        this.sizes = sizes;
    }
}

