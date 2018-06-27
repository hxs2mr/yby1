package com.itislevel.lyl.mvp.ui.main.dynamic.childfragment;

import com.itislevel.lyl.base.BaseFragment;
import com.itislevel.lyl.base.BasePresenter;

/**
 * Created by Administrator on 2018\5\2 0002.
 */

public abstract class RootCancleFragment <T extends BasePresenter> extends BaseFragment<T>{
    /** Fragment当前状态是否可见 */
    protected boolean isVisible;
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(getUserVisibleHint()) {
            isVisible = true;
            lazyLoad();   //等当前的Fragment可见以后再加载数据，网络请求等
        } else {
            isVisible = false;
        }
    }
  /*
    * to avoid viewpager load invisible pages,
    * extend this fragment, fragment will load data later within method delayLoad();
    * */

    /**
     * 延迟加载
     * 子类必须重写此方法
     */
    protected abstract void lazyLoad();
}
