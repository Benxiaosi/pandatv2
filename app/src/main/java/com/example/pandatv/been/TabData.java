package com.example.pandatv.been;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by 本丶小丝 on 2018/3/13.
 */
@Entity
public class TabData {
    public TabData(String url, String title) {
        this.url = url;
        this.title = title;
    }

    @Id(autoincrement = true)
    private Long id;
    private String url;
    private String title;
    @Generated(hash = 1054298039)
    public TabData(Long id, String url, String title) {
        this.id = id;
        this.url = url;
        this.title = title;
    }
    @Generated(hash = 912424272)
    public TabData() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getUrl() {
        return this.url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getTitle() {
        return this.title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
}
