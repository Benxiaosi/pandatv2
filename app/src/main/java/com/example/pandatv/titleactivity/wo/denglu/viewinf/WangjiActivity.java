package com.example.pandatv.titleactivity.wo.denglu.viewinf;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.graphics.drawable.Drawable;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.CycleInterpolator;
import android.view.animation.TranslateAnimation;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pandatv.R;
import com.example.pandatv.base.BaseActivity;
import com.example.pandatv.titleactivity.wo.zhuce.fragment.untils.FoundPswdModel;
import com.example.pandatv.titleactivity.wo.zhuce.fragment.untils.HandlerListener;
import com.example.pandatv.titleactivity.wo.zhuce.fragment.untils.HandlerMessage;
import com.example.pandatv.titleactivity.wo.zhuce.fragment.untils.XjlHandler;
import com.example.pandatv.untils.ToastUtil;
import com.google.gson.Gson;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WangjiActivity extends BaseActivity implements View.OnFocusChangeListener{


    private TextView xieyi;
    private TimeCount mTime;
    private TextView mEdit_phonenumber, mEdit_checkimage;
    private TextView mEdit_checkphone, mEdit_checknewpswd;

    private TextView mHint_phonenumber, mHint_checkimage, mHint_checkphone, mHint_checknewpswd;
    private String erroType = "移动网络环境下无法进行注册,请开启WIFI后进行注册";
    //获取图形验证码
    private TextView mPersonal_reg_imgcheck;
    //获取手机验证码
    private TextView mPersonal_reg_phonecheck;

    private TextView mTvfoundpswd;


    @Override
    protected void initData() {

    }

    public String JSESSIONID = null;
    private byte[] mCaptchaBytes;

    //获取验证码图片成功
    private static final int IMG_GET_SUCCES = 1000;
    private static final int MSG_LOGIN_IN_ERROR = 1002;
    //获取邮箱注册成功
    private static final int MSG_LOGIN_IN_SUCCES = 1003;
    //获取手机验证码成功、失败
    private static final int MSG_GETTING_SUCCESS = 1004;
    private static final int MSG_GETTING_ERROR = 1005;
    //找回密码成功、失败
    private static final int MSG_RESET_PSWD_SUCCESS = 1006;
    private static final int MSG_RESET_PSWD_ERROR = 1007;

    //输入框输入的验证码
    private String mCaptchaEditTextString;


    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_GETTING_SUCCESS:
                    mTime.start();
                    dismissLoadDialog();


                    break;
                case MSG_GETTING_ERROR:
                    dismissLoadDialog();



                    break;
                case MSG_RESET_PSWD_SUCCESS:
                    dismissLoadDialog();
                    if (msg.obj != null) {
                        Toast toast = Toast.makeText(WangjiActivity.this, (String) msg.obj, Toast.LENGTH_LONG);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        toast.show();
                        WangjiActivity.this.finish();
                    }

                    break;
                case MSG_RESET_PSWD_ERROR:
                    dismissLoadDialog();
                    if (msg.obj != null) {

                        ToastUtil.showShort(WangjiActivity.this, (String) msg.obj);
                    }


                    break;
                case IMG_GET_SUCCES:
                    dismissLoadDialog();
                    Drawable captchaImage = byteToDrawable(mCaptchaBytes);
                    mPersonal_reg_imgcheck.setBackgroundDrawable(captchaImage);
                    mPersonal_reg_imgcheck.setText("");
                    break;

            }
        }
    };

    @Override
    protected void initView() {
        showBackwardView(true);
        setTitle("忘记密码");
        showForwardView(false);
        showImgView(false);
        showHuoDong(false);
        xieyi = (TextView) this.findViewById(R.id.personal_reg_xieyi_view);

        mEdit_phonenumber = (TextView) this.findViewById(R.id.edit_phonenumber);
        mEdit_checkimage = (TextView) this.findViewById(R.id.edit_checkimage);
        mEdit_checkphone = (TextView) this.findViewById(R.id.edit_checkphone);
        mEdit_checknewpswd = (TextView) this.findViewById(R.id.edit_newpssword);

        mTime = new TimeCount(180000, 1000);//构造CountDownTimer对象

        mHint_phonenumber = (TextView) this.findViewById(R.id.hint_phonenumber);
        mHint_checkimage = (TextView) this.findViewById(R.id.hint_checkimage);
        mHint_checkphone = (TextView) this.findViewById(R.id.hint_checkphone);
        mHint_checknewpswd = (TextView) this.findViewById(R.id.hint_newpssword);

        mPersonal_reg_imgcheck = (TextView) this.findViewById(R.id.personal_reg_imgcheck);
        mPersonal_reg_phonecheck = (TextView) this.findViewById(R.id.personal_reg_phonecheck);

        mTvfoundpswd = (TextView) this.findViewById(R.id.tvfoundpswd);


        sendCaptchaHttpMessage();


        mPersonal_reg_imgcheck.setOnClickListener(new ViewClick());
        mPersonal_reg_phonecheck.setOnClickListener(new ViewClick());
        mTvfoundpswd.setOnClickListener(new ViewClick());

        mEdit_phonenumber.setOnFocusChangeListener(this);
        mEdit_checkimage.setOnFocusChangeListener(this);
        mEdit_checkphone.setOnFocusChangeListener(this);
        mEdit_checknewpswd.setOnFocusChangeListener(this);
    }


    public int getActivityId() {
        return R.layout.activity_wangji;
    }

    public void onFocusChange(View v, boolean hasFocus) {
        if (hasFocus) {
            switch (v.getId()) {
                case R.id.edit_phonenumber:

                    mHint_phonenumber.setText("");
                    break;
                case R.id.edit_checkimage:
                    if (!checkPhone())
                        return;
                    mHint_checkimage.setText("");

                    break;
                case R.id.edit_checkphone:
                    if (!checkCaptcha())
                        return;
                    mHint_checkphone.setText("");
                    break;
                case R.id.edit_newpssword:
                    if (!checkPhoneCheck())
                        return;

                    mHint_checknewpswd.setText("");
                    break;
            }

        }
    }


    class TimeCount extends CountDownTimer {
        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);//参数依次为总时长,和计时的时间间隔
        }

        @Override
        public void onFinish() {//计时完毕时触发
            mPersonal_reg_phonecheck.setText(getString(R.string.regist_yanzhengma));
            mPersonal_reg_phonecheck.setClickable(true);
        }

        @Override
        public void onTick(long millisUntilFinished) {//计时过程显示
            mPersonal_reg_phonecheck.setClickable(false);
            mPersonal_reg_phonecheck.setText(getString(R.string.regist_sendmessage_again) + "(" + millisUntilFinished / 1000 + ")");
        }
    }


    class ViewClick implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.personal_reg_imgcheck:
                    //获取验证码
                    sendCaptchaHttpMessage();
                    break;
                case R.id.personal_reg_phonecheck:

                    mEdit_phonenumber.clearFocus();
                    mEdit_checkimage.clearFocus();
                    mEdit_checkphone.clearFocus();

                    String phone = mEdit_phonenumber.getText().toString().trim();
                    String tImageChcek = mEdit_checkimage.getText().toString().trim();
                    if (TextUtils.isEmpty(phone)) {
                        mHint_phonenumber.setText(getString(R.string.regist_tips));
                    }

                    if (TextUtils.isEmpty(tImageChcek)) {
                        mHint_checkimage.setText(getString(R.string.regist_error_tips));
                    }
                    if (!checkCaptcha()) {
                        return;
                    } else {

                        mHint_phonenumber.setText("");
                        mHint_checkimage.setText("");

                        sendCaptchaSmsHttpMessage();
                    }

                    break;
                case R.id.tvfoundpswd:
                    mEdit_phonenumber.clearFocus();
                    mEdit_checkimage.clearFocus();
                    mEdit_checkphone.clearFocus();
                    mEdit_checknewpswd.clearFocus();



                    if (!checkPhone()) {
                        return;
                    }

                    if (!checkCaptcha()) {
                        return;
                    }
                    if (!checkPhoneCheck()) {
                        return;
                    }

                    if (!checkPasswork()) {
                        return;
                    }


                    sendPhoneRegistHttp();

                    break;

            }
        }

    }

    private void showTishiDialog(String tishiString) {
        try {
            if (isFinishing()) {
                return;
            }

            View tview = View.inflate(
                    this,
                    R.layout.person_register_tishi_dialog, null);
            TextView tishiView = (TextView) tview.findViewById(R.id.register_tishi_txt);
            tishiView.setText(tishiString);
            TextView tishiSure = (TextView) tview.findViewById(R.id.register_tishi_btn_sure);
            final Dialog dialog = new Dialog(this, R.style.dialog);

            dialog.setContentView(tview);
            dialog.setCanceledOnTouchOutside(true);
            tishiSure.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
            dialog.show();
        } catch (Exception e) {
            /*logger.debug(TimeUtil.getTime2(System.currentTimeMillis())
					+ "弹出框异常:" + e.toString());*/
        }
    }

    private void shakeViewToNotifyUser(View view) {
        Animation shake = new TranslateAnimation(0, 15, 0, 0);
        shake.setInterpolator(new CycleInterpolator(3));
        shake.setDuration(300);
        view.startAnimation(shake);
    }


    /**
     * 获取图片验证码
     */
    private void sendCaptchaHttpMessage() {


        showLoadingDialog();

        new Thread(new Runnable() {

            @Override
            public void run() {
                String from = "http://reg.cntv.cn/simple/verificationCode.action";
                HttpGet httpGet = new HttpGet(from);

                try {

                    DefaultHttpClient httpClient = new DefaultHttpClient();
                    HttpResponse httpResponse = httpClient.execute(httpGet);

                    if (httpResponse.getStatusLine().getStatusCode() == 200) {// 读返回数据

                        CookieStore cookieStore = httpClient.getCookieStore();
                        List<Cookie> cookies = cookieStore.getCookies();
                        for (int index = 0, count = cookies.size(); index < count; index++) {
                            Cookie cookie = cookies.get(index);
                            if ("JSESSIONID".equals(cookie.getName())) {
                                JSESSIONID = cookie.getValue();

                                break;
                            }
                        }
                        mCaptchaBytes = EntityUtils.toByteArray(httpResponse
                                .getEntity());
                        mHandler.sendEmptyMessage(IMG_GET_SUCCES);

                    } else {
                        showTishiDialog("获取验证码失败");
                    }
                } catch (ClientProtocolException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }).start();
//        PandaApi.getSecurityCode().enqueue(new Callback<byte[]>() {
//            @Override
//            public void onResponse(Call<byte[]> call, Response<byte[]> response) {
//                JSESSIONID = HttpHeaderHelper.parseAttribute(response.headers(), "JSESSIONID");
//                mCaptchaBytes = response.body();
//                dismissLoadDialog();
//                Drawable captchaImage = byteToDrawable(mCaptchaBytes);
//                mPersonal_reg_imgcheck.setBackgroundDrawable(captchaImage);
//                mPersonal_reg_imgcheck.setText("");
//            }
//
//            @Override
//            public void onFailure(Call<byte[]> call, Throwable t) {
//
//                dismissLoadDialog();
//                mPersonal_reg_imgcheck.setText(getString(R.string.forget_image_yanzhengma));
//                mPersonal_reg_imgcheck.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//
//                showTishiDialog("获取验证码失败");
//            }
//        });


    }

    //检查手机号
    private boolean checkPhone() {
        String phoneString = mEdit_phonenumber.getText().toString().trim();
        if (TextUtils.isEmpty(phoneString)) {
            mHint_phonenumber.setText(getString(R.string.regist_nullphonenumber_tips));
            //shakeViewToNotifyUser(mEdit_phonenumber);
            return false;
        }
        Pattern pattern = Pattern.compile("^1[3578]\\d{9}$");
        Matcher matcher = pattern.matcher(phoneString);
        if (matcher.matches()) {
            mHint_phonenumber.setText("");
            return true;
        } else {
            mHint_phonenumber.setText(getString(R.string.regist_phone_reg_error));
            return false;
        }
    }

    //检查密码
    private boolean checkPasswork() {
        String editpasswordsString = mEdit_checknewpswd.getText().toString();

        if (TextUtils.isEmpty(editpasswordsString)) {
            mHint_checknewpswd.setText(getString(R.string.regist_null_paswd_tips));
            //shakeViewToNotifyUser(mEdit_checknewpswd);
            return false;
        } else if (editpasswordsString.length() < 6 || editpasswordsString.length() > 16) {
            mHint_checknewpswd.setText(getString(R.string.regist_paswd_reg));
            return false;
        } else {
            mHint_checknewpswd.setText("");
            return true;
        }
    }

    /**
     * 检查手机验证码
     */

    private boolean checkPhoneCheck() {
        String phonecheck = mEdit_checkphone.getText().toString().trim();
        if (TextUtils.isEmpty(phonecheck)) {
            mHint_checkphone.setText(getString(R.string.regist_yanzhengma_null));
            //shakeViewToNotifyUser(mEdit_checkphone);
            return false;
        } else {
            mHint_checkphone.setText(" ");
            return true;
        }
    }

    /**
     * 检查验证码
     *
     * @return
     */
    private boolean checkCaptcha() {
        if (mCaptchaBytes == null) {
            Toast.makeText(this, R.string.no_obtain_code, Toast.LENGTH_SHORT).show();
            return false;
        }

        mCaptchaEditTextString = mEdit_checkimage.getText().toString().trim();
        if (mCaptchaEditTextString.contains(" ")) {
            mHint_checkimage.setText(getString(R.string.regist_yanzhengma_error));
            return false;
        }
        if (mCaptchaEditTextString == null || "".equals(mCaptchaEditTextString)) {
            //shakeViewToNotifyUser(mEdit_checkimage);
            mHint_checkimage.setText(getString(R.string.regist_yanzhengma_null));
            return false;
        } else {
            mHint_checkimage.setText("");
            return true;
        }

    }

    /**
     * 短信验证码的请求
     */
    private void sendCaptchaSmsHttpMessage() {

        showLoadingDialog();
        XjlHandler<String> tHandler = new XjlHandler<String>(new HandlerListener() {

            @Override
            public void handlerMessage(HandlerMessage msg) {

                String string = (String) msg.obj;

                if (string.endsWith("success")) {
                    Message tmsg = mHandler.obtainMessage(MSG_GETTING_SUCCESS);
                    tmsg.obj = "发送成功";
                    mHandler.sendMessage(tmsg);
                } else if (string.endsWith("registered")) {
                    Message tmsg = mHandler.obtainMessage(MSG_GETTING_ERROR);
                    tmsg.obj = "您的手机号未注册";
                    mHandler.sendMessage(tmsg);
                } else if (string.endsWith("sendfailure")) {
                    Message tmsg = mHandler.obtainMessage(MSG_GETTING_ERROR);
                    tmsg.obj = "验证码发送失败";
                    mHandler.sendMessage(tmsg);
                } else if (string.endsWith("sendagain")) {
                    Message tmsg = mHandler.obtainMessage(MSG_GETTING_ERROR);
                    tmsg.obj = "三分钟内只能获取一次";
                    mHandler.sendMessage(tmsg);
                } else if (string.endsWith("ipsendagain")) {
                    Message tmsg = mHandler.obtainMessage(MSG_GETTING_ERROR);
                    tmsg.obj = "同一IP用户请求校验码超过5次";
                    mHandler.sendMessage(tmsg);
                } else if (string.endsWith("mobileoften")) {
                    Message tmsg = mHandler.obtainMessage(MSG_GETTING_ERROR);
                    tmsg.obj = "同一手机号用户请求校验码超过3次";
                    mHandler.sendMessage(tmsg);
                } else if (string.endsWith("mobilecodeerror")) {
                    Message tmsg = mHandler.obtainMessage(MSG_GETTING_ERROR);
                    tmsg.obj = "验证码不正确";
                    mHandler.sendMessage(tmsg);
                }

            }
        });

        String phoneString = mEdit_phonenumber.getText().toString().trim();
        String imgyanzhengma = mEdit_checkimage.getText().toString().trim();
        String url = "http://reg.cntv.cn/regist/getVerifiCode.action";


        HashMap<String, String> tHeaders = new HashMap<String, String>();
        try {

            tHeaders.put("Referer", URLEncoder.encode("http://cbox_mobile.regclientuser.cntv.cn", "UTF-8"));
            tHeaders.put("User-Agent", URLEncoder.encode("CNTV_APP_CLIENT_CBOX_MOBILE", "UTF-8"));
            tHeaders.put("Cookie", "JSESSIONID=" + JSESSIONID);
        } catch (Exception e) {

        }

        HashMap<String, String> params = new HashMap<String, String>();
        params.put("method", "getRequestVerifiCodeM");
        params.put("mobile", phoneString);
        params.put("verfiCodeType", "2");
        params.put("verificationCode", imgyanzhengma);
        tHandler.postHeaderJson(url, tHeaders, params, 11);

    }

    /***
     * 手机号重置请求
     * @param
     * @return
     */
    private void sendPhoneRegistHttp() {

        showLoadingDialog();
        XjlHandler<String> tHandler = new XjlHandler<String>(new HandlerListener() {
            @Override
            public void handlerMessage(HandlerMessage msg) {
                String tString = (String) msg.obj;
                Gson tGson = new Gson();
                FoundPswdModel foundPswdModel = tGson.fromJson(tString, FoundPswdModel.class);
                if (foundPswdModel.getErrtype().equals("0")) {
                    Message tmsg = mHandler.obtainMessage(MSG_RESET_PSWD_SUCCESS);
                    tmsg.obj = "新密码设置成功";
                    mHandler.sendMessage(tmsg);
                } else if (foundPswdModel.getErrtype().equals("100")) {
                    //内部程序错
                    Message tmsg = mHandler.obtainMessage(MSG_RESET_PSWD_ERROR);
                    tmsg.obj = foundPswdModel.getMsg();
                    mHandler.sendMessage(tmsg);
                } else if (foundPswdModel.getErrtype().equals("102")) {
                    //缺少参数
                    Message tmsg = mHandler.obtainMessage(MSG_RESET_PSWD_ERROR);
                    tmsg.obj = foundPswdModel.getMsg();
                    mHandler.sendMessage(tmsg);
                } else if (foundPswdModel.getErrtype().equals("103")) {
                    //参数无效
                    Message tmsg = mHandler.obtainMessage(MSG_RESET_PSWD_ERROR);
//                                msg.obj = foundPswdModel.getMsg();
                    tmsg.obj = "验证码输入错误";
                    mHandler.sendMessage(tmsg);
                } else if (foundPswdModel.getErrtype().equals("117")) {
                    //密码仅限6～16个字符
                    Message tmsg = mHandler.obtainMessage(MSG_RESET_PSWD_ERROR);
                    tmsg.obj = foundPswdModel.getMsg();
                    mHandler.sendMessage(tmsg);
                } else if (foundPswdModel.getErrtype().equals("331")) {
                    //手机号无效
                    Message tmsg = mHandler.obtainMessage(MSG_RESET_PSWD_ERROR);
                    msg.obj = foundPswdModel.getMsg();
                    mHandler.sendMessage(tmsg);
                } else if (foundPswdModel.getErrtype().equals("334")) {
                    //手机号未注册
                    Message tmsg = mHandler.obtainMessage(MSG_RESET_PSWD_ERROR);
                    msg.obj = foundPswdModel.getMsg();
                    mHandler.sendMessage(tmsg);
                }

            }
        });
        String url = "https://reg.cntv.cn/regist/resetPasswdByMobile.action";
        String from = "http://cbox_mobile.regclientuser.cntv.cn";
        String tPhoneNumber = mEdit_phonenumber.getText().toString().trim();
        String tCheckPhone = mEdit_checkphone.getText().toString().trim();
        String tPassWord = mEdit_checknewpswd.getText().toString();

        HashMap<String, String> tHeader = new HashMap<String, String>();
        try {

            tHeader.put("Referer", URLEncoder.encode(from, "UTF-8"));
            tHeader.put("User-Agent", URLEncoder.encode("CNTV_APP_CLIENT_CBOX_MOBILE", "UTF-8"));
            tHeader.put("Cookie", "JSESSIONID=" + JSESSIONID);

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        HashMap<String, String> tParams = new HashMap<String, String>();

        tParams.put("mobile", tPhoneNumber);
        tParams.put("verfiCode", tCheckPhone);
        try {
            tParams.put("passWd", URLEncoder.encode(tPassWord, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        tParams.put("from", from);
        tParams.put("Callback", "");

        tHandler.postHeaderJson(url, tHeader, tParams, 12);
    }


    public static Drawable byteToDrawable(byte[] byteArray) {
        try {
            String string = new String(byteArray, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        ByteArrayInputStream ins = new ByteArrayInputStream(byteArray);
        return Drawable.createFromStream(ins, null);
    }

    @Override
    protected void onPause() {
        //如果系统键盘还在，则隐藏系统键盘
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        super.onPause();
    }

}

