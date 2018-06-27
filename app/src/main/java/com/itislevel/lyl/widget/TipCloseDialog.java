package com.itislevel.lyl.widget;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * desc:功能说明必写
 * user:itisi
 * date:2017/11/22.11:08
 * path:com.itislevel.lyl.widget.TipCloseDialog
 **/
public class TipCloseDialog extends Dialog {
    public TipCloseDialog(@NonNull Context context) {
        super(context);
    }

    public TipCloseDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected TipCloseDialog(@NonNull Context context, boolean cancelable, @Nullable
            OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }
}
