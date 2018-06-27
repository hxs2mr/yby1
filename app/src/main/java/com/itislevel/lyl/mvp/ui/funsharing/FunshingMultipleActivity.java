package com.itislevel.lyl.mvp.ui.funsharing;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.itislevel.lyl.R;
import com.itislevel.lyl.adapter.MeiZiMultipleAdapter;
import com.itislevel.lyl.app.Constants;
import com.itislevel.lyl.base.RootActivity;
import com.itislevel.lyl.mvp.model.bean.FileUploadBean;
import com.itislevel.lyl.mvp.model.bean.FunsharingAddBean;
import com.itislevel.lyl.mvp.model.bean.FunsharingCommnetAddBean;
import com.itislevel.lyl.mvp.model.bean.FunsharingLikeBean;
import com.itislevel.lyl.mvp.model.bean.FunsharingListBean;
import com.itislevel.lyl.mvp.model.bean.FunsharingMyBean;
import com.itislevel.lyl.mvp.model.bean.MeiZiBean;
import com.itislevel.lyl.mvp.model.bean.MeiZiMultipleBean;
import com.scwang.smartrefresh.layout.api.RefreshLayout;

import java.util.List;

import butterknife.BindView;

public class FunshingMultipleActivity extends RootActivity<FunsharingPresenter> implements
        FunsharingContract.View
        , View.OnClickListener {

    @BindView(R.id.refreshLayout)
    RefreshLayout refreshLayout;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;


    private int pageIndex=1;//分页加载时 当前页是第几页
    private MeiZiMultipleAdapter adapter;

//    @BindView(R.id.ninegrid)
//    NineGridView ninegrid;



    @Override
    protected int getLayoutId() {
        return R.layout.activity_funshing_multiple;
    }

    @Override
    protected void initEventAndData() {

        setToolbarTvTitle("多item布局");
        mPresenter.loadDataMul(Constants.PAGE_NUMBER10,pageIndex);

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void showContent(String msg) {

    }

    @Override
    public void showData(List<MeiZiBean> data) {


    }

    @Override
    public void showDataMultiple(List<MeiZiMultipleBean> data) {

        adapter=new MeiZiMultipleAdapter(data);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);




//        ninegrid.setAdapter(new NineGridViewClickAdapter(this,urlList));
    }

    @Override
    public void addDynamic(String funsharingAddBean) {

    }




    @Override
    public void shareList(FunsharingListBean funsharingListBeans) {

    }

    @Override
    public void shareListMy(FunsharingMyBean funsharingMyBeans) {
    }

    @Override
    public void shareDel(String message) {

    }

    @Override
    public void commentShareAdd(FunsharingCommnetAddBean funsharingCommnetAddBean) {

    }


    @Override
    public void commentShareDel(String message) {

    }

    @Override
    public void shareLikeOrCancel(FunsharingLikeBean funsharingLikeBean) {

    }

    @Override
    public void uploadHeader(FileUploadBean fileUploadBean) {

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
}
