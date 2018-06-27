package com.itislevel.lyl.mvp.ui.chatkeyboardview;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;


import com.itislevel.lyl.app.Constants;
import com.itislevel.lyl.utils.SharedPreferencedUtils;
import com.itislevel.lyl.utils.SoftKeyboardUtil;
import com.orhanobut.logger.Logger;


/**
 * **********************
 * 功 能:源码来自开源项目
 * https://github.com/dss886/Android-EmotionInputDetector
 * 创建人:itisi
 * 邮  箱:itisivip@qq.com
 * 创建时间:2017/6/23 15:25
 * 修改人:itisi
 * 修改时间: 2017/6/23 15:25
 * 修改内容:itisi
 * *********************
 */

public class EmotionKeyboard {
    private static final String TAG = "EmotionKeyboard";
//    private static final String SHARE_PREFERENCE_NAME = "EmotionKeyboard";
//    private static final String SHARE_PREFERENCE_SOFT_INPUT_HEIGHT = "soft_input_height";

    // TODO: 2018-02-02   ChatActivity

    private Activity mActivity;
    private InputMethodManager mInputManager;//软键盘管理类

    private View mEmotionLayout;//表情布局
    private View mExtendLayout;//扩展布局
    private EditText mEditText;//输入框?
    private View mContentView;//内容布局view,即除了表情布局或者软键盘布局以外的布局，用于固定bar的高度，防止跳闪

    public EmotionKeyboard() {
    }

    /**
     * 外部静态调用
     *
     * @param activity
     * @return
     */
    public static EmotionKeyboard with(Activity activity) {
        EmotionKeyboard emotionInputDetector = new EmotionKeyboard();
        // TODO: 2018-02-02   ChatActivity
        emotionInputDetector.mActivity = activity;
        emotionInputDetector.mInputManager = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);

        return emotionInputDetector;
    }

    /**
     * 绑定内容view，此view用于固定bar的高度，防止跳闪
     *
     * @param contentView
     * @return
     */
    public EmotionKeyboard bindToContent(View contentView) {
        mContentView = contentView;
        return this;
    }

    /**
     * 绑定编辑框
     *
     * @param editText
     * @return
     */
    public EmotionKeyboard bindToEditText(EditText editText) {
        mEditText = editText;
        mEditText.requestFocus();
        mEditText.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_UP
                        && (mEmotionLayout.isShown() || mExtendLayout.isShown())) {
                    lockContentHeight();//显示软件盘时，锁定内容高度，防止跳闪。
                    Log.i(TAG, "onTouch: ");
                    hideEmotionLayout(true);//隐藏表情布局，显示软件盘
                    hideExtionLayout(false);//上面已经显示 这里不用显示了
                    //软件盘显示后，释放内容高度
                    mEditText.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            unlockContentHeightDelayed();
                        }
                    }, 200L);
                }
                return false;
            }
        });

        return this;
    }

    /**
     * 绑定表情按钮
     *
     * @param emotionButton
     * @return
     */
    public EmotionKeyboard bindToEmotionButton(View emotionButton) {
        emotionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: 2017/7/20  qq 都没有滚动 暂时注释
//                mActivity.recyclerSmoothScrollToBottom();//内容布局滚动到底部
                if (mExtendLayout.isShown()) {
                    mExtendLayout.setVisibility(View.GONE);
                }
                if (mEmotionLayout.isShown()) {
                    lockContentHeight();//显示软件盘时，锁定内容高度，防止跳闪。
                    hideEmotionLayout(true);//隐藏表情布局，显示软件盘
                    unlockContentHeightDelayed();//软件盘显示后，释放内容高度
                } else {
                    if (isSoftInputShown()) { //同上
                        lockContentHeight();
                        showEmotionLayout();
                        unlockContentHeightDelayed();
                    } else {
                        showEmotionLayout();//两者都没显示，直接显示表情布局
                    }
                }
            }
        });
        return this;
    }

    public EmotionKeyboard bindToExtentionButton(View extendButton) {
        extendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: 2017/7/20  qq 都没有滚动 暂时注释
//                mActivity.recyclerSmoothScrollToBottom();//内容布局滚动到底部
                if (mEmotionLayout.isShown()) {
                    mEmotionLayout.setVisibility(View.GONE);
                }
                if (mExtendLayout.isShown()) {
                    lockContentHeight();//显示软件盘时，锁定内容高度，防止跳闪。
                    hideExtionLayout(true);//隐藏扩展布局，显示软件盘
                    unlockContentHeightDelayed();//软件盘显示后，释放内容高度
                } else {
                    if (isSoftInputShown()) {//同上
                        lockContentHeight();
                        showExtendLayout();
                        unlockContentHeightDelayed();
                    } else {
                        showExtendLayout();//两者都没显示，直接显示扩展布局
                    }
                }
            }
        });
        return this;
    }

    /**
     * 设置表情内容布局
     *
     * @param emotionView
     * @return
     */
    public EmotionKeyboard setEmotionView(View emotionView) {
        mEmotionLayout = emotionView;
        return this;
    }

    public EmotionKeyboard setExtendView(View extendView) {
        mExtendLayout = extendView;
        return this;
    }

    public EmotionKeyboard build() {
        //设置软件盘的模式：SOFT_INPUT_ADJUST_RESIZE
        // 这个属性表示Activity的主窗口总是会被调整大小，从而保证软键盘显示空间。
        //从而方便我们计算软件盘的高度
        mActivity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN |
                WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        //隐藏软件盘
        hideSoftInput();
        return this;
    }

    /**
     * 点击返回键时先隐藏表情布局
     *
     * @return
     */
    public boolean interceptBackPress() {
        if (mEmotionLayout.isShown()) {
            hideEmotionLayout(false);
            return true;
        }
        if (mExtendLayout.isShown()) {
            hideExtionLayout(false);
            return true;
        }
        return false;
    }

    /**
     * 显示表情布局
     */
    public void showEmotionLayout() {
        int softInputHeight = getSupportSoftInputHeight();
        if (softInputHeight == 0) {
            softInputHeight = getKeyBoardHeight();
        }
        hideSoftInput();
        mEmotionLayout.getLayoutParams().height = softInputHeight;
        mEmotionLayout.setVisibility(View.VISIBLE);
    }

    /**
     * 隐藏表情布局
     *
     * @param showSoftInput 是否显示软件盘
     */
    public void hideEmotionLayout(boolean showSoftInput) {
        if (mEmotionLayout.isShown()) {
            mEmotionLayout.setVisibility(View.GONE);
            if (showSoftInput) {
                showSoftInput();
            }
        }
    }

    public void hideEmotionLayout() {
        mEmotionLayout.setVisibility(View.GONE);
        mExtendLayout.setVisibility(View.GONE);
    }




    /**
     * 显示扩展菜单布局
     */
    public void showExtendLayout() {
        int softInputHeight = getSupportSoftInputHeight();
        if (softInputHeight == 0) {
            softInputHeight = getKeyBoardHeight();
        }
        hideSoftInput();

        mExtendLayout.getLayoutParams().height = softInputHeight;
        mExtendLayout.setVisibility(View.VISIBLE);
    }

    /**
     * 隐藏表情布局
     *
     * @param showSoftInput 是否显示软件盘
     */
    public void hideExtionLayout(boolean showSoftInput) {
        if (mExtendLayout.isShown()) {
            mExtendLayout.setVisibility(View.GONE);
            if (showSoftInput) {
                showSoftInput();
            }
        }
    }

    public boolean isAtLeastShow() {
        return mExtendLayout.isShown() || mEmotionLayout.isShown();
//        return mExtendLayout.getHeight();
    }

    /**
     * 锁定内容高度，防止跳闪
     */
    private void lockContentHeight() {
        LinearLayoutCompat.LayoutParams params = (LinearLayoutCompat.LayoutParams) mContentView.getLayoutParams();
        params.height = mContentView.getHeight();
        params.weight = 0.0F;
    }

    /**
     * 释放被锁定的内容高度
     */
    private void unlockContentHeightDelayed() {
        mEditText.postDelayed(new Runnable() {
            @Override
            public void run() {
                ((LinearLayoutCompat.LayoutParams) mContentView.getLayoutParams()).weight = 1.0F;
            }
        }, 200L);
    }

    /**
     * 编辑框获取焦点，并显示软件盘
     */
    public void showSoftInput() {
        mEditText.requestFocus();
        mEditText.post(new Runnable() {
            @Override
            public void run() {
                mInputManager.showSoftInput(mEditText, 0);
            }
        });
    }

    /**
     * 隐藏软件盘
     */
    private void hideSoftInput() {
        mEditText.clearFocus();
        mInputManager.hideSoftInputFromWindow(mEditText.getWindowToken(), 0);
    }

    /**
     * 是否显示软件盘
     * @return
     */
    private boolean isSoftInputShown() {
        return getSupportSoftInputHeight() != 0;
    }

    public int getSupportSoftInputHeight() {
        int supportSoftInputHeight = SoftKeyboardUtil.getSupportSoftInputHeight(mActivity);
        Logger.i("计算高度-软键盘:"+supportSoftInputHeight);
        return supportSoftInputHeight;
    }



    /**
     * 获取软键盘高度，由于第一次直接弹出表情时会出现小问题，
     * 787是一个均值，作为临时解决方案
     *
     * @return
     */
    public int getKeyBoardHeight() {
        int anInt = SharedPreferencedUtils.getInt(Constants.SOFT_INPUT_HEIGHT, 554);//554
        Logger.i("获取缓存-软键盘:"+anInt);
        return anInt;
    }

}
