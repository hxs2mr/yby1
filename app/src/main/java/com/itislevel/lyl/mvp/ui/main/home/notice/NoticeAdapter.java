package com.itislevel.lyl.mvp.ui.main.home.notice;

import android.content.Context;
import android.view.View;

/**
 * Created by HXS on 2017/5/19.
 */

public  abstract  class NoticeAdapter { 
    public abstract int getCount();

    public abstract View getView(Context context, int position);
}