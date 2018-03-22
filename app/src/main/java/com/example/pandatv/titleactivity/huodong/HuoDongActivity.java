package com.example.pandatv.titleactivity.huodong;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.pandatv.R;
import com.example.pandatv.base.BaseActivity;

public class HuoDongActivity extends BaseActivity {

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        setTitle("原创·互动");
        showBackwardView(true);
        showForwardView(false);
        showImgView(false);
        showHuoDong(false);
    }

    @Override
    public int getActivityId() {
        return R.layout.activity_huo_dong;
    }
}
