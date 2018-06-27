package com.itislevel.lyl.widget;

import com.chad.library.adapter.base.loadmore.LoadMoreView;
import com.itislevel.lyl.R;

/**
 * desc:功能说明必写
 * user:itisi
 * date:2017/12/5.15:57
 * path:com.itislevel.lyl.widget.LoadMoreViewNone
 **/
public class LoadMoreViewNone extends LoadMoreView {
    @Override
    public int getLayoutId() {
        super.setLoadMoreEndGone(false);
        return R.layout.view_load_more;
    }

    @Override
    protected int getLoadingViewId() {
        return R.id.load_more_loading_view;
    }

    @Override
    protected int getLoadFailViewId() {
        return R.id.load_more_loading_view;
    }

    @Override
    protected int getLoadEndViewId() {
        return R.id.load_more_loading_view;
    }


}
