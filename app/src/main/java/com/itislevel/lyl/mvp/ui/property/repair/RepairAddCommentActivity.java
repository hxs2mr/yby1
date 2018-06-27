package com.itislevel.lyl.mvp.ui.property.repair;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.AppCompatRatingBar;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.RatingBar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.itislevel.lyl.R;
import com.itislevel.lyl.app.App;
import com.itislevel.lyl.app.Constants;
import com.itislevel.lyl.base.RootActivity;
import com.itislevel.lyl.mvp.model.bean.ProperCommentList;
import com.itislevel.lyl.mvp.model.bean.ReAddComSuccBean;
import com.itislevel.lyl.mvp.model.bean.RepairCityListBean;
import com.itislevel.lyl.mvp.model.bean.RepairListBean;
import com.itislevel.lyl.mvp.model.bean.SeleBean;
import com.itislevel.lyl.utils.ActivityUtil;
import com.itislevel.lyl.utils.GsonUtil;
import com.itislevel.lyl.utils.SAToast;
import com.itislevel.lyl.utils.SharedPreferencedUtils;
import com.itislevel.lyl.utils.UtilsStyle;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;


/**
 * Created by Administrator on 2018\6\8 0008.
 */

public class RepairAddCommentActivity extends RootActivity<RepairPresenter>implements RepairContract.View {

    @BindView(R.id.edit_comment)
    TextInputEditText edit_comment;

    @BindView(R.id.head_image)
    CircleImageView head_image;

    @BindView(R.id.ratingBar)
    AppCompatRatingBar ratingBar;

    @BindView(R.id.jieguo_tv)
    AppCompatTextView jieguo_tv;

    @BindView(R.id.checkbox)
    AppCompatCheckBox checkbox;

    @BindView(R.id.fabu_tv)
    AppCompatTextView fabu_tv;

    private Bundle bundle;
    private String rwid;
    private String headurl;

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
        return R.layout.activity_addrecomment;
    }

    private boolean check()
    {
        String COM = edit_comment.getText().toString();
        boolean isPass = true;
        if(COM.isEmpty()){
            edit_comment.setError("请输入内容");
            isPass = false;
        }
        return isPass;
    }
    @Override
    protected void initEventAndData() {
        bundle = getIntent().getExtras();
        rwid =bundle.getString("rwid");
        headurl = bundle.getString("headurl");

        Glide.with(App.getInstance())
                .load(Constants.IMG_SERVER_PATH + headurl)
                .asBitmap()
                .error(R.mipmap.person_head)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(head_image);
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                if (v<=2.0)
                {
                    jieguo_tv.setText("不满意");
                }else  if (v<=3.0)
                {
                    jieguo_tv.setText("一般");
                }else if (v<=4.0)
                {
                    jieguo_tv.setText("满意");
                }
                else if (v>=5.0)
                {
                    jieguo_tv.setText("非常满意");
                }
            }
        });
    }

    @OnClick({R.id.fabu_tv,R.id.p_p_back})
    public  void onclick(View view){
        switch (view.getId())
        {
            case R.id.fabu_tv://发布
                if(check())
                {
                    loadingDialog.show();
                    Map<String, Object> request = new HashMap<>();
                    request.put("token", SharedPreferencedUtils.getStr("p_token",""));
                    request.put("phone", SharedPreferencedUtils.getStr("proprety_phone",""));
                    request.put("rwid",rwid);
                    request.put("userid",SharedPreferencedUtils.getStr("p_user_id",""));
                    if(checkbox.isChecked())
                    {
                        request.put("observer","匿名");
                    }else {
                        request.put("observer",SharedPreferencedUtils.getStr("p_user_n","匿名"));
                    }
                    request.put("comment",edit_comment.getText().toString());
                    request.put("score",(ratingBar.getRating()*2)+"");
                    request.put("phone",SharedPreferencedUtils.getStr("proprety_phone",""));
                    mPresenter.addCommentEstates(GsonUtil.obj2JSON(request));
                }
                break;
            case R.id.p_p_back:
                ActivityUtil.getInstance().closeActivity(this);
                break;
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

    @Override
    public void maintenanceList(RepairListBean bean) {

    }

    @Override
    public void queryarealist(List<RepairCityListBean> bean) {

    }

    @Override
    public void queryrepairallcatelist(List<RepairCityListBean> bean) {

    }

    @Override
    public void commentEstatesList(ProperCommentList bean) {

    }

    @Override
    public void stateError(Throwable e) {
        loadingDialog.dismiss();
        super.stateError(e);
    }
    @Override
    public void addCommentEstates(String data) {
        loadingDialog.dismiss();
        SAToast.makeText(this,"添加成功").show();
        EventBus.getDefault().post(new ReAddComSuccBean(""));
        ActivityUtil.getInstance().closeActivity(this);
    }

    @Override
    public void addCollectMaintenance(String action) {

    }

    @Override
    public void seleCommentConunt(SeleBean bean) {

    }

    @Override
    public void prolooknumcount(String data) {

    }
}
