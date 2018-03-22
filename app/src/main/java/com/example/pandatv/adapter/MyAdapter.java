package com.example.pandatv.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pandatv.R;
import com.example.pandatv.view.activity.RadioActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by 本丶小丝 on 2018/3/6.
 */

public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<User_Adapter> list;
    private int type;



    public MyAdapter(Context context, List<User_Adapter> list, int type) {
        this.context = context;
        this.list = list;
        this.type = type;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder = null;
        if (type == 1) {
            View view = LayoutInflater.from(context).inflate(R.layout.holder_one, null);
            holder = new Holder_one(view);
        } else if (type == 2) {
            View view = LayoutInflater.from(context).inflate(R.layout.holder_two, null);
            holder = new Holder_two(view);
        } else if (type == 3) {
            View view = LayoutInflater.from(context).inflate(R.layout.holder_three, null);
            holder = new Holder_Three(view);
        }if (type == 4) {
            View view = LayoutInflater.from(context).inflate(R.layout.holder_one, null);
            holder = new Holder_Four(view);
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (type == 1) {
            Holder_one one = (Holder_one) holder;
            Picasso.with(context).load(list.get(position).getImg()).into((one.imageView));
            one.textView2.setText(list.get(position).getText());
            ((Holder_one) holder).itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, RadioActivity.class);
                    intent.putExtra("type",1);
                    intent.putExtra("url",list.get(position).getUrl());
                    context.startActivity(intent);
                }
            });
        } else if (type == 2) {
            Holder_two one = (Holder_two) holder;
            Picasso.with(context).load(list.get(position).getImg()).into((one.image));
            one.title.setText(list.get(position).getText());
            one.time.setText(list.get(position).getShowtime());
            one.data.setText(list.get(position).getData());
            ((Holder_two) holder).itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, RadioActivity.class);
                    intent.putExtra("type",1);
                    intent.putExtra("url",list.get(position).getUrl());
                    context.startActivity(intent);
                }
            });
        }else if (type == 3) {
            Holder_Three one = (Holder_Three) holder;
            Picasso.with(context).load(list.get(position).getImg()).into((one.img));
            one.textView.setText(list.get(position).getText());
            ((Holder_Three) holder).itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, RadioActivity.class);
                    intent.putExtra("type",1);
                    intent.putExtra("url",list.get(position).getUrl());
                    context.startActivity(intent);
                }
            });
        } else if (type == 4) {
            Holder_Four one = (Holder_Four) holder;
            Picasso.with(context).load(list.get(position).getImg()).into((one.imageView));
            one.textView2.setText(list.get(position).getText());
            ((Holder_Four) holder).itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, RadioActivity.class);
                    intent.putExtra("type",1);
                    intent.putExtra("url",list.get(position).getUrl());
                    context.startActivity(intent);
                }
            });
        }
    }

    //1
    public class Holder_one extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView textView2;

        public Holder_one(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            textView2 = itemView.findViewById(R.id.textView2);
        }
    }

    //2
    public class Holder_two extends RecyclerView.ViewHolder {
        private ImageView image;
        private TextView time;
        private TextView title;
        private TextView data;

        public Holder_two(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.img);
            time = itemView.findViewById(R.id.showtime);
            title = itemView.findViewById(R.id.tit);
            data = itemView.findViewById(R.id.data);
        }
    }

    //3
    public class Holder_Three extends RecyclerView.ViewHolder {
        private ImageView img;
        private TextView textView;

        public Holder_Three(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img);
            textView = itemView.findViewById(R.id.textView);
        }
    }
    //4
    public class Holder_Four extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView textView2;

        public Holder_Four(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            textView2 = itemView.findViewById(R.id.textView2);
        }
    }


    @Override
    public int getItemCount() {
        return list.size();
    }


}
