package com.itislevel.lyl.adapter;

import android.text.TextUtils;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.itislevel.lyl.R;
import com.itislevel.lyl.app.App;
import com.itislevel.lyl.app.Constants;
import com.itislevel.lyl.mvp.model.bean.BlessDetailGiftListBean;
import com.itislevel.lyl.mvp.model.bean.FamilyBlessListBean;
import com.itislevel.lyl.mvp.model.bean.TestBean;
import com.itislevel.lyl.utils.imageload.ImageLoadConfiguration;
import com.itislevel.lyl.utils.imageload.ImageLoadProxy;

/**
 * desc:功能说明必写
 * user:itisi
 * date:2017/12/21.19:19
 * path:com.itislevel.lyl.adapter.TeamTypeAdapter
 **/
public class TestAdapter extends BaseQuickAdapter<FamilyBlessListBean.ListBean, BaseViewHolder> {

    public TestAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, FamilyBlessListBean.ListBean item) {

        helper.setText(R.id.tv_test,item.getNickname()+":"+item.getWishes());

    }
}
