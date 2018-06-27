package com.itislevel.lyl.adapter;

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
import com.itislevel.lyl.mvp.model.bean.FamilyListBean;
import com.itislevel.lyl.mvp.model.bean.FamilyPersonListBean;
import com.itislevel.lyl.utils.DateUtil;
import com.itislevel.lyl.utils.SharedPreferencedUtils;

/**
 * desc:功能说明必写
 * user:itisi
 * date:2017/12/12.17:14
 * path:com.itislevel.lyl.adapter.FamilyListAdapter
 **/
public class FamilyPersonListAdapter extends BaseQuickAdapter<FamilyPersonListBean.ListBean,
        BaseViewHolder> {
    String nickname = SharedPreferencedUtils.getStr(Constants.USER_ID);
    public FamilyPersonListAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, FamilyPersonListBean.ListBean item) {

        //   iv_photo tv_name tv_look_count tv_add_username
//        tv_receive_gitcount tv_time

        helper.addOnClickListener(R.id.tv_name);
        helper.addOnClickListener(R.id.tv_look_count);
        helper.addOnClickListener(R.id.tv_add_username);
        helper.addOnClickListener(R.id.tv_receive_gitcount);
        helper.addOnClickListener(R.id.tv_time);
        helper.addOnClickListener(R.id.iv_photo);
        helper.addOnClickListener(R.id.linear_shou);
        helper.addOnClickListener(R.id.linear_liu);
        helper.addOnClickListener(R.id.linear_person);
        helper.addOnClickListener(R.id.tv_delete);
        TextView tv_delete = helper.getView(R.id.tv_delete);
        View view = helper.getView(R.id.tv_del);
        if (view!=null){
            helper.addOnClickListener(R.id.tv_del);
        }
        if(item.getFetename()!=null)
        {
            if((item.getUserid()+"").contains(nickname))
            {
                tv_delete.setVisibility(View.VISIBLE);
            }
        }
        helper.setText(R.id.tv_name, item.getDeadname()+"的祭祀");
        helper.setText(R.id.tv_look_count, ""+item.getLooknum() );
        helper.setText(R.id.tv_add_username, item.getFetename());
        helper.setText(R.id.tv_receive_gitcount, "收到祭品数:"+item.getSacrificenum() );
        helper.setText(R.id.tv_time,"祭祀日期:"+ DateUtil.timeSpanToDate(item.getCreatedtime()));
        helper.setText(R.id.location_name, ""+item.getProvincename()+item.getCityname() );
        String imgestr = item.getImgestr();
        if (imgestr.contains("@")){
            String[] split = imgestr.split("@");

            Glide.with(App.getInstance())
                    .load(Constants.IMG_SERVER_PATH + split[0].split(",")[2])
                    .asBitmap()
                    .error(R.mipmap.family_adderror)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into((ImageView) helper.getView(R.id.iv_photo));

        }else{
            Glide.with(App.getInstance())
                    .load(Constants.IMG_SERVER_PATH + item.getImgestr().split(",")[2])
                    .asBitmap()
                    .error(R.mipmap.family_adderror)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into((ImageView) helper.getView(R.id.iv_photo));

        }

    }


}
