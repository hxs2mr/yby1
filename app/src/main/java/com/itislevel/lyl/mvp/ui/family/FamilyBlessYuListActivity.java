package com.itislevel.lyl.mvp.ui.family;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.android.flexbox.FlexboxLayout;
import com.itislevel.lyl.R;
import com.itislevel.lyl.app.Constants;
import com.itislevel.lyl.base.RootActivity;
import com.itislevel.lyl.mvp.model.bean.BlessCartListBean;
import com.itislevel.lyl.mvp.model.bean.BlessListBean;
import com.itislevel.lyl.mvp.model.bean.BlessOrderBean;
import com.itislevel.lyl.mvp.model.bean.CartUpdateBean;
import com.itislevel.lyl.mvp.model.bean.FamilyBlessListBean;
import com.itislevel.lyl.mvp.model.bean.FamilyBlessListRecevieBean;
import com.itislevel.lyl.mvp.model.bean.FamilyGiftListBean;
import com.itislevel.lyl.mvp.model.bean.FamilyListBean;
import com.itislevel.lyl.mvp.model.bean.FamilyPhotoFrameBean;
import com.itislevel.lyl.mvp.model.bean.FamilyQueryFramBackBean;
import com.itislevel.lyl.mvp.model.bean.FamilyReceiveGiftBean;
import com.itislevel.lyl.mvp.model.bean.FamilySacrificeTypeBean;
import com.itislevel.lyl.mvp.model.bean.FamilySendGiftRecordBean;
import com.itislevel.lyl.mvp.model.bean.FamilyUsualLanguageBean;
import com.itislevel.lyl.mvp.model.bean.FileUploadBean;
import com.itislevel.lyl.mvp.model.bean.LetterBean;
import com.itislevel.lyl.mvp.model.bean.Song;
import com.itislevel.lyl.mvp.model.bean.YuBean;
import com.itislevel.lyl.utils.ActivityUtil;
import com.itislevel.lyl.utils.GsonUtil;
import com.itislevel.lyl.utils.SAToast;
import com.itislevel.lyl.utils.SharedPreferencedUtils;
import com.itislevel.lyl.utils.ToastUtil;
import com.itislevel.lyl.utils.rxbus.RxBus;
import com.itislevel.lyl.utils.rxbus.annotation.UseRxBus;
import com.orhanobut.logger.Logger;
import com.vondear.rxtools.activity.AndroidBug5497Workaround;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

@UseRxBus
public class FamilyBlessYuListActivity extends RootActivity<FamilyPresenter> implements FamilyContract.View, BaseQuickAdapter.OnItemClickListener, BaseQuickAdapter.OnItemChildClickListener, View.OnClickListener{

    Bundle bundle=null;

    @BindView(R.id.fbl_parent)
    FlexboxLayout fbl_parent;


    @BindView(R.id.tv_family_temp)
    TextView tv_family_temp;

    @BindView(R.id.et_content)
    EditText et_content;

    @BindView(R.id.son_name)
    EditText son_name;


    @BindView(R.id.ll_whish_parent)
    LinearLayout ll_whish_parent;

    @BindView(R.id.btn_liji_send)
    Button btn_liji_send;


    @BindView(R.id.tv_send)
    TextView tv_send;

    private String feteid;
    private String touserid;

    private String selectWish="";

    @Override
    protected int getLayoutId() {
        return R.layout.activity_family_bless_yu_list;
    }

    @Override
    protected void initEventAndData() {
        AndroidBug5497Workaround.assistActivity(this);
        bundle=getIntent().getExtras();

        feteid = bundle.getString("feteid");
        touserid = bundle.getString("touserid");
        setToolbarTvTitle("常用语");

//        setToolbarMoreTxt("确定");
//        setToolbarMoreClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Map<String, String> request = new HashMap<>();
//                request.put("token", SharedPreferencedUtils.getStr(Constants.USER_TOKEN));
//                request.put("usernum", SharedPreferencedUtils.getStr(Constants.USER_NUM));
//                request.put("userid", SharedPreferencedUtils.getStr(Constants.USER_ID));
//                request.put("wishes", selectWish);
//                request.put("feteid", feteid);
//                request.put("receiveuserid", touserid);
//
//                mPresenter.familyBlessAdd(GsonUtil.obj2JSON(request));
//            }
//        });
        loadData();

    }

    @OnClick({R.id.tv_send,R.id.btn_liji_send})
    public void click(View view){
        switch (view.getId()){
            case R.id.tv_send:
                if(son_name.getText().toString().equals(""))
                {
                    SAToast.makeText(this,"请输入送人！").show();
                    return;
                }
                String content = et_content.getText().toString().trim();
                if (TextUtils.isEmpty(content)){
                    ToastUtil.Info("请输入内容");
                    return;
                }
                loadingDialog.show();
                addFamilyBlessYu(content,son_name.getText().toString());
                break;
            case R.id.btn_liji_send:
                ll_whish_parent.setVisibility(View.VISIBLE);
                et_content.requestFocus();
                et_content.setFocusable(true);

               btn_liji_send.setVisibility(View.GONE);
                break;
        }
    }

    private void addFamilyBlessYu(String content,String son_name) {
        Map<String, String> request = new HashMap<>();
        request.put("token", SharedPreferencedUtils.getStr(Constants.USER_TOKEN));
        request.put("usernum", SharedPreferencedUtils.getStr(Constants.USER_NUM));
        request.put("userid", SharedPreferencedUtils.getStr(Constants.USER_ID));
        request.put("wishes", content);
        request.put("feteid", feteid);
        request.put("receiveuserid", touserid);
        request.put("buyusername", son_name);
        mPresenter.familyBlessAdd(GsonUtil.obj2JSON(request));
    }

    private void loadData() {
        Map<String, String> request = new HashMap<>();
        request.put("token", SharedPreferencedUtils.getStr(Constants.USER_TOKEN));
        request.put("usernum", SharedPreferencedUtils.getStr(Constants.USER_NUM));
        request.put("feteid", feteid);
        request.put("pageIndex", "1");
        request.put("pageSize", "9999");

        System.out.println("json数据为***********************************************:"+GsonUtil.obj2JSON(request));

        mPresenter.familyUsualLanguage(GsonUtil.obj2JSON(request));
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

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
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    public void showContent(String msg) {

    }

    @Override
    public void familyAdd(String message) {

    }

    @Override
    public void familyList(FamilyListBean familyListBean) {

    }

    @Override
    public void familyListMy(FamilyListBean familyListBean) {

    }

    @Override
    public void familyBlessList(FamilyBlessListBean familyBlessListBean) {

    }

    @Override
    public void familyReceiveSacrifice(FamilyReceiveGiftBean familyReceiveGiftBean) {

    }

    @Override
    public void familyListGift(FamilyGiftListBean familyGiftListBean) {

    }

    @Override
    public void familySendGift(FamilySendGiftRecordBean familySendGiftRecordBean) {

    }

    @Override
    public void familyReceiveBless(FamilyBlessListRecevieBean familyBlessListRecevieBean) {

    }

    @Override
    public void familyViewCount(String message) {

    }

    @Override
    public void familyCate(FamilySacrificeTypeBean familySacrificeTypeBean) {

    }

    @Override
    public void familyUsualLanguage(FamilyUsualLanguageBean familyUsualLanguageBean) {
        List<FamilyUsualLanguageBean.ModelvolistBean> list = familyUsualLanguageBean.getModelvolist();
        WindowManager windowManager = getWindowManager ();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay ().getMetrics (displayMetrics);
        int widthPixels = displayMetrics.widthPixels;
        int heightPixels = displayMetrics.heightPixels;
        ViewGroup.LayoutParams layoutParams = tv_family_temp.getLayoutParams();
        if (list!=null&&list.size()>0){

            for (FamilyUsualLanguageBean.ModelvolistBean item:list){
                TextView textView=new TextView(this);
                textView.setLayoutParams(layoutParams);
                textView.setText(item.getValue());
                textView.setTextSize(18);
                textView.setMaxLines(1);
                textView.setWidth(widthPixels/2-50);
                textView.setEllipsize(TextUtils.TruncateAt.END);
                textView.setTextColor(getResources().getColor(R.color.colorNav));
                textView.setPadding(5,5,5,5);
                textView.setBackground(getResources().getDrawable(R.drawable.yu_normal_shape));
                textView.setOnClickListener(this);
                fbl_parent.addView(textView);
            }
            tv_family_temp.setVisibility(View.GONE);
        }else {
            tv_family_temp.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void familyPhotoFrame(FamilyPhotoFrameBean familyPhotoFrameBean) {

    }

    @Override
    public void familyPhotoBack(FamilyPhotoFrameBean familyPhotoFrameBean) {

    }

    @Override
    public void familyBlessAdd(String message) {

        Logger.w("新建寄语:"+message);

        EventBus.getDefault().post(new YuBean(son_name.getText().toString(),et_content.getText().toString()));

        ActivityUtil.getInstance().closeActivity(this);
        loadingDialog.dismiss();
    }

    @Override
    public void stateError() {
        super.stateError();
        loadingDialog.dismiss();
    }

    @Override
    public void familySearch(FamilyListBean familyListBean) {

    }

    @Override
    public void familyReceiveGiftById(FamilyReceiveGiftBean familyReceiveGiftBean) {

    }

    @Override
    public void familySaveFPhotoFrameAndBack(String message) {

    }

    @Override
    public void familyQueryFrameAndBack(FamilyQueryFramBackBean familyQueryFramBackBean) {

    }

    @Override
    public void familyDel(String action) {

    }

    @Override
    public void uploadHeader(FileUploadBean fileUploadBean) {

    }

    @Override
    public void generatorOrder(String blessOrderBean) {

    }

    @Override
    public void immediateOrder(String blessOrderBean) {

    }

    @Override
    public void familyCartAdd(String message) {

    }

    @Override
    public void familyCartList(BlessCartListBean blessCartListBean) {

    }

    @Override
    public void familyCartUpdate(CartUpdateBean message) {

    }



    @Override
    public void familyCartDel(String message) {

    }

    @Override
    public void familySearch(BlessListBean blessListBean) {

    }

    @Override
    public void selectletter(LetterBean letterBean) {

    }

    @Override
    public void onClick(View v) {

        int childCount = fbl_parent.getChildCount();
        if (v instanceof TextView){
            for (int i = 0; i < childCount; i++) {
                TextView textView = (TextView) fbl_parent.getChildAt(i);
                textView.setTextColor(getResources().getColor(R.color.colorNav));
                textView.setBackground(getResources().getDrawable(R.drawable.yu_normal_shape));
            }

            TextView textView= (TextView) v;
            textView.setBackground(getResources().getDrawable(R.drawable.yu_select_shape));
            textView.setTextColor(getResources().getColor(R.color.colorWhite));

            selectWish=textView.getText().toString().trim();

            et_content.setText(selectWish);

        }


    }
}
