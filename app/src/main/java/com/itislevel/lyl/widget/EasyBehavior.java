package com.itislevel.lyl.widget;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.orhanobut.logger.Logger;

/**
 * desc:功能说明必写
 * user:itisi
 * date:2018-03-09.15:45
 * path:com.itislevel.testbehavior.EasyBehavior
 **/
public class EasyBehavior extends CoordinatorLayout.Behavior<View >{
    public EasyBehavior() {
    }

    public EasyBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    // 代表寻找被观察View
    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, View  child, View dependency) {
        Logger.e("on");
        return super.layoutDependsOn(parent, child, dependency);
        //告知监听的dependency是Button
//        return dependency instanceof Button;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, View  child, View dependency) {
        //return super.onDependentViewChanged(parent, child, dependency);
        child.setX(dependency.getX()+200);
        child.setY(dependency.getY()+200);
//        child.setText(dependency.getX()+","+dependency.getY());
        Logger.e("change");

        return true;
    }
}
