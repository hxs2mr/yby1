package com.itislevel.lyl.widget.videorecord.shortvideo.editor.common;

import android.content.Context;
import android.view.View;

import com.itislevel.lyl.R;
import com.itislevel.lyl.widget.videorecord.shortvideo.editor.common.widget.layer
        .TCLayerOperationView;

/**
 * Created by hanszhli on 2017/6/21.
 * <p>
 * 创建 OperationView的工厂
 */

public class TCLayerOperationViewFactory {

    public static TCLayerOperationView newOperationView(Context context) {
        return (TCLayerOperationView) View.inflate(context, R.layout.layout_layer_operation_view, null);
    }
}
