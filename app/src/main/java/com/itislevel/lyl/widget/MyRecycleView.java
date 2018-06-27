package com.itislevel.lyl.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import com.itislevel.lyl.mvp.ui.main.dynamic.BaseDynamicAdapter;

/**
 * Created by Administrator on 2018\5\23 0023.
 *
 *
 * 解决RecycleView滑动过程中的卡顿现象
 *
 * 思路：

 1.能否检测是不是快速滚动，只有快速滚动中不加载图片及复杂布局。

 2.滚动停止时，判断是否存在图片或布局未加载，如果有，才加载。否则，不做任何操作。

 3.如何判断快速滚动？

 对于快速滚动的监听，方法有很多，比如列表滚动中计算一下滚动速度，如果速度大于某个值，我们就认为是快速滚动。
 */

public class MyRecycleView extends RecyclerView implements  GestureDetector.OnGestureListener{
    private final GestureDetector mGestureDetector;//手势监听
    private int id;//用来区分不同页面

    private int scroll_count;

    private boolean scrlled;//是否滚动了,优化用
    private BaseDynamicAdapter mAdapter;//Item适配器

    public RecyclerView mRecyclerView;//列表控件
    private LayoutManager layout;
    private Context context;
    public MyRecycleView(Context context) {
        this(context, null);
    }
    public MyRecycleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        addOnScrollListener(new ImageAutoLoadScrollListener());
        this.mGestureDetector = new GestureDetector(context, this);
    }
    /**
     * 设置adapter
     */

    @Override
    public void setAdapter(Adapter adapter) {
        if(adapter instanceof BaseDynamicAdapter) {
            this.mAdapter = (BaseDynamicAdapter) adapter;
        }
        super.setAdapter(adapter);
    }
    @Override
    public void setLayoutManager(LayoutManager layout) {
        this.layout = layout;
        super.setLayoutManager(layout);
    }
    @Override
    public boolean onDown(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        if(Math.abs(v1) > 4000){
            mAdapter.setScrolling(true);
        }
        return false;
    }
    public class ImageAutoLoadScrollListener extends OnScrollListener{

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            if(dy > 40){
                scrlled = true;
            }
            super.onScrolled(recyclerView, dx, dy);
        }

        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            switch (newState){
                case SCROLL_STATE_IDLE: // The RecyclerView is not currently scrolling.
                    //对于滚动不加载图片的尝试
                    if(mAdapter.getScrolling() && scrlled) {
                        //对于滚动不加载图片的尝试
                        mAdapter.setScrolling(false);
                        mAdapter.notifyDataSetChanged();
                    }
                    scrlled = false;
                    break;
                case SCROLL_STATE_DRAGGING: // The RecyclerView is currently being dragged by outside input such as user touch input.
                    mAdapter.setScrolling(true);
                    scrlled = false;
                    //   mAdapter.setScrolling(false);
                    break;
                case SCROLL_STATE_SETTLING: // The RecyclerView is currently animating to a final position while not under
                    mAdapter.setScrolling(true);
                    scrlled = false;
                    //  mAdapter.setScrolling(true);
                    break;
            }
        }
    }
}
