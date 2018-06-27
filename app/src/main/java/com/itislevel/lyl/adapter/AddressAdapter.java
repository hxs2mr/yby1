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
public class AddressAdapter extends RecyclerView.Adapter<AddressAdapter.ProvinceHolder> {
    private List<AddressBean> list;//要显示的数据
    private Context context;//创建视图时需要

    private OnItemClickListener clickListener;

    public interface OnItemClickListener {

        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);
    }

    public void setClickListener(OnItemClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public AddressAdapter(List<AddressBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    /**
     * 创建视图
     */
    @Override
    public ProvinceHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_province,parent,false);


        //实例化MainViewHolder---- 传View过去
        ProvinceHolder holder = new ProvinceHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ProvinceHolder holder, final int position) {
        AddressBean addressBean = list.get(position);

        String name = addressBean.getName();
        //控件中设置值
//        if (addressBean.getName().startsWith("内蒙古自治区")){
//            name="内蒙古";
//        }else if(addressBean.getName().startsWith("广西壮族自治区")){
//            name="广西省";
//        }else if(addressBean.getName().startsWith("西藏自治区")){
//            name="西藏";
//        }else if(addressBean.getName().startsWith("宁夏回族自治区")){
//            name="宁夏";
//        }else if(addressBean.getName().startsWith("新疆维吾尔自治区")){
//            name="新疆";
//        }
        holder.tv_name.setText(name);
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
            tv_name = (TextView)itemView.findViewById(R.id.tv_name);

        }
    }
}
