package com.kartnap.chandan.newswallet.data;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Chandan on 11/3/2017.
 */

public class News {

    private String title;

    private String description;

    private String url;
    private String urltoImage;
    public News(){

    }

    public News(String title, String description, String url,String urltoImage) {
        this.title = title;
        this.description = description;
        this.url = url;
        this.urltoImage = urltoImage;
    }

    public String getUrltoImage() {
        return urltoImage;
    }

    public void setUrltoImage(String urltoImage) {
        this.urltoImage = urltoImage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
