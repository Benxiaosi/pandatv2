package com.example.pandatv.titleactivity.wo.zhuce.fragment.untils;

import android.util.Log;

import java.io.IOException;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


/**
 * @author Xiao JinLai
 * @Date 2015-12-10 12:26
 * @Description 网络请求处理类
 */
public class XjlHandler<T> {

    private static final int ERROR = Integer.MAX_VALUE; // 请求错误数值

    private HandlerListener mListener = null;

    public XjlHandler(HandlerListener listener) {

        mListener = listener;
    }


    public void postHeaderJson(String url, Map<String, String> headers, Map<String, String> params,
                               final int what) {


        OkHttpClient client = new OkHttpClient();
        FormBody.Builder builder = new FormBody.Builder();


        for (String key : params.keySet()) {
            builder.add(key, params.get(key));
        }
        FormBody body = builder.build();
        Request.Builder post = new Request.Builder().url(url).post(body);
        for (String key : headers.keySet()) {
            post.addHeader(key, headers.get(key));
        }
        Request build = post.build();
        client.newCall(build).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                HandlerMessage tMessage = new HandlerMessage();
                tMessage.what = ERROR;
                //                tMessage.obj = error;
                mListener.handlerMessage(tMessage);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                HandlerMessage tMessage = new HandlerMessage();
                tMessage.what = what;
                tMessage.obj = response.body().string();
                Log.e( "onResponse: ", (String) tMessage.obj);
                mListener.handlerMessage(tMessage);
            }
        });


    }


}
