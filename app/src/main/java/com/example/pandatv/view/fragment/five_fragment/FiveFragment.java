package com.example.pandatv.view.fragment.five_fragment;


import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.pandatv.R;
import com.example.pandatv.app.MyApp;
import com.example.pandatv.been.AllData;
import com.example.pandatv.been.AllDataDao;
import com.example.pandatv.been.TabData;
import com.example.pandatv.been.TabDataDao;
import com.example.pandatv.view.ViewPagerN;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FiveFragment extends Fragment implements View.OnClickListener {


    private TabLayout tb;
    private TextView add;
    private ViewPagerN vp;
    private RequestQueue queue;
    private AllDataDao allDataDao;
    private TabDataDao tabDataDao;
    private List<String> list;
    private List<Fragment> fragmentList;
    private MyFragmentDapter_Five dapter;
    private TextView exit;
    private TextView text;
    private TextView but;
    private RecyclerView tabrcy;
    private RecyclerView allRcy;
    private PopupWindow window;
    private MyAdapter adapter;
    private MyAllAdapter myAllAdapter;
    private int a = 0;
    private List<AllData> alldata;
    private List<TabData> data;


    public FiveFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        allDataDao = MyApp.getMyApp().getDaoSession().getAllDataDao();
        tabDataDao = MyApp.getMyApp().getDaoSession().getTabDataDao();
        View view = inflater.inflate(R.layout.fragment_five, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        queue = Volley.newRequestQueue(getContext());
        tb = (TabLayout) view.findViewById(R.id.tb);
        add = (TextView) view.findViewById(R.id.add);
        vp = (ViewPagerN) view.findViewById(R.id.vpf);
        add.setOnClickListener(this);
        tb.setupWithViewPager(vp);
        fragmentList = new ArrayList<>();
        list = new ArrayList<>();

        StringRequest request = new StringRequest(StringRequest.Method.GET, "http://www.ipanda.com/kehuduan/PAGE14501775094142282/index.json", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                Table panda = gson.fromJson(response, Table.class);
//                Log.e( "setGuanCha: ", steing);
                List<Table.TablistBean> tablist = panda.getTablist();
                List<Table.AlllistBean> beans = panda.getAlllist();
                ArrayList<Table.AlllistBean> objects = new ArrayList<>();
                objects.addAll(beans);
                int a = 0;
                if (allDataDao.loadAll().size() == 0) {
                    for (int i = 0; i < beans.size(); i++) {
                        for (int j = 0; j < tablist.size(); j++) {
                            if (beans.get(i).getTitle().equals(tablist.get(j).getTitle())) {
                                a = 1;
                                break;
                            }
                        }
                        if (a == 1) {
                            objects.remove(beans.get(i));
                            a = 0;
                        }
                    }
                    for (int i = 0; i < objects.size(); i++) {
                        AllData data = new AllData(objects.get(i).getUrl(), objects.get(i).getTitle());
                        allDataDao.insert(data);
                    }
                    for (int i = 0; i < tablist.size(); i++) {
                        TabData data = new TabData(tablist.get(i).getUrl(), tablist.get(i).getTitle());
                        tabDataDao.insert(data);
                    }
                    setData();
                    dapter = new MyFragmentDapter_Five(getChildFragmentManager(), fragmentList, list);
                    vp.setAdapter(dapter);
                } else {
                    setData();
                    dapter = new MyFragmentDapter_Five(getChildFragmentManager(), fragmentList, list);
                    vp.setAdapter(dapter);
                }

//                Log.e( "onResponse: ", "----------------------------------------------");

            }
        }, null);
        queue.add(request);


    }


    private void setData() {
        List<TabData> tabData = tabDataDao.loadAll();
        for (int i = 0; i < tabData.size(); i++) {
            list.add(tabData.get(i).getTitle());
            fragmentList.add(new Two_FiveFragment(tabData.get(i).getUrl()));
        }
    }

    //展示pop
    public void setTouXiang() {
        View inflate = LayoutInflater.from(getActivity()).inflate(R.layout.pop, null);
        window = new PopupWindow(inflate, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        window.setOutsideTouchable(true);
        window.setBackgroundDrawable(new BitmapDrawable());
        window.showAtLocation(inflate, Gravity.CENTER, 0, 0);
        exit = (TextView) inflate.findViewById(R.id.exit);
        exit.setOnClickListener(this);
        text = (TextView) inflate.findViewById(R.id.text);
        text.setOnClickListener(this);
        but = (TextView) inflate.findViewById(R.id.but);
        but.setOnClickListener(this);
        tabrcy = (RecyclerView) inflate.findViewById(R.id.tabrcy);
        allRcy = (RecyclerView) inflate.findViewById(R.id.allRcy);
        tabrcy.setLayoutManager(new GridLayoutManager(getContext(), 3));
        allRcy.setLayoutManager(new GridLayoutManager(getContext(), 3));
        data = new ArrayList<>();
        alldata = new ArrayList<>();
        for (TabData t : tabDataDao.loadAll()) {
            data.add(new TabData(t.getUrl(), t.getTitle()));
        }

        adapter = new MyAdapter(getContext(), data);
        tabrcy.setAdapter(adapter);
        alldata.addAll(allDataDao.loadAll());
        myAllAdapter = new MyAllAdapter(getContext(), alldata);
        allRcy.setAdapter(myAllAdapter);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add:
                setTouXiang();
                break;
            case R.id.exit:
                window.dismiss();

                list.clear();

                fragmentList.clear();

                setData();
                dapter = new MyFragmentDapter_Five(getChildFragmentManager(), fragmentList, list);
                vp.setAdapter(dapter);
                break;
            case R.id.but:
                if (a == 0) {
                    but.setText("完成");
                    text.setVisibility(View.VISIBLE);
                    setShow();
                    a = 1;
                } else {
                    but.setText("编辑");
                    text.setVisibility(View.INVISIBLE);
                    Log.e("onMove: ", data.get(3).getTitle());
                    upData();
                    setNoShow();
                    a = 0;
                }
                break;
        }
    }

    private void upData() {
        Log.e("onMove: ", data.get(3).getTitle());
        tabDataDao.deleteAll();
        allDataDao.deleteAll();
        for (TabData t : data) {
            tabDataDao.insert(t);
        }

        for (AllData t : alldata) {
            allDataDao.insert(t);
        }

//        data.clear();
//        data.addAll(tabDataDao.loadAll());

    }

    private void setShow() {
        myAllAdapter.setOnClick(new MyAllAdapter.OnClick() {
            @Override
            public void onClick(View view, int id) {


                AllData data2 = alldata.get(id);
                alldata.remove(data2);
                data.add(new TabData(data2.getUrl(), data2.getTitle()));
//                data.clear();
//                data.addAll(tabDataDao.loadAll());
                adapter.notifyDataSetChanged();
//                alldata.clear();
//                alldata.addAll(allDataDao.loadAll());
                myAllAdapter.notifyDataSetChanged();
            }
        });
        adapter.setOnClick(new MyAdapter.OnClickLin() {
            @Override
            public void onClick(View view, int id) {
                adapter.notifyDataSetChanged();
                Log.e("onMove: ", id+"");
//                TabData load = tabDataDao.load(id);
//                tabDataDao.delete(load);
//                allDataDao.insert(new AllData(load.getUrl(), load.getTitle()));
                TabData data2 = data.get(id);
                data.remove(data2);
                alldata.add(new AllData(data2.getUrl(), data2.getTitle()));
//                data.clear();
//                data.addAll(tabDataDao.loadAll());
                adapter.notifyDataSetChanged();
//                alldata.clear();
//                alldata.addAll(allDataDao.loadAll());
                myAllAdapter.notifyDataSetChanged();
            }


        }, true);
        adapter.setLongOnClick(new MyAdapter.OnLongClickLin() {
            @Override
            public void onLongClick(View view) {
                recyclerViewItemHelper();
                Log.e("onMove: ", data.get(3).getTitle());
                adapter.notifyDataSetChanged();
            }
        });
    }

    private void setNoShow() {
        myAllAdapter.setOnClick(new MyAllAdapter.OnClick() {
            @Override
            public void onClick(View view, int id) {
            }
        });
        adapter.setOnClick(new MyAdapter.OnClickLin() {
            @Override
            public void onClick(View view, int id) {

            }

        }, false);
        adapter.setLongOnClick(new MyAdapter.OnLongClickLin() {
            @Override
            public void onLongClick(View view) {

            }
        });
    }

    private void recyclerViewItemHelper() {
        new ItemTouchHelper(new ItemTouchHelper.Callback() {
            /**
             * 确定当前view滑动方向的；--》获取用户对recyclerview的item拖拽方向
             * @param recyclerView
             * @param viewHolder
             * @return
             */
            @Override
            public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {

                int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN
                        | ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
                int swipeFlags = 0;
                return makeMovementFlags(dragFlags, swipeFlags);
            }

            /**
             * 用来处理上一个方法（指定滑动方向）带来的效果，用来move 具体的 view
             * @param recyclerView
             * @param viewHolder
             * @param target
             * @return
             */
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {

                int oldFlag = viewHolder.getAdapterPosition();
                int targetFlag = target.getAdapterPosition();

                if (oldFlag < targetFlag) {
                    for (int i = oldFlag; i < targetFlag; i++) {
                        Collections.swap(data, i, i + 1);
                    }
                } else {
                    //把第三个移动第二个 3--》2
                    for (int i = oldFlag; i > targetFlag; i--) {
                        Collections.swap(data, i, i - 1);
                    }
                }
                adapter.notifyItemMoved(oldFlag, targetFlag);
                adapter.notifyItemRangeChanged(0, data.size());
                Log.e("onMove: ", data.get(3).getTitle());
                return true;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {

            }

            @Override
            public boolean isLongPressDragEnabled() {
                return true;
            }

            @Override
            public void onSelectedChanged(final RecyclerView.ViewHolder viewHolder, int actionState) {
                if (actionState != ItemTouchHelper.ACTION_STATE_IDLE) {
                    viewHolder.itemView.setBackgroundColor(Color.BLUE);

                }

                super.onSelectedChanged(viewHolder, actionState);
            }

            @Override
            public void onChildDrawOver(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
                super.onChildDrawOver(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
            }

            @Override
            public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                super.clearView(recyclerView, viewHolder);
                viewHolder.itemView.setBackgroundColor(0);
            }
        }).attachToRecyclerView(tabrcy);
    }
}
