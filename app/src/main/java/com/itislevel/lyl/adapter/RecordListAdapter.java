package com.itislevel.lyl.adapter;

import android.app.Activity;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.itislevel.lyl.R;
import com.itislevel.lyl.app.App;
import com.itislevel.lyl.app.Constants;
import com.itislevel.lyl.mvp.model.bean.FamilyListBean;
import com.itislevel.lyl.mvp.model.bean.ListFollowItemBean;
import com.itislevel.lyl.mvp.model.bean.PayLuBean;
import com.itislevel.lyl.mvp.model.bean.RecordBean;
import com.itislevel.lyl.utils.DateUtil;
import com.itislevel.lyl.utils.SharedPreferencedUtils;

import java.util.List;

import static com.itislevel.lyl.mvp.ui.main.dynamic.childfragment.dfind.DFindFragment.DFindcommentItemListener;
import static com.itislevel.lyl.mvp.ui.property.payrecord.PropertyPayRecordActivity.payRecordListener;
import static com.itislevel.lyl.utils.DateUtil.monthToDate;

/**
 * desc:功能说明必写
 * user:itisi
 * date:2017/12/12.17:14
 * path:com.itislevel.lyl.adapter.FamilyListAdapter
 **/
public class RecordListAdapter extends BaseQuickAdapter<PayLuBean, BaseViewHolder> {
    private Activity mActivity;
    public RecordListAdapter(int layoutResId, Activity activity) {
        super(layoutResId);
        this.mActivity = activity;
    }

    @Override
    protected void convert(BaseViewHolder helper,PayLuBean item) {
            RecyclerView recyclerView= helper.getView(R.id.recyclerview);

        List<PayLuBean.ListBean> comments = item.getList();
        LinearLayoutManager layoutManager = new LinearLayoutManager(mActivity);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        if (comments != null && comments.size() > 0){
            RecordNewListAdapter  adapter = new RecordNewListAdapter(R.layout.item_newrecode, mActivity);
            adapter.setEnableLoadMore(false);
            adapter.setOnItemClickListener(payRecordListener);
            adapter.setOnItemChildClickListener(payRecordListener);
            adapter.setNewData(comments);
            recyclerView.setAdapter(adapter);
        }else {
            RecordNewListAdapter  adapter1 = new RecordNewListAdapter(R.layout.item_comment, mActivity);
            adapter1.setEnableLoadMore(false);
            recyclerView.setAdapter(adapter1);
        }
        //   iv_photo tv_name tv_look_count tv_add_username
//        tv_receive_gitcount tv_time
    }
}
