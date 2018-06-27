package com.itislevel.lyl.widget;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.flyco.animation.BaseAnimatorSet;
import com.flyco.dialog.widget.base.BottomBaseDialog;
import com.itislevel.lyl.R;
import com.itislevel.lyl.adapter.BlessGoodsGiftAdapter;
import com.itislevel.lyl.mvp.model.bean.BlessCartListBean;
import com.itislevel.lyl.mvp.model.bean.BlessGiftBean;
import com.itislevel.lyl.mvp.model.bean.GiftBean;
import com.itislevel.lyl.mvp.ui.blessing.BlessingDetailActivity;
import com.itislevel.lyl.mvp.ui.main.dynamic.childfragment.dfind.DFindFragment;
import com.itislevel.lyl.mvp.ui.main.dynamic.childfragment.dfollow.DFollowFragment;
import com.itislevel.lyl.utils.DecimalUtils;
import com.itislevel.lyl.utils.SAToast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DyNamicFollowGiftDialog extends BottomBaseDialog<DyNamicFollowGiftDialog> implements View
        .OnClickListener, BaseQuickAdapter.OnItemClickListener
        , BaseQuickAdapter.OnItemChildClickListener {

    //是否已添加没有更多数据的布局
    private boolean isAddNoMoreView = false;
    private int pageIndex = 1;//分页加载时 当前页是第几页
    private int totalPage = 0;//总页数
    private boolean isRefreshing = true;//当前是刷新还是加载
    private BlessGoodsGiftAdapter adapter;


    List<BlessCartListBean.ShopcartlistBean> shopcartlistBeanList;


    DFollowFragment findFragment;
    BlessingDetailActivity activity;
    BlessGiftBean blessGiftBean;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @BindView(R.id.tv_totalprice)
    TextView tv_totalprice;

    @BindView(R.id.tv_count)
    TextView tv_count;

    @BindView(R.id.tv_iv)
    AppCompatImageView tv_iv;

    @BindView(R.id.tv_count_temp)
    TextView tv_count_temp;

    @BindView(R.id.tv_temp)
    TextView tv_temp;

    @BindView(R.id.tv_send)
    TextView tv_send;

    @BindView(R.id.gift_back)
    AppCompatImageView gift_back;

    private int onclick_location=0;
    private int last_onclick_location= 0;
    private int last_onclick_location2= 0;
    private boolean onclick_num=true;
    private int  num=0;
    View liwu_linear_header ;
    public DyNamicFollowGiftDialog(DFollowFragment findFragment, Context context, View animateView, BlessGiftBean
            blessGiftBean, BlessCartListBean cartListBean) {
        super(context, animateView);
        this.blessGiftBean = blessGiftBean;
        this.findFragment = findFragment;
        this.activity = activity;
        if (cartListBean != null && cartListBean.getShopcartlist() != null && cartListBean
                .getShopcartlist().size() > 0) {
            this.shopcartlistBeanList = cartListBean.getShopcartlist();
        }
    }

    public DyNamicFollowGiftDialog(Context context) {
        super(context);
    }

    @Override
    public View onCreateView() {
        View inflate = View.inflate(mContext, R.layout.item_bless_gift, null);
        ButterKnife.bind(this, inflate);
        return inflate;
    }

    @Override
    public void setUiBeforShow() {
        gift_back.setOnClickListener(this);
        tv_totalprice.setOnClickListener(this);
        tv_send.setOnClickListener(this);

        tv_iv.setOnClickListener(this);
        tv_count_temp.setOnClickListener(this);
        tv_temp.setOnClickListener(this);

        initAdapter();
        if (blessGiftBean != null && blessGiftBean.getList() != null && blessGiftBean.getList()
                .size() > 0) {
            adapter.setNewData(blessGiftBean.getList());
        }
    }

    /**
     * 初始化adapter
     */
    private void initAdapter(){
        if (adapter == null) {
            adapter = new BlessGoodsGiftAdapter(R.layout.item_goods_bless_gift);
            adapter.setOnItemClickListener(this);
            adapter.setOnItemChildClickListener(this);
            adapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN); //切换动画
            adapter.setEnableLoadMore(false);
//            LinearLayoutManager layoutManager = new LinearLayoutManager(context);
            GridLayoutManager layoutManager = new GridLayoutManager(activity, 4, LinearLayoutManager
                    .VERTICAL, false);
//            layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(adapter);
        }

        double total = 0;
        int count=0;
        if (shopcartlistBeanList != null) {
            for (BlessCartListBean.ShopcartlistBean cart : shopcartlistBeanList) {
                total += Double.parseDouble(cart.getPrice())*cart.getCount();
                count+=cart.getCount();
            }
            tv_totalprice.setText(DecimalUtils.format2(total));
            tv_count.setText(count+"");
        }

    }

    /**
     * 设置总价格
     *
     * @param tp
     */
    public void setTotalPrice(String tp) {

        tv_totalprice.setText(tp );
    }

    /**
     * 设置数量
     * @param count
     */
    public void setCount(String count) {
        tv_count.setText(count );
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
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.gift_back:
                this.dismiss();
                break;
            case R.id.tv_send:
                String totalPrice = tv_totalprice.getText().toString();
                if (TextUtils.isEmpty(totalPrice)||totalPrice.equals("0")
                        ||shopcartlistBeanList==null||shopcartlistBeanList.size()<=0) {
                    SAToast.makeText(getContext(),"请选择礼品").show();
                    return;
                }
                String str = "";
                double tp=0;
                String detail="";
                for (GiftBean cart : blessGiftBean.getList()) {
                    if(!cart.isOnclick())
                    {
                        str += cart.getGiftid() + "," + cart.getFirstcateid() + "," + cart.getSellprice() +
                                "," + 1 + "," + cart.getGiftname() + "," + cart
                                .getImgurl();
                        str += "@";

                        tp+=Double.parseDouble(cart.getSellprice());
                        detail+=cart.getGiftid() + ","+1+"@";
                    }
                }
                str = str.substring(0, str.length() - 1);
                detail = detail.substring(0, detail.length() - 1);

                findFragment.dialogGenerateOrder(onclick_location);
                break;
            case R.id.tv_totalprice://购物车环节
                if (shopcartlistBeanList == null || shopcartlistBeanList.size() <= 0) {
                    SAToast.makeText(getContext(),"购物车暂无数据").show();
                    return;
                }
             /*   BlessCartDialog cartDialog = new BlessCartDialog(activity, null,
                        shopcartlistBeanList, tv_totalprice.getText().toString(),tv_count.getText().toString(), this);
                cartDialog.show();*/
                this.dismiss();
                break;

            case R.id.tv_iv:
                if (shopcartlistBeanList == null || shopcartlistBeanList.size() <= 0) {
                    SAToast.makeText(getContext(),"购物车暂无数据").show();
                    return;
                }
       /*         BlessCartDialog cartDialog1 = new BlessCartDialog(activity, null,
                        shopcartlistBeanList, tv_totalprice.getText().toString(),tv_count.getText().toString(), this);
                cartDialog1.show();*/
                this.dismiss();
                break;
            case R.id.tv_count_temp:
                if (shopcartlistBeanList == null || shopcartlistBeanList.size() <= 0) {
                    SAToast.makeText(getContext(),"购物车暂无数据").show();
                    return;
                }
               /* BlessCartDialog cartDialog2 = new BlessCartDialog(activity, null,
                        shopcartlistBeanList, tv_totalprice.getText().toString(),tv_count.getText().toString(), this);
                cartDialog2.show();*/
                this.dismiss();
                break;
            case R.id.tv_temp:
                if (shopcartlistBeanList == null || shopcartlistBeanList.size() <= 0) {
                    SAToast.makeText(getContext(),"购物车暂无数据").show();
                    return;
                }
                //购物车弹窗
              /*  BlessCartDialog cartDialog3 = new BlessCartDialog(activity, null,
                        shopcartlistBeanList, tv_totalprice.getText().toString(),tv_count.getText().toString(), this);
                cartDialog3.show();*/
                this.dismiss();
                break;

        }
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        onclick_location = this.adapter.getItemViewType(position);
        if(onclick_num)//记录上一个位置
        {
            last_onclick_location = last_onclick_location2;
            last_onclick_location2 = onclick_location;
            onclick_num=false ;
        }else {
            onclick_num=true ;
            last_onclick_location = last_onclick_location2;
            last_onclick_location2 = onclick_location;
        }
        View view1 = adapter.getViewByPosition(recyclerView,last_onclick_location,R.id.liwu_linear_header);//记录上一个位置
        this.adapter.getItem(last_onclick_location).setOnclick(true);
        if (view1!=null)
        {
            view1.setBackgroundResource(R.drawable.bless_item_onclick_false);
        }

        View view2 = adapter.getViewByPosition(recyclerView,onclick_location,R.id.liwu_linear_header);//当前的位置

        this.adapter.getItem(onclick_location).setOnclick(false);
        if (view2!=null)
        {
            view2.setBackgroundResource(R.drawable.bless_item_onclick_false);
        }

        view2.setBackgroundResource(R.drawable.bless_item_onclick);

        GiftBean item = this.adapter.getItem(position);

        BlessCartListBean.ShopcartlistBean shopcartlistBean = new BlessCartListBean
                .ShopcartlistBean();

        int giftid = item.getGiftid();
        boolean isAdded = false;
        if (shopcartlistBeanList != null) {
            for (BlessCartListBean.ShopcartlistBean cart : shopcartlistBeanList) {

                if (cart.getGoodsid() == giftid) {
                    isAdded = true;
                    cart.setCount(cart.getCount() + 1);
                   /// activity.dialogUpdateCart(cart);//修改购物车商品数量
//                    activity.dialogAddCart(cart);
                    break;
                }
            }
        } else {
            shopcartlistBeanList = new ArrayList<>();
        }

        if (!isAdded) {
            shopcartlistBean.setImgurl(item.getImgurl());
            shopcartlistBean.setPrice(item.getSellprice());
            shopcartlistBean.setGoodsid(item.getGiftid());
            shopcartlistBean.setCateid(item.getFirstcateid());
            shopcartlistBean.setCount(1);
            shopcartlistBean.setGoodsname(item.getGiftname());
            shopcartlistBeanList.add(shopcartlistBean);

           // findFragment.dialogAddCart(shopcartlistBean);//添加购物车
        }


        double price = Double.parseDouble(item.getSellprice());
        String total = tv_totalprice.getText().toString().trim();
        double parseDouble = Double.parseDouble(total);
        parseDouble = parseDouble + price;
        tv_totalprice.setText(DecimalUtils.format2(parseDouble));

        tv_count.setText((Integer.parseInt(tv_count.getText().toString())+1)+"");

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
