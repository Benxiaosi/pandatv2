package com.example.pandatv.view.fragment.four_fragment.fragment;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pandatv.R;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class OneFragment_two extends Fragment {

private List<OneBeen.BookmarkBean.WatchTalkBean> talk;

    @SuppressLint("ValidFragment")
    public OneFragment_two(List<OneBeen.BookmarkBean.WatchTalkBean> talk) {
        this.talk = talk;
    }

    public OneFragment_two() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_one_fragment_two, container, false);
        return view;
    }

}
