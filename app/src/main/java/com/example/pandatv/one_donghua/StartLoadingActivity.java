package com.example.pandatv.one_donghua;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.pandatv.R;
import com.example.pandatv.view.activity.ShowActivity;

public class StartLoadingActivity extends AppCompatActivity {
    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            startActivity(new Intent(StartLoadingActivity.this, ShowActivity.class));
            finish();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_loading);

        handler.sendEmptyMessageDelayed(0, 3000);

    }
}
