package com.itislevel.lyl.mvp.ui.chatkeyboardview;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


import com.itislevel.lyl.R;
import com.itislevel.lyl.utils.ScreenUtils;

import java.util.List;

/**
 * **********************
 * 功 能:底部横向滚动tab适配器
 * 创建人:itisi
 * 邮  箱:itisivip@qq.com
 * 创建时间:2017/6/23 16:32
 * 修改人:itisi
 * 修改时间: 2017/6/23 16:32
 * 修改内容:itisi
 * *********************
 */

public class  HorizontalRecyclerviewAdapter  extends RecyclerView.Adapter<HorizontalRecyclerviewAdapter.ViewHolder>  {

    private List<ImageEmotionModel> datas;;
    private Context mContext;
    private LayoutInflater mInflater;
    private OnClickItemListener mOnClickItemListener;

//    public OnClickItemListener getOnClickItemListener() {
//        return mOnClickItemListener;
//    }

    public void setOnClickItemListener(OnClickItemListener onClickItemListener) {
        mOnClickItemListener = onClickItemListener;
    }

    public HorizontalRecyclerviewAdapter(FragmentActivity context, List<ImageEmotionModel> datas) {
        this.datas = datas;
        mContext = context;
        mInflater= LayoutInflater.from(mContext);
    }

    @Override
    public HorizontalRecyclerviewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=mInflater.inflate(R.layout.item_rv_horizontal,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final HorizontalRecyclerviewAdapter.ViewHolder holder, int position) {
        ImageEmotionModel model = datas.get(position);
        /**
         * 处理点击事件和长按事件
         */
        if (mOnClickItemListener!=null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //使用该方法获取position，防止点击事件时pos未刷新问题
                    int pos = holder.getLayoutPosition();
                    mOnClickItemListener.onItemClick(holder.itemView, pos,datas);
                }
            });
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    //使用该方法获取position，防止点击事件时pos未刷新问题
                    int pos = holder.getLayoutPosition();
                    mOnClickItemListener.onItemLongClick(holder.itemView, pos,datas);
                    return false;
                }
            });
        }
        /**
         * 动态计算底部tab的宽度。
         */
        int width= ScreenUtils.getScreenWidth(mContext);
        float itemW=width/6;
        ViewGroup.LayoutParams lp=  holder.imageBtn.getLayoutParams();
        lp.width= (int) itemW;

        //设置icon
        holder.imageBtn.setImageDrawable(model.icon);
        if(model.isSelected){
            holder.imageBtn.setBackgroundColor(mContext.getResources().getColor(R.color.bg_horizontal_btn_selected));
        }else {
            holder.imageBtn.setBackgroundColor(mContext.getResources().getColor(R.color.bg_horizontal_btn_normal));
        }
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView imageBtn;
        public ViewHolder(View itemView) {
            super(itemView);
            imageBtn= (ImageView) itemView.findViewById(R.id.image_btn);
        }
    }

    /**
     * RecyclerView点击事件接口
     */
    public interface OnClickItemListener{
        void onItemClick(View view, int position, List<ImageEmotionModel> datas);
        void onItemLongClick(View view, int position, List<ImageEmotionModel> datas);
    }
}
