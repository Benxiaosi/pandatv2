package com.example.pandatv.view.fragment.shou_one.modle;

import com.example.pandatv.untils.MyOkHttp;
import com.example.pandatv.untils.UpDataToP;

/**
 * Created by 本丶小丝 on 2018/3/6.
 */

public class Modle implements ModleInf {
    @Override
    public void getData(String string, UpDataToP up) {
        MyOkHttp.getInstance().getData(string,up);
    }
}
