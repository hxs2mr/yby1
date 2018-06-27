package com.itislevel.lyl.widget;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.flyco.animation.BaseAnimatorSet;
import com.flyco.dialog.widget.NormalDialog;
import com.flyco.dialog.widget.internal.BaseAlertDialog;
import com.itislevel.lyl.R;
import com.itislevel.lyl.mvp.ui.livingexpensess.LivingPropertyListActivity;
import com.itislevel.lyl.utils.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * desc:功能说明必写
 * user:itisi
 * date:2018-02-08.13:10
 * path:com.itislevel.lyl.widget.PropertyDialog
 **/
public class PropertyDialog extends BaseAlertDialog<PropertyDialog> {


    @BindView(R.id.tv_one)
    TextView tv_one;
    @BindView(R.id.tv_tow)
    TextView tv_tow;
    @BindView(R.id.tv_three)
    TextView tv_three;
    @BindView(R.id.tv_four)
    TextView tv_four;

    @BindView(R.id.tv_five)
    TextView tv_five;
    @BindView(R.id.tv_six)
    TextView tv_six;
    @BindView(R.id.tv_seven)
    TextView tv_seven;
    @BindView(R.id.tv_eight)
    TextView tv_eight;

    @BindView(R.id.tv_nine)
    TextView tv_nine;
    @BindView(R.id.tv_ten)
    TextView tv_ten;
    @BindView(R.id.tv_eleven)
    TextView tv_eleven;
    @BindView(R.id.tv_twelve)
    TextView tv_twelve;

    @BindView(R.id.btn_ok)
    TextView btn_ok;

    LivingPropertyListActivity context;
    private int oldMonth=1;
    private int newMonth=-1;

    /**
     * method execute order:
     * show:constrouctor---show---oncreate---onStart---onAttachToWindow
     * dismiss:dismiss---onDetachedFromWindow---onStop
     *
     * @param context
     */
    public PropertyDialog(LivingPropertyListActivity context,int month) {
        super(context);
        this.context=context;
        oldMonth=month;
        newMonth=month;
//        setDefault(month);

    }

    @Override
    public View onCreateView() {
        View inflate = View.inflate(mContext, R.layout.dialog_property, null);
        ButterKnife.bind(this, inflate);

        return inflate;
    }

    @Override
    public void setUiBeforShow() {
//        super.setUiBeforShow();
        switch (oldMonth){
            case 1:
                tv_one.setBackground(context.getResources().getDrawable(R.drawable.selector_tv_property_select));
                break;
            case 2:
                tv_tow.setBackground(context.getResources().getDrawable(R.drawable.selector_tv_property_select));
                break;
            case 3:
                tv_three.setBackground(context.getResources().getDrawable(R.drawable.selector_tv_property_select));
                break;
            case 4:
                tv_four.setBackground(context.getResources().getDrawable(R.drawable.selector_tv_property_select));
                break;

            case 5:
                tv_five.setBackground(context.getResources().getDrawable(R.drawable.selector_tv_property_select));
                break;
            case 6:
                tv_six.setBackground(context.getResources().getDrawable(R.drawable.selector_tv_property_select));
                break;
            case 7:
                tv_seven.setBackground(context.getResources().getDrawable(R.drawable.selector_tv_property_select));
                break;
            case 8:
                tv_eight.setBackground(context.getResources().getDrawable(R.drawable.selector_tv_property_select));
                break;


            case 9:
                tv_nine.setBackground(context.getResources().getDrawable(R.drawable.selector_tv_property_select));
                break;
            case 10:
                tv_ten.setBackground(context.getResources().getDrawable(R.drawable.selector_tv_property_select));
                break;
            case 11:
                tv_eleven.setBackground(context.getResources().getDrawable(R.drawable.selector_tv_property_select));
                break;
            case 12:
                tv_twelve.setBackground(context.getResources().getDrawable(R.drawable.selector_tv_property_select));
                break;


        }
    }

    @OnClick({R.id.tv_one, R.id.tv_tow, R.id.tv_three, R.id.tv_four,
            R.id.tv_five, R.id.tv_six, R.id.tv_seven, R.id.tv_eight,
            R.id.tv_nine, R.id.tv_ten, R.id.tv_eleven, R.id.tv_twelve
            , R.id.btn_ok
    })
    public void click(View view) {
        switch (view.getId()) {
            case R.id.tv_one:
                setDefault(1);
                tv_one.setBackground(context.getResources().getDrawable(R.drawable.selector_tv_property_select));
                break;
            case R.id.tv_tow:
                setDefault(2);
                tv_tow.setBackground(context.getResources().getDrawable(R.drawable.selector_tv_property_select));
                break;
            case R.id.tv_three:
                setDefault(3);
                tv_three.setBackground(context.getResources().getDrawable(R.drawable.selector_tv_property_select));
                break;
            case R.id.tv_four:
                setDefault(4);
                tv_four.setBackground(context.getResources().getDrawable(R.drawable.selector_tv_property_select));
                break;
            case R.id.tv_five:
                setDefault(5);
                tv_five.setBackground(context.getResources().getDrawable(R.drawable.selector_tv_property_select));
                break;
            case R.id.tv_six:
                setDefault(6);
                tv_six.setBackground(context.getResources().getDrawable(R.drawable.selector_tv_property_select));
                break;
            case R.id.tv_seven:
                setDefault(7);
                tv_seven.setBackground(context.getResources().getDrawable(R.drawable.selector_tv_property_select));
                break;
            case R.id.tv_eight:
                setDefault(8);
                tv_eight.setBackground(context.getResources().getDrawable(R.drawable.selector_tv_property_select));
                break;
            case R.id.tv_nine:
                setDefault(9);
                tv_nine.setBackground(context.getResources().getDrawable(R.drawable.selector_tv_property_select));
                break;
            case R.id.tv_ten:
                setDefault(10);
                tv_ten.setBackground(context.getResources().getDrawable(R.drawable.selector_tv_property_select));
                break;
            case R.id.tv_eleven:
                setDefault(11);
                tv_eleven.setBackground(context.getResources().getDrawable(R.drawable.selector_tv_property_select));
                break;
            case R.id.tv_twelve:
                setDefault(12);
                tv_twelve.setBackground(context.getResources().getDrawable(R.drawable.selector_tv_property_select));
                break;
            case R.id.btn_ok:
                if (oldMonth!=newMonth){
                    context.setMonth(newMonth);
                }
                dismiss();
                break;
        }

    }

    private void setDefault(int mon){

        newMonth=mon;
        tv_one.setBackground(context.getResources().getDrawable(R.drawable.selector_tv_property_normal));
        tv_tow.setBackground(context.getResources().getDrawable(R.drawable.selector_tv_property_normal));
        tv_three.setBackground(context.getResources().getDrawable(R.drawable.selector_tv_property_normal));
        tv_four.setBackground(context.getResources().getDrawable(R.drawable.selector_tv_property_normal));

        tv_five.setBackground(context.getResources().getDrawable(R.drawable.selector_tv_property_normal));
        tv_six.setBackground(context.getResources().getDrawable(R.drawable.selector_tv_property_normal));
        tv_seven.setBackground(context.getResources().getDrawable(R.drawable.selector_tv_property_normal));
        tv_eight.setBackground(context.getResources().getDrawable(R.drawable.selector_tv_property_normal));

        tv_nine.setBackground(context.getResources().getDrawable(R.drawable.selector_tv_property_normal));
        tv_ten.setBackground(context.getResources().getDrawable(R.drawable.selector_tv_property_normal));
        tv_eleven.setBackground(context.getResources().getDrawable(R.drawable.selector_tv_property_normal));
        tv_twelve.setBackground(context.getResources().getDrawable(R.drawable.selector_tv_property_normal));


    }


}
