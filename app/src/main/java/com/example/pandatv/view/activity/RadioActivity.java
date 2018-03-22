package com.example.pandatv.view.activity;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import android.view.View;
import android.view.WindowManager;
import android.widget.MediaController;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.widget.VideoView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.pandatv.R;
import com.example.pandatv.adapter.MyAdapter;
import com.example.pandatv.adapter.User_Adapter;
import com.example.pandatv.one_donghua.MainActivity;
import com.example.pandatv.view.fragment.shou_one.fragment.GsonBeen.Panda;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class RadioActivity extends AppCompatActivity {
    public String url = "http://vdn.apps.cntv.cn/api/getVideoInfoForCBox.do?pid=";

    private VideoView videoView;
    private RelativeLayout Video_play_quan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radio);
        initView();
        initData();
    }

    private void initData() {
        int extra = getIntent().getIntExtra("type", 0);
        String url = getIntent().getStringExtra("url");
        Log.e("initData: ", url + "--------------------------------------");
        playVideo(extra, url);
    }


    public void playVideo(int extra, String path) {
        RequestQueue queue = Volley.newRequestQueue(this);
        if (extra == 1) {

            StringRequest request = new StringRequest(StringRequest.Method.GET, url + path, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONObject object = new JSONObject(response);
                        JSONObject video = object.getJSONObject("video");
                        JSONArray array = video.getJSONArray("chapters");
                        JSONObject jsonArray = array.getJSONObject(0);
                        Log.e("onResponse: ", jsonArray.getString("url") + "--------------------");
                        videoView.setVideoURI(Uri.parse(jsonArray.getString("url")));
                        videoView.setMediaController(new MediaController(RadioActivity.this));
                        videoView.start();
                        videoView.requestFocus();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, null);
            queue.add(request);

        } else if (extra == 2) {


        } else {
            Toast.makeText(this, "无法播放此视频", Toast.LENGTH_SHORT).show();
            finish();
        }


    }

    private void initView() {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        videoView = (VideoView) findViewById(R.id.videoView);
//        Video_play_quan = (RelativeLayout) findViewById(R.id.Video_play_quan);
    }

}
