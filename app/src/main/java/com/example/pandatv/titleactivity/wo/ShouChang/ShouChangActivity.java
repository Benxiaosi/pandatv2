package com.example.pandatv.titleactivity.wo.ShouChang;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.pandatv.R;
import com.example.pandatv.base.BaseActivity;

public class ShouChangActivity extends BaseActivity {



    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        setTitle("收藏");
        showBackwardView(true);
        showForwardView(false);
        showImgView(false);
        showHuoDong(false);
    }

    @Override
    public int getActivityId() {
        return R.layout.activity_shou_chang;
    }
}
