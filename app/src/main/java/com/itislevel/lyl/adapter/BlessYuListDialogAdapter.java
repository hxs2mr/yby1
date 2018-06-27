package com.itislevel.lyl.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.itislevel.lyl.R;
import com.itislevel.lyl.mvp.model.bean.FamilyBlessListBean;
import com.itislevel.lyl.mvp.model.bean.FamilySacrificeTypeBean;
import com.itislevel.lyl.mvp.model.bean.TestBean;

import java.util.List;

/**
 * desc:功能说明必写
 * user:itisi
 * date:2018-02-23.11:29
 * path:com.itislevel.lyl.adapter.BlessYuListDialogAdapter
 **/
public class BlessYuListDialogAdapter extends BaseQuickAdapter<FamilyBlessListBean.ListBean,
        BaseViewHolder> {


    public BlessYuListDialogAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, FamilyBlessListBean.ListBean item) {

        helper.addOnClickListener(R.id.tv_name);
        helper.setText(R.id.tv_name,item.getNickname()+":"+item.getWishes());
    }
}
