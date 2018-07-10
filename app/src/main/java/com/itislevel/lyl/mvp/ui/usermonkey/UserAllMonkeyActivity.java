package com.itislevel.lyl.mvp.ui.usermonkey;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;

import com.itislevel.lyl.R;
import com.itislevel.lyl.base.RootActivity;
import com.itislevel.lyl.utils.ActivityUtil;
import com.itislevel.lyl.utils.ToastUtil;
import com.itislevel.lyl.utils.UtilsStyle;
import com.itislevel.lyl.widget.UserMonkeyPassDialog;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 作者： XS
 * 邮箱：1363826037@qq.com
 * 描述:全额体现
 * 创建时间:  2018\7\7 0007 13:37
 */
public class UserAllMonkeyActivity extends RootActivity<UserMonkeyPresenter>implements UserMonkeyContract.View{

    @BindView(R.id.tixian_tv)
    AppCompatTextView tixian_tv;//全部体现

    @BindView(R.id.all_edtext)
    TextInputEditText all_edtext;//

    @BindView(R.id.all_monkey_button)
    AppCompatButton all_monkey_button;

    @BindView(R.id.lin_monkey_yu)
    AppCompatTextView lin_monkey_yu;

    @BindView(R.id.lin_monkey_error)
    AppCompatTextView lin_monkey_error;

    @BindView(R.id.bank_tv)
    AppCompatTextView bank_tv;
    private Bundle bundle;
    private String monkey="0";
    private UserMonkeyPassDialog monkeyDialog;
    private boolean is_show_popu=true;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UtilsStyle.setStatusBarMode(this,true); //黑色的主题图标
    }
    @Override
    protected void initInject() {
            getActivityComponent().inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_allmonkey;
    }

    @Override
    protected void initEventAndData() {
        bundle = getIntent().getExtras();
        monkey = bundle.getString("monkey");
        lin_monkey_yu.setText("零钱余额 ¥"+monkey);
        setToolWight("零钱提现");
        all_monkey_button.setClickable(false);
       // all_edtext.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        all_edtext.addTextChangedListener(new EditTextChangeListener());
    }
    @OnClick({R.id.tixian_tv,R.id.all_monkey_button})
    public void OnClick(View view)
    {
        switch (view.getId())
        {
            case R.id.tixian_tv://全部体现
                all_edtext.setText(monkey);
                break;
            case R.id.all_monkey_button://点击体现
                if (is_show_popu)
                {
                    openSendGiftDialog();
                }
                break;
        }
    }
    private View.OnClickListener itemsOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

        }
    };
    private void openSendGiftDialog() {
        monkeyDialog = new UserMonkeyPassDialog(this, this,null,all_edtext.getText().toString().trim());
        monkeyDialog.show();
    }
    public void YzPassword()//验证输入的密码  体现接口
    {
        ToastUtil.Info("密码验证！");
        Bundle bundle = new Bundle();
        bundle.putString("monkey",all_edtext.getText().toString().trim());
        bundle.putString("address",bank_tv.getText().toString());
        ActivityUtil.getInstance().openActivity(this,UserMonkeyTiXianActivity.class,bundle);
        ActivityUtil.getInstance().closeActivity(this);
    }
    class EditTextChangeListener implements TextWatcher {
        /**
         * 编辑框内容发生改变之前的会回调
         *
         * @param s
         * @param start
         * @param count
         * @param after
         */
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        /**
         * 编辑框的内容正在发生改变时的回调方法 >>用户正在输入
         * 我们可以在这里实时地 通过搜索匹配用户的输入
         *
         * @param charSequence
         * @param start
         * @param before
         * @param count
         */
        @Override
        public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
            if(!charSequence.toString().equals("")&&!charSequence.toString().equals("."))
            {
                float start_monkey =  Float.parseFloat(charSequence.toString());
                float end_monkey =  Float.parseFloat(monkey);

                if(start_monkey > end_monkey)
                {
                    is_show_popu = false;
                    lin_monkey_error.setVisibility(View.VISIBLE);
                    lin_monkey_yu.setVisibility(View.GONE);
                }else{
                    is_show_popu = true;
                    lin_monkey_error.setVisibility(View.GONE);
                    lin_monkey_yu.setVisibility(View.VISIBLE);
                }
            }else {
                is_show_popu = true;
                lin_monkey_error.setVisibility(View.GONE);
                lin_monkey_yu.setVisibility(View.VISIBLE);
            }
            if (TextUtils.isEmpty(charSequence.toString())) {
                tixian_tv.setVisibility(View.VISIBLE);
                all_monkey_button.setClickable(false);
                all_monkey_button.setBackgroundResource(R.drawable.login_select_yes);
            }else {
                all_monkey_button.setBackgroundResource(R.drawable.login_ok);
                all_monkey_button.setClickable(true);
                if(tixian_tv.getVisibility() == View.VISIBLE)
                {
                    tixian_tv.setVisibility(View.GONE);
                }
            }
            // 限制最多能输入9位整数
            if (charSequence.toString().contains(".")) {
                if (charSequence.toString().indexOf(".") > 9) {
                    charSequence = charSequence.toString().subSequence(0,9) + charSequence.toString().substring(charSequence.toString().indexOf("."));
                    all_edtext.setText(charSequence);
                    all_edtext.setSelection(9);
                }
            }else {
                if (charSequence.toString().length() > 9){
                    charSequence = charSequence.toString().subSequence(0,9);
                    all_edtext.setText(charSequence);
                    all_edtext.setSelection(9);
                }
            }
            // 判断小数点后只能输入两位
            if (charSequence.toString().contains(".")) {
                if (charSequence.length() - 1 - charSequence.toString().indexOf(".") > 2) {
                    charSequence = charSequence.toString().subSequence(0,
                            charSequence.toString().indexOf(".") + 3);
                    all_edtext.setText(charSequence);
                    all_edtext.setSelection(charSequence.length());
                }
            }
            //如果第一个数字为0，第二个不为点，就不允许输入
            if (charSequence.toString().startsWith("0") && charSequence.toString().trim().length() > 1) {
                if (!charSequence.toString().substring(1, 2).equals(".")) {
                    all_edtext.setText(charSequence.subSequence(0, 1));
                    all_edtext.setSelection(1);
                    return;
                }
            }
        }
        /**
         * 编辑框的内容改变以后,用户没有继续输入时 的回调方法
         *
         * @param s
         */
        @Override
        public void afterTextChanged(Editable s) {
            if (all_edtext.getText().toString().trim() != null && !all_edtext.getText().toString().trim().equals("")) {
                if (all_edtext.getText().toString().trim().substring(0, 1).equals(".")) {
                    all_edtext.setText("0" + all_edtext.getText().toString().trim());
                    all_edtext.setSelection(2);
                }
            }
        }
    }
    @Override
    public void stateEmpty() {

    }

    @Override
    public void stateLoading() {

    }

    @Override
    public void stateSuccess() {

    }
}
