package com.itislevel.lyl.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * **********************
 * 功 能:非MVP的Fragment的基类
 * 创建人:itisi
 * 邮  箱:itisivip@qq.com
 * 创建时间:2017/7/5 16:53
 * 修改人:itisi
 * 修改时间: 2017/7/5 16:53
 * 修改内容:itisi
 * *********************
 */

public abstract class NoMVPFragment extends Fragment {//SupportFragment Fragment
    /**
     * 根布局
     */
    protected View mRootView;
    /**
     * 上下文
     */
    protected Activity mActivity;
    /**
     * 上下文
     */
    protected Context mContext;
    /**
     * butterknife
     */
    private Unbinder mUnbinder;
    /**
     * 是否初始化
     */
    protected boolean isInited=false;

    @Override
    public void onAttach(Context context) {
        mActivity= (Activity) context;
        mContext=context;
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView=inflater.inflate(getLayoutId(),null,false);
        mUnbinder= ButterKnife.bind(this,mRootView);
        return mRootView;

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        isInited=true;
        initEventAndData();
    }

    //这个是 supportFragment 中的,因为如果要使用这个 就必须使用Support 而我以后要使用SwipeBack
    //所以放弃
    //    @Override
//    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
//        super.onLazyInitView(savedInstanceState);
//        isInited=true;
//        initEventAndData();
//    }


    /**
     * 返回布局资源的id
     * @return
     */
    protected abstract int getLayoutId();

    /**
     * 初始化事件和数据
     */
    protected abstract void initEventAndData();

    @Override
    public void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
    }
}
