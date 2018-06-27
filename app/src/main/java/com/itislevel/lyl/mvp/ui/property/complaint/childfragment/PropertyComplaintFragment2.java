package com.itislevel.lyl.mvp.ui.property.complaint.childfragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.itislevel.lyl.R;
import com.itislevel.lyl.adapter.ComplinHorstAdapter;
import com.itislevel.lyl.adapter.ComplintTouAdapter;
import com.itislevel.lyl.app.Constants;
import com.itislevel.lyl.base.RootFragment;
import com.itislevel.lyl.mvp.model.bean.AddressBean;
import com.itislevel.lyl.mvp.model.bean.ComSearchBean;
import com.itislevel.lyl.mvp.model.bean.ComplaintTypeBean;
import com.itislevel.lyl.mvp.model.bean.FileUploadBean;
import com.itislevel.lyl.mvp.model.bean.PropertyDetailBean;
import com.itislevel.lyl.mvp.model.db.DBHorstoryUtil;
import com.itislevel.lyl.mvp.ui.main.dynamic.childfragment.RootCancleFragment;
import com.itislevel.lyl.mvp.ui.property.PropertLoginActivity;
import com.itislevel.lyl.mvp.ui.property.complaint.ComplaintContract;
import com.itislevel.lyl.mvp.ui.property.complaint.ComplaintPresenter;
import com.itislevel.lyl.mvp.ui.property.complaint.complintdetail.ComplaintDetailActivity;
import com.itislevel.lyl.utils.ActivityUtil;
import com.itislevel.lyl.utils.GsonUtil;
import com.itislevel.lyl.utils.SAToast;
import com.itislevel.lyl.utils.SharedPreferencedUtils;
import com.itislevel.lyl.utils.TCUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018\5\29 0029.
 */

public class PropertyComplaintFragment2 extends RootCancleFragment<ComplaintPresenter> implements ComplaintContract.View, BaseQuickAdapter.OnItemClickListener, BaseQuickAdapter.RequestLoadMoreListener {

    @BindView(R.id.et_search)
    AppCompatEditText et_search;

    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;

    @BindView(R.id.complint_horst_clear)
    AppCompatImageView complint_horst_clear;

    @BindView(R.id.search_title)
    AppCompatTextView search_title;
    private List<String> horst_list= new ArrayList<>();//历史搜索

    private ComplinHorstAdapter adapter;
    private boolean isReady = false;
    private boolean isLoaded = false;//防止重复加载
    private int load_more =0;
    private int pageIndex = 1;//分页加载时 当前页是第几页
    private int totalPage = 0;//总页数
    private boolean isRefreshing = true;//当前是刷新还是加载
    @Override
    protected int getLayoutId() {
        return R.layout.property_complintfragment2;
    }
    @Override
    protected void initEventAndData() {
        initAdapter();
        et_search.clearFocus();//失去焦点
        //getHistoryList();//得到历史记录数组
        isReady=true;
        lazyLoad();
    }

    @Override
    public void onPause() {
        super.onPause();
        et_search.clearFocus();//失去焦点
    }

    private void initAdapter() {
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        recyclerview.setLayoutManager(manager);
        if (adapter == null){
            adapter = new ComplinHorstAdapter(R.layout.search_item);
            adapter.setOnItemClickListener(this);
            adapter.setOnLoadMoreListener(this,recyclerview);//设置加载更多
            adapter.openLoadAnimation();
            recyclerview.setAdapter(adapter);
        }
        et_search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_SEARCH){
                    if (!TextUtils.isEmpty(et_search.getText().toString()))
                        {
                            pageIndex =1;
                            isRefreshing = true;
                            loadData(et_search.getText().toString().trim());
                        }
                        hideSoftKeyboard(et_search);
                    return true;
                }
                    return false;
            }
        });
    }
    private void loadData(String kerword) {
        loadingDialog.show();
        Map<String, Object> request = new HashMap<>();
        request.put("token", SharedPreferencedUtils.getStr("p_token",""));
        request.put("phone", SharedPreferencedUtils.getStr("proprety_phone",""));
        request.put("keyword",kerword);
        request.put("vid",SharedPreferencedUtils.getStr("vid",""));
        request.put("pageIndex", pageIndex+"");
        request.put("pageSize", Constants.PAGE_NUMBER10+ "");
        System.out.println("JSON********************"+ GsonUtil.obj2JSON(request));
        mPresenter.findComplaintList(GsonUtil.obj2JSON(request));//获取广告
    }

/*    private void getHistoryList() {
        horst_list.clear();
        horst_list.addAll(DBHorstoryUtil.getInstance(getContext())
                .queryHistorySearchList());
        adapter.setNewData(horst_list);
        showViews();
    }
    private void showViews() {
        if (horst_list.size() > 0) {
            complint_horst_clear.setVisibility(View.VISIBLE);
        } else {
            complint_horst_clear.setVisibility(View.GONE);
            View emptyView = View.inflate(getContext(), R.layout.partial_empty_view, null);
            TextView tv_message = emptyView.findViewById(R.id.tv_message);
            tv_message.setText("暂无搜索记录");
            adapter.setEmptyView(emptyView);
        }
    }
*/
    @OnClick({R.id.complint_horst_clear})
    public void onclick(View view)
    {
        switch (view.getId())
        {
            case R.id.complint_horst_clear://清空历史记录
                show_clear_cart();
                /*
                *
                * HistorySearchUtil.getInstance(MainActivity.this)
                        .putNewSearch(searchEdit.getText().toString());//保存记录到数据库
                  getHistoryList();
                * */
       /*         DBHorstoryUtil.getInstance(getContext())
                        .putNewSearch(searchEdit.getText().toString());//保存记录到数据库
                getHistoryList();*/
                break;
        }
    }

    public void show_clear_cart(){
        //实例化建造者
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        //设置警告对话框的标题
        builder.setTitle("历史记录");
        //设置警告显示的图片
//        builder.setIcon(android.R.drawable.ic_dialog_alert);
        //设置警告对话框的提示信息
        builder.setMessage("是否删除历史记录？");
        //设置”正面”按钮，及点击事件
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                DBHorstoryUtil.getInstance(getContext())
                        .deleteAllHistorySearch();
               // getHistoryList();
            }
        });
        //设置“反面”按钮，及点击事件
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();
    }
    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }
    @Override
    public void useNightMode(boolean isNight) {

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
    public void uploadHeader(FileUploadBean fileUploadBean) {

    }

    @Override
    public void complaintType(List<ComplaintTypeBean> data) {

    }

    @Override
    public void addComplaint(String data) {

    }

    @Override
    public void findComplaintList(ComSearchBean bean) {//搜索结果
        loadingDialog.dismiss();
        //返回的数据 第一次
        if ( bean.getList() == null || bean
                .getList().size() <= 0) {
            SAToast.makeText(getContext(),"暂无相关信息").show();
            View emptyView = View.inflate(getContext(), R.layout.partial_empty_view, null);
            TextView viewById = emptyView.findViewById(R.id.tv_message);
            adapter.setEmptyView(emptyView);
        }

        if (isRefreshing) {
            et_search.setText("");
            adapter.setNewData(bean.getList());
        } else {
            adapter.addData(bean.getList());
            adapter.loadMoreComplete();
        }
    }

    @Override
    public void detaComplaintList(List<PropertyDetailBean> bean) {

    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        SAToast.makeText(getContext(),"点击"+this.adapter.getData().get(position).getId()).show();
        Bundle bundle = new Bundle();
        bundle.putString("id",this.adapter.getData().get(position).getId()+"");
        ActivityUtil.getInstance().openActivity(getActivity(), ComplaintDetailActivity.class,bundle);
    }
    public static void hideSoftKeyboard(View view) {
        Context context = view.getContext();
        InputMethodManager imm = (InputMethodManager) context
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    @Override
    protected void lazyLoad() {
        if (!isReady || !isVisible || isLoaded) {
            return;
        }
        isLoaded = true;
        loadData("");
    }

    public  static PropertyComplaintFragment2 newInstance(int num) {
        PropertyComplaintFragment2 f = new PropertyComplaintFragment2();
        // Supply num input as an argument.
        Bundle args = new Bundle();
        args.putInt("flage", num);
        f.setArguments(args);
        return f;
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
            loadData("");
        }
    }
}
