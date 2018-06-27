package com.itislevel.lyl.adapter;

import android.app.Activity;
import android.graphics.Color;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.itislevel.lyl.R;
import com.itislevel.lyl.mvp.model.bean.RepairCityListBean;

import static com.itislevel.lyl.mvp.ui.property.repair.PropertyRepairActivity.lei_name_s;
import static com.itislevel.lyl.mvp.ui.property.repair.PropertyRepairActivity.loaction_name_s;
import static com.itislevel.lyl.mvp.ui.property.repair.PropertyRepairActivity.select_type;

/**
 * desc:功能说明必写
 * user:itisi
 * date:2017/12/5.14:29
 * path:com.itislevel.lyl.adapter.ProvinceAdapter
 **/
public class RepairPopuAdapter extends BaseQuickAdapter<RepairCityListBean, BaseViewHolder> {
    private  Activity mActivity;
    public RepairPopuAdapter(int layoutResId, Activity activity) {
        super(layoutResId);
        openLoadAnimation(BaseQuickAdapter.ALPHAIN);
        this.mActivity = activity;
    }

    @Override
    protected void convert(BaseViewHolder helper, RepairCityListBean item) {
        LinearLayoutCompat frist_linear = helper.getView(R.id.frist_linear);
        AppCompatTextView show_color = helper.getView(R.id.show_color);

        if(select_type==0)//表示的是城市
        {  if(loaction_name_s.equals(item.getS_name()))
            {
                frist_linear.setBackgroundColor(Color.parseColor("#f5f5f5"));
                show_color.setVisibility(View.VISIBLE);
            }
            helper.setText(R.id.qu_name,item.getS_name());
        }else {//表示的是类别
            if(lei_name_s.equals(item.getCatename()))
            {
                frist_linear.setBackgroundColor(Color.parseColor("#f5f5f5"));
                show_color.setVisibility(View.VISIBLE);
            }
            helper.setText(R.id.qu_name,item.getCatename());
        }
    }
}
