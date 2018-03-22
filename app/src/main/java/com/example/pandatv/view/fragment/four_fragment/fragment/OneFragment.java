package com.example.pandatv.view.fragment.four_fragment.fragment;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.pandatv.R;
import com.example.pandatv.view.fragment.four_fragment.adapter.MyFragmentDapter;
import com.example.pandatv.view.ViewPagerN;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class OneFragment extends Fragment {
    private boolean isShow = false;
    private String url;
    private ImageView vd;
    private TextView title;
    private TextView jiao;
    private LinearLayout jian;
    private TextView jiandata;
    private TabLayout tb;
    private ViewPagerN vp;
    private RequestQueue queue;
    private List<String> list;
    private List<Fragment> fragmentList;

    @SuppressLint("ValidFragment")
    public OneFragment(String url) {
        this.url = url;
    }

    public OneFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_one, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        queue = Volley.newRequestQueue(getContext());
        vd = (ImageView) view.findViewById(R.id.vd);
        title = (TextView) view.findViewById(R.id.title);
        jiao = (TextView) view.findViewById(R.id.jiao);
        jian = (LinearLayout) view.findViewById(R.id.jian);
        jiandata = (TextView) view.findViewById(R.id.jiandata);
        tb = (TabLayout) view.findViewById(R.id.tb);
        vp = (ViewPagerN) view.findViewById(R.id.vp);
        tb.setupWithViewPager(vp);
        list=new ArrayList<>();
        fragmentList = new ArrayList<>();
        StringRequest request = new StringRequest(StringRequest.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Gson gson = new Gson();
                OneBeen oneBeen = gson.fromJson(response, OneBeen.class);
//                Log.e( "setGuanCha: ", steing);
                List<OneBeen.LiveBean> live = oneBeen.getLive();
                title.setText(live.get(0).getTitle());
                jiandata.setText(live.get(0).getBrief());
                jian.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                if (isShow) {
                                                    isShow = false;
                                                    jiao.setBackgroundResource(R.color.white);
                                                    jiao.setBackgroundResource(R.drawable.com_facebook_tooltip_blue_bottomnub);
                                                    jiandata.setVisibility(View.GONE);
                                                } else {
                                                    isShow = true;
                                                    jiandata.setVisibility(View.VISIBLE);
                                                    jiao.setBackgroundResource(R.color.white);
                                                    jiao.setBackgroundResource(R.drawable.com_facebook_tooltip_blue_topnub);
                                                }
                                            }
                                        }
                );
                OneBeen.BookmarkBean bookmark = oneBeen.getBookmark();
                List<OneBeen.BookmarkBean.MultipleBean> multiple = bookmark.getMultiple();
                list.add(multiple.get(0).getTitle());
                fragmentList.add(new OneFragment_one(multiple));
                List<OneBeen.BookmarkBean.WatchTalkBean> talk = bookmark.getWatchTalk();
                list.add(talk.get(0).getTitle());
                fragmentList.add(new OneFragment_two(talk));
                MyFragmentDapter dapter = new MyFragmentDapter(getActivity().getSupportFragmentManager(), fragmentList, list);
                vp.setAdapter(dapter);


            }
        }, null);
        queue.add(request);
    }
}
