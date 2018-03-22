package com.example.pandatv.view.fragment.shou_one.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import com.example.pandatv.view.fragment.shou_one.fragment.GsonBeen.Cctv;
import com.example.pandatv.view.fragment.shou_one.fragment.GsonBeen.GuangYing;
import com.example.pandatv.view.fragment.shou_one.fragment.GsonBeen.Panda;
import com.example.pandatv.view.fragment.shou_one.modle.RollBean;
import com.example.pandatv.view.fragment.shou_one.presenter.Persenter;
import com.example.pandatv.view.fragment.shou_one.viewinf.ViewShowInf;
import com.google.gson.Gson;
import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.adapter.StaticPagerAdapter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShouFragment extends Fragment implements ViewShowInf {


    private RollPagerView roll;
    private ImageView img1;
    private TextView text1;
    private RecyclerView rey_one;
    private TextView text2;
    private ImageView img_2;
    private TextView two_one_text;
    private TextView two_one_text2;
    private TextView two_two_text;
    private TextView two_two_text2;
    private RecyclerView rey_two;
    private RollBean one;
    private RequestQueue queue;
    private TextView text3;
    private RecyclerView rey_three;
    private TextView text4;
    private RecyclerView rey_four;
    private TextView text5;
    private RecyclerView rey_five;
    private TextView text6;
    private ImageView image6;
    private TextView textView_6;
    private TextView text7;
    private RecyclerView rey_7;
    private TextView text8;
    private RecyclerView rey_8;

    public ShouFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_shou, container, false);
        initView(view);
        //轮播图
        return view;
    }

    @Override
    public void upData(String str) {
        one = new Gson().fromJson(str, RollBean.class);
//        Toast.makeText(getContext(), str, Toast.LENGTH_SHORT).show();
        setRoll();
        //推荐
        setJinCai();
        //熊猫观察
        setGuanCha();
        //熊猫直播
        setXiongMaoZhiBob();
        //长城直播
        setChangChengZhiBob();
        //直播中国
        setChinaZhiBob();
        //特别策划
        setCeHua();
        //CCTV
        setCctv();
        //光影世界
        setGuangYing();


    }

    private void initView(View view) {
        queue = Volley.newRequestQueue(getContext());
        roll = (RollPagerView) view.findViewById(R.id.roll);
        img1 = (ImageView) view.findViewById(R.id.img1);
        text1 = (TextView) view.findViewById(R.id.text1);
        rey_one = (RecyclerView) view.findViewById(R.id.rey_one);
        text2 = (TextView) view.findViewById(R.id.text2);
        img_2 = (ImageView) view.findViewById(R.id.img_2);
        two_one_text = (TextView) view.findViewById(R.id.two_one_text);
        two_one_text2 = (TextView) view.findViewById(R.id.two_one_text2);
        two_two_text = (TextView) view.findViewById(R.id.two_two_text);
        two_two_text2 = (TextView) view.findViewById(R.id.two_two_text2);
        rey_two = (RecyclerView) view.findViewById(R.id.rey_two);
        new Persenter(this).doSomeThing("http://www.ipanda.com/kehuduan/PAGE14501749764071042/index.json");
        text3 = (TextView) view.findViewById(R.id.text3);
        rey_three = (RecyclerView) view.findViewById(R.id.rey_three);
        text4 = (TextView) view.findViewById(R.id.text4);
        rey_four = (RecyclerView) view.findViewById(R.id.rey_four);
        text5 = (TextView) view.findViewById(R.id.text5);
        rey_five = (RecyclerView) view.findViewById(R.id.rey_five);

        text6 = (TextView) view.findViewById(R.id.text6);
        image6 = (ImageView) view.findViewById(R.id.image6);
        textView_6 = (TextView) view.findViewById(R.id.textView_6);
        text7 = (TextView) view.findViewById(R.id.text7);
        rey_7 = (RecyclerView) view.findViewById(R.id.rey_7);
        text8 = (TextView) view.findViewById(R.id.text8);
        rey_8 = (RecyclerView) view.findViewById(R.id.rey_8);

    }

    //轮播图
    private void setRoll() {
        roll.setPlayDelay(1000);
        final List<ImageView> list = new ArrayList<>();
        List<RollBean.DataBean.BigImgBean> img = one.getData().getBigImg();
        for (int i = 0; i < img.size(); i++) {
            ImageView view = new ImageView(getContext());
            Picasso.with(getContext()).load(img.get(i).getImage()).into(view);
            list.add(view);
        }
        roll.setAdapter(new StaticPagerAdapter() {
            @Override
            public View getView(ViewGroup container, int position) {
                return list.get(position);
            }

            @Override
            public int getCount() {
                return list.size();
            }
        });
    }

    //精彩推荐
    private void setJinCai() {
        ArrayList<User_Adapter> users = new ArrayList<>();
        RollBean.DataBean.AreaBean area = one.getData().getArea();
        Picasso.with(getContext()).load(area.getImage()).into(img1);

        text1.setText(area.getTitle());
        rey_one.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        List<RollBean.DataBean.AreaBean.ListscrollBean> listscroll = area.getListscroll();
        for (int i = 0; i < listscroll.size(); i++) {
            users.add(new User_Adapter(listscroll.get(i).getImage(), listscroll.get(i).getTitle(), listscroll.get(i).getPid()));
        }
        MyAdapter adapter = new MyAdapter(getContext(), users, 1);
        rey_one.setAdapter(adapter);
    }

    //熊猫观察
    private void setGuanCha() {

        RollBean.DataBean.PandaeyeBean pandaeye = one.getData().getPandaeye();
        text2.setText(pandaeye.getTitle());
        List<RollBean.DataBean.PandaeyeBean.ItemsBean> items = pandaeye.getItems();
        two_one_text.setText(items.get(0).getBrief());
        two_one_text2.setText(items.get(0).getTitle());
        two_two_text.setText(items.get(1).getBrief());
        two_two_text2.setText(items.get(1).getTitle());
        rey_two.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

        StringRequest request = new StringRequest(StringRequest.Method.GET, pandaeye.getPandaeyelist(), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                ArrayList<User_Adapter> users = new ArrayList<>();
                Gson gson = new Gson();
                Panda panda = gson.fromJson(response, Panda.class);
//                Log.e( "setGuanCha: ", steing);
                List<Panda.ListBean> list = panda.getList();
                for (int i = 0; i < list.size(); i++) {
                    users.add(new User_Adapter(list.get(i).getImage(), list.get(i).getTitle(), list.get(i).getDaytime(), list.get(i).getUrl(), list.get(i).getVideoLength()));
                }
                MyAdapter adapter = new MyAdapter(getContext(), users, 2);
                rey_two.setAdapter(adapter);
            }
        }, null);
        queue.add(request);

    }

    //熊猫直播
    private void setXiongMaoZhiBob() {
        ArrayList<User_Adapter> users = new ArrayList<>();
        RollBean.DataBean.PandaliveBean pandalive = one.getData().getPandalive();


        text3.setText(pandalive.getTitle());
        rey_three.setLayoutManager(new GridLayoutManager(getContext(), 3));
        List<RollBean.DataBean.PandaliveBean.ListBean> list = pandalive.getList();
        for (int i = 0; i < list.size(); i++) {
            users.add(new User_Adapter(list.get(i).getImage(), list.get(i).getTitle(), list.get(i).getVid()));
        }
        MyAdapter adapter = new MyAdapter(getContext(), users, 3);
        rey_three.setAdapter(adapter);
    }

    //长城直播
    private void setChangChengZhiBob() {
        ArrayList<User_Adapter> users = new ArrayList<>();
        RollBean.DataBean.WallliveBean walllive = one.getData().getWalllive();


        text4.setText(walllive.getTitle());
        rey_four.setLayoutManager(new GridLayoutManager(getContext(), 3));
        List<RollBean.DataBean.WallliveBean.ListBeanX> list = walllive.getList();
        for (int i = 0; i < list.size(); i++) {
            users.add(new User_Adapter(list.get(i).getImage(), list.get(i).getTitle(), list.get(i).getVid()));
        }
        MyAdapter adapter = new MyAdapter(getContext(), users, 3);
        rey_four.setAdapter(adapter);
    }

    //直播中国
    private void setChinaZhiBob() {
        ArrayList<User_Adapter> users = new ArrayList<>();
        RollBean.DataBean.ChinaliveBean chinalive = one.getData().getChinalive();
        text5.setText(chinalive.getTitle());
        rey_five.setLayoutManager(new GridLayoutManager(getContext(), 3));
        List<RollBean.DataBean.ChinaliveBean.ListBeanXX> list = chinalive.getList();
        for (int i = 0; i < list.size(); i++) {
            users.add(new User_Adapter(list.get(i).getImage(), list.get(i).getTitle(), list.get(i).getVid()));
        }
        MyAdapter adapter = new MyAdapter(getContext(), users, 3);
        rey_five.setAdapter(adapter);
    }

    //特别策划
    private void setCeHua() {
        RollBean.DataBean.InteractiveBean interactive = one.getData().getInteractive();
        List<RollBean.DataBean.InteractiveBean.InteractiveoneBean> beans = interactive.getInteractiveone();
        text6.setText(interactive.getTitle());
        textView_6.setText(beans.get(0).getTitle());
        Picasso.with(getContext()).load(beans.get(0).getImage()).into(image6);
    }

    //CCTV
    private void setCctv() {
        ArrayList<User_Adapter> users = new ArrayList<>();
        RollBean.DataBean.CctvBean cctv = one.getData().getCctv();
        text7.setText(cctv.getTitle());
        rey_7.setLayoutManager(new GridLayoutManager(getContext(), 2));
        StringRequest request = new StringRequest(StringRequest.Method.GET, cctv.getListurl(), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                ArrayList<User_Adapter> users = new ArrayList<>();
                Gson gson = new Gson();
                Cctv panda = gson.fromJson(response, Cctv.class);
//                Log.e( "setGuanCha: ", steing);
                List<Cctv.ListBean> list = panda.getList();
                for (int i = 0; i < list.size(); i++) {
                    users.add(new User_Adapter(list.get(i).getImage(), list.get(i).getTitle(), list.get(i).getVid()));
                }
                MyAdapter adapter = new MyAdapter(getContext(), users, 4);
                rey_7.setAdapter(adapter);
            }
        }, null);
        queue.add(request);
    }
    //光影中国
    private void setGuangYing(){
        List<RollBean.DataBean.ListBeanXXX> list = one.getData().getList();
        text8.setText(list.get(0).getTitle());
        rey_8.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

        StringRequest request = new StringRequest(StringRequest.Method.GET, list.get(0).getListUrl(), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                ArrayList<User_Adapter> users = new ArrayList<>();
                Gson gson = new Gson();
                GuangYing panda = gson.fromJson(response, GuangYing.class);
//                Log.e( "setGuanCha: ", steing);
                List<GuangYing.ListBean> list1 = panda.getList();
                for (int i = 0; i < list1.size(); i++) {
                    users.add(new User_Adapter(list1.get(i).getImage(), list1.get(i).getTitle(), list1.get(i).getDaytime(), list1.get(i).getUrl(), list1.get(i).getVideoLength()));
                }
                MyAdapter adapter = new MyAdapter(getContext(), users, 2);
                rey_8.setAdapter(adapter);
            }
        }, null);
        queue.add(request);
    }
}

