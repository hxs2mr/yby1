package com.itislevel.lyl.mvp.ui.location;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.itislevel.lyl.R;
import com.itislevel.lyl.mvp.model.bean.Location_Bean;

import java.util.ArrayList;
import java.util.List;

/**
 */

public class TreeAdapter extends RecyclerView.Adapter<BaseTelecomHolder> {

    private Context context;
    List<Location_Bean> list_data ;
    private LayoutInflater mInflater;
    private OnScrollListener mOnScrollListener;
    private Activity mActivity;

    public TreeAdapter(Context context,  List<Location_Bean> data,Activity activity) {
        this.context = context;
        this.list_data = data;
        this.mInflater = LayoutInflater.from(context);
        this.mActivity =activity;

    }

    public void notifyDataSetChanged(  List<Location_Bean> data) {
        this.list_data = data;
        notifyDataSetChanged();
    }

    @Override
    public BaseTelecomHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        switch (viewType) {
            case TreeBean.ONELIST:
                view = mInflater.inflate(R.layout.location_trunk_item, parent, false);
                return new TrunkHolder(context, view);
            case TreeBean.TWOLIST:
                view = mInflater.inflate(R.layout.location_leaf_item, parent, false);
                return new LeafViewHolder(context, view,mActivity);
            default:
                view = mInflater.inflate(R.layout.location_trunk_item, parent, false);
                return new TrunkHolder(context, view);
        }
    }

    @Override
    public void onBindViewHolder(BaseTelecomHolder holder, int position) {
        switch (getItemViewType(position)) {
            case TreeBean.ONELIST:
                TrunkHolder parentViewHolder = (TrunkHolder) holder;
                parentViewHolder.bindView(list_data.get(position), position, itemClickListener);
                break;
            case TreeBean.TWOLIST:
                LeafViewHolder childViewHolder = (LeafViewHolder) holder;
                childViewHolder.bindView(list_data.get(position), position );
                break;
        }

    }

    /**
     * 滚动监听接口
     */
    public interface OnScrollListener {
        void scrollTo(int pos);
    }

    public void setOnScrollListener(OnScrollListener onScrollListener) {
        this.mOnScrollListener = onScrollListener;
    }


    @Override
    public int getItemCount() {
        return list_data.size();
    }

    @Override
    public int getItemViewType(int position) {
        return list_data.get(position).getType();
    }

    private ItemClickListener itemClickListener = new ItemClickListener() {

        @Override
        public void onExpandChildren(Location_Bean bean) {
            int position = list_data.lastIndexOf(bean);//确定当前点击的item位置
            List<Location_Bean> rdblist = getLeafData(list_data.get(position).getList_city().size(),position);
            list_data.get(position).setCitylist(rdblist);
            //获取要展示的子布局数据对象，注意区分onHideChildren方法中的getChildBean()。
            if (rdblist == null || rdblist.size() == 0) {
                return;
            }
            for (int i = rdblist.size() - 1; i > -1; i--) {

                add(rdblist.get(i), position + 1);//在当前的item下方插入
            }
            if (position == list_data.size() - 2 && mOnScrollListener != null) { //如果点击的item为最后一个
                mOnScrollListener.scrollTo(position + rdblist.size());//向下滚动，使子布局能够完全展示
            }
        }

        @Override
        public void onHideChildren(Location_Bean bean) {
            int position = list_data.lastIndexOf(bean);//确定当前点击的item位置
            List<Location_Bean> children = bean.getCitylist();//获取子布局对象

            if (children == null) {
                return;
            }
            for (int i = 1; i < children.size() + 1; i++) {

                remove(position + 1);//删除
            }
            if (mOnScrollListener != null) {
                mOnScrollListener.scrollTo(position);
            }
        }
    };

    @NonNull
    private List<Location_Bean> getLeafData(int size,int postion) {
        List<Location_Bean> rdblist = new ArrayList<>();
        for (int j = 0; j < size; j++) {
            Location_Bean rdb = new Location_Bean();
            rdb.setLeaf1(list_data.get(postion).getList_city().get(j).getS_name());
            rdb.setLeaf2(list_data.get(postion).getList_city().get(j).getId()+"");
            rdb.setType(Location_Bean.TWOLIST);
            rdblist.add(rdb);
        }
        return rdblist;
    }

    /**
     * 在父布局下方插入一条数据
     *
     * @param bean
     * @param position
     */
    public void add(Location_Bean bean, int position) {
        notifyItemInserted(position);
        list_data.add(position, bean);
    }

    /**
     * 移除子布局数据
     *
     * @param position
     */
    protected void remove(int position) {
        list_data.remove(position);
        notifyItemRemoved(position);
    }


}
