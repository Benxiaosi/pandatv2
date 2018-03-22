package com.example.pandatv.titleactivity.wo.lishi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.pandatv.R;
import com.example.pandatv.base.BaseActivity;

public class GuanKanActivity extends BaseActivity {



    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        setTitle("历史记录");
        showBackwardView(true);
        showForwardView(false);
        showImgView(false);
        showHuoDong(false);
        showTextView(true);
        showBianJi(true);
    }



    @Override
    public int getActivityId() {
        return R.layout.activity_guan_kan;
    }
}
