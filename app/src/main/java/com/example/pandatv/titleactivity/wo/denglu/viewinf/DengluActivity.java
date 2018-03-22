package com.example.pandatv.titleactivity.wo.denglu.viewinf;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pandatv.R;
import com.example.pandatv.base.BaseActivity;
import com.example.pandatv.titleactivity.wo.zhuce.fragment.untils.HandlerMessage;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class DengluActivity extends BaseActivity implements ViewInf {
    public String JSESSIONID = null;
    public String verifycode = null;
    public String uct = null;
    private String mUserSeqId;
    private String mUserId;
    private String mNickName;
    private LinearLayout weixin;
    private LinearLayout qq;
    private LinearLayout weibo;
    private EditText text_name;
    private EditText text_password;
    private TextView wangji;
    private Button login;
    private UMShareAPI umShareAPI;
    private SharedPreferences user;
    private SharedPreferences.Editor edit;

    @Override
    protected void initData() {
        qq.setOnClickListener(this);
        weixin.setOnClickListener(this);
        weibo.setOnClickListener(this);
        login.setOnClickListener(this);
        wangji.setOnClickListener(this);
    }

    @Override
    protected void initView() {
        setTitle("登录");
        showBackwardView(true);
        showForwardView(false);
        showImgView(false);
        showHuoDong(false);
        showTextView(true);
        user = getSharedPreferences("user", MODE_PRIVATE);
        edit = user.edit();
        umShareAPI = UMShareAPI.get(this);
        weixin = findViewById(R.id.weixin);
        qq = findViewById(R.id.qq);
        weibo = findViewById(R.id.weibo);
        text_name = findViewById(R.id.text_name);
        text_password = findViewById(R.id.text_password);
        wangji = findViewById(R.id.wangji);
        login = findViewById(R.id.login);

    }


    @Override
    public int getActivityId() {
        return R.layout.activity_denglu;
    }

    @Override
    public void loginWeiXin() {
        setDiSanFang(SHARE_MEDIA.WEIXIN);
    }

    @Override
    public void loginQQ() {
        setDiSanFang(SHARE_MEDIA.QQ);
    }

    @Override
    public void loginXinLang() {
        setDiSanFang(SHARE_MEDIA.SINA);
    }

    @Override
    public String getTextName() {
        return text_name.getText().toString();
    }

    @Override
    public String getPassWord() {
        return text_name.getText().toString();
    }

    @Override
    public void login() {

        String userName = text_name.getText().toString()
                .trim();
        String password = text_password.getText().toString();
        OkHttpClient client = new OkHttpClient();
        String from = "https://reg.cntv.cn/login/login.action";
        try {
            String url = from + "?username="
                    + URLEncoder.encode(userName, "UTF-8")
                    + "&password=" + password
                    + "&service=client_transaction" + "&from="
                    + URLEncoder.encode(from, "UTF-8");
            FormBody body = new FormBody.Builder().add("username", userName).add("password", password).build();


            Request build = new Request.Builder().url(url).build();


            client.newCall(build).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    Log.e("onFailure: ", e.getMessage());
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
//                    Log.e("onResponse: ", response.body().string() + "--------------------------------------------------------------------------------------------");
                    try {
                        final JSONObject jo = new JSONObject(response.body().string());
                        mUserSeqId = jo.getString("user_seq_id");
                        if (mUserSeqId.equals("0")) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    try {
                                        dismissLoadDialog();
                                        Toast.makeText(DengluActivity.this, jo.getString("errMsg"), Toast.LENGTH_SHORT).show();
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            });

                        } else {
                            getUserTicket();
                        }


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void zhuChe() {

    }

    @Override
    public void wangJi() {
        startActivity(new Intent(this, WangjiActivity.class));

    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.qq:
                loginQQ();
                break;

            case R.id.weixin:
                loginWeiXin();
                break;

            case R.id.weibo:
                loginXinLang();
                break;
            case R.id.wangji:
                wangJi();
                break;
            case R.id.login:
                showLoadingDialog();
                if (checkEmpty(text_name) == false) {
                    dismissLoadDialog();
                    Toast.makeText(this, "账号不能为空！", Toast.LENGTH_SHORT).show();

                    return;
                }
                if (checkEmpty(text_password) == false) {
                    dismissLoadDialog();
                    Toast.makeText(this, "密码不能为空！", Toast.LENGTH_SHORT).show();
                    return;
                }
                login();
                break;
        }
    }

    public void setDiSanFang(SHARE_MEDIA san) {


        umShareAPI.getPlatformInfo(this, san, new UMAuthListener() {
            @Override
            public void onStart(SHARE_MEDIA share_media) {

            }

            @Override
            public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
                edit.putBoolean("user", true).commit();
                finish();
            }

            @Override
            public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {

            }

            @Override
            public void onCancel(SHARE_MEDIA share_media, int i) {

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }

    /**
     * 获取昵称
     */
    private void getUserTicket() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String client = "http://cbox_mobile.regclientuser.cntv.cn";
                String url = "http://my.cntv.cn/intf/napi/api.php" + "?client="
                        + "cbox_mobile" + "&method=" + "user.getNickName"
                        + "&userid=" + mUserSeqId;
                HttpGet httpGet = new HttpGet(url);
                try {
                    httpGet.addHeader("Referer",
                            URLEncoder.encode(client, "UTF-8"));
                    httpGet.addHeader("User-Agent", URLEncoder.encode(
                            "CNTV_APP_CLIENT_CBOX_MOBILE", "UTF-8"));
                    httpGet.addHeader("Cookie", "verifycode=" + verifycode);

                    DefaultHttpClient httpClient = new DefaultHttpClient();
                    HttpResponse httpResponse = httpClient.execute(httpGet);

                    if (httpResponse.getStatusLine().getStatusCode() == 200) {// 读返回数据
                        String strResult = EntityUtils.toString(httpResponse
                                .getEntity());
                        JSONObject jo = new JSONObject(strResult);
                        if (jo.getString("code").equals("0")) {
                            if (jo.has("content")) {
                                JSONObject contentJSONObject = jo
                                        .getJSONObject("content");
                                if (contentJSONObject.has("nickname")) {
                                    mNickName = contentJSONObject
                                            .getString("nickname");
                                    edit.putBoolean("user", true).commit();
                                    edit.putString("username", mNickName).commit();
                                    dismissLoadDialog();
                                    finish();

                                }
                            }
                        } else {
                            String codeErrorString = jo.getString("error");

                        }
                    }
                } catch (ClientProtocolException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUserSeqId = null;
        mNickName = null;
    }

    private boolean checkEmpty(EditText editText) {
        String testTxt = editText.getText().toString();
        if (testTxt == null || "".equals(testTxt)) {
            return false;
        }
        return true;
    }
}
