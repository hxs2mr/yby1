package com.itislevel.lyl.wxapi;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.itislevel.lyl.R;
import com.itislevel.lyl.mvp.ui.livingexpensess.LivingPropertyListActivity;
import com.itislevel.lyl.mvp.ui.pay.PayInfoActivity;
import com.itislevel.lyl.utils.ActivityUtil;
import com.itislevel.lyl.utils.SharedPreferencedUtils;
import com.itislevel.lyl.utils.rxbus.RxBus;
import com.itislevel.lyl.utils.rxbus.annotation.UseRxBus;
import com.orhanobut.logger.Logger;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
@UseRxBus
public class WXPayEntryActivity extends AppCompatActivity implements IWXAPIEventHandler {

    private IWXAPI api;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wxpay_entry);

        api = WXAPIFactory.createWXAPI(this, "wx9428227f6e4cf4c2");
        api.handleIntent(getIntent(), this);

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
    }

    @Override
    public void onReq(BaseReq req) {
        int a = 1;
    }

    @Override
    public void onResp(BaseResp resp) {
        Logger.d("onPayFinish, errCode = " + resp.errCode);

        if (resp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {

            //通知刷新
//            RxBus.getInstance().post(RxBus.getInstance().getTag
//                    (PayInfoActivity.class,
//                            RxBus.TAG_UPDATE),  resp.errCode);

            SharedPreferencedUtils.setInt("wx_pay_code",resp.errCode);
        }

        ActivityUtil.getInstance().closeActivity(this);
    }
}
