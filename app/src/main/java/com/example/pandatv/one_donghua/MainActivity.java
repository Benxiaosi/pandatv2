package com.example.pandatv.one_donghua;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.example.pandatv.R;
import com.example.pandatv.one_donghua.adapter.PageAdapter;
import com.example.pandatv.view.activity.ShowActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ViewPager vp;
    private SharedPreferences donghua;
    private SharedPreferences.Editor edit;
    private ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        donghua = getSharedPreferences("donghua", MODE_PRIVATE);
        edit = donghua.edit();
        vp = (ViewPager) findViewById(R.id.vp);
        img = (ImageView) findViewById(R.id.img);
        if (donghua.getBoolean("donghua", true)) {
            initData();
        } else {

                startActivity(new Intent(MainActivity.this, StartLoadingActivity.class));
                finish();


        }


    }

    private void initData() {
        ArrayList<ImageView> img = new ArrayList<>();
        ImageView view = new ImageView(this);
        view.setImageResource(R.drawable.guide_one);
        img.add(view);
        ImageView view2 = new ImageView(this);
        view2.setImageResource(R.drawable.guide_two);
        img.add(view2);
        ImageView view3 = new ImageView(this);
        view3.setImageResource(R.drawable.guide_three);
        img.add(view3);
        PageAdapter adapter = new PageAdapter(img);
        vp.setAdapter(adapter);
        view3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit.putBoolean("donghua", false).commit();
                startActivity(new Intent(MainActivity.this, ShowActivity.class));
                finish();
            }
        });

    }
}
