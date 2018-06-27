package com.itislevel.lyl.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.itislevel.lyl.R;
import com.itislevel.lyl.mvp.model.bean.AddressBean;

import java.util.List;

/**
 * desc:功能说明必写
 * user:itisi
 * date:2017/12/5.14:29
 * path:com.itislevel.lyl.adapter.ProvinceAdapter
 **/
public class ReasonAdapter extends RecyclerView.Adapter<ReasonAdapter.ProvinceHolder> {
    private List<String> list;//要显示的数据
    private Context context;//创建视图时需要

    private OnItemClickListener clickListener;

    public interface OnItemClickListener {

        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);
    }

    public void setClickListener(OnItemClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public ReasonAdapter(List<String> list, Context context) {
        this.list = list;
        this.context = context;
    }

    /**
     * 创建视图
     */
    @Override
    public ProvinceHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_reason,parent,false);

        //实例化MainViewHolder---- 传View过去
        ProvinceHolder holder = new ProvinceHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ProvinceHolder holder, final int position) {

        holder.tv_name.setText(list.get(position));
        if (clickListener!=null){
            holder.tv_name.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickListener.onItemClick(v,position);
//                    holder.tv_name.setTextColor(context.getResources().getColor(R.color.colorBtn));
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

   public class ProvinceHolder extends RecyclerView.ViewHolder{
       TextView tv_name;
        public ProvinceHolder(View itemView) {
            super(itemView);
            //初始化控件
            tv_name = (TextView)itemView.findViewById(R.id.tv_reason);

        }
    }
}
