package com.itislevel.lyl.adapter;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.View;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.itislevel.lyl.R;
import com.itislevel.lyl.adapter.recyclew.ItemType;
import com.itislevel.lyl.adapter.recyclew.MultipViewHolder;
import com.itislevel.lyl.adapter.recyclew.MultipleItemEntity;
import com.itislevel.lyl.app.Constants;
import com.itislevel.lyl.mvp.model.bean.PropretyNoticeBean;
import com.itislevel.lyl.mvp.ui.main.home.HomeAdapter.MultipleRecyclerAdapter;
import com.itislevel.lyl.mvp.ui.main.home.HomeModel.HomeFields;
import com.itislevel.lyl.utils.SAToast;

import java.util.List;

import butterknife.BindView;
import de.hdodenhof.circleimageview.CircleImageView;

import static com.itislevel.lyl.utils.DateUtil.timeSpanToDate;

/**
 * Created by Administrator on 2018\5\24 0024.
 */

public class PropretyHomeAdapter extends MultipleRecyclerAdapter{
    private Context mContext;
    private CircleImageView p_image1;
    private CircleImageView p_image2;
    private AppCompatTextView p_title1;
    private AppCompatTextView p_title2;
    private AppCompatTextView p_comment1;
    private AppCompatTextView p_comment2;
    private AppCompatTextView p_time1;
    private AppCompatTextView p_time2;
    private     LinearLayoutCompat p_ton_linear1;
    private     LinearLayoutCompat p_ton_linear2;
    private    AppCompatTextView p_all_ton;
    private  LinearLayoutCompat linear_ton;
    private LinearLayoutCompat wu_ton_linear;
    public PropretyHomeAdapter(List<MultipleItemEntity> data,Context context) {
        super(data);
        this.mContext = context;
        addItemType(ItemType.PROPRETY_ITEM, R.layout.proprety_item);
        //设置宽度监听
        setSpanSizeLookup(this);
        openLoadAnimation(BaseQuickAdapter.SLIDEIN_BOTTOM);
    }

    public static PropretyHomeAdapter create(List<MultipleItemEntity> data, Context context){
        return new PropretyHomeAdapter(data,context);
    }

    @Override
    protected void convert(MultipViewHolder helper, MultipleItemEntity item) {
        super.convert(helper, item);
        switch (helper.getItemViewType())
        {
            case ItemType.PROPRETY_ITEM:
                 helper.addOnClickListener(R.id.p_all_ton);
                 helper.addOnClickListener(R.id.p_ton_linear1);
                 helper.addOnClickListener(R.id.p_ton_linear2);
                 p_ton_linear1 = helper.getView(R.id.p_ton_linear1);
                 p_ton_linear2 = helper.getView(R.id.p_ton_linear2);
                 p_image1= helper.getView(R.id.p_image1);
                 p_image2= helper.getView(R.id.p_image2);
                 p_title1= helper.getView(R.id.p_title1);
                 p_title2= helper.getView(R.id.p_title2);
                 p_comment1= helper.getView(R.id.p_comment1);
                 p_comment2= helper.getView(R.id.p_comment2);
                 p_time1= helper.getView(R.id.p_time1);
                 p_time2= helper.getView(R.id.p_time2);
                 p_all_ton= helper.getView(R.id.p_all_ton);
                 linear_ton= helper.getView(R.id.linear_ton);
                 wu_ton_linear= helper.getView(R.id.wu_ton_linear);
                List<PropretyNoticeBean.ListBean> list = null;
                if(item.getField(HomeFields.PROPRE_LIST)!=null)
                {
                    list = item.getField(HomeFields.PROPRE_LIST);
                    if(list.size()>=2)
                    {
                        p_ton_linear1.setVisibility(View.VISIBLE);
                        p_ton_linear2.setVisibility(View.VISIBLE);
                        wu_ton_linear.setVisibility(View.GONE);
                        linear_ton.setVisibility(View.VISIBLE);
                        init_two(list);
                        p_all_ton.setText("查看更多通知("+list.size()+")");
                    }else if(list.size()==1){
                        p_ton_linear1.setVisibility(View.VISIBLE);
                        p_ton_linear2.setVisibility(View.GONE);
                        wu_ton_linear.setVisibility(View.GONE);
                        linear_ton.setVisibility(View.VISIBLE);
                        init_one(list);
                        p_all_ton.setVisibility(View.GONE);
                    }else {
                        p_all_ton.setVisibility(View.GONE);
                        wu_ton_linear.setVisibility(View.VISIBLE);
                        linear_ton.setVisibility(View.GONE);
                    }
                }else {
                    p_all_ton.setVisibility(View.GONE);
                    wu_ton_linear.setVisibility(View.VISIBLE);
                    linear_ton.setVisibility(View.GONE);
                }
                break;

                default:
                    break;
        }
    }
    private void init_two(List<PropretyNoticeBean.ListBean> bean){
        p_comment1.setText(bean.get(0).getContent());
        p_comment2.setText(bean.get(1).getContent());
        Glide.with(mContext)
                .load(Constants.IMG_SERVER_PATH +bean.get(0).getHeadurl())
                .asBitmap()
                .error(R.mipmap.person_head)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(p_image1);
        Glide.with(mContext)
                .load(Constants.IMG_SERVER_PATH +bean.get(1).getHeadurl())
                .asBitmap()
                .error(R.mipmap.person_head)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(p_image2);
        p_title1.setText(bean.get(0).getTitile());
        p_title2.setText(bean.get(1).getTitile());
        p_time1.setText(timeSpanToDate(bean.get(0).getCreatedtime()));
        p_time2.setText(timeSpanToDate(bean.get(1).getCreatedtime()));
    }
    private void init_one(List<PropretyNoticeBean.ListBean> list ){
        p_comment1.setText(list.get(0).getContent());
        Glide.with(mContext)
                .load(Constants.IMG_SERVER_PATH +list.get(0).getHeadurl())
                .asBitmap()
                .error(R.mipmap.person_head)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(p_image1);
        p_title1.setText(list.get(0).getTitile());
        p_time1.setText(timeSpanToDate(list.get(0).getCreatedtime()));
    }
}
