package com.example.pandatv.titleactivity.wo.xinxi;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.pandatv.R;
import com.example.pandatv.base.BaseActivity;
import com.squareup.picasso.Picasso;

import java.io.File;

public class XinXiActivity extends BaseActivity {

    private SharedPreferences user;
    private SharedPreferences.Editor edit;
    private ImageView userimg;
    private LinearLayout touxiang;
    private TextView username;
    private LinearLayout nicheng;
    private TextView exit;
    private PopupWindow window;
    private Button bt_qu;
    private Button bt_xiang;
    private Button bt_pai;
    private String path;

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        path = getExternalCacheDir().getAbsolutePath() + File.separator + "222.png";
        setTitle("个人信息");
        showBackwardView(true);
        showForwardView(false);
        showImgView(false);
        showHuoDong(false);
        user = getSharedPreferences("user", Context.MODE_PRIVATE);
        edit = user.edit();
        username = findViewById(R.id.username);
        userimg = findViewById(R.id.userimg);
        touxiang = findViewById(R.id.touxiang);
        nicheng = findViewById(R.id.nicheng);
        exit = findViewById(R.id.exit);
        nicheng.setOnClickListener(this);
        touxiang.setOnClickListener(this);
        exit.setOnClickListener(this);
    }

    @Override
    public int getActivityId() {
        return R.layout.activity_xin_xi;
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.nicheng:
                startActivity(new Intent(this, SetNameActivity.class));
                break;
            case R.id.touxiang:
                setTouXiang();
                break;
            case R.id.exit:
                edit.putBoolean("user", false).commit();
                edit.putString("username","name").commit();
                finish();
                break;
        }
    }

    public void setTouXiang() {
        View inflate = LayoutInflater.from(XinXiActivity.this).inflate(R.layout.item, null);
        window = new PopupWindow(inflate, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        window.setOutsideTouchable(true);
        window.setBackgroundDrawable(new BitmapDrawable());
        window.showAtLocation(inflate, Gravity.BOTTOM, 0, 0);
        setData(inflate);
        setLiener();
    }

    private void setData(View v) {
        bt_pai = v.findViewById(R.id.bt_pai);
        bt_qu = v.findViewById(R.id.bt_qu);
        bt_xiang = v.findViewById(R.id.bt_xiang);

    }

    private void setLiener() {
        bt_pai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent();
                intent2.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
//                向意图对象当中，传入指定的路径
                intent2.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(path)));
                startActivityForResult(intent2, 300);
                window.dismiss();
            }
        });
        bt_qu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                window.dismiss();
            }
        });
        bt_xiang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent();
                intent3.setType("image/*");
                intent3.setAction(Intent.ACTION_GET_CONTENT);
                intent3.putExtra(Intent.EXTRA_PHONE_NUMBER, Uri.fromFile(new File(path)));
                startActivityForResult(intent3, 200);
                window.dismiss();

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 300 && resultCode == Activity.RESULT_OK) {

//            发现图片在ImageView上无法显示，原因是图片过大导致的，所以要对于图片进行处理。
//            二次采样   对于图片的宽度和高度进行处理，对于图片的质量进行处理

//            1.获取用于设置图片属性的参数
            BitmapFactory.Options options = new BitmapFactory.Options();
//            2.对于属性进行设置，需要解锁边缘
            options.inJustDecodeBounds = true;
//            3.对于图片进行编码处理
            BitmapFactory.decodeFile(path, options);
//            4.获取原来图片的宽度和高度
            int outHeight = options.outHeight;
            int outWidth = options.outWidth;
//            5.200,200  获得要压缩的比例
            int sampleHeight = outHeight / 200;  //2
            int sampleWidth = outWidth / 200;    //1.5
//            6.获取较大的比例
            int size = Math.max(sampleHeight, sampleWidth);
//            7.设置图片压缩的比例
            options.inSampleSize = size;
            /**图片的质量   1个字节是8位
             * ARGB_8888  32位     4字节   100*100*4 = 40000 个字节
             * ARGB_4444  16位     2字节   100*100*2 = 20000 个字节
             * RGB_565    16位      2字节  100*100*2 = 20000 个字节
             * Alpha_8    8位       1字节  100*100*1 = 10000 个字节
             *
             * 100px*100px  的图片
             * */
            options.inPreferredConfig = Bitmap.Config.RGB_565;   //设置图片的质量类型
//            8.锁定边缘
            options.inJustDecodeBounds = false;
            Bitmap bitmap = BitmapFactory.decodeFile(path, options);
            userimg.setImageResource(R.color.white);
            Picasso.with(this).load(new File(path)).into(userimg);
//            img.setImageBitmap(bitmap);


        } else if (requestCode == 200 && resultCode == Activity.RESULT_OK) {
            Uri uri = data.getData();

            userimg.setImageResource(R.color.white);
            Picasso.with(this).load(uri).into(userimg);


        }
    }
public void setUser(){
    username.setText(user.getString("username", "name"));
//        img_view.setBackgroundResource(user.getInt("userimg",0));
    userimg.setImageResource(user.getInt("userimg", 0));
}
    @Override
    protected void onResume() {
        super.onResume();
        setUser();
    }
}
