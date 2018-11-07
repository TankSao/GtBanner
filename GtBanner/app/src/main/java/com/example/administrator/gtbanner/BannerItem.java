package com.example.administrator.gtbanner;

import android.graphics.Bitmap;

/**
 * Created by Administrator on 2018/11/7.
 */

public class BannerItem {
    private int id;
    private String content;
    private String url;
    private Bitmap bitmap;

    public BannerItem(int id,String content,String url,Bitmap bitmap){
        this.id = id;
        this.content = content;
        this.url = url;
        this.bitmap = bitmap;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }
}
