package com.itislevel.lyl.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;

import com.itislevel.lyl.R;
import com.itislevel.lyl.app.App;
import com.orhanobut.logger.Logger;

/**
 * desc:功能说明必写
 * user:itisi
 * date:2018-03-09.14:29
 * path:com.itislevel.lyl.widget.MyHeaderBehavior
 **/
public class MyHeaderBehavior extends CoordinatorLayout.Behavior<View>{

    public MyHeaderBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onLayoutChild(CoordinatorLayout parent, View child, int layoutDirection) {
        Logger.e("onLayoutChild.....");
        CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) child.getLayoutParams();
        if(params!=null && params.height == CoordinatorLayout.LayoutParams.MATCH_PARENT){
            child.layout(0,0,parent.getWidth(),parent.getHeight());
            child.setTranslationY(getHeaderHeight());
            return true;
        }

        return super.onLayoutChild(parent, child, layoutDirection);



    }

    @Override
    public boolean onStartNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull
            View child, @NonNull View directTargetChild, @NonNull View target, int axes, int type) {
//        return super.onStartNestedScroll(coordinatorLayout, child, directTargetChild, target,
//                axes, type);
        return (axes & ViewCompat.SCROLL_AXIS_VERTICAL) != 0;
    }



    @Override
    public void onNestedPreScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull View
            child, @NonNull View target, int dx, int dy, @NonNull int[] consumed, int type){
        // 在这个方法里面只处理向上滑动
        if(dy < 0){
            return;
        }

        float transY =  child.getTranslationY() - dy;
        Logger.e("transY:"+transY+"++++child.getTranslationY():"+child.getTranslationY()+"---->dy:"+dy);
        if(transY > 0){
            child.setTranslationY(transY);
            consumed[1]= dy;
        }
    }

    @Override
    public void onNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull View child,
                               @NonNull View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, int type) {
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed,
                dxUnconsumed, dyUnconsumed, type);

        // 在这个方法里只处理向下滑动
        if(dyUnconsumed >0){
            return;
        }

        float transY = child.getTranslationY() - dyUnconsumed;
        Logger.e("------>transY:"+transY+"****** child.getTranslationY():"+child.getTranslationY()+"--->dyUnconsumed"+dxUnconsumed);
        if(transY > 0 && transY < getHeaderHeight()){
            child.setTranslationY(transY);
        }


    }

    public int getHeaderHeight(){
//        return App.getInstance().getResources().getDimensionPixelOffset(R.dimen.header_height);
        return App.getInstance().getResources().getDimensionPixelOffset(R.dimen.d100);
    }
}
