package com.example.NewsViewer;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.example.NewsViewer.data.PhotoSize;
import com.example.NewsViewer.data.VkResponseItem;

import java.util.ArrayList;

public class NewNewsAdapter extends RecyclerView.Adapter<NewNewsAdapter.NewNewsViewHolder> {

    private ArrayList<VkResponseItem> news;

    public NewNewsAdapter() {
        this.news = new ArrayList<>();
    }

    public void addNews(ArrayList<VkResponseItem> news) {
        this.news.addAll(news);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public NewNewsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_news, viewGroup, false);
        NewNewsViewHolder newNewsViewHolder = new NewNewsViewHolder(view);
        return newNewsViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull NewNewsViewHolder newNewsViewHolder, int position) {
        newNewsViewHolder.tvNumber.setText(String.valueOf(position + 1));
        newNewsViewHolder.tvNews.setText(news.get(position).getNewsText());
        VkResponseItem item = news.get(position);
        if (item.getAttachments() != null
                && item.getAttachments().get(0).getType().equalsIgnoreCase("photo")) {
            ArrayList<PhotoSize> sizes = item.getAttachments().get(0).getPhoto().getSizes();
            for (int i = 0; i < sizes.size(); i++) {
                PhotoSize photoSize = sizes.get(i);
                if (photoSize.getType().equalsIgnoreCase("x")) {
                    newNewsViewHolder.sdwImage.setImageURI(photoSize.getUrl());
                }
            }
        } else if (item.getAttachments() != null
                && item.getAttachments().get(0).getType().equalsIgnoreCase("album")) {
            ArrayList<PhotoSize> sizes = item.getAttachments().get(0).getAlbum().getThumb().getSizes();
            for (int i = 0; i < sizes.size(); i++) {
                PhotoSize photoSize = sizes.get(i);
                if (photoSize.getType().equalsIgnoreCase("x")) {
                    newNewsViewHolder.sdwImage.setImageURI(photoSize.getUrl());
                }
            }
        } else {
            newNewsViewHolder.sdwImage.setVisibility(View.GONE);
        }
    }


    @Override
    public int getItemCount() {
        return news.size();
    }

    class NewNewsViewHolder extends RecyclerView.ViewHolder {

        TextView tvNews;
        TextView tvNumber;
        SimpleDraweeView sdwImage;

        public NewNewsViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNews = itemView.findViewById(R.id.tv_item_news);
            tvNumber = itemView.findViewById(R.id.tv_item_news_news_count);
            sdwImage = itemView.findViewById(R.id.item_news_sdw_image);
        }
    }
}
