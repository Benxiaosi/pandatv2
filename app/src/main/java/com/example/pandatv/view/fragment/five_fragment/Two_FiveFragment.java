package com.example.pandatv.view.fragment.five_fragment;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.pandatv.R;
import com.example.pandatv.been.AllData;
import com.example.pandatv.been.TabData;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class Two_FiveFragment extends Fragment {
    private String url;
    private RecyclerView tabrcy;
    private RequestQueue queue;

    @SuppressLint("ValidFragment")
    public Two_FiveFragment(String url) {
        this.url = url;
    }

    public Two_FiveFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_two__five, container, false);
        initView(inflate);
        return inflate;
    }

    private void initView(View inflate) {
        queue = Volley.newRequestQueue(getContext());
        tabrcy = (RecyclerView) inflate.findViewById(R.id.tabrcy);
        StringRequest request = new StringRequest(StringRequest.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject object = new JSONObject(response);
                    JSONArray live = object.getJSONArray("live");
                    MyTabAdapter adapter = new MyTabAdapter(live, getContext());
                    tabrcy.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
                    tabrcy.setAdapter(adapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

//                Log.e( "onResponse: ", "----------------------------------------------");

            }
        }, null);
        queue.add(request);
    }
}
