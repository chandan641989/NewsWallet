package com.kartnap.chandan.newswallet;

/**
 * Created by Chandan on 11/1/2017.
 */

public class NewsItem {
    int image;
    String title;
    String description;


    public NewsItem(int image, String title, String description) {
        this.image = image;
        this.title = title;
        this.description = description;

    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
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




}