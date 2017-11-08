package com.kartnap.chandan.newswallet;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.kartnap.chandan.newswallet.data.News;

import java.security.AccessControlContext;
import java.util.List;

/**
 * Created by Chandan on 11/1/2017.
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.MyViewHolder>{



    private Context mContext;
    private List<News> newsItemList;

    public NewsAdapter(Context mContext, List<News> newsItemList)
    {
        this.mContext = mContext;
        this.newsItemList = newsItemList;

    }



    @Override
    public NewsAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_card_layout,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final NewsAdapter.MyViewHolder holder, final int position) {

        holder.newsTitle.setText(newsItemList.get(position).getTitle());
        //holder.newsUrl.setText(newsItemList.get(position).getUrl());
        //holder.newsDescription.setText(newsItemList.get(position).getDescription());
        Glide.with(mContext).load(newsItemList.get(position).getUrltoImage())
                .placeholder(R.drawable.logo)
                .into(holder.urlToImage);


        holder.newsUrl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext,ReadMoreActivity.class);
                intent.putExtra("title",newsItemList.get(position).getTitle());
                intent.putExtra("link",newsItemList.get(position).getUrl());
                intent.putExtra("linkToImage",newsItemList.get(position).getUrltoImage());
                intent.putExtra("overview",newsItemList.get(position).getDescription());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(intent);

            }
        });


    }

    @Override
    public int getItemCount() {
        return newsItemList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView newsTitle,newsDescription,newsUrl;
        public ImageView urlToImage;

        //public ImageView newsImage;

        public MyViewHolder(View itemView) {

            super(itemView);
            urlToImage = (ImageView)itemView.findViewById(R.id.card_image);
            newsTitle = (TextView)itemView.findViewById(R.id.card_title);
            //newsDescription = (TextView)itemView.findViewById(R.id.card_description);
            newsUrl = (TextView)itemView.findViewById(R.id.viewMore_link);


        }


    }
}
