package com.example.pandatv.titleactivity.wo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.pandatv.R;
import com.example.pandatv.base.BaseActivity;
import com.example.pandatv.titleactivity.wo.ShouChang.ShouChangActivity;
import com.example.pandatv.titleactivity.wo.denglu.viewinf.DengluActivity;
import com.example.pandatv.titleactivity.wo.lishi.GuanKanActivity;
import com.example.pandatv.titleactivity.wo.shezhi.SheZhiActivity;
import com.example.pandatv.titleactivity.wo.xinxi.XinXiActivity;

public class WoActivity extends BaseActivity {


    private LinearLayout denglu;
    private LinearLayout guankan;
    private LinearLayout shouchang;
    private LinearLayout shezhi;
    private ImageView userimg;
    private TextView username;
    private SharedPreferences user;
    private ImageView jian;
    private SharedPreferences.Editor edit;
    private ImageView img_view;

    @Override
    protected void initData() {
        denglu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (user.getBoolean("user", false)) {
                    startActivity(new Intent(WoActivity.this, XinXiActivity.class));
                } else {
                    startActivity(new Intent(WoActivity.this, DengluActivity.class));
                }

            }
        });
        guankan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WoActivity.this, GuanKanActivity.class));
            }
        });
        shouchang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WoActivity.this, ShouChangActivity.class));
            }
        });
        shezhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WoActivity.this, SheZhiActivity.class));
            }
        });
    }

    @Override
    protected void initView() {
        showBackwardView(true);
        setTitle("个人中心");
        showForwardView(false);
        showImgView(false);
        showHuoDong(false);
        user = getSharedPreferences("user", MODE_PRIVATE);
        edit = user.edit();
        username = findViewById(R.id.username);
        userimg = findViewById(R.id.userimg);
        denglu = findViewById(R.id.denglu);
        guankan = findViewById(R.id.guankan);
        shouchang = findViewById(R.id.shouchang);
        shezhi = findViewById(R.id.shezhi);
        jian = findViewById(R.id.jian);
        img_view = findViewById(R.id.img_view);
    }

    @Override
    public int getActivityId() {
        return R.layout.activity_wo;
    }

    public void setUser() {

        if (user.getBoolean("user", false)) {
            img_view.setVisibility(View.VISIBLE);
            username.setText(user.getString("username", "name"));

            if (user.getInt("userimg", 0) == 0) {

                userimg.setImageResource(R.color.subscribe_item_selected_bg);
                userimg.setImageResource(R.drawable.personal_login_head);
                edit.putInt("userimg", R.drawable.personal_login_head).commit();
            } else {
                userimg.setImageResource(user.getInt("userimg", 0));
            }

        }else {
            img_view.setVisibility(View.GONE);
            username.setText("点击登录");
            userimg.setImageResource(R.drawable.person_sign);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUser();
    }


}



