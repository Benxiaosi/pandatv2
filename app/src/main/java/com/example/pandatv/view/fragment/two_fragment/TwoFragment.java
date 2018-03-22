package com.example.pandatv.view.fragment.two_fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.pandatv.R;
import com.example.pandatv.adapter.MyAdapter;
import com.example.pandatv.adapter.User_Adapter;
import com.example.pandatv.view.fragment.shou_one.fragment.GsonBeen.Panda;
import com.example.pandatv.view.fragment.two_fragment.modle.Been_Two;
import com.example.pandatv.view.fragment.two_fragment.modle.Been_Two_Rcy;
import com.example.pandatv.view.fragment.two_fragment.presenter.Persenter_Two;
import com.example.pandatv.view.fragment.two_fragment.viewinf.ViewShowInf_Two;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class TwoFragment extends Fragment implements ViewShowInf_Two {


    private ImageView img;
    private RecyclerView recy;
    private TextView title;
    private RequestQueue queue;

    public TwoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragmen 、
        View view = inflater.inflate(R.layout.fragment_two, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        queue = Volley.newRequestQueue(getContext());
        Persenter_Two two = new Persenter_Two(this);
        two.doSomeThing("http://www.ipanda.com/kehuduan/PAGE14503485387528442/index.json");
        img = (ImageView) view.findViewById(R.id.img);
        recy = (RecyclerView) view.findViewById(R.id.recy);
        title = (TextView) view.findViewById(R.id.title);
        recy.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
    }

    @Override
    public void upData(String str) {
        Been_Two two = new Gson().fromJson(str, Been_Two.class);
        Picasso.with(getContext()).load(two.getData().getBigImg().get(0).getImage()).into(img);
        title.setText(two.getData().getBigImg().get(0).getTitle());
        StringRequest request = new StringRequest(StringRequest.Method.GET, two.getData().getListurl(), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                ArrayList<User_Adapter> users = new ArrayList<>();
                Gson gson = new Gson();
                Been_Two_Rcy panda = gson.fromJson(response, Been_Two_Rcy.class);
//                Log.e( "setGuanCha: ", steing);
                List<Been_Two_Rcy.ListBean> list = panda.getList();
                for (int i = 0; i < list.size(); i++) {
                    users.add(new User_Adapter(list.get(i).getPicurl(), list.get(i).getTitle(), "2017-12-1"+i, list.get(i).getGuid(), list.get(i).getVideolength()));
                }
                MyAdapter adapter = new MyAdapter(getContext(), users, 2);
                recy.setAdapter(adapter);
            }
        }, null);
        queue.add(request);
    }
}
