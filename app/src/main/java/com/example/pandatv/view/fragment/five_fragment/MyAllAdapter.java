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

import java.util.List;

/**
 * Created by 本丶小丝 on 2018/3/13.
 */

public class MyAllAdapter extends RecyclerView.Adapter<MyAllAdapter.Holder> {
    private Context context;
    public List<AllData> tablist;
    private Holder holder;
    private OnClick onClick;


    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.popdata, null);
        holder = new Holder(view);
        return holder;
    }

    public MyAllAdapter() {
    }

    public MyAllAdapter(Context context, List<AllData> tablist) {
        this.context = context;
        this.tablist = tablist;
    }

    @Override
    public void onBindViewHolder(final Holder holder, final int position) {
        holder.title.setText(tablist.get(position).getTitle());
        final AllDataDao alldao = MyApp.getMyApp().getDaoSession().getAllDataDao();
        final TabDataDao dao = MyApp.getMyApp().getDaoSession().getTabDataDao();
        final List<AllData> all = alldao.loadAll();
        final MyAdapter adapter = new MyAdapter();
        holder.title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onClick!=null) {
                    onClick.onClick(holder.itemView, position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return tablist.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        private TextView title;

        public Holder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.datatitle);

        }

    }

    public void setOnClick(OnClick onClick) {
        this.onClick = onClick;
    }

    public interface OnClick {

        void onClick(View view, int id);
    }
}
