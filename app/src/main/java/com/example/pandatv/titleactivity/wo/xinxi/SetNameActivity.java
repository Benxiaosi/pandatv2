package com.example.pandatv.titleactivity.wo.xinxi;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pandatv.R;
import com.example.pandatv.base.BaseActivity;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class SetNameActivity extends BaseActivity {
    private SharedPreferences user;
    private SharedPreferences.Editor edit;
    private EditText newname;
    private String string;


    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        user = getSharedPreferences("user", Context.MODE_PRIVATE);
        edit = user.edit();
        newname = (EditText) findViewById(R.id.newname);
        setTitle("修改昵称");
        showBackwardView(true);
        showForwardView(false);
        showImgView(false);
        showHuoDong(false);
        showBaoCun(true);
        string = user.getString("username", "name");
        newname.setText(string);


        baocun.setTextColor(getResources().getColor(R.color.transparent_half));


        newname.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                final String str = newname.getText().toString();


                if (str.equals(string)) {
                    baocun.setTextColor(getResources().getColor(R.color.transparent_half));
                } else {
                    baocun.setTextColor(getResources().getColor(R.color.white));
                    baocun.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (TextUtils.isEmpty(str)) {
                                Toast.makeText(SetNameActivity.this, "昵称不能为空！", Toast.LENGTH_SHORT).show();
                                return;
                            }
                            edit.putString("username", str).commit();
                            comitName(str);

                        }


                    });


                }

            }
        });


    }

    @Override
    public int getActivityId() {
        return R.layout.activity_set_name;
    }

    private void comitName(final String s) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL("http://reg.cntv.cn/simple/verificationCode.action");
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("GET");
                    httpURLConnection.connect();
                    Log.e("TAG", "返回状态码==" + httpURLConnection.getResponseCode());
                    OutputStream stream = httpURLConnection.getOutputStream();
                    stream.write(("username=" + s).getBytes());
                    stream.flush();
                    stream.close();
                    httpURLConnection.disconnect();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            finish();
                        }
                    });
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (ProtocolException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();


    }
}
