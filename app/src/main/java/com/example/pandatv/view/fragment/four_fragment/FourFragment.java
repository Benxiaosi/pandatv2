package com.example.pandatv.view.fragment.four_fragment;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.pandatv.R;
import com.example.pandatv.view.fragment.four_fragment.adapter.MyFragmentDapter;
import com.example.pandatv.view.fragment.four_fragment.fragment.FuFragment;
import com.example.pandatv.view.fragment.four_fragment.fragment.OneFragment;
import com.example.pandatv.view.ViewPagerN;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FourFragment extends Fragment {

    private String [] urls = new String[]{
            "http://api.cntv.cn/video/videolistById?vsid=VSET100167216881&n=7&serviceId=panda&o=desc&of=time&p=1",
            "http://api.cntv.cn/video/videolistById?vsid=VSET100219009515&n=7&serviceId=panda&o=desc&of=time&p=1",
            "http://api.cntv.cn/video/videolistById?vsid=VSET100272959126&n=7&serviceId=panda&o=desc&of=time&p=1",
            "http://api.cntv.cn/video/videolistById?vsid=VSET100340574858&n=7&serviceId=panda&o=desc&of=time&p=1",
            "http://api.cntv.cn/video/videolistById?vsid=VSET100284428835&n=7&serviceId=panda&o=desc&of=time&p=1",
            "http://api.cntv.cn/video/videolistById?vsid=VSET100237714751&n=7&serviceId=panda&o=desc&of=time&p=1",
            "http://api.cntv.cn/video/videolistById?vsid=VSET100167308855&n=7&serviceId=panda&o=desc&of=time&p=1",
            "http://api.cntv.cn/video/videolistById?vsid=VSET100219009515&n=7&serviceId=panda&o=desc&of=time&p=1"
    };

    private TabLayout tb;
    private ViewPagerN vp;
    private RequestQueue queue;
    private List<String> list;
    private List<Fragment> fragmentList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_three, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        queue = Volley.newRequestQueue(getContext());
        tb = (TabLayout) view.findViewById(R.id.tb);
        vp = (ViewPagerN) view.findViewById(R.id.vp);
        tb.setupWithViewPager(vp);
        fragmentList = new ArrayList<>();
        list = new ArrayList<>();
        StringRequest request = new StringRequest(StringRequest.Method.GET, "http://www.ipanda.com/kehuduan/PAGE14501772263221982/index.json", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Gson gson = new Gson();
                Been_Three panda = gson.fromJson(response, Been_Three.class);
//                Log.e( "setGuanCha: ", steing);
                List<Been_Three.TablistBean> tablist = panda.getTablist();
                for (int i = 0; i <tablist.size() ; i++) {
                    list.add(tablist.get(i).getTitle());
                    if (i==0){
                        fragmentList.add(new OneFragment(tablist.get(i).getUrl()));
                    }else {
                        fragmentList.add(new FuFragment(urls[i-1]));
                    }
                }
                MyFragmentDapter dapter = new MyFragmentDapter(getChildFragmentManager(), fragmentList, list);
                vp.setAdapter(dapter);
            }
        }, null);
        queue.add(request);
    }
}
