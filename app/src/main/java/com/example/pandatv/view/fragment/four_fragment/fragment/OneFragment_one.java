package com.example.pandatv.view.fragment.four_fragment.fragment;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.pandatv.R;
import com.example.pandatv.adapter.MyAdapter;
import com.example.pandatv.adapter.User_Adapter;
import com.example.pandatv.view.fragment.two_fragment.modle.Been_Two_Rcy;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class OneFragment_one extends Fragment {

    List<OneBeen.BookmarkBean.MultipleBean> multiple;
    private RecyclerView rcy;
    private RequestQueue queue;

    @SuppressLint("ValidFragment")
    public OneFragment_one(List<OneBeen.BookmarkBean.MultipleBean> multiple) {
        this.multiple = multiple;
    }

    public OneFragment_one() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_one_fragment_one, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        queue = Volley.newRequestQueue(getContext());
        rcy = (RecyclerView) view.findViewById(R.id.rcy);

        StringRequest request = new StringRequest(StringRequest.Method.GET, multiple.get(0).getUrl(), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                ArrayList<User_Adapter> users = new ArrayList<>();
                Gson gson = new Gson();
                Been_One_One panda = gson.fromJson(response,Been_One_One.class);
//                Log.e( "setGuanCha: ", steing);
                List<Been_One_One.ListBean> list = panda.getList();
                for (int i = 0; i < list.size(); i++) {
                    users.add(new User_Adapter(list.get(i).getImage(),list.get(i).getTitle(),list.get(i).getPid()));
                }
                MyAdapter adapter = new MyAdapter(getContext(), users,3 );
                rcy.setAdapter(adapter);
            }
        }, null);
        queue.add(request);
    }
}
