package com.example.pandatv.view.fragment.two_fragment.presenter;

import com.example.pandatv.untils.UpDataToP;
import com.example.pandatv.view.fragment.two_fragment.modle.ModleInf_two;
import com.example.pandatv.view.fragment.two_fragment.modle.Modle_Two;
import com.example.pandatv.view.fragment.two_fragment.viewinf.ViewShowInf_Two;

/**
 * Created by 本丶小丝 on 2018/3/6.
 */

public class Persenter_Two implements PersenterInf_Two {
    private ViewShowInf_Two view;
    private ModleInf_two modle;

    public Persenter_Two(ViewShowInf_Two view) {
        this.view = view;
        this.modle = new Modle_Two();
    }

    @Override
    public void doSomeThing(String url) {
        modle.getData(url, new UpDataToP() {
            @Override
            public void setDataToP(String string) {
                view.upData(string);
            }
        });
    }
}
