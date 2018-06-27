package com.itislevel.lyl.widget;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.flyco.animation.BaseAnimatorSet;
import com.flyco.dialog.widget.base.BottomBaseDialog;
import com.google.android.flexbox.FlexboxLayout;
import com.itislevel.lyl.R;
import com.itislevel.lyl.mvp.model.bean.HappyBlessUsualLanguageBean;
import com.itislevel.lyl.mvp.ui.blessing.BlessingDetailActivity;
import com.itislevel.lyl.utils.ToastUtil;
import com.itislevel.lyl.utils.rxbus.RxBus;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BlessYUDialog extends BottomBaseDialog<BlessYUDialog> implements View
        .OnClickListener {
    @BindView(R.id.ll_bless_yu_parent)
    LinearLayout ll_bless_yu_parent;

    @BindView(R.id.fbl_parent)
    FlexboxLayout fbl_parent;

    @BindView(R.id.et_content)
    EditText et_content;
    @BindView(R.id.tv_send)
    TextView tv_send;
//
    @BindView(R.id.tv_bless_temp)
    TextView tv_bless_temp;
    @BindView(R.id.tv_bless_temp1)
    TextView tv_bless_temp1;
    @BindView(R.id.tv_bless_temp3)
    TextView tv_bless_temp3;
    @BindView(R.id.tv_bless_temp4)
    TextView tv_bless_temp4;

    Context context;
    HappyBlessUsualLanguageBean usualLanguageBean;

    public BlessYUDialog(Context context, View animateView, HappyBlessUsualLanguageBean
            usualLanguageBean) {
        super(context, animateView);
        this.usualLanguageBean = usualLanguageBean;
        this.context = context;
    }


    @Override
    public View onCreateView() {
        View inflate = View.inflate(mContext, R.layout.item_bless_yu, null);
        ButterKnife.bind(this, inflate);


        return inflate;
    }

    @Override
    public void setUiBeforShow() {

        ViewGroup.LayoutParams layoutParams = tv_bless_temp.getLayoutParams();


        if (usualLanguageBean != null && usualLanguageBean.getModelVo() != null &&
                usualLanguageBean.getModelVo().size() > 0) {

            for (HappyBlessUsualLanguageBean.ModelVoBean item : usualLanguageBean.getModelVo()) {
                TextView textView = new TextView(context);
                textView.setLayoutParams(layoutParams);
                textView.setOnClickListener(this);
                textView.setText(item.getValue());
                textView.setBackground(context.getResources().getDrawable(R.drawable
                        .shape_et_focus));
                textView.setVisibility(View.VISIBLE);
                textView.setTextSize(14F);
                textView.setTextColor(Color.parseColor("#ff7a00"));
                textView.setPadding(8, 8, 8, 8);
                textView.setGravity(Gravity.CENTER);
                fbl_parent.addView(textView);
            }

        }
        tv_bless_temp.setVisibility(View.VISIBLE);
        tv_send.setOnClickListener(this);
        tv_bless_temp.setOnClickListener(this);
        tv_bless_temp1.setOnClickListener(this);
        tv_bless_temp3.setOnClickListener(this);
        tv_bless_temp4.setOnClickListener(this);
}


    @Override
    public void show() {
        super.show();
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {

            @Override
            public void run() {
                et_content.setFocusable(true);
                InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Context
                        .INPUT_METHOD_SERVICE);
                inputMethodManager.showSoftInput(et_content,0);
            }  }, 0);
    }

    private BaseAnimatorSet mWindowInAs;
    private BaseAnimatorSet mWindowOutAs;

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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_send:
                String str = et_content.getText().toString();
                if (TextUtils.isEmpty(str)) {
                    ToastUtil.Info("请输入内容");
                    return;
                }
                RxBus.getInstance().post(RxBus.getInstance().getTag(BlessingDetailActivity.class,
                        RxBus.TAG_UPDATE), str);

                break;
            case R.id.tv_bless_temp:
                RxBus.getInstance().post(RxBus.getInstance().getTag(BlessingDetailActivity.class,
                        RxBus.TAG_UPDATE), "生活如意，事业高升");
                break;
            case R.id.tv_bless_temp1:
                RxBus.getInstance().post(RxBus.getInstance().getTag(BlessingDetailActivity.class,
                        RxBus.TAG_UPDATE), "事业有成，美梦成真");
                break;
            case R.id.tv_bless_temp3:
                RxBus.getInstance().post(RxBus.getInstance().getTag(BlessingDetailActivity.class,
                        RxBus.TAG_UPDATE), "福气东来，鸿运通天");
                break;
            case R.id.tv_bless_temp4:
                RxBus.getInstance().post(RxBus.getInstance().getTag(BlessingDetailActivity.class,
                        RxBus.TAG_UPDATE), "生意兴隆，事业高升");
                break;
            default:
//                ToastUtil.Info(((TextView)v).getText().toString());
                et_content.setText(((TextView) v).getText().toString());

                break;
        }
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
