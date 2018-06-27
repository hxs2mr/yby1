package com.itislevel.lyl.widget;

import android.content.Context;
import android.view.View;

import com.flyco.dialog.widget.popup.base.BasePopup;
import com.itislevel.lyl.R;

/**
 * desc:功能说明必写
 * user:itisi
 * date:2017/12/12.17:50
 * path:com.itislevel.lyl.widget.FamilyPopup
 **/
public class FamilyPopup extends BasePopup<FamilyPopup> {
    public FamilyPopup(Context context) {
        super(context);
    }

    @Override
    public View onCreatePopupView() {
        return View.inflate(mContext, R.layout.item_pop_del, null);
    }

    @Override
    public void setUiBeforShow() {

    }
}
