package com.example.pandatv.view.fragment.four_fragment.fragment;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.pandatv.R;
import com.example.pandatv.adapter.MyAdapter;
import com.example.pandatv.adapter.User_Adapter;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FuFragment extends Fragment {


    private String url;
    private RecyclerView rcy;
    private RequestQueue queue;
    private List<User_Adapter> list;

    public FuFragment() {
    }

    @SuppressLint("ValidFragment")
    public FuFragment(String url) {
        this.url = url;
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fu, container, false);

        initView(view);

        return view;
    }

    private void initView(View view) {
        queue = Volley.newRequestQueue(getContext());
        list = new ArrayList<>();
        rcy = (RecyclerView) view.findViewById(R.id.rcy);
        rcy.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        StringRequest request = new StringRequest(StringRequest.Method.GET, url , new Response.Listener<String>() {
                   @Override
                   public void onResponse(String response) {
                       try {
                           JSONObject array = new JSONObject(response);
                           JSONArray video = array.getJSONArray("video");
                           for (int i = 0; i < video.length(); i++) {
                               JSONObject object = video.getJSONObject(i);
                               list.add(new User_Adapter(object.getString("img"), object.getString("t"), "2017-12-0" + i + " 16:19", object.getString("vsid")));
                           }
                           MyAdapter adapter = new MyAdapter(getContext(), list, 2);
                           rcy.setAdapter(adapter);
                       } catch (JSONException e) {
                           e.printStackTrace();
                       }


                   }
               }, null);
               queue.add(request);


    }
}
