package com.example.pandatv.base;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.pandatv.R;
import com.example.pandatv.titleactivity.huodong.HuoDongActivity;
import com.example.pandatv.titleactivity.wo.WoActivity;
import com.example.pandatv.titleactivity.wo.zhuce.ZhuCeActivity;

/**
 * Created by 本丶小丝 on 2018/3/5.
 */

public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView mTitleTextView;
    private Button mBackwardbButton;
    private Button mForwardButton;
    private FrameLayout mContentLayout;
    private ImageView img;
    private TextView text_title;
    private Button button_backward;
    private Button button_forward;
    private Button button;
    private RelativeLayout layout_titlebar;
    public TextView textView;
    public TextView bianji;
    private Dialog loadDialog;
    private int dialogNum = 0;
    public TextView baocun;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupViews();
        setContentView(getActivityId());
        initView();
        initData();

    }

    protected abstract void initData();

    protected abstract void initView();

    public abstract int getActivityId();

    private void setupViews() {
        super.setContentView(R.layout.baseativity);
        img = (ImageView) findViewById(R.id.img);
        text_title = (TextView) findViewById(R.id.text_title);
        button_backward = (Button) findViewById(R.id.button_backward);
        button_forward = (Button) findViewById(R.id.button_forward);
        button = (Button) findViewById(R.id.button);
        layout_titlebar = (RelativeLayout) findViewById(R.id.layout_titlebar);
        mContentLayout = (FrameLayout) findViewById(R.id.layout_content);
        textView = (TextView) findViewById(R.id.textView);
        bianji = (TextView) findViewById(R.id.bianji);
        baocun = (TextView) findViewById(R.id.baocun);
        button_backward.setOnClickListener(this);
        button_forward.setOnClickListener(this);
        button.setOnClickListener(this);
        textView.setOnClickListener(this);
        bianji.setOnClickListener(this);
    }

    /**
     * 是否显示返回按钮
     *
     * @param
     * @param show true则显示
     */
    protected void showBackwardView(boolean show) {
        if (button_backward != null) {
            if (show) {
                button_backward.setVisibility(View.VISIBLE);
            } else {
                button_backward.setVisibility(View.GONE);
            }
        } // else ignored
    }

    protected void showImgView(boolean show) {
        if (img != null) {
            if (show) {
                img.setVisibility(View.VISIBLE);
            } else {
                img.setVisibility(View.GONE);
            }
        } // else ignored
    }

    protected void showBianJi(boolean show) {
        if (bianji != null) {
            if (show) {
                bianji.setVisibility(View.VISIBLE);
            } else {
                bianji.setVisibility(View.GONE);
            }
        } // else ignored
    }

    protected void showBaoCun(boolean show) {
        if (baocun != null) {
            if (show) {
                baocun.setVisibility(View.VISIBLE);
            } else {
                baocun.setVisibility(View.GONE);
            }
        } // else ignored
    }

    protected void showTitleView(boolean show) {
        if (text_title != null) {
            if (show) {

                text_title.setVisibility(View.VISIBLE);
            } else {
                text_title.setVisibility(View.GONE);
            }
        } // else ignored
    }

    protected void showTextView(boolean show) {
        if (textView != null) {
            if (show) {
                textView.setVisibility(View.VISIBLE);
            } else {
                textView.setVisibility(View.GONE);
            }
        } // else ignored
    }

    /**
     * 提供是否显示提交按钮
     *
     * @param
     * @param show true则显示
     */
    protected void showForwardView(boolean show) {
        if (button_forward != null) {
            if (show) {
                button_forward.setVisibility(View.VISIBLE);
            } else {
                button_forward.setVisibility(View.INVISIBLE);
            }
        } // else ignored
    }

    protected void showHuoDong(boolean show) {
        if (button != null) {
            if (show) {
                button.setVisibility(View.VISIBLE);
            } else {
                button.setVisibility(View.INVISIBLE);
            }
        } // else ignored
    }

    /**
     * 返回按钮点击后触发
     *
     * @param backwardView
     */
    protected void onBackward(View backwardView) {
        finish();
    }

    protected void onHuoDong(View backwardView) {
        startActivity(new Intent(this, HuoDongActivity.class));

    }

    /**
     * 提交按钮点击后触发
     *
     * @param forwardView
     */
    protected void onForward(View forwardView) {
        startActivity(new Intent(this, WoActivity.class));
    }


    //设置标题内容
    @Override
    public void setTitle(int titleId) {
        mTitleTextView.setText(titleId);
    }

    //设置标题内容
    @Override
    public void setTitle(CharSequence title) {
        text_title.setText(title);
    }

    //设置标题文字颜色
    @Override
    public void setTitleColor(int textColor) {
        text_title.setTextColor(textColor);
    }

    public void setBarColor(int textColor) {
        layout_titlebar.setBackgroundResource(textColor);
    }


    //取出FrameLayout并调用父类removeAllViews()方法
    @Override
    public void setContentView(int layoutResID) {
        mContentLayout.removeAllViews();
        View.inflate(this, layoutResID, mContentLayout);
        onContentChanged();
    }

    @Override
    public void setContentView(View view) {
        mContentLayout.removeAllViews();
        mContentLayout.addView(view);
        onContentChanged();
    }

    /* (non-Javadoc)
     * @see android.app.Activity#setContentView(android.view.View, android.view.ViewGroup.LayoutParams)
     */
    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        mContentLayout.removeAllViews();
        mContentLayout.addView(view, params);
        onContentChanged();
    }


    /* (non-Javadoc)
     * @see android.view.View.OnClickListener#onClick(android.view.View)
     * 按钮点击调用的方法
     */

    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.button_backward:
                onBackward(v);
                break;

            case R.id.button_forward:
                onForward(v);
                break;

            case R.id.button:
                onHuoDong(v);
                break;
            case R.id.textView:
                startActivity(new Intent(this, ZhuCeActivity.class));
                break;
            case R.id.bianji:
                break;
        }
    }

    public void showLoadingDialog() {

        dialogNum++;
        if (loadDialog != null && loadDialog.isShowing()) {
            loadDialog.dismiss();
            loadDialog = null;
        }
        loadDialog = new Dialog(this, R.style.dialog);
        loadDialog.setCanceledOnTouchOutside(false);

        loadDialog.setContentView(R.layout.layout_dialog);
        try {
            loadDialog.show();
        } catch (WindowManager.BadTokenException exception) {
            exception.printStackTrace();
        }
    }

    public void dismissLoadDialog() {
        dialogNum--;
        if (dialogNum > 0) {
            return;
        }
        if (null != loadDialog && loadDialog.isShowing() == true) {
            loadDialog.dismiss();
            loadDialog = null;
        }
    }


}
