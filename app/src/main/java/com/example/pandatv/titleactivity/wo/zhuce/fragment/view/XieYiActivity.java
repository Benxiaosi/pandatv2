package com.example.pandatv.titleactivity.wo.zhuce.fragment.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.pandatv.R;
import com.example.pandatv.base.BaseActivity;

public class XieYiActivity extends BaseActivity {



    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        showBackwardView(true);
        setTitle("央视网络服务使用协议");
        showForwardView(false);
        showImgView(false);
        showHuoDong(false);
        setTitleColor(R.color.bg_color);
        setBarColor(R.color.com_facebook_share_button_text_color);
    }

    @Override
    public int getActivityId() {
        return R.layout.activity_xie_yi;
    }
}
