package com.example.pandatv.view.fragment.four_fragment.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by 本丶小丝 on 2018/3/12.
 */

public class MyFragmentDapter extends FragmentPagerAdapter {
    private List<Fragment> fragmentList;
    private List<String> list;

    public MyFragmentDapter(FragmentManager fm, List<Fragment> fragmentList, List<String> list) {
        super(fm);
        this.fragmentList = fragmentList;
        this.list = list;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return list.get(position);
    }
}
