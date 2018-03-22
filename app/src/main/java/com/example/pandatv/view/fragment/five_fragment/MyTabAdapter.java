package com.example.pandatv.view.fragment.five_fragment;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.pandatv.R;
import com.example.pandatv.view.activity.ZhiBoActivity;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by 本丶小丝 on 2018/3/16.
 */

public class MyTabAdapter extends RecyclerView.Adapter<MyTabAdapter.Holder> {
    private JSONArray array;
    private Context context;
    private boolean isShow = false;

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.view_five, parent,false);
        Holder holder = new Holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final Holder holder, int position) {
        try {
            final JSONObject jsonObject = array.getJSONObject(position);

            Picasso.with(context).load(jsonObject.getString("image")).into(holder.vd);
            holder.title.setText(jsonObject.getString("title"));
            holder.jiandata.setText(jsonObject.getString("brief"));
            holder.jian.setOnClickListener(new View.OnClickListener() {
                                               @Override
                                               public void onClick(View v) {
                                                   if (isShow) {
                                                       isShow = false;
                                                       holder.jiao.setBackgroundResource(R.color.white);
                                                       holder.jiao.setBackgroundResource(R.drawable.com_facebook_tooltip_blue_bottomnub);
                                                       holder.jiandata.setVisibility(View.GONE);
                                                   } else {
                                                       isShow = true;
                                                       holder.jiandata.setVisibility(View.VISIBLE);
                                                       holder.jiao.setBackgroundResource(R.color.white);
                                                       holder.jiao.setBackgroundResource(R.drawable.com_facebook_tooltip_blue_topnub);
                                                   }
                                               }
                                           }
            );
            holder.vd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, ZhiBoActivity.class);
                    try {
                        String title = jsonObject.getString("id");
                        intent.putExtra("type",2);
                        intent.putExtra("url", title);
                        context.startActivity(intent);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public MyTabAdapter(JSONArray array, Context context) {
        this.array = array;
        this.context = context;
    }

    @Override
    public int getItemCount() {
        return array.length();
    }

    public class Holder extends RecyclerView.ViewHolder {
        private ImageView vd;
        private TextView title;
        private TextView jiao;
        private LinearLayout jian;
        private TextView jiandata;

        public Holder(View view) {
            super(view);
            vd = (ImageView) view.findViewById(R.id.vd);
            title = (TextView) view.findViewById(R.id.title);
            jiao = (TextView) view.findViewById(R.id.jiao);
            jian = (LinearLayout) view.findViewById(R.id.jian);
            jiandata = (TextView) view.findViewById(R.id.jiandata);
        }
    }
}
