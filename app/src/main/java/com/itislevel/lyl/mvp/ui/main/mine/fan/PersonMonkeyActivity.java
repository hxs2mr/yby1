package com.itislevel.lyl.mvp.ui.main.mine.fan;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.AppCompatImageView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;

import com.itislevel.lyl.R;
import com.itislevel.lyl.app.Constants;
import com.itislevel.lyl.base.RootActivity;
import com.itislevel.lyl.mvp.model.bean.FanRecodeBean;
import com.itislevel.lyl.mvp.model.bean.FanXianBean;
import com.itislevel.lyl.mvp.model.bean.ShanHomeBean;
import com.itislevel.lyl.mvp.ui.pay.PayInfoActivity;
import com.itislevel.lyl.utils.ActivityUtil;
import com.itislevel.lyl.utils.GsonUtil;
import com.itislevel.lyl.utils.SharedPreferencedUtils;
import com.itislevel.lyl.utils.ToastUtil;
import com.itislevel.lyl.utils.UtilsStyle;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 作者： XS
 * 邮箱：1363826037@qq.com
 * 描述:充值钱
 * 创建时间:  2018\7\3 0003 18:26
 */
public class PersonMonkeyActivity  extends RootActivity<PersonFanPresenter> implements PersonFanContract.View{
    @BindView(R.id.monkey_editetex)
    TextInputEditText monkey_editetex;

    @BindView(R.id.monkey_clear)
    AppCompatImageView monkey_clear;
    private Bundle bundle;
    private String total_price="";
    @Override
    protected void initInject() {
    getActivityComponent().inject(this);
    }
    @Override
    protected int getLayoutId() {
        return R.layout.activity_fanmonkey;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UtilsStyle.setStatusBarMode(this,true); //黑色的主题图标
    }

    @Override
    protected void initEventAndData() {
        setToolWight("充值");
        bundle = new Bundle();
        monkey_editetex.addTextChangedListener(new EditTextChangeListener());
    }
    @OnClick({R.id.monkey_clear,R.id.monkey_next})
    public void onclick(View view)
    {
        switch (view.getId())
        {
            case R.id.monkey_clear:
                monkey_editetex.setText("");
                break;
            case R.id.monkey_next:
                String title_s = monkey_editetex.getText().toString();
                if(title_s.isEmpty()){
                    monkey_editetex.setError("输入充值金额");
                }else{
                    total_price = title_s;
                    loadingDialog.show();
                    next_data();
                }
                break;
        }
    }

    private void next_data() {
        Map<String,String> request = new HashMap<>();
        request.put("token", SharedPreferencedUtils.getStr(Constants.USER_TOKEN,""));
        request.put("usernum", SharedPreferencedUtils.getStr(Constants.USER_NUM,""));
        request.put("merchantid",SharedPreferencedUtils.getStr("fan_merchantid",""));
        request.put("payfee", monkey_editetex.getText().toString());
        mPresenter.onlinerecharge(GsonUtil.obj2JSON(request));
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

    @Override
    public void merchantmainpage(ShanHomeBean bean) {

    }

    @Override
    public void rechargeRecord(FanRecodeBean bean) {

    }

    @Override
    public void cashbackist(FanXianBean bean) {

    }

    @Override
    public void stateError(Throwable e) {
        super.stateError(e);
        loadingDialog.dismiss();
        ToastUtil.Info("服务器异常!");
    }

    @Override
    public void onlinerecharge(String msg) {
        loadingDialog.dismiss();
        bundle.putString(Constants.PAY_ORDERNUM, msg);
        bundle.putString(Constants.PAY_MODULE_NAME, Constants.CART_MODEL_FAN);
        bundle.putInt(Constants.PAY_FROM_ACTIVITY, Constants.PAY_FROM_FAN_PROPERTY);

        bundle.putString(Constants.PAY_TOTALPRICE, total_price+"");
        bundle.putString(Constants.PAY_GOODS_DETAIL, "充值金额:");
        bundle.putString(Constants.PAY_GOODS_DESC,"充值金额:");
        total_price="";
        ActivityUtil.getInstance().openActivity(this,
                PayInfoActivity.class, bundle);
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
            if (TextUtils.isEmpty(charSequence.toString())) {
                    monkey_clear.setVisibility(View.GONE);
            }else {
                if(monkey_clear.getVisibility() == View.GONE)
                {
                    monkey_clear.setVisibility(View.VISIBLE);
                }
            }

            // 限制最多能输入9位整数
            if (charSequence.toString().contains(".")) {
                if (charSequence.toString().indexOf(".") > 9) {
                    charSequence = charSequence.toString().subSequence(0,9) + charSequence.toString().substring(charSequence.toString().indexOf("."));
                    monkey_editetex.setText(charSequence);
                    monkey_editetex.setSelection(9);
                }
            }else {
                if (charSequence.toString().length() > 9){
                    charSequence = charSequence.toString().subSequence(0,9);
                    monkey_editetex.setText(charSequence);
                    monkey_editetex.setSelection(9);
                }
            }
            // 判断小数点后只能输入两位
            if (charSequence.toString().contains(".")) {
                if (charSequence.length() - 1 - charSequence.toString().indexOf(".") > 2) {
                    charSequence = charSequence.toString().subSequence(0,
                            charSequence.toString().indexOf(".") + 3);
                    monkey_editetex.setText(charSequence);
                    monkey_editetex.setSelection(charSequence.length());
                }
            }
            //如果第一个数字为0，第二个不为点，就不允许输入
            if (charSequence.toString().startsWith("0") && charSequence.toString().trim().length() > 1) {
                if (!charSequence.toString().substring(1, 2).equals(".")) {
                    monkey_editetex.setText(charSequence.subSequence(0, 1));
                    monkey_editetex.setSelection(1);
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
            if (monkey_editetex.getText().toString().trim() != null && !monkey_editetex.getText().toString().trim().equals("")) {
                if (monkey_editetex.getText().toString().trim().substring(0, 1).equals(".")) {
                    monkey_editetex.setText("0" + monkey_editetex.getText().toString().trim());
                    monkey_editetex.setSelection(2);
                }
            }
        }
    }
}
