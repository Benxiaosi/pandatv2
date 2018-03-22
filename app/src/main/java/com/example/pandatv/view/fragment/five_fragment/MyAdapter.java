package com.example.pandatv.view.fragment.five_fragment;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.pandatv.R;
import com.example.pandatv.app.MyApp;
import com.example.pandatv.been.AllData;
import com.example.pandatv.been.AllDataDao;
import com.example.pandatv.been.TabData;
import com.example.pandatv.been.TabDataDao;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 本丶小丝 on 2018/3/13.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.Holder> {
    private Context context;
    public List<TabData> tablist;
    private OnClickLin onClick;

    public View itemview;
    private Holder holder;
    private List<TextView> textViews = new ArrayList<>();
private OnLongClickLin onLongClick;

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.popdata, null);
        holder = new Holder(view);
        return holder;
    }

    public MyAdapter(Context context, List<TabData> tablist) {
        this.context = context;
        this.tablist = tablist;
    }

    public MyAdapter() {

    }

    @Override
    public void onBindViewHolder(Holder holder, final int position) {
        holder.title.setText(tablist.get(position).getTitle());
        final AllDataDao alldao = MyApp.getMyApp().getDaoSession().getAllDataDao();
        final TabDataDao dao = MyApp.getMyApp().getDaoSession().getTabDataDao();
        final List<TabData> all = dao.loadAll();
        final MyAllAdapter adapter = new MyAllAdapter();
        textViews.add(holder.shan);
        holder.title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onClick != null) {
                    onClick.onClick(v, position);
                }

            }
        });
        holder.title.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (onLongClick != null) {
                    onLongClick.onLongClick(v);
                }

                return false;
            }
        });
    }

    //    public void setOnClick(boolean click){
//        if (holder!=null) {
//            holder.title.setClickable(click);
//            if (click){
//                holder.shan.setVisibility(View.VISIBLE);
//            }else {
//                holder.shan.setVisibility(View.GONE);
//            }
//
//        }
//    }
    @Override
    public int getItemCount() {
        return tablist.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        private TextView title;
        public TextView shan;

        public Holder(View itemView) {
            super(itemView);
            itemview = itemView;
            title = itemView.findViewById(R.id.datatitle);
            shan = itemView.findViewById(R.id.shan);
        }
    }

    public void setOnClick(OnClickLin onClick,Boolean show) {
        this.onClick = onClick;
        if (show) {
            for (int i = 0; i < textViews.size(); i++) {
                textViews.get(i).setVisibility(View.VISIBLE);
            }
        }else {
            for (int i = 0; i < textViews.size(); i++) {
                textViews.get(i).setVisibility(View.GONE);
            }
        }

    }
    public void setLongOnClick(OnLongClickLin onClick) {
       this.onLongClick = onClick;
    }

    public interface OnClickLin {
        void onClick(View view, int id);

    }
    public interface OnLongClickLin {

        void onLongClick(View view);
    }
}
