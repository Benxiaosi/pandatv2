package com.example.pandatv.untils;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.io.IOException;


import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by 本丶小丝 on 2018/3/6.
 */

public class MyOkHttp {
    private static final MyOkHttp ourInstance = new MyOkHttp();
    private final OkHttpClient build;
    private UpDataToP upDataToP;
    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            upDataToP.setDataToP((String) msg.obj);
        }
    };


    public static MyOkHttp getInstance() {
        return ourInstance;
    }

    private MyOkHttp() {
        build = new OkHttpClient.Builder().build();

    }

    public void getData(final String url, final UpDataToP up) {
        upDataToP = up;
        new Thread(new Runnable() {
            @Override
            public void run() {
                Request request = new Request.Builder().url(url).build();
                build.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        handler.obtainMessage(0,response.body().string()).sendToTarget();

//                Log.e( "onResponse: ", response.body().string());
                    }
                });
            }
        }).start();


    }
}
