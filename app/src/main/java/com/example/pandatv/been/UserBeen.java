package com.example.pandatv.been;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by 本丶小丝 on 2018/3/6.
 */
@Entity
public class UserBeen {
    @Id(autoincrement = true)
    private Long id;
    private String userName;
    private int userImg;
    private String phone;
    private String youXiang;
    private String qq;
    private String weiXin;
    private String xinLang;
    @Generated(hash = 95345534)
    public UserBeen(Long id, String userName, int userImg, String phone,
            String youXiang, String qq, String weiXin, String xinLang) {
        this.id = id;
        this.userName = userName;
        this.userImg = userImg;
        this.phone = phone;
        this.youXiang = youXiang;
        this.qq = qq;
        this.weiXin = weiXin;
        this.xinLang = xinLang;
    }
    @Generated(hash = 1404545435)
    public UserBeen() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getUserName() {
        return this.userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public int getUserImg() {
        return this.userImg;
    }
    public void setUserImg(int userImg) {
        this.userImg = userImg;
    }
    public String getPhone() {
        return this.phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getYouXiang() {
        return this.youXiang;
    }
    public void setYouXiang(String youXiang) {
        this.youXiang = youXiang;
    }
    public String getQq() {
        return this.qq;
    }
    public void setQq(String qq) {
        this.qq = qq;
    }
    public String getWeiXin() {
        return this.weiXin;
    }
    public void setWeiXin(String weiXin) {
        this.weiXin = weiXin;
    }
    public String getXinLang() {
        return this.xinLang;
    }
    public void setXinLang(String xinLang) {
        this.xinLang = xinLang;
    }
}
