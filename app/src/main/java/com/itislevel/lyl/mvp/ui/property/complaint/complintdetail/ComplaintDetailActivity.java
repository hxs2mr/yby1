package com.itislevel.lyl.mvp.ui.property.complaint.complintdetail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutCompat;
import android.text.TextUtils;
import android.view.View;

import com.itislevel.lyl.R;
import com.itislevel.lyl.app.Constants;
import com.itislevel.lyl.base.RootActivity;
import com.itislevel.lyl.mvp.model.bean.ComSearchBean;
import com.itislevel.lyl.mvp.model.bean.ComplaintTypeBean;
import com.itislevel.lyl.mvp.model.bean.FileUploadBean;
import com.itislevel.lyl.mvp.model.bean.PropertyDetailBean;
import com.itislevel.lyl.mvp.ui.property.complaint.ComplaintContract;
import com.itislevel.lyl.mvp.ui.property.complaint.ComplaintPresenter;
import com.itislevel.lyl.utils.ActivityUtil;
import com.itislevel.lyl.utils.GsonUtil;
import com.itislevel.lyl.utils.SharedPreferencedUtils;
import com.itislevel.lyl.utils.UtilsStyle;
import com.lzy.ninegrid.ImageInfo;
import com.lzy.ninegrid.NineGridView;
import com.lzy.ninegrid.preview.NineGridViewClickAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

import static com.itislevel.lyl.utils.DateUtil.timeSpanToDate;

/**
 * Created by Administrator on 2018\5\30 0030.
 */

public class ComplaintDetailActivity extends RootActivity<ComplaintPresenter> implements ComplaintContract.View{
    @BindView(R.id.toushu_name)
    AppCompatTextView toushu_name;//投诉人的姓名
    @BindView(R.id.toushu_qu)
    AppCompatTextView toushu_qu;//小区
    @BindView(R.id.toushu_comment)
    AppCompatTextView toushu_comment;//投诉的内容
    @BindView(R.id.toushu_time)
    AppCompatTextView toushu_time;//投诉的时间
    @BindView(R.id.toushu_ninegrid)
    NineGridView toushu_images;//投诉的图片

    @BindView(R.id.p_p_back)
    LinearLayoutCompat p_p_back;
    private Bundle bundle;
    private String id ;
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
        return R.layout.activity_complaintdetail;
    }

    @Override
    protected void initEventAndData() {
        bundle = getIntent().getExtras();
        id = bundle.getString("id");
        loadData();
    }

    private void loadData() {
        loadingDialog.show();
        Map<String, Object> request = new HashMap<>();
        request.put("token", SharedPreferencedUtils.getStr("p_token",""));
        request.put("phone", SharedPreferencedUtils.getStr("proprety_phone",""));
        request.put("id",id);
        mPresenter.detaComplaintList(GsonUtil.obj2JSON(request));
    }

    @OnClick({R.id.p_p_back})
    public void onclick(View view)
    {
        switch (view.getId())
        {
            case R.id.p_p_back:
                ActivityUtil.getInstance().closeActivity(this);
                break;
        }
    }
    @Override
    public void uploadHeader(FileUploadBean fileUploadBean) {

    }

    @Override
    public void complaintType(List<ComplaintTypeBean> data) {

    }

    @Override
    public void addComplaint(String data) {

    }

    @Override
    public void findComplaintList(ComSearchBean bean) {

    }

    @Override
    public void stateError(Throwable e) {
        super.stateError(e);
        loadingDialog.dismiss();
    }

    @Override
    public void detaComplaintList(List<PropertyDetailBean> bean) {
        toushu_qu.setText("投诉对象："+bean.get(0).getVillagename());
        toushu_comment.setText(bean.get(0).getContent());
        toushu_name.setText("投诉人:"+bean.get(0).getRealname());
        toushu_time.setText( timeSpanToDate(bean.get(0).getCreatedtime()));


        // 图片 ninegrid_share
        List<ImageInfo> urlList = new ArrayList<>();
        ImageInfo imageInfo;
        //包含图片
        String imge = bean.get(0).getImgs();

        if (!TextUtils.isEmpty(imge)) {
            String[] split = imge.split(",");
            for (String url : split) {
                if (!TextUtils.isEmpty(url) && url != null && url != "" && !url.equals(",")) {
                    imageInfo = new ImageInfo();
                    imageInfo.setBigImageUrl(Constants.IMG_SERVER_PATH + url.trim());
                    imageInfo.setThumbnailUrl(Constants.IMG_SERVER_PATH + url.trim());
                    urlList.add(imageInfo);
                }else {
                }
            }
            toushu_images.setAdapter(new NineGridViewClickAdapter(this, urlList));
        } else {
            toushu_images.setVisibility(View.GONE);
        }
        loadingDialog.dismiss();
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
