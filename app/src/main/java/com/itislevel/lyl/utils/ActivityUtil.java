package com.itislevel.lyl.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;


/**
 * **********************
 * 功 能:Activity工具类 copy
 * 创建人:itisi
 * 邮  箱:itisivip@qq.com
 * 创建时间:2017/7/11 10:34
 * 修改人:itisi
 * 修改时间: 2017/7/11 10:34
 * 修改内容:itisi
 * *********************
 */
public class ActivityUtil {

    private static final String TAG = ActivityUtil.class.getSimpleName();
    private static ActivityUtil instance = null;
//	private ProgressDialog dialog;
//	private final List<Activity> activitys = new ArrayList<>();

    public static ActivityUtil getInstance() {
        if (instance == null) {
            synchronized (ActivityUtil.class) {
                if (instance == null) {
                    instance = new ActivityUtil();
                }
            }
        }
        return instance;
    }

    /**
     * 保存Activity
     *
     * @param act
     */
    public void save(Activity act) {
//		if(act!=null) {
//			activitys.add(act);
//		}
    }

    /**
     * 移出并注销所有Activity
     */
    public void finishAll() {
//		for(int i=activitys.size()-1;i>=0;i--) {
//			// 逆序才可以边移出边注销
//			Activity act = activitys.remove(i);
//			if(act!=null) act.finish();
//		}

    }

    /**
     * 移出Activity
     *
     * @param act
     */
    public void remove(Activity act) {
//		activitys.remove(act);
    }


    /**
     * 显示确认对话框
     *
     * @param context 上下文
     * @param title   标题
     * @param message 提示信息
     */
    public void showConfirmDialog(Context context, String title, String message) {
        new AlertDialog.Builder(context).setTitle(title).setMessage(message)
                .setPositiveButton(android.R.string.ok,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                dialog.dismiss();
                            }
                        }).show();
    }

    /**
     * 加载系统等待框
     *
     * @param context 上下文
     */
    public void showDialog(Context context) {
//		dialog =  new ProgressDialog(context);
//		dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
//		    // 设置ProgressDialog 提示信息
//		dialog.setMessage(ResourcesUtil.getResourceString(context, R.string.wait));
//		dialog.show();
    }

    /**
     * 关闭系统等待框
     */
    public void dismissDialog() {
//		if(dialog != null && dialog.isShowing()) {
//			dialog.cancel();
//			dialog = null;
//		}
    }

    /**
     * 不带bundle的Activity跳转-带有转场动画的
     *
     * @param activity
     * @param clazz
     */
    public void openActivity(Activity activity, Class<?> clazz) {
        openActivity(activity, clazz, null, SceneAnim.AnimType.ZOOM_IN);
    }
    public void openActivityTop(Activity activity, Class<?> clazz) {
        Intent intent = new Intent(activity, clazz);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        activity.startActivity(intent);
        SceneAnim.openActivityWithAnim(activity, SceneAnim.AnimType.ZOOM_IN);
    }


    /**
     * 带bundle的Activity跳转-带有转场动画的 默认是缩放进入
     *
     * @param activity
     * @param clazz
     * @param bundle
     */
    public void openActivity(Activity activity, Class<?> clazz, Bundle bundle) {
        openActivity(activity, clazz, bundle, SceneAnim.AnimType.ZOOM_IN);
    }

    /**
     * 不带bundle的Activity跳转-带有转场动画的 可指定转场动画
     *
     * @param activity
     * @param clazz
     * @param animInType
     */
    public void openActivity(Activity activity, Class<?> clazz, SceneAnim.AnimType animInType) {
        openActivity(activity, clazz, null, animInType);
    }

    /**
     * 带bundle的Activity跳转-带有转场动画的 可指定转场动画
     *
     * @param activity
     * @param clazz
     * @param bundle
     * @param animInType
     */
    public void openActivity(Activity activity, Class<?> clazz, Bundle bundle, SceneAnim.AnimType animInType) {
        Intent intent = new Intent(activity, clazz);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        activity.startActivity(intent);
        SceneAnim.openActivityWithAnim(activity, animInType);
    }

    /**
     * 关闭当前界面--带有转场动画的
     *
     * @param activity
     */
    public void closeActivity(Activity activity) {
        activity.finish();
        //加上动画的时候  侧滑关闭之后 还有一小段动画
		SceneAnim.closeActivityWithAnim(activity);
    }

}
