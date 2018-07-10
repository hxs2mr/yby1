package com.itislevel.lyl.adapter;

import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.itislevel.lyl.R;
import com.itislevel.lyl.app.App;
import com.itislevel.lyl.app.Constants;
import com.itislevel.lyl.mvp.model.bean.FamilyPersonListBean;
import com.itislevel.lyl.mvp.model.bean.ShanHomeBean;
import com.itislevel.lyl.utils.DateUtil;
import com.itislevel.lyl.utils.SharedPreferencedUtils;

/**
 * desc:功能说明必写
 * user:itisi
 * date:2017/12/12.17:14
 * path:com.itislevel.lyl.adapter.FamilyListAdapter
 **/
public class ShanListAdapter extends BaseQuickAdapter<ShanHomeBean.PageBeanBean.ListBean,
        BaseViewHolder> {
    String nickname = SharedPreferencedUtils.getStr(Constants.USER_ID);
    public ShanListAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper,ShanHomeBean.PageBeanBean.ListBean item) {
        helper.setText(R.id.shan_name, item.getShopname());
        helper.setText(R.id.shan_number, item.getTradenum());
        helper.setText(R.id.shan_qi_number, item.getPeriods() + "");
        helper.setText(R.id.shan_total_monkey, item.getPeriodlimit() + "");
        helper.setText(R.id.shan_qi_monkey, item.getPerperiodlimit() + "");
        helper.setText(R.id.shan_qi_time, item.getPeriodstarttime() + "至" + item.getPeriodendtime());

        AppCompatTextView shan_type = helper.getView(R.id.shan_type);   //分期的状态
        AppCompatTextView shan_shengyu = helper.getView(R.id.shan_shengyu); //剩余多少期未领取

    }
}
