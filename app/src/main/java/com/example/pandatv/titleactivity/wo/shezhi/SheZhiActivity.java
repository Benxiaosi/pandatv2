package com.example.pandatv.titleactivity.wo.shezhi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.pandatv.R;
import com.example.pandatv.base.BaseActivity;

public class SheZhiActivity extends BaseActivity {


    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        setTitle("设置");
        showBackwardView(true);
        showForwardView(false);
        showImgView(false);
        showHuoDong(false);
    }

    @Override
    public int getActivityId() {
        return R.layout.activity_she_zhi;
    }
}
