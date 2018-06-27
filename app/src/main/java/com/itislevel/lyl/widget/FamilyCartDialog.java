package com.itislevel.lyl.widget;

import android.animation.ObjectAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.flyco.animation.BaseAnimatorSet;
import com.flyco.dialog.widget.base.BottomBaseDialog;
import com.itislevel.lyl.R;
import com.itislevel.lyl.adapter.BlessCartAdapter;
import com.itislevel.lyl.mvp.model.bean.BlessCartListBean;
import com.itislevel.lyl.mvp.ui.blessing.BlessingDetailActivity;
import com.itislevel.lyl.mvp.ui.family.FamilyGiftListActivity;
import com.itislevel.lyl.utils.DecimalUtils;
import com.itislevel.lyl.utils.ToastUtil;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FamilyCartDialog extends BottomBaseDialog<FamilyCartDialog> implements
        BaseQuickAdapter.OnItemChildClickListener {


    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @BindView(R.id.tv_totalprice)
    TextView tv_totalprice;

    @BindView(R.id.tv_count)
    TextView tv_count;


    @BindView(R.id.tv_send)
    TextView tv_send;

    String totalPrice;
    String count;

    BlessCartAdapter adapter;

    FamilyGiftListActivity activity;
    List<BlessCartListBean.ShopcartlistBean> shopcartlistBeanList;

    public FamilyCartDialog(FamilyGiftListActivity activity, View animateView,
                            List<BlessCartListBean.ShopcartlistBean> shopcartlistBeanList, String
                                   totalPrice,String count) {
        super(activity, animateView);
        this.shopcartlistBeanList = shopcartlistBeanList;
        this.activity = activity;
        this.totalPrice = totalPrice;
        this.count=count;
    }


    @Override
    public View onCreateView() {
        View inflate = View.inflate(mContext, R.layout.item_bless_cart_layout, null);
        ButterKnife.bind(this, inflate);
        mLlTop.setPadding(0, 0, 0, 80);
        return inflate;
    }

    @Override
    public void setUiBeforShow() {

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
                    ToastUtil.Info("请选择祭品");
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
               // activity.dialogGenerateOrder(str,tp,detail);
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
                }
                break;
        }

        double tp = 0;
        int count1=0;
        for (BlessCartListBean.ShopcartlistBean cart : shopcartlistBeanList) {
            tp += Double.parseDouble(cart.getPrice())*cart.getCount();
            count1+=cart.getCount();
        }

        tv_totalprice.setText(DecimalUtils.format2(tp));
        tv_count.setText(count1+"");

        activity.setTotalPrice(DecimalUtils.format2(tp));
        activity.setCount(count1+"");

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
}
