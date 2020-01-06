package com.bawei.zhangjiafu1707b20200106.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bawei.zhangjiafu1707b20200106.R;
import com.bawei.zhangjiafu1707b20200106.entity.Rigth_Entity;
import com.bawei.zhangjiafu1707b20200106.view.MainActivity;
import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author: 张家辅
 * @date: 2020/01/06
 */
public class Rigth_Adapter extends RecyclerView.Adapter<Rigth_Adapter.MyHoder>{
    Context context;
    List<Rigth_Entity.DataBean> list;
    public Rigth_Adapter(Context context, List<Rigth_Entity.DataBean> data) {
        this.list=data;
        this.context=context;

    }

    @NonNull
    @Override
    public MyHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = View.inflate(context, R.layout.rigth_layout, null);
        MyHoder myHoder = new MyHoder(inflate);
        return myHoder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyHoder holder, int position) {
        holder.text.setText(list.get(position).getGoods_english_name());
        Glide.with(context).load(list.get(position).getGoods_thumb())
                .error(R.mipmap.ic_launcher_round)
                .placeholder(R.mipmap.ic_launcher_round)
                .into(holder.image);


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyHoder extends RecyclerView.ViewHolder {
     @BindView(R.id.right_text)
     TextView text;
     @BindView(R.id.right_image)
     ImageView image;
     public MyHoder(@NonNull View itemView) {
         super(itemView);
         ButterKnife.bind(this,itemView);
     }
 }
}
