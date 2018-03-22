package com.example.pandatv.view.activity;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.MediaController;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.pandatv.R;

import org.json.JSONException;
import org.json.JSONObject;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;

public class ZhiBoActivity extends AppCompatActivity {
    private String url1 = "http://vdn.live.cntv.cn/api2/live.do?channel=pa://cctv_p2p_hd";
    private JCVideoPlayer jcVideoPlayerStandard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhi_bo);
        String url = getIntent().getStringExtra("url");
        RequestQueue queue = Volley.newRequestQueue(this);
        jcVideoPlayerStandard = (JCVideoPlayer) findViewById(R.id.videoplayer);
        StringRequest request = new StringRequest(StringRequest.Method.GET, url1 + url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject object = new JSONObject(response);
                    JSONObject array = object.getJSONObject("hls_url");
                    jcVideoPlayerStandard.setUp(array.getString("hls1"),  "播放视频的标题，可以为空");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, null);
        queue.add(request);



//        jcVideoPlayerStandard.loop  = true;//是否循环播放

//        Glide.with(getApplicationContext()).load("http://p.qpic.cn/videoyun/0/2449_43b6f696980311e59ed467f22794e792_1/640")
//                .into(jcVideoPlayerStandard.thumbImageView);
//        jcVideoPlayerStandard.widthRatio = 4;//播放比例
//        jcVideoPlayerStandard.heightRatio = 3;

//        JCVideoPlayerStandard.startFullscreen(this, JCVideoPlayerStandard.class, "http://2449.vod.myqcloud.com/2449_22ca37a6ea9011e5acaaf51d105342e3.f20.mp4", "嫂子辛苦了");
        //直接进入全屏
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();

    }

}
