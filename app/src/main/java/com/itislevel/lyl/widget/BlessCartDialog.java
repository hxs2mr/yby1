package com.itislevel.lyl.widget;

import android.animation.ObjectAnimator;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.flyco.animation.BaseAnimatorSet;
import com.flyco.dialog.widget.base.BottomBaseDialog;
import com.google.android.flexbox.FlexboxLayout;
import com.itislevel.lyl.R;
import com.itislevel.lyl.adapter.BlessCartAdapter;
import com.itislevel.lyl.adapter.BlessGoodsGiftAdapter;
import com.itislevel.lyl.app.Constants;
import com.itislevel.lyl.mvp.model.bean.BlessCartListBean;
import com.itislevel.lyl.mvp.model.bean.HappyBlessUsualLanguageBean;
import com.itislevel.lyl.mvp.ui.blessing.BlessingDetailActivity;
import com.itislevel.lyl.mvp.ui.pay.PayInfoActivity;
import com.itislevel.lyl.net.LoaderStyle;
import com.itislevel.lyl.net.RestClent;
import com.itislevel.lyl.net.callback.IFailure;
import com.itislevel.lyl.net.callback.ISuccess;
import com.itislevel.lyl.utils.ActivityUtil;
import com.itislevel.lyl.utils.DecimalUtils;
import com.itislevel.lyl.utils.SAToast;
import com.itislevel.lyl.utils.SharedPreferencedUtils;
import com.itislevel.lyl.utils.ToastUtil;
import com.itislevel.lyl.utils.rxbus.RxBus;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.itislevel.lyl.mvp.ui.main.home.HomeFragment.SHOP_TYPE;

public class BlessCartDialog extends BottomBaseDialog<BlessCartDialog> implements
        BaseQuickAdapter.OnItemChildClickListener, View.OnClickListener {
//    @BindView(R.id.ll_bless_yu_parent)
//    LinearLayout ll_bless_yu_parent;
//
//    @BindView(R.id.fbl_parent)
//    FlexboxLayout fbl_parent;
//
//    @BindView(R.id.et_content)
//    EditText et_content;
//    @BindView(R.id.tv_send)
//    TextView tv_send;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @BindView(R.id.tv_totalprice)
    TextView tv_totalprice;

    @BindView(R.id.tv_count)
    TextView tv_count;

    @BindView(R.id.tv_send)
    TextView tv_send;

    @BindView(R.id.cart_back)
    AppCompatImageView cart_back;

    @BindView(R.id.clear_all_linear)

    LinearLayoutCompat clear_all_linear;//清空购物车

    String totalPrice;
    String count;

    BlessCartAdapter adapter;

    BlessingDetailActivity activity;
    List<BlessCartListBean.ShopcartlistBean> shopcartlistBeanList;

    BlessGiftDialog giftDialog;

    public BlessCartDialog(BlessingDetailActivity activity, View animateView,
                           List<BlessCartListBean.ShopcartlistBean> shopcartlistBeanList, String
                                   totalPrice,String count, BlessGiftDialog giftDialog) {
        super(activity, animateView);
        this.shopcartlistBeanList = shopcartlistBeanList;
        this.activity = activity;
        this.totalPrice = totalPrice;
        this.giftDialog = giftDialog;
        this.count=count;
    }


    @Override
    public View onCreateView(){
        View inflate = View.inflate(mContext, R.layout.item_bless_cart_layout, null);
        ButterKnife.bind(this, inflate);
        mLlTop.setPadding(0, 0, 0, 80);
        return inflate;
    }

    @Override
    public void setUiBeforShow() {
        cart_back.setOnClickListener(this);
        clear_all_linear.setOnClickListener(this);
        tv_totalprice.setText(totalPrice);
        tv_count.setText(count);
        initAdapter();
        if (shopcartlistBeanList != null && shopcartlistBeanList.size() > 0) {
            adapter.setNewData(shopcartlistBeanList);
        }

        tv_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String totalPrice = tv_totalprice.getText().toString();
                if (TextUtils.isEmpty(totalPrice)||totalPrice.equals("0")
                        ||shopcartlistBeanList==null||shopcartlistBeanList.size()<=0) {

                    SAToast.makeText(getContext(),"请选择礼品").show();
                    return;
                }

                String str = "";
                double tp=0;
                String detail="";
                for (BlessCartListBean.ShopcartlistBean cart : shopcartlistBeanList) {
                    str += cart.getGoodsid() + "," + cart.getCateid() + "," + cart.getPrice() +
                            "," + cart.getCount() + "," + cart.getGoodsname() + "," + cart
                            .getImgurl();
                    str += "@";

                    tp+=Double.parseDouble(cart.getPrice())*cart.getCount();
                    detail+=cart.getGoodsid() + ","+cart.getCount()+"@";
                }
                str = str.substring(0, str.length() - 1);
                detail = detail.substring(0, detail.length() - 1);
                activity.dialogGenerateOrder(str,tp,detail);
            }
        });

    }

    /**
     * 初始化adapter
     */
    private void initAdapter() {
        if (adapter == null) {
            adapter = new BlessCartAdapter(R.layout.item_bless_cart_item,activity);
//            adapter.setOnItemClickListener(this);
            adapter.setOnItemChildClickListener(this);
            adapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN); //切换动画
            adapter.setEnableLoadMore(false);
            LinearLayoutManager layoutManager = new LinearLayoutManager(activity);
//            GridLayoutManager layoutManager = new GridLayoutManager(context, 2,
// LinearLayoutManager
//                    .HORIZONTAL, false);
//            layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            recyclerView.addItemDecoration(new HorizontalDividerItemDecoration.Builder(activity)
                    .build());
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(adapter);

        }
    }


    @Override
    public void show() {
        super.show();

    }

    private BaseAnimatorSet mWindowInAs;
    private BaseAnimatorSet mWindowOutAs;

    @Override
    protected BaseAnimatorSet getWindowInAs() {
        if (mWindowInAs == null) {
            mWindowInAs = new WindowsInAs();
        }
        return mWindowInAs;
    }

    @Override
    protected BaseAnimatorSet getWindowOutAs() {
        if (mWindowOutAs == null) {
            mWindowOutAs = new WindowsOutAs();
        }
        return mWindowOutAs;
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        // iv_del tv_count iv_add
        BlessCartListBean.ShopcartlistBean item = this.adapter.getItem(position);
        switch (view.getId()) {
            case R.id.iv_add:
                int after = item.getCount() + 1;
                item.setCount(after);
                adapter.notifyItemChanged(position);
                activity.dialogUpdateCart(item);
//                activity.dialogAddCart(item);

                break;
            case R.id.iv_del:
                int count = item.getCount();
                if (count <= 1) {
                    adapter.remove(position);
                    activity.dialogDelCart(item);
                } else {
                    count = count - 1;
                    item.setCount(count);
                    adapter.notifyItemChanged(position);
                    activity.dialogUpdateCart(item);
//                    activity.dialogUpdateCart(item);
                }
                break;
            case R.id.bisniss_delete://左滑删除
                activity.dialogDelCart(item);
                adapter.remove(position);
                break;
        }

        double tp = 0;
        int count=0;
        for (BlessCartListBean.ShopcartlistBean cart : shopcartlistBeanList) {
            tp += Double.parseDouble(cart.getPrice())*cart.getCount();
            count+=cart.getCount();
        }

        tv_totalprice.setText(DecimalUtils.format2(tp));
        tv_count.setText(count+"");
        giftDialog.setTotalPrice(DecimalUtils.format2(tp));
        giftDialog.setCount(count+"");

    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.cart_back:
                this.dismiss();
                break;
            case R.id.clear_all_linear:
                show_clear_cart(clear_all_linear);
                break;
        }
    }




    class WindowsInAs extends BaseAnimatorSet {
        @Override
        public void setAnimation(View view) {
            ObjectAnimator rotationX = ObjectAnimator.ofFloat(view, "rotationX", 10, 0f)
                    .setDuration(150);
            rotationX.setStartDelay(200);
            animatorSet.playTogether(
                    ObjectAnimator.ofFloat(view, "scaleX", 1.0f, 0.8f).setDuration(350),
                    ObjectAnimator.ofFloat(view, "scaleY", 1.0f, 0.8f).setDuration(350),
//                    ObjectAnimator.ofFloat(view, "alpha", 1.0f, 0.5f).setDuration(350),
                    ObjectAnimator.ofFloat(view, "rotationX", 0f, 10).setDuration(200),
                    rotationX,
                    ObjectAnimator.ofFloat(view, "translationY", 0, -0.1f * mDisplayMetrics
                            .heightPixels).setDuration(350)
            );
        }
    }

    class WindowsOutAs extends BaseAnimatorSet {
        @Override
        public void setAnimation(View view) {
            ObjectAnimator rotationX = ObjectAnimator.ofFloat(view, "rotationX", 10, 0f)
                    .setDuration(150);
            rotationX.setStartDelay(200);
            animatorSet.playTogether(
                    ObjectAnimator.ofFloat(view, "scaleX", 0.8f, 1.0f).setDuration(350),
                    ObjectAnimator.ofFloat(view, "scaleY", 0.8f, 1.0f).setDuration(350),
//                    ObjectAnimator.ofFloat(view, "alpha", 1.0f, 0.5f).setDuration(350),
                    ObjectAnimator.ofFloat(view, "rotationX", 0f, 10).setDuration(200),
                    rotationX,
                    ObjectAnimator.ofFloat(view, "translationY", -0.1f * mDisplayMetrics
                            .heightPixels, 0).setDuration(350)
            );
        }
    }

    public void show_clear_cart(View v){
        //实例化建造者
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        //设置警告对话框的标题
        builder.setTitle("清空购物车");
        //设置警告显示的图片
//        builder.setIcon(android.R.drawable.ic_dialog_alert);
        //设置警告对话框的提示信息
        builder.setMessage("您是否需要清空购物车？");
        //设置”正面”按钮，及点击事件
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
           /*     int size = adapter.getItemCount();
                BlessCartListBean.ShopcartlistBean item;
                for(int  i =0 ; i < size ; i++)
                {
                    item =adapter.getItem(i);
                    activity.dialogDelCart(item);
                }*/
                clear_shop();
            }
        });
        //设置“反面”按钮，及点击事件
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        //设置“中立”按钮，及点击事件
        builder.setNeutralButton("等等看吧", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        //显示对话框
        builder.show();
    }
    private void clear_shop()
    {
        Map<String, String> request = new HashMap<>();
        request.put("token", SharedPreferencedUtils.getStr(Constants.USER_TOKEN));
        request.put("usernum", SharedPreferencedUtils.getStr(Constants.USER_NUM));
        request.put("userid",  SharedPreferencedUtils.getStr(Constants.USER_ID));
        request.put("type", SHOP_TYPE);
        String  jsonObject = JSON.toJSONString(request);
        RestClent.builder()
                .url("clearShopCart")
                .params("action",jsonObject)
                .loader(activity, LoaderStyle.BallClipRotateIndicator)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        System.out.println("清空购物车返回的数据******************"+response);
                        BlessCartDialog.this.dismiss();
                        adapter.clear();
                        SAToast.makeText(activity,"已清空!").show();
                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void onIFailure() {

                    }
                })
                .build()
                .post();
    }

}
