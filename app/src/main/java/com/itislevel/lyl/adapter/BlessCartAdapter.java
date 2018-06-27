package com.itislevel.lyl.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.itislevel.lyl.R;
import com.itislevel.lyl.app.App;
import com.itislevel.lyl.app.Constants;
import com.itislevel.lyl.mvp.model.bean.BlessCartListBean;
import com.itislevel.lyl.mvp.model.bean.BlessGiftBean;
import com.itislevel.lyl.utils.imageload.ImageLoadConfiguration;
import com.itislevel.lyl.utils.imageload.ImageLoadProxy;
import com.itislevel.lyl.widget.SlidingButtonView;

import butterknife.BindView;

/**
 * desc:功能说明必写
 * user:itisi
 * date:2017/12/21.19:19
 * path:com.itislevel.lyl.adapter.TeamTypeAdapter
 **/
public class BlessCartAdapter extends BaseQuickAdapter<BlessCartListBean.ShopcartlistBean,
        BaseViewHolder> implements SlidingButtonView.IonSlidingButtonListener{
    private SlidingButtonView mMenu = null;
    Activity mActivity;
    public BlessCartAdapter(int layoutResId, Activity activity) {
        super(layoutResId);
        this.mActivity = activity;
    }

    public void clear()
    {
        getData().clear();
        notifyDataSetChanged();
    }
    @Override
    protected void convert(BaseViewHolder helper, BlessCartListBean.ShopcartlistBean item) {
        //  tv_name tv_price iv_del tv_cart_count iv_add

        helper.addOnClickListener(R.id.iv_del);
        helper.addOnClickListener(R.id.iv_add);
        helper.addOnClickListener(R.id.bisniss_delete);

        helper.setText(R.id.tv_name, item.getGoodsname());
        helper.setText(R.id.tv_price, item.getPrice());
        helper.setText(R.id.tv_cart_count, item.getCount()+"");


        AppCompatTextView bisniss_delete = helper.getView(R.id.bisniss_delete);

        LinearLayoutCompat infomation_width_linear = helper.getView(R.id.infomation_width_linear);

        WindowManager wm = (WindowManager) mActivity.getSystemService(Context.WINDOW_SERVICE);
        int width = wm.getDefaultDisplay().getWidth(); //获取屏幕的宽度

        LinearLayoutCompat.LayoutParams linearParams =(LinearLayoutCompat.LayoutParams) infomation_width_linear.getLayoutParams(); //取控件textView当前的布局参数 linearParams.height = 20;// 控件的高强制设成20

        linearParams.width = width;// 控件的宽强制设成30

        infomation_width_linear.setLayoutParams(linearParams);

        ((SlidingButtonView) helper.itemView).setSlidingButtonListener(BlessCartAdapter.this);
        ImageLoadProxy.getInstance()
                .load(new ImageLoadConfiguration.Builder(App.getInstance())
                        .url(Constants.IMG_SERVER_PATH+item.getImgurl())
                        .defaultImageResId(R.mipmap.icon_img_load_pre)
                        .imageView((ImageView) helper.getView(R.id.iv_goods)).build());

    }


    @Override
    public void onMenuIsOpen(View view) {
        mMenu = (SlidingButtonView) view;
    }

    @Override
    public void onDownOrMove(SlidingButtonView slidingButtonView) {
        if(menuIsOpen()){
            if(mMenu != slidingButtonView){
                closeMenu();
            }
        }
    }

    public Boolean menuIsOpen() {
        if(mMenu != null){
            return true;
        }
        return false;
    }
    public void closeMenu() {
        mMenu.closeMenu();
        mMenu = null;
    }

}
