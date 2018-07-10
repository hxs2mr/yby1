package com.itislevel.lyl.widget;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;

import com.flyco.animation.BaseAnimatorSet;
import com.flyco.dialog.widget.base.BaseDialog;
import com.flyco.dialog.widget.base.BottomBaseDialog;
import com.itislevel.lyl.R;
import com.itislevel.lyl.mvp.ui.usermonkey.UserAllMonkeyActivity;
import com.itislevel.lyl.utils.ToastUtil;

import butterknife.BindInt;
import butterknife.BindView;
import butterknife.ButterKnife;

import static com.itislevel.lyl.utils.KeyBordUtil.popSoftKeyboard;
import static com.itislevel.lyl.utils.KeyBordUtil.showSoftKeyboard;
import static com.itislevel.lyl.widget.VerificationCodeView.VCInputType.NUMBERPASSWORD;

/**
 * 作者： XS
 * 邮箱：1363826037@qq.com
 * 描述:
 * 创建时间:  2018\7\9 0009 10:20
 */
public class UserMonkeyPassDialog extends BottomBaseDialog<UserMonkeyPassDialog> implements View.OnClickListener {

    @BindView(R.id.back_im)
    AppCompatImageView back_im;

    @BindView(R.id.monkey_tv)
    AppCompatTextView monkey_tv;

    @BindView(R.id.verificationcodeview)
    VerificationCodeView verificationCodeView;

    UserAllMonkeyActivity allMonkeyActivity;
    private BaseAnimatorSet mWindowInAs;
    private BaseAnimatorSet mWindowOutAs;
    private String monkey;

    public UserMonkeyPassDialog(UserAllMonkeyActivity activity,Context context, View animateView,String monkey) {
        super(context, animateView);
        this.allMonkeyActivity = activity;
        this.monkey = monkey;
    }

    @Override
    public View onCreateView() {
        View inflate = View.inflate(mContext, R.layout.password_popu, null);
        ButterKnife.bind(this, inflate);
        return inflate;
    }

    @Override
    public void setUiBeforShow() {
        monkey_tv.setText("¥"+monkey);
        back_im.setOnClickListener(this);
        verificationCodeView.setOnCodeFinishListener(new VerificationCodeView.OnCodeFinishListener() {
            @Override
            public void onComplete(String content) {
                //验证密码是否正确
                allMonkeyActivity.YzPassword();
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.back_im:
                this.dismiss();
                break;
        }
    }
    @Override
    protected BaseAnimatorSet getWindowInAs() {
        if (mWindowInAs == null) {
            mWindowInAs = new WindowsInAs();
        }
        return mWindowInAs;
    }

    @Override
    protected BaseAnimatorSet getWindowOutAs() {
        if (mWindowOutAs == null) {
            mWindowOutAs = new WindowsOutAs();
        }
        return mWindowOutAs;
    }
    class WindowsInAs extends BaseAnimatorSet {
        @Override
        public void setAnimation(View view) {
            ObjectAnimator rotationX = ObjectAnimator.ofFloat(view, "rotationX", 10, 0f)
                    .setDuration(150);
            rotationX.setStartDelay(200);
            animatorSet.playTogether(
                    ObjectAnimator.ofFloat(view, "scaleX", 1.0f, 0.8f).setDuration(350),
                    ObjectAnimator.ofFloat(view, "scaleY", 1.0f, 0.8f).setDuration(350),
//                    ObjectAnimator.ofFloat(view, "alpha", 1.0f, 0.5f).setDuration(350),
                    ObjectAnimator.ofFloat(view, "rotationX", 0f, 10).setDuration(200),
                    rotationX,
                    ObjectAnimator.ofFloat(view, "translationY", 0, -0.1f * mDisplayMetrics
                            .heightPixels).setDuration(350)
            );
        }
    }
    class WindowsOutAs extends BaseAnimatorSet {
        @Override
        public void setAnimation(View view) {
            ObjectAnimator rotationX = ObjectAnimator.ofFloat(view, "rotationX", 10, 0f)
                    .setDuration(150);
            rotationX.setStartDelay(200);
            animatorSet.playTogether(
                    ObjectAnimator.ofFloat(view, "scaleX", 0.8f, 1.0f).setDuration(350),
                    ObjectAnimator.ofFloat(view, "scaleY", 0.8f, 1.0f).setDuration(350),
//                    ObjectAnimator.ofFloat(view, "alpha", 1.0f, 0.5f).setDuration(350),
                    ObjectAnimator.ofFloat(view, "rotationX", 0f, 10).setDuration(200),
                    rotationX,
                    ObjectAnimator.ofFloat(view, "translationY", -0.1f * mDisplayMetrics
                            .heightPixels, 0).setDuration(350)
            );
        }
    }
}
