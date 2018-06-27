package com.itislevel.lyl.mvp.ui.family.giftchildfragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.itislevel.lyl.R;
import com.itislevel.lyl.adapter.FamilyGiftListAdapter;
import com.itislevel.lyl.app.Constants;
import com.itislevel.lyl.mvp.model.bean.BlessCartListBean;
import com.itislevel.lyl.mvp.model.bean.FamilyGiftListBean;
import com.itislevel.lyl.mvp.model.bean.FamilyReceiveGiftBean;
import com.itislevel.lyl.mvp.ui.family.FamilyGiftListActivity;
import com.itislevel.lyl.mvp.ui.main.dynamic.childfragment.RootCancleFragment;
import com.itislevel.lyl.mvp.ui.main.dynamic.childfragment.dfind.DFindFragment;
import com.itislevel.lyl.utils.GsonUtil;
import com.itislevel.lyl.utils.SAToast;
import com.itislevel.lyl.utils.SharedPreferencedUtils;
import com.itislevel.lyl.utils.ToastUtil;
import com.itislevel.lyl.widget.FamilyCartDialog;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

import static com.itislevel.lyl.mvp.ui.family.FamilyDetailActivity.listBean_Detail;
import static com.itislevel.lyl.mvp.ui.family.FamilyGiftListActivity.feteid;
import static com.itislevel.lyl.mvp.ui.family.FamilyGiftListActivity.touserid;

/**
 * Created by Administrator on 2018\5\21 0021.
 */

public class GiftChildFragment extends RootCancleFragment<GiftChildPresenter>implements GiftChildContract.View, BaseQuickAdapter.OnItemClickListener, BaseQuickAdapter.RequestLoadMoreListener, SwipeRefreshLayout.OnRefreshListener {
    @BindView(R.id.refreshLayout)
    SwipeRefreshLayout refreshLayout;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @BindView(R.id.ll_whish_parent)
    LinearLayout ll_whish_parent;

    @BindView(R.id.son_name)
    EditText son_name;

    @BindView(R.id.son_wu)
    TextView son_wu;

    @BindView(R.id.tv_send)
    TextView tv_send;

    private boolean isReady = false;
    private boolean isLoaded = false;//防止重复加载
    private FamilyGiftListAdapter adapter;

    private int selectCateId ;

    private int onclick_location=0;
    private int last_onclick_location= 0;
    private int last_onclick_location2= 0;
    private boolean onclick_num=true;

    private String son_end_name="";

    private int pageIndex = 1;//分页加载时 当前页是第几页
    private int totalPage = 0;//总页数
    private boolean isRefreshing = true;//当前是刷新还是加载

    List<BlessCartListBean.ShopcartlistBean> shopcartlistBeanList; //购物车数据
    private double totalPrice;
    private String goodsDetail;
    private int load_more = 0;
    private View view_end;
    private String gif_name="";
    private  String gif_imgurl="";
    private String nick_name="";

    private int is_on=0;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = this.getArguments();
        if(bundle != null){
            selectCateId = bundle.getInt("flage");
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.giftchildfragment;
    }

    @Override
    protected void initEventAndData() {
        ll_whish_parent.setVisibility(View.GONE);
        initRefreshLayout_recyclview();
        initAdapter();
        refreshLayout.setOnRefreshListener(this);
        isReady=true;
        refreshLayout.setRefreshing(true);//刷新效果
        lazyLoad();
    }

    private void loadData() {
        Map<String, String> request = new HashMap<>();
        request.put("token", SharedPreferencedUtils.getStr(Constants.USER_TOKEN));
        request.put("usernum", SharedPreferencedUtils.getStr(Constants.USER_NUM));
        request.put("firstcateid", selectCateId+"");

        mPresenter.familyListGift(GsonUtil.obj2JSON(request));
    }

    private void loadCarList() {
        Map<String, String> request = new HashMap<>();
        request.put("token", SharedPreferencedUtils.getStr(Constants.USER_TOKEN));
        request.put("usernum", SharedPreferencedUtils.getStr(Constants.USER_NUM));
        request.put("userid", SharedPreferencedUtils.getStr(Constants.USER_ID));
        request.put("type", Constants.CART_TYPE_FAMILY);
        mPresenter.familyCartList(GsonUtil.obj2JSON(request));
    }

    @Override
    public void familyListGift(FamilyGiftListBean familyGiftListBean) {
        load_more++;
        if(load_more==1)
        {
            adapter.setOnLoadMoreListener(this,recyclerView);//设置加载更多
        }

        if ( (familyGiftListBean.getList() == null || familyGiftListBean.getList().size() <= 0)) {
            View emptyView = View.inflate(getContext(), R.layout.partial_empty_view, null);
            TextView viewById = emptyView.findViewById(R.id.tv_message);
            adapter.setEmptyView(emptyView);
        }
        List<FamilyGiftListBean.ListBean> list = familyGiftListBean.getList();
        if (list == null || list.size() <= 0) {
            ToastUtil.Info("暂无数据");
            adapter.getData().clear();
            adapter.notifyDataSetChanged();
        } else {
            adapter.setNewData(familyGiftListBean.getList());
        }
//        totalPage = familyGiftListBean.getTotalPage();
        if (isRefreshing) {
//            adapter.setNewData(familyGiftListBean.getList());
            refreshLayout.setRefreshing(false);
        } else {
//            adapter.addData(familyGiftListBean.getList());
            adapter.loadMoreComplete();
        }
    }
    private void initRefreshLayout_recyclview(){//设置刷新的颜色和款式及recyclevew
        //注册成为订阅者
        refreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright, android.R.color.holo_orange_dark, android.R.color.holo_red_dark);
    }
    /**
     * 初始化adapter
     */
    private void initAdapter() {
        if (adapter == null) {
            adapter = new FamilyGiftListAdapter(R.layout.item_goods_family_gift);
            adapter.setOnItemClickListener(this);
            adapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN); //切换动画
            adapter.setEnableLoadMore(false);
//            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 4, LinearLayoutManager.HORIZONTAL, false);
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
//            recyclerView.addItemDecoration(new HorizontalDividerItemDecoration.Builder(this)
//                    .build());
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(adapter);

        }

    }

    public void dialogGenerateOrder() {

        loadingDialog.show();
        loadingDialog.setLabel(Constants.CART_GENERATE_ORDER_TXT);


//        bundle.putInt(Constants.PAY_TYPE, 1);//0立即支付 1从购物车过来的
//        bundle.putString(Constants.PAY_CART_STR, carstr);
//        bundle.putString(Constants.PAY_RECEIVE_USERID, touserid);
//        bundle.putString(Constants.PAY_MODULE_ID, feteid);
//        bundle.putString(Constants.PAY_MODULE_NAME, Constants.CART_MODEL_FAMILY);
//        bundle.putString(Constants.PAY_TOTALPRICE, tp + "");
//        bundle.putString(Constants.PAY_GOODS_DESC, "支付礼品费用");
//        bundle.putString(Constants.PAY_GOODS_DETAIL, detail);
//        bundle.putInt(Constants.PAY_FROM_ACTIVITY, Constants.PAY_FROM_FETE_GIFT);

        gif_name = this.adapter.getData().get(onclick_location).getSacrifname();
        gif_imgurl = this.adapter.getData().get(onclick_location).getImgurl();
        nick_name =SharedPreferencedUtils.getStr(Constants.USER_NICK_NAME);
        int bus_id = Integer.parseInt( SharedPreferencedUtils.getStr(Constants.USER_ID));
        Map<String, String> request = new HashMap<>();
        request.put("token", SharedPreferencedUtils.getStr(Constants.USER_TOKEN));
        request.put("usernum", SharedPreferencedUtils.getStr(Constants.USER_NUM));
        request.put("buyuserid", bus_id+"");
        request.put("modelename", Constants.CART_MODEL_FAMILY);
        request.put("moduleid", feteid+"");
        request.put("receiveuserid", touserid);
        request.put("goodsid", this.adapter.getData().get(onclick_location).getSacrifid() +"");
        request.put("cateid", this.adapter.getData().get(onclick_location).getFirstcateid() + "");
        request.put("price", this.adapter.getData().get(onclick_location).getSellprice());
        request.put("count", "1");
        request.put("goodsname", gif_name);
        request.put("imgurl",gif_imgurl);
        request.put("buyusername",son_end_name);

        request.put("buyersheadimg", SharedPreferencedUtils.getStr(Constants.USER_HEADER,"").equals("")?"hxs.jpg":SharedPreferencedUtils.getStr(Constants.USER_HEADER));
        request.put("dyimgorct", "0@"+SharedPreferencedUtils.getStr(Constants.USER_HEADER,"hxs.jpg"));

        mPresenter.immediateOrder(GsonUtil.obj2JSON(request));//立即下单
    }
  /*  public void dialogUpdateCart(BlessCartListBean.ShopcartlistBean item) {

        Map<String, String> request = new HashMap<>();
        request.put("token", SharedPreferencedUtils.getStr(Constants.USER_TOKEN));
        request.put("usernum", SharedPreferencedUtils.getStr(Constants.USER_NUM));
        request.put("userid", SharedPreferencedUtils.getStr(Constants.USER_ID));

        request.put("type", Constants.CART_TYPE_FAMILY);
        request.put("goodsid", item.getGoodsid() + "");
        request.put("count", item.getCount() + "");

        mPresenter.familyCartUpdate(GsonUtil.obj2JSON(request));
    }*/
    @Override
    public void familyCartList(BlessCartListBean blessCartListBean) {
    }

    @Override
    public void immediateOrder(String blessOrderBean) {
        listBean_Detail = new FamilyReceiveGiftBean.ListBean(son_end_name,gif_name,gif_imgurl, System.currentTimeMillis());
        loadingDialog.dismiss();
        SAToast.makeText(getContext(),"送礼成功").show();
        EventBus.getDefault().post(listBean_Detail);
        getActivity().finish();
    }

    @Override
    protected void lazyLoad() {
        if (!isReady || !isVisible || isLoaded) {
            return;
        }
        isLoaded = true;
        loadData();
        loadCarList();
    }
    @Override
    public void stateEmpty() {

    }

    @Override
    public void stateLoading() {

    }

    @Override
    public void stateSuccess() {

    }
    @OnClick({R.id.tv_send})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.tv_send:
                son_end_name = son_name.getText().toString();
                if(son_end_name.equals(""))
                {
                    SAToast.makeText(getContext(),"请输入送礼人!").show();
                    return;
                }
                if (is_on==0) {
                    ToastUtil.Info("请选择祭品");
                    return;
                }
           /*     String str = "";
                double tp = 0;
                String detail = "";
                for (BlessCartListBean.ShopcartlistBean cart : shopcartlistBeanList) {
                    str += cart.getGoodsid() + "," + cart.getCateid() + "," + cart.getPrice() +
                            "," + cart.getCount() + "," + cart.getGoodsname() + "," + cart
                            .getImgurl();
                    str += "@";

                    tp += Double.parseDouble(cart.getPrice()) * cart.getCount();
                    detail += cart.getGoodsid() + "," + cart.getCount() + "@";
                }
                str = str.substring(0, str.length() - 1);
                detail = detail.substring(0, detail.length() - 1);*/
                dialogGenerateOrder();
                break;
       /*     case R.id.tv_temp_cart1:
            case R.id.tv_totalprice:
            case R.id.tv_temp_cart:
                if (shopcartlistBeanList == null || shopcartlistBeanList.size() <= 0) {
                    ToastUtil.Info("购物车暂无数据");
                    return;
                }

                FamilyCartDialog cartDialog = new FamilyCartDialog(getActivity(),
                        null, shopcartlistBeanList, tv_totalprice.getText().toString(),tv_count.getText().toString());
                cartDialog.show();
                break;*/
        }
    }

    @Override
    protected void initInject() {
            getFragmentComponent().inject(this);
    }
    @Override
    public void useNightMode(boolean isNight) {

    }
    public  static GiftChildFragment newInstance(int num) {
        GiftChildFragment f = new GiftChildFragment();
        // Supply num input as an argument.
        Bundle args = new Bundle();
        args.putInt("flage", num);
        f.setArguments(args);
        return f;
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        hideSoftKeyboard(son_name);
        is_on = 1;
        onclick_location = position;
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
        if (view1!=null)
        {
            view1.setBackgroundResource(R.drawable.shape_tv_bless_tab_normal);
        }

        View view2 =adapter.getViewByPosition(recyclerView,onclick_location,R.id.liwu_linear_header);//当前的位置
        if (view2!=null)
        {
            view2.setBackgroundResource(R.drawable.shape_tv_bless_tab_normal);
        }
        view2.setBackgroundResource(R.drawable.bless_item_onclick);

        ll_whish_parent.setVisibility(View.VISIBLE);
        son_wu.setText(" "+this.adapter.getData().get(onclick_location).getSacrifname());
        view_end = view2;
        FamilyGiftListBean.ListBean item = this.adapter.getItem(position);
        BlessCartListBean.ShopcartlistBean shopcartlistBean = new BlessCartListBean
                .ShopcartlistBean();

        int giftid = item.getSacrifid();
        boolean isAdded = false;
       /* if (shopcartlistBeanList != null) {//暂时隐藏
            for (BlessCartListBean.ShopcartlistBean cart : shopcartlistBeanList) {

                if (cart.getGoodsid() == giftid) {
                    isAdded = true;
                    cart.setCount(cart.getCount() + 1);
                    dialogAddCart(cart); //添加一个购物车物品就请求网络一次
                    break;
                }
            }
        } else {
            shopcartlistBeanList = new ArrayList<>();
        }

        if (!isAdded) {
            shopcartlistBean.setImgurl(item.getImgurl());
            shopcartlistBean.setPrice(item.getSellprice());
            shopcartlistBean.setGoodsid(item.getSacrifid());
            shopcartlistBean.setCateid(Integer.parseInt(selectCateId));
            shopcartlistBean.setCount(1);
            shopcartlistBean.setGoodsname(item.getSacrifname());

            shopcartlistBeanList.add(shopcartlistBean);
            dialogAddCart(shopcartlistBean);
        }

        double price = Double.parseDouble(item.getSellprice());
        String total = tv_totalprice.getText().toString().trim();
        double parseDouble = Double.parseDouble(total);
        parseDouble = parseDouble + price;
        tv_totalprice.setText(DecimalUtils.format2(parseDouble));
        tv_count.setText((Integer.parseInt(tv_count.getText().toString())+1)+"");
          */
    }
    /**
     * 隐藏软键盘
     *
     * @param view
     */
    public static void hideSoftKeyboard(View view) {
        Context context = view.getContext();
        InputMethodManager imm = (InputMethodManager) context
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    @Override
    public void onLoadMoreRequested() {
        adapter.setEnableLoadMore(true);
        pageIndex++;
        if (pageIndex > totalPage) {
            adapter.loadMoreEnd();
            return;
        } else {
            isRefreshing = false;
            loadData();
        }
    }

    @Override
    public void onRefresh() {
        refreshLayout.setRefreshing(true);
        pageIndex = 1;
        isRefreshing = true;
        loadData();
    }
}
