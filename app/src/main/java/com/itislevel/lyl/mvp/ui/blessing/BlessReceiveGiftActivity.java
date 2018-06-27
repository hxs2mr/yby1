package com.itislevel.lyl.mvp.ui.blessing;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.itislevel.lyl.R;
import com.itislevel.lyl.adapter.BlessDetailGiftAdapter;
import com.itislevel.lyl.adapter.FamilyDetailReceiveGiftAdapter;
import com.itislevel.lyl.base.RootActivity;
import com.itislevel.lyl.mvp.model.bean.AliPayBean;
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
import com.itislevel.lyl.mvp.model.bean.FamilyReceiveGiftBean;
import com.itislevel.lyl.mvp.model.bean.FileUploadBean;
import com.itislevel.lyl.mvp.model.bean.HappyBlessUsualLanguageBean;
import com.itislevel.lyl.mvp.model.bean.ListFollowItemBean;
import com.itislevel.lyl.utils.GsonUtil;
import com.scwang.smartrefresh.layout.api.RefreshLayout;

import java.util.List;

import butterknife.BindView;

public class BlessReceiveGiftActivity extends RootActivity<BlessingPresenter> implements
        BlessingContract.View {

    @BindView(R.id.refreshLayout)
    RefreshLayout refreshLayout;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    //是否已添加没有更多数据的布局
    private boolean isAddNoMoreView = false;
    private int pageIndex = 1;//分页加载时 当前页是第几页
    private int totalPage = 0;//总页数
    private boolean isRefreshing = true;//当前是刷新还是加载

    Bundle bundle;
    BlessDetailGiftAdapter adapter;

    BlessDetailGiftListBean detailGiftListBean;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_bless_receive_gift2;
    }

    @Override
    protected void initEventAndData() {
        bundle=getIntent().getExtras();
        setToolbarTvTitle("收到的礼物");
        //  bundle.putString("familyReceiveGift",GsonUtil.obj2JSON(familyReceiveGift));
        String model = bundle.getString("model");
        if(model.equals("find")){//发现模块的

        }else {

        }
        String receiveGift = bundle.getString("receiveGift");

        detailGiftListBean = GsonUtil.toObject(receiveGift, BlessDetailGiftListBean.class);

        initRefreshListener();
        initAdapter();

    }
    @Override
    protected void onResume() {
        super.onResume();
//        refreshLayout.autoRefresh();//刷新效果
//        loadData();
        adapter.setNewData(detailGiftListBean.getList());
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
            adapter = new BlessDetailGiftAdapter(R.layout
                    .item_bless_detail_gift2);
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
    private void initRefreshListener() {
        refreshLayout.setEnableRefresh(false);
        refreshLayout.setEnableLoadmore(false);
//        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
//            @Override
//            public void onRefresh(RefreshLayout refreshlayout) {
//                pageIndex = 1;
//                isRefreshing = true;
//                loadData();
//            }
//        });
//
//        refreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
//            @Override
//            public void onLoadmore(RefreshLayout refreshlayout) {
//                pageIndex++;
//                if (pageIndex > totalPage) {
//                    if (!isAddNoMoreView) {
//                        isAddNoMoreView = true;
//                        View view = getLayoutInflater().inflate(R.layout.partial_no_more_data,
//                                null, false);
//                        adapter.addFooterView(view);
//                    }
//                    ToastUtil.Info("没有更多啦~~");
//                    refreshLayout.finishLoadmore(true);//
//                    return;
//                } else {
//                    isRefreshing = false;
//                    loadData();
//                }
//            }
//        });


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
