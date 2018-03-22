package com.example.pandatv.adapter;

/**
 * Created by 本丶小丝 on 2018/3/6.
 */

public class User_Adapter {
    public User_Adapter(String img, String text, String data, String url, String showtime) {
        this.img = img;
        this.text = text;
        this.data = data;
        this.url = url;
        this.showtime = showtime;
    }

    private String img;
     private String text;
     private String data;
     private String url;

    public String getShowtime() {
        return showtime;
    }

    public void setShowtime(String showtime) {
        this.showtime = showtime;
    }

    private String showtime;
    public String getData() {
        return data;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setData(String data) {
        this.data = data;
    }

    public User_Adapter(String img, String text, String data, String url) {
        this.img = img;
        this.text = text;
        this.data = data;
        this.url = url;
    }

    public User_Adapter(String img, String text, String url) {
        this.img = img;
        this.text = text;
        this.url = url;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getImg() {

        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
