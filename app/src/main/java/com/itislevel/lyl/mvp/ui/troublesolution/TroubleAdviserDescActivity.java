package com.itislevel.lyl.mvp.ui.troublesolution;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.itislevel.lyl.R;
import com.itislevel.lyl.app.App;
import com.itislevel.lyl.app.Constants;
import com.itislevel.lyl.base.RootActivity;
import com.itislevel.lyl.mvp.model.bean.BlessOrderBean;
import com.itislevel.lyl.mvp.model.bean.FileUploadBean;
import com.itislevel.lyl.mvp.model.bean.MeiZiBean;
import com.itislevel.lyl.mvp.model.bean.TroubleAddBean;
import com.itislevel.lyl.mvp.model.bean.TroubleAdviserMyBean;
import com.itislevel.lyl.mvp.model.bean.TroubleCommentAdd;
import com.itislevel.lyl.mvp.model.bean.TroubleListBean;
import com.itislevel.lyl.mvp.model.bean.TroubleTypeBean;
import com.itislevel.lyl.utils.ActivityUtil;
import com.itislevel.lyl.utils.imageload.ImageLoadConfiguration;
import com.itislevel.lyl.utils.imageload.ImageLoadProxy;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class TroubleAdviserDescActivity extends RootActivity<TroublesolutionPresenter> implements
        TroublesolutionContract.View {

    Bundle bundle = new Bundle();
    private String PROVINCE_ID = "";
    private String CITY_ID = "";
    private String COUNTY_ID = ""; //很多情况下是空的

    private String PROVINCE_TITLE = "";
    private String CITY_TITLE = "";
    private String COUNTY_TITLE = "";//很多情况下是空的

    @BindView(R.id.iv_photo)
    ImageView iv_photo;
    @BindView(R.id.tv_name)
    TextView tv_name;
    @BindView(R.id.tv_desc)
    TextView tv_desc;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_trouble_adviser_desc;
    }

    @Override
    protected void initEventAndData() {
        bundle = this.getIntent().getExtras();
        setToolbarTvTitle("顾问团队");

//        bundle.putString("trouble_desc_photo",item.getPhoto());
//        bundle.putString("trouble_desc_title",item.getName());
//        bundle.putString("trouble_desc_desc",item.getPersonalcv());
//  iv_photo tv_name tv_desc

        tv_name.setText( bundle.getString("trouble_desc_title"));
        tv_desc.setText(bundle.getString("trouble_desc_desc"));

        ImageLoadProxy.getInstance()
                .load(new ImageLoadConfiguration.Builder(App.getInstance())
                        .url(Constants.IMG_SERVER_PATH +  bundle.getString("trouble_desc_photo"))
                        .defaultImageResId(R.mipmap.ic_launcher)
                        .imageView(iv_photo).build());

    }

    @OnClick({R.id.btn_go_advise})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.btn_go_advise:
                ActivityUtil.getInstance().openActivity(TroubleAdviserDescActivity.this,
                        TroubleAdviserAddActivity.class, bundle);
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
    public void showContent(String msg) {

    }

    @Override
    public void showDataList(List<MeiZiBean> meiZiBeans) {

    }

    @Override
    public void troubleAdd(TroubleAddBean troubleAdd) {

    }

    @Override
    public void troubleList(TroubleListBean troubleListBean) {

    }

    @Override
    public void troubleListMy(TroubleListBean troubleListBean) {

    }

    @Override
    public void troubleDel(String action) {

    }

    @Override
    public void troubleCommentReplay(TroubleCommentAdd troubleCommentAdd) {

    }

    @Override
    public void troubleCommentDel(String action) {

    }

    @Override
    public void troubleType(TroubleTypeBean troubleTypeBean) {

    }

    @Override
    public void teamProblemAdd(String message) {

    }

    @Override
    public void teamProblemList(Object object) {

    }

    @Override
    public void teamReplay(Object object) {

    }

    @Override
    public void teamMyProblemList(TroubleAdviserMyBean troubleAdviserMyBean) {

    }

    @Override
    public void teamViewCount(String message) {

    }

    @Override
    public void uploadHeader(FileUploadBean fileUploadBean) {

    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }
}
