package com.itislevel.lyl.base;

import android.annotation.TargetApi;
import android.app.Activity;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ContentFrameLayout;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.DisplayCutout;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.itislevel.lyl.R;
import com.itislevel.lyl.app.App;
import com.itislevel.lyl.utils.ActivityUtil;
import com.itislevel.lyl.utils.PhoneUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.imid.swipebacklayout.lib.SwipeBackLayout;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;
import qiu.niorgai.StatusBarCompat;

import static com.itislevel.lyl.utils.Dp_to_Px.dip2px;
import static com.itislevel.lyl.utils.Dp_to_Px.px2dip;
import static com.itislevel.lyl.utils.PhoneUtil.getNotchSizeAtHuawei;
import static com.itislevel.lyl.utils.PhoneUtil.getSystemModel;
import static com.itislevel.lyl.utils.PhoneUtil.hasNotchAtHuawei;
import static com.itislevel.lyl.utils.PhoneUtil.hasNotchAtVivo;

/**
 * **********************
 * 功 能:非MVP的Activity的基类
 * 创建人:itisi
 * 邮  箱:itisivip@qq.com
 * 创建时间:2017/7/5 16:53
 * 修改人:itisi
 * 修改时间: 2017/7/5 16:53
 * 修改内容:itisi
 * *********************
 */

public abstract class NoMVPActivity extends SwipeBackActivity { //SwipeBackActivity AppCompatActivity
    protected Activity mActivity;
    private Unbinder mUnbinder;

    @BindView(R.id.toolbar_all)
    protected Toolbar mToolbar;
    //    @BindView(R.id.toolbar_search)
//    protected SearchView mSearchView;
    @BindView(R.id.toolbar_title)
    TextView mToolbarTitle;
    @BindView(R.id.toolbar_more)
    TextView mToolbalMore;

    @BindView(R.id.iv_back_linear)
    LinearLayoutCompat iv_back_linear;

    @BindView(R.id.iv_more_linear)
    LinearLayoutCompat iv_more_linear;

    protected SwipeBackLayout mSwipeBackLayout;

    @BindView(R.id.iv_back)
    ImageView mImageViewBack;
    public static  boolean ISLIUHAN = false;
    public static  int ISLIUHANNUMBER = 0;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        //将window的背景图设置为空
        getWindow().setBackgroundDrawable(null);
        StatusBarCompat.translucentStatusBar(this, true);
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        if(Build.VERSION.SDK_INT >= 28)
        {
            getNotchParams();//Android P之后的解决方式
        }
      //  StatusBarCompat.translucentStatusBar(this, true);//沉侵式状态栏
        mUnbinder = ButterKnife.bind(this);
        mActivity = this;
        onViewCreated();
        initToolbar();
//        initSearchView();
        setSwipeBackOriginal();
        initSwipeBack();//初始化 侧滑返回
         initEventAndData();
        System.out.println("手机的型号******************"+getSystemModel());
        if(ISLIUHAN)//Android P刘海屏适配
        {
            LinearLayoutCompat.LayoutParams linearParams = (LinearLayoutCompat.LayoutParams) mToolbar.getLayoutParams();
            linearParams.height =ISLIUHANNUMBER+dip2px(this,40);
            mToolbar.setLayoutParams(linearParams);
        }else {//在P之前的刘海屏适配  目前只适配到华为 vivo   oppo
            System.out.println("手机的型号******************"+getSystemModel());
            if(hasNotchAtHuawei(this))//华为
            {
                ISLIUHAN = true;
                ISLIUHANNUMBER  = getNotchSizeAtHuawei(this)[1];
            }else   if(hasNotchAtVivo(this)){//vivo
                ISLIUHAN = true;
                ISLIUHANNUMBER  = dip2px(this,27);
            }else   if(PhoneUtil.hasNotchAtOPPO(this)){//oppo
                ISLIUHAN = true;
                ISLIUHANNUMBER  = 85;
            }
        }

    }

    @TargetApi(28)
    public void getNotchParams() {
        final View decorView = getWindow().getDecorView();
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_DEFAULT;
        getWindow().setAttributes(lp);

        decorView.post(new Runnable() {
            @Override
            public void run() {
                DisplayCutout displayCutout = decorView.getRootWindowInsets().getDisplayCutout();
                Log.e("TAG", "安全区域距离屏幕左边的距离 SafeInsetLeft:" + displayCutout.getSafeInsetLeft());
                Log.e("TAG", "安全区域距离屏幕右部的距离 SafeInsetRight:" + displayCutout.getSafeInsetRight());
                Log.e("TAG", "安全区域距离屏幕顶部的距离 SafeInsetTop:" + displayCutout.getSafeInsetTop());
                Log.e("TAG", "安全区域距离屏幕底部的距离 SafeInsetBottom:" + displayCutout.getSafeInsetBottom());
                List<Rect> rects = displayCutout.getBoundingRects();
                if (rects == null || rects.size() == 0) {
                    Log.e("TAG", "不是刘海屏");
                } else {
                    Log.e("TAG", "刘海屏数量:" + rects.size());
                    ISLIUHAN = true;
                    ISLIUHANNUMBER  = displayCutout.getSafeInsetTop();
                    for (Rect rect : rects) {
                        Log.e("TAG", "刘海屏区域：" + rect);
                    }
                }
            }
        });
    }

    public TextView getTvMorView(){
        iv_more_linear.setVisibility(View.VISIBLE);
        return mToolbalMore;
    }

    public TextView getTvTitleView(){
        return mToolbarTitle;
    }

    /**
     * 初始化 toolbar
     */
    private void initToolbar() {
        boolean isShowNavigationIcon = setIsNavigationIconShow();//是否显示返回图标
        String title = setToolbarTvTitle();//标题
        String moreTxt = setToolbarMoreTxt();//更多-文字--可能会换成 字体图标
        int menuLayoutId = setMenuLayoutId();//溢出菜单布局id
        Toolbar.OnMenuItemClickListener onMenuItemClickListener = setMenuItemClickListener();//溢出菜单点击事件
        if (onMenuItemClickListener!=null){
            iv_more_linear.setVisibility(View.INVISIBLE);
        }
        if (!isToolbarTransparent()) {
            setToolbarBackground(R.color.new_tool);
        }
        if (isShowNavigationIcon) {
//            mToolbar.setNavigationIcon(R.mipmap.menu_back);
//            mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    ActivityUtil.getInstance().closeActivity(mActivity);
//                }
//            });
            iv_back_linear.setVisibility(View.VISIBLE);
            iv_back_linear.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    iv_back_linear.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            ActivityUtil.getInstance().closeActivity(mActivity);
                        }
                    },0);
                }
            });
        }

        //填充的方式 但是不够自由 切换图标 不够自由

        if (!TextUtils.isEmpty(title)) {
            mToolbarTitle.setText(title);
//            mToolbar.setTitle(title);
        }
        if (!TextUtils.isEmpty(moreTxt)) {
            iv_more_linear.setVisibility(View.VISIBLE);
            mToolbalMore.setText(moreTxt);
        }
        if (menuLayoutId != 0 && onMenuItemClickListener != null) {
            //设置 Toolbar menu
            mToolbar.inflateMenu(menuLayoutId);
            // 设置溢出菜单的图标 --这个图标可传  可不传
            mToolbar.setOverflowIcon(getResources().getDrawable(R.mipmap.menu_three_circle));
            // 设置menu item 点击事件
            mToolbar.setOnMenuItemClickListener(onMenuItemClickListener);
        }

    }

    /**
     * 是否显示返回按钮
     *
     * @return
     */
    protected boolean setIsNavigationIconShow() {
        return true;
    }

    /**
     * 设置标题--此方法一般在进入页面的时候调用,且标题不会常变
     *
     * @return
     */
    protected String setToolbarTvTitle() {
        return "";
    }

    /**
     * 设置标题--此方法一般用于动态改变title
     *
     * @param title
     */
    protected void setToolbarTvTitle(String title) {
        mToolbarTitle.setText(title);
    }

    protected void setToolbarTitle(String title) {
        mToolbar.setTitle(title);
    }

    /**
     * 标题点击事件
     * @param clickListener
     */
    protected void setToolbarTvClickListener(View.OnClickListener clickListener) {
        mToolbarTitle.setOnClickListener(clickListener);

    }

    /**
     * 标题长安事件
     * @param longClickListener
     */
    protected void setToolbarTvLongClickListener(View.OnLongClickListener longClickListener) {
        mToolbarTitle.setOnLongClickListener(longClickListener);
    }
    /**
     * 设置更多---右边的文字 将来可换成子图图标
     *
     * @return
     */
    protected String setToolbarMoreTxt() {
        return "";
    }
    /**
     * 动态设置 更多 标题的文字
     * @param title
     */
    protected void setToolbarMoreTxt(String title) {
        iv_more_linear.setVisibility(View.VISIBLE);
        mToolbalMore.setText(title);
    }
    /**
     * 给更多---设置单击事件--必须设置文本内容 菜单才会显示-才有点击效果
     *
     * @param clickListener
     */
    protected void setToolbarMoreClickListener(View.OnClickListener clickListener) {
        iv_more_linear.setOnClickListener(clickListener);
    }
    /**
     * 设置溢出菜单布局---
     * 还需设置溢出菜单的点击事件
     *
     * @return
     */
    protected int setMenuLayoutId() {
        return 0;
    }

    /**
     * 设置溢出菜单的点击事件---还需设置溢出菜单布局
     *
     * @return
     */
    protected Toolbar.OnMenuItemClickListener setMenuItemClickListener() {
        return null;
    }

    /**
     * 隐藏toolbar
     */
    protected void setToolbarHide() {
        mToolbar.setVisibility(View.GONE);
    }
    /**
     * 设置toolbar的背景颜色
     *
     * @param color
     */
    protected void setToolbarBackground(int color) {
        mToolbar.setBackground(getResources().getDrawable(R.drawable.login_tool_bar));
    }

    /**
     * 是否是透明 即不设置颜色
     *
     * @return
     */
    protected boolean isToolbarTransparent() {
        return false;
    }

    /**
     * 禁止滑动关闭 主页用 Splash页面用
     */
    protected boolean setSwipeEnabled() {
        return true;
    }

    /**
     * 滑动关闭的方向 默认是从做左向右滑动
     * swipeBackOriginal=SwipeBackLayout.EDGE_LEFT;
     * 可设置EDGE_LEFT, EDGE_RIGHT, EDGE_ALL, EDGE_BOTTOM
     */
    public int setSwipeBackOriginal() {
        return SwipeBackLayout.EDGE_LEFT;
    }

    private void initSwipeBack() {
        mSwipeBackLayout = getSwipeBackLayout();
        mSwipeBackLayout.setEdgeTrackingEnabled(setSwipeBackOriginal());
        mSwipeBackLayout.setEnableGesture(setSwipeEnabled());
    }


    /**
     * 返回布局资源的id
     *
     * @return
     */
    protected abstract int getLayoutId();

    /**
     * 初始化事件和数据
     */
    protected abstract void initEventAndData() ;

    /**
     * 视图创建完成以后 可能需要做的事
     */
    protected void onViewCreated() {
        App.getInstance().addActivity(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        App.getInstance().removeActivity(this);
        mUnbinder.unbind();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        ActivityUtil.getInstance().closeActivity(this);
    }
}
