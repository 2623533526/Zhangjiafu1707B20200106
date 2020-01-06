package com.bawei.zhangjiafu1707b20200106.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bawei.zhangjiafu1707b20200106.R;
import com.bawei.zhangjiafu1707b20200106.Servier.MySerice;
import com.bawei.zhangjiafu1707b20200106.entity.Left_Entity;
import com.bawei.zhangjiafu1707b20200106.view.MainActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author: 张家辅
 * @date: 2020/01/06
 */
public class Lefta_Adapter extends RecyclerView.Adapter<Lefta_Adapter.MyHoder>{
     Context context;
    List<String> list;
    public Lefta_Adapter(MainActivity mainActivity, List<String> category) {
        this.context=mainActivity;
        this.list=category;
    }


    @NonNull
    @Override
    public MyHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = View.inflate(context, R.layout.left_layout, null);
        MyHoder myHoder = new MyHoder(inflate);
        return myHoder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyHoder holder, int position) {
        holder.text.setText(list.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                leftCallBack.success(list.get(position));
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyHoder extends RecyclerView.ViewHolder {
     @BindView(R.id.left_text)
     TextView text;
     public MyHoder(@NonNull View itemView) {
         super(itemView);
         ButterKnife.bind(this,itemView);
     }
 }
 leftCallBack leftCallBack;

    public void setLeftCallBack(Lefta_Adapter.leftCallBack leftCallBack) {
        this.leftCallBack = leftCallBack;
    }
    public interface leftCallBack{
        void success(String name);
 }
}
