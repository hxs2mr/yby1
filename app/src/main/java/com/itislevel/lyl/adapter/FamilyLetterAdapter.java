package com.itislevel.lyl.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.itislevel.lyl.R;
import com.itislevel.lyl.mvp.model.bean.FamilyBlessListRecevieBean;
import com.itislevel.lyl.mvp.model.bean.LetterBean;

import static com.itislevel.lyl.utils.DateUtil.timeSpanToDateTime1;
import static com.itislevel.lyl.utils.DateUtil.timeStrToDateTime;

/**
 * desc:功能说明必写
 * user:itisi
 * date:2017/12/12.17:14
 * path:com.itislevel.lyl.adapter.FamilyListAdapter
 **/
public class FamilyLetterAdapter extends BaseQuickAdapter<LetterBean.ListBean,
        BaseViewHolder> {
    public FamilyLetterAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, LetterBean.ListBean item) {
        //  tv_name  tv_content
        helper.setText(R.id.item_title,item.getTitle());
        helper.setText(R.id.item_looknum,"浏览量"+item.getLooknum());
        helper.setText(R.id.item_time,timeSpanToDateTime1(item.getCreatedtime()));

    }


}
