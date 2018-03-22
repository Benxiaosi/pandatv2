package com.example.pandatv.view.fragment.shou_one.presenter;

import com.example.pandatv.untils.UpDataToP;
import com.example.pandatv.view.fragment.shou_one.modle.Modle;
import com.example.pandatv.view.fragment.shou_one.modle.ModleInf;
import com.example.pandatv.view.fragment.shou_one.viewinf.ViewShowInf;

/**
 * Created by 本丶小丝 on 2018/3/6.
 */

public class Persenter implements PersenterInf {
    private ViewShowInf view;
    private ModleInf modle;

    public Persenter(ViewShowInf view) {
        this.view = view;
        this.modle = new Modle();
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
