package com.example.NewsViewer;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {

    private ArrayList<String> news;

    public NewsAdapter(ArrayList<String> news) {
        this.news = news;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int type) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View newsView = layoutInflater.inflate(R.layout.item_news, viewGroup, false);
        NewsViewHolder newsViewHolder = new NewsViewHolder(newsView);
        return newsViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder newsViewHolder, int position) {
        newsViewHolder.tvNew.setText(news.get(position));
    }

    @Override
    public int getItemCount() {
        if( news == null){
            return 0;
        } else {
            return news.size();
        }
//        return news==null?0:news.size();
    }

    public class NewsViewHolder extends RecyclerView.ViewHolder{

        TextView tvNew;

        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNew = itemView.findViewById(R.id.tv_item_news);
        }
    }
}
