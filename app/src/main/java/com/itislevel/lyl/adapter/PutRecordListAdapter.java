package com.itislevel.lyl.adapter;

import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.itislevel.lyl.R;
import com.itislevel.lyl.mvp.model.bean.PayLuBean;

import java.util.ArrayList;
import java.util.List;

import static com.itislevel.lyl.mvp.ui.property.payrecord.PropertyPayRecordActivity.payRecordListener;
import static com.itislevel.lyl.mvp.ui.usermonkey.putrecord.PutRecordActivity.putRecordListener;

/**
 * desc:功能说明必写
 * user:itisi
 * date:2017/12/12.17:14
 * path:com.itislevel.lyl.adapter.FamilyListAdapter
 **/
public class PutRecordListAdapter extends BaseQuickAdapter<PayLuBean, BaseViewHolder> {
    private Activity mActivity;
    public PutRecordListAdapter(int layoutResId, Activity activity) {
        super(layoutResId);
        this.mActivity = activity;
    }

    @Override
    protected void convert(BaseViewHolder helper,PayLuBean item) {
            RecyclerView recyclerView= helper.getView(R.id.recyclerview);

        List<String> comments = new ArrayList<>();
        comments.add("");
        comments.add("");
        comments.add("");
        comments.add("");
        comments.add("");

        LinearLayoutManager layoutManager = new LinearLayoutManager(mActivity);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        if (comments != null && comments.size() > 0){
            PutRecordItemListAdapter  adapter = new PutRecordItemListAdapter(R.layout.item_putitemrecord, mActivity);
            adapter.setEnableLoadMore(false);
            adapter.setOnItemClickListener(putRecordListener);
            adapter.setNewData(comments);
            recyclerView.setAdapter(adapter);
        }else {
            RecordNewListAdapter  adapter1 = new RecordNewListAdapter(R.layout.item_comment, mActivity);
            adapter1.setEnableLoadMore(false);
            recyclerView.setAdapter(adapter1);
        }
    }
}
