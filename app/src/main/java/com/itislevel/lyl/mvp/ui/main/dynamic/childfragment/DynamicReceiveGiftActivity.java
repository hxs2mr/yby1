package com.itislevel.lyl.mvp.ui.main.dynamic.childfragment;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.itislevel.lyl.R;
import com.itislevel.lyl.adapter.BlessDetailGiftAdapter;
import com.itislevel.lyl.adapter.DynamicDetailGiftAdapter;
import com.itislevel.lyl.base.RootActivity;
import com.itislevel.lyl.mvp.model.bean.AliPayBean;
import com.itislevel.lyl.mvp.model.bean.BaseGiftBean;
import com.itislevel.lyl.mvp.model.bean.BlessAddBean;
import com.itislevel.lyl.mvp.model.bean.BlessAddLikeBean;
import com.itislevel.lyl.mvp.model.bean.BlessCartListBean;
import com.itislevel.lyl.mvp.model.bean.BlessCommentBean;
import com.itislevel.lyl.mvp.model.bean.BlessDetailGiftListBean;
import com.itislevel.lyl.mvp.model.bean.BlessGiftBean;
import com.itislevel.lyl.mvp.model.bean.BlessListBean;
import com.itislevel.lyl.mvp.model.bean.BlessReceiveGiftBean;
import com.itislevel.lyl.mvp.model.bean.BlessReceiveYuBean;
import com.itislevel.lyl.mvp.model.bean.BlessSendGiftBean;
import com.itislevel.lyl.mvp.model.bean.CartUpdateBean;
import com.itislevel.lyl.mvp.model.bean.FileUploadBean;
import com.itislevel.lyl.mvp.model.bean.HappyBlessUsualLanguageBean;
import com.itislevel.lyl.mvp.model.bean.ListFollowItemBean;
import com.itislevel.lyl.mvp.model.bean.ListItemBean;
import com.itislevel.lyl.mvp.model.bean.ListPersonBean;
import com.itislevel.lyl.mvp.model.bean.ListTonCityItemBean;
import com.itislevel.lyl.mvp.ui.blessing.BlessingContract;
import com.itislevel.lyl.mvp.ui.blessing.BlessingPresenter;
import com.itislevel.lyl.utils.GsonUtil;
import com.scwang.smartrefresh.layout.api.RefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import static com.itislevel.lyl.mvp.ui.dynamicmyperson.childpersonfragment.Dynamic_personFragment.person_giftBeans;
import static com.itislevel.lyl.mvp.ui.main.dynamic.childfragment.dfind.DFindFragment.giftListBeans;
import static com.itislevel.lyl.mvp.ui.main.dynamic.childfragment.dfollow.DFollowFragment.followgiftListBeans;
import static com.itislevel.lyl.mvp.ui.main.dynamic.childfragment.toncity.DTonCityFragment.tongiftListBeans;

public class DynamicReceiveGiftActivity extends RootActivity<BlessingPresenter> implements
        BlessingContract.View {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    //是否已添加没有更多数据的布局
    private boolean isAddNoMoreView = false;
    private int pageIndex = 1;//分页加载时 当前页是第几页
    private int totalPage = 0;//总页数
    private boolean isRefreshing = true;//当前是刷新还是加载

    Bundle bundle;
    DynamicDetailGiftAdapter adapter;

    List<ListFollowItemBean.GiftListBean> Dfins;

    List<ListTonCityItemBean.GiftListBean>  Tons;

    List<ListItemBean.GiftListBean>    Follows;


    List<BaseGiftBean> beanList;

    List<ListPersonBean.GiftListBean> Persons;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_bless_receive_gift2;
    }

    @Override
    protected void initEventAndData() {
        if(beanList!=null)
        {
            beanList.clear();
        }
        beanList = new ArrayList<>();
        bundle=getIntent().getExtras();
        setToolbarTvTitle("收到的礼物");
        //  bundle.putString("familyReceiveGift",GsonUtil.obj2JSON(familyReceiveGift));
        String model = bundle.getString("model");
        if(model.equals("find")){//发现模块的
            if(Dfins!=null)
            {
                Dfins.clear();
            }
            Dfins  = giftListBeans;
            int size = giftListBeans.size();
            for(int i = 0 ; i <size ;i++ ){
                BaseGiftBean baseGiftBean = new BaseGiftBean(Dfins.get(i).getGoodsname(),Dfins.get(i).getBuyuserid(),
                        Dfins.get(i).getCount(),Dfins.get(i).getGoodsid(),
                        Dfins.get(i).getNicknameX(),Dfins.get(i).getImgurlX());
                beanList.add(baseGiftBean);
            }
        }else if(model.equals("follow")){
            Follows = followgiftListBeans;
            int size = Follows.size();
            for(int i = 0 ; i <size ;i++ ){
                BaseGiftBean baseGiftBean = new BaseGiftBean(Follows.get(i).getGoodsname(),Follows.get(i).getBuyuserid(),
                        Follows.get(i).getCount(),Follows.get(i).getGoodsid(),
                        Follows.get(i).getNicknameX(),Follows.get(i).getImgurlX());
                beanList.add(baseGiftBean);
            }
        }else if(model.equals("ton")) {
            Tons = tongiftListBeans;
            int size = Tons.size();
            for(int i = 0 ; i <size ;i++ ){
                BaseGiftBean baseGiftBean = new BaseGiftBean(Tons.get(i).getGoodsname(),Tons.get(i).getBuyuserid(),
                        Tons.get(i).getCount(),Tons.get(i).getGoodsid(),
                        Tons.get(i).getNicknameX(),Tons.get(i).getImgurlX());
                beanList.add(baseGiftBean);
            }
        }else {
            Persons = person_giftBeans;
            int size = Persons.size();
            for(int i = 0 ; i <size ;i++ ){
                BaseGiftBean baseGiftBean = new BaseGiftBean(Persons.get(i).getGoodsname(),Persons.get(i).getBuyuserid(),
                        Persons.get(i).getCount(),Persons.get(i).getGoodsid(),
                        Persons.get(i).getNicknameX(),Persons.get(i).getImgurlX());
                beanList.add(baseGiftBean);
            }
        }
        initAdapter();

    }
    @Override
    protected void onResume() {
        super.onResume();
//        refreshLayout.autoRefresh();//刷新效果
//        loadData();
        adapter.setNewData(beanList);
    }
    private void loadData() {
//        Map<String, String> request = new HashMap<>();
//        request.put("token", SharedPreferencedUtils.getStr(Constants.USER_TOKEN));
//        request.put("usernum", SharedPreferencedUtils.getStr(Constants.USER_NUM));
//        request.put("userid", SharedPreferencedUtils.getStr(Constants.USER_ID));
//        request.put("pageIndex", pageIndex + "");
//        request.put("pageSize", Constants.PAGE_NUMBER20 + "");
//
//        mPresenter.shareListMy(GsonUtil.obj2JSON(request));

    }

    /**
     * 初始化adapter
     */
    private void initAdapter() {
        if (adapter == null) {
            adapter = new DynamicDetailGiftAdapter(R.layout.item_bless_detail_gift2);
//            adapter.setOnItemClickListener(giftClickListener);
//            adapter.setOnItemChildClickListener(giftClickListener);
            adapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN); //切换动画
            adapter.setEnableLoadMore(false);

//            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
//            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

            GridLayoutManager layoutManager = new GridLayoutManager(this, 3, LinearLayoutManager
                    .VERTICAL, false);

//            recyclerView.addItemDecoration(new HorizontalDividerItemDecoration.Builder(this)
//                    .build());

            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(adapter);
        }

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

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    public void showContent(String msg) {

    }

    @Override
    public void happAdd(String action) {

    }

    @Override
    public void happyListMy(BlessListBean blessListBean) {

    }

    @Override
    public void happyViewCount(String action) {

    }

    @Override
    public void happyDel(String action) {

    }

    @Override
    public void happyList(BlessListBean blessListBean) {

    }

    @Override
    public void happyComment(BlessCommentBean blessCommentBean) {

    }

    @Override
    public void happyCommentDel(String action) {

    }

    @Override
    public void happyLike(BlessAddLikeBean blessAddLikeBean) {

    }

    @Override
    public void happyBlessAdd(BlessAddBean blessAddBean) {

    }

    @Override
    public void happyBlessReceiveList(BlessReceiveYuBean blessReceiveYuBean) {

    }

    @Override
    public void happyUsualLanguage(HappyBlessUsualLanguageBean blessUsualLanguageBeanb) {

    }

    @Override
    public void happyGiftList(BlessGiftBean blessGiftBean) {

    }

    @Override
    public void happyGiftReceiveListMy(BlessReceiveGiftBean blessReceiveGiftBean) {

    }

    @Override
    public void happyGiftSendListMy(BlessSendGiftBean blessSendGiftBean) {

    }

    @Override
    public void happyOrderAdd(String blessOrderBean) {

    }

    @Override
    public void happyCartAdd(String message) {

    }

    @Override
    public void happyCartList(BlessCartListBean blessCartListBean) {

    }

    @Override
    public void happyCartUpdate(CartUpdateBean message) {

    }

    @Override
    public void happyCartDel(String message) {

    }

    @Override
    public void happySearch(BlessListBean blessListBean) {

    }

    @Override
    public void happyDetailsGiftList(BlessDetailGiftListBean detailGiftListBean) {

    }

    @Override
    public void uploadHeader(FileUploadBean fileUploadBean) {

    }

    @Override
    public void alipay(AliPayBean action) {

    }
}
