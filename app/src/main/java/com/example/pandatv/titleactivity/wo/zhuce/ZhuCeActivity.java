package com.example.pandatv.titleactivity.wo.zhuce;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.pandatv.R;
import com.example.pandatv.base.BaseActivity;
import com.example.pandatv.titleactivity.wo.zhuce.fragment.MilsFragment;
import com.example.pandatv.titleactivity.wo.zhuce.fragment.ShoujiFragment;

public class ZhuCeActivity extends BaseActivity {

    private RadioButton shouji;
    private RadioButton youxiang;
    private RadioGroup vp;
    private FrameLayout fra;
    private FragmentManager supportFragmentManager;


    protected void initData() {
vp.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
    @SuppressLint("ResourceAsColor")
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId){
            case R.id.shouji:
                supportFragmentManager.beginTransaction().replace(R.id.fra,new ShoujiFragment()).commit();
                shouji.setTextColor(getResources().getColor(R.color.cctv_tab_sel));
                youxiang.setTextColor(getResources().getColor(R.color.cctv_tab_unsel));
                break;
            case R.id.youxiang:
                supportFragmentManager.beginTransaction().replace(R.id.fra,new MilsFragment()).commit();
                youxiang.setTextColor(getResources().getColor(R.color.cctv_tab_sel));
                shouji.setTextColor(getResources().getColor(R.color.cctv_tab_unsel));
                break;
        }
    }
});
    }

    @SuppressLint("ResourceAsColor")
    @Override
    protected void initView() {
        setTitle("注册");
        showBackwardView(true);
        showForwardView(false);
        showImgView(false);
        showHuoDong(false);
        shouji = (RadioButton) findViewById(R.id.shouji);
        youxiang = (RadioButton) findViewById(R.id.youxiang);
        vp = (RadioGroup) findViewById(R.id.vp);
        fra = (FrameLayout) findViewById(R.id.fra);
        supportFragmentManager = getSupportFragmentManager();
        supportFragmentManager.beginTransaction().add(R.id.fra,new ShoujiFragment()).commit();
        shouji.setTextColor(getResources().getColor(R.color.cctv_tab_sel));

    }

    @Override
    public int getActivityId() {
        return R.layout.activity_zhu_ce;
    }

}
