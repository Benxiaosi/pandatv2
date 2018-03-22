package com.example.pandatv.view.activity;

import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.pandatv.R;
import com.example.pandatv.base.BaseActivity;
import com.example.pandatv.view.fragment.five_fragment.FiveFragment;
import com.example.pandatv.view.fragment.threefragment.ThreeFragment;
import com.example.pandatv.view.fragment.shou_one.fragment.ShouFragment;
import com.example.pandatv.view.fragment.four_fragment.FourFragment;
import com.example.pandatv.view.fragment.two_fragment.TwoFragment;

import java.util.ArrayList;
import java.util.List;

public class ShowActivity extends BaseActivity {


    private FrameLayout frm;

    private RadioButton one;

    private RadioButton two;

    private RadioButton three;

    private RadioButton four;

    private RadioButton five;
    private List<RadioButton> list;
    private FragmentManager manager;
    private RadioGroup vp;


    @Override
    protected void initData() {
        list.add(one);
        list.add(two);
        list.add(three);
        list.add(four);
        list.add(five);

    }

    @Override
    protected void initView() {
        showTitleView(false);
        showBackwardView(false);
        showForwardView(true);
        showImgView(true);
        showHuoDong(true);
        manager = getSupportFragmentManager();
        manager.beginTransaction().add(R.id.frm, new ShouFragment()).commit();
        frm = (FrameLayout) findViewById(R.id.frm);
        one = (RadioButton) findViewById(R.id.one);
        two = (RadioButton) findViewById(R.id.two);
        three = (RadioButton) findViewById(R.id.three);
        four = (RadioButton) findViewById(R.id.four);
        five = (RadioButton) findViewById(R.id.five);
        vp = findViewById(R.id.vp);
        vp.setOnClickListener(this);
        one.setOnClickListener(this);
        two.setOnClickListener(this);
        three.setOnClickListener(this);
        four.setOnClickListener(this);
        five.setOnClickListener(this);
        list = new ArrayList<>();
        setColor();
    }

    @Override
    public int getActivityId() {
        return R.layout.activity_show;
    }


    public void setColor() {
        for (int j = 0; j < list.size(); j++) {

            if (list.get(j).isChecked()) {
                list.get(j).setBackgroundResource(R.color.xuanzhong);
            } else {
                list.get(j).setBackgroundResource(R.color.weixuan);
            }
        }
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.one:
                manager.beginTransaction().replace(R.id.frm, new ShouFragment()).commit();
                setTitle("");
                showForwardView(true);
                showImgView(true);
                showHuoDong(true);
                setColor();
                break;
            case R.id.two:
                manager.beginTransaction().replace(R.id.frm, new TwoFragment()).commit();
                setTitle("熊猫观察");
                showTitleView(true);
                showImgView(false);
                showHuoDong(false);
                setColor();
                break;
            case R.id.three:
                manager.beginTransaction().replace(R.id.frm, new ThreeFragment()).commit();
                setTitle("熊猫文化");
                showTitleView(true);
                showImgView(false);
                showHuoDong(false);
                setColor();
                break;
            case R.id.four:
                manager.beginTransaction().replace(R.id.frm, new FourFragment()).commit();
                setTitle("熊猫直播");
                showTitleView(true);
                showImgView(false);
                showHuoDong(false);
                setColor();
                break;
            case R.id.five:
                manager.beginTransaction().replace(R.id.frm, new FiveFragment()).commit();
                setTitle("直播中国");
                showTitleView(true);
                showImgView(false);
                showHuoDong(false);
                setColor();
                break;


        }
    }


}
