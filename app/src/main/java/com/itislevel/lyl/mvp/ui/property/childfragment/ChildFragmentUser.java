package com.itislevel.lyl.mvp.ui.property.childfragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.itislevel.lyl.R;
import com.itislevel.lyl.adapter.LiveAddressBean;
import com.itislevel.lyl.adapter.SelectQuAdapter;
import com.itislevel.lyl.app.Constants;
import com.itislevel.lyl.base.RootFragment;
import com.itislevel.lyl.mvp.model.bean.PropertLoginBean;
import com.itislevel.lyl.mvp.model.bean.PropretyLiveBean;
import com.itislevel.lyl.mvp.model.bean.PropretyLoginSuccessBean;
import com.itislevel.lyl.mvp.model.bean.PropretyNoticeBean;
import com.itislevel.lyl.mvp.model.bean.VillageNameBean;
import com.itislevel.lyl.mvp.ui.property.PropertLoginActivity;
import com.itislevel.lyl.mvp.ui.property.ProperttPersonActivity;
import com.itislevel.lyl.mvp.ui.property.PropertyContract;
import com.itislevel.lyl.mvp.ui.property.PropertyPresenter;
import com.itislevel.lyl.mvp.ui.property.childfragment.historicalbill.HistoricalBillActivity;
import com.itislevel.lyl.mvp.ui.property.collection.ProCollectionActivity;
import com.itislevel.lyl.mvp.ui.property.complaint.PropertyComplatintActivity;
import com.itislevel.lyl.mvp.ui.property.homelist.PropertyHomeListActivity;
import com.itislevel.lyl.mvp.ui.property.houselist.PropertyHouseListActivity;
import com.itislevel.lyl.mvp.ui.property.payrecord.PropertyPayRecordActivity;
import com.itislevel.lyl.utils.ActivityUtil;
import com.itislevel.lyl.utils.GsonUtil;
import com.itislevel.lyl.utils.SharedPreferencedUtils;
import com.itislevel.lyl.utils.UtilsStyle;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import cn.finalteam.rxgalleryfinal.ui.widget.HorizontalDividerItemDecoration;
import de.hdodenhof.circleimageview.CircleImageView;

import static com.itislevel.lyl.mvp.ui.property.PropertyHomeActvity.customer_service_phone;


/**
 * Created by Administrator on 2018\5\22 0022.
 */

public class ChildFragmentUser extends RootFragment<PropertyPresenter>implements PropertyContract.View, BaseQuickAdapter.OnItemClickListener{

    @BindView(R.id.person_center_linear)
    LinearLayoutCompat person_center_linear;

    @BindView(R.id.linear_over)
    AppCompatButton linear_over;

    private PopupWindow POPU;

    @BindView(R.id.select_qu_linear)
    LinearLayoutCompat select_qu_linear;
    @BindView(R.id.select_qu_im)
    AppCompatImageView select_qu_im;
    @BindView(R.id.gray_layout)
    View gray_layout;
    @BindView(R.id.qu_name)
    AppCompatTextView qu_name;

    @BindView(R.id.p_user_phone)
    AppCompatTextView p_user_phone;

    @BindView(R.id.p_use_name)
    AppCompatTextView p_user_name;
    @BindView(R.id.p_image)
    CircleImageView p_image;
    @BindView(R.id.complaint_linear)
    LinearLayoutCompat complaint_linear;

    private SelectQuAdapter adapter;
    private String vid;
    private String p_imageurl;
    boolean islogin;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UtilsStyle.setStatusBarMode(getActivity(),true); //黑色的主题图标
        EventBus.getDefault().register(this);
    }
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }
    @Override
    protected int getLayoutId() {
        return R.layout.childuser_fragment;
    }
    @Override
    protected void initEventAndData() {
        vid = SharedPreferencedUtils.getStr("vid","");
        String qu_name_t =  SharedPreferencedUtils.getStr("villagename","请登录");
        String p_name =  SharedPreferencedUtils.getStr("p_user_name","请登录");
        String p_phone =  SharedPreferencedUtils.getStr("proprety_phone","  ");
         p_imageurl =  SharedPreferencedUtils.getStr("p_head_url","  ");
        qu_name.setText(qu_name_t);
        p_user_phone.setText(p_phone);
        p_user_name.setText(p_name);
        Glide.with(mActivity)
                .load(Constants.IMG_SERVER_PATH+p_imageurl)
                .asBitmap()
                .error(R.mipmap.person_head)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(p_image);
    }
    @Subscribe
    public void Event(PropretyLoginSuccessBean bean)
    {
        qu_name.setText(bean.getVillagename());

        String p_name =  SharedPreferencedUtils.getStr("p_user_name","请登录");
        String p_phone =  SharedPreferencedUtils.getStr("proprety_phone","  ");
        String p_imageurl =  SharedPreferencedUtils.getStr("p_head_url","  ");
        p_user_phone.setText(p_phone);
        p_user_name.setText(p_name);
        Glide.with(mActivity)
                .load(Constants.IMG_SERVER_PATH+p_imageurl)
                .asBitmap()
                .error(R.mipmap.person_head)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(p_image);
    }
    private void initAdapter(RecyclerView select_recycle ) {

            LinearLayoutManager manager = new LinearLayoutManager(getContext());
            // ((SimpleItemAnimator)recyclerView.getItemAnimator()).setSupportsChangeAnimations(false);
            select_recycle.setLayoutManager(manager);
            adapter = new SelectQuAdapter(R.layout.select_qu_item_two,getActivity());
            adapter.setOnItemClickListener(this);
            adapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_BOTTOM); //切换动画
            adapter.setEnableLoadMore(false);
            select_recycle.setAdapter(adapter);
        /*
        * 测试的数据
        * */
    }
    @Override
    protected void initInject() {
    getFragmentComponent().inject(this);
    }
    @OnClick({R.id.person_center_linear,R.id.linear_over,R.id.select_qu_linear,R.id.call_image,R.id.p_fanwu_linear,R.id.complaint_linear,R.id.pay_record,R.id.shou_linear})
   public  void   click(View view)
    {
        switch (view.getId())
        {
            case R.id.linear_over://退出登录
                show_clear_cart();
             break;
            case R.id.select_qu_linear:
                if(!qu_name.getText().toString().trim().equals("请登录"))
                {
                    gray_layout.setVisibility(View.VISIBLE);
                    POPU = showTipPopupWindow(select_qu_im);
                    RotateAnimation animation = new RotateAnimation(0, 180, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                    animation.setDuration(350);
                    animation.setFillAfter(true);
                    select_qu_im.startAnimation(animation);

                    Map<String, Object> request = new HashMap<>();
                    request.put("token", SharedPreferencedUtils.getStr("p_token",""));
                    request.put("phone", SharedPreferencedUtils.getStr("proprety_phone",""));
                    mPresenter.findVillagename(GsonUtil.obj2JSON(request));

                }else {
                    SharedPreferencedUtils.setBool("isproprety",false);
                    ActivityUtil.getInstance().openActivity(getActivity(), PropertLoginActivity.class);
                }
                break;
            case R.id.call_image:
                show_call();
                break;
            case R.id.p_fanwu_linear:
                vid = SharedPreferencedUtils.getStr("vid","");
                p_imageurl =  SharedPreferencedUtils.getStr("p_head_url","  ");
                Bundle bundle = new Bundle();
                bundle.putString("vid",vid);
                bundle.putString("image",p_imageurl);
                islogin = SharedPreferencedUtils.getBool("isproprety", false);
                if (!islogin) {
                    ActivityUtil.getInstance().openActivity(getActivity(), PropertLoginActivity.class);
                    return;
                }else {
                    ActivityUtil.getInstance().openActivity(getActivity(), PropertyHomeListActivity.class,bundle);
                }
                break;
            case R.id.complaint_linear://历史账单
                islogin = SharedPreferencedUtils.getBool("isproprety", false);
                if (!islogin) {
                    ActivityUtil.getInstance().openActivity(getActivity(), PropertLoginActivity.class);
                    return;
                }else {
                    ActivityUtil.getInstance().openActivity(getActivity(), HistoricalBillActivity.class);
                }
                break;
            case R.id.pay_record:
                vid = SharedPreferencedUtils.getStr("vid","");
                Bundle bundle1 = new Bundle();
                bundle1.putString("vid",vid);
                islogin = SharedPreferencedUtils.getBool("isproprety", false);
                if (!islogin) {
                    ActivityUtil.getInstance().openActivity(getActivity(), PropertLoginActivity.class);
                    return;
                }else {
                    ActivityUtil.getInstance().openActivity(getActivity(), PropertyPayRecordActivity.class);
                }
                break;
            case R.id.shou_linear://收藏
                islogin = SharedPreferencedUtils.getBool("isproprety", false);
                if (!islogin) {
                    ActivityUtil.getInstance().openActivity(getActivity(), PropertLoginActivity.class);
                    return;
                }else {
                    ActivityUtil.getInstance().openActivity(getActivity(), ProCollectionActivity.class);
                }
                break;

        }

    }

    public void show_clear_cart(){
        //实例化建造者
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        //设置警告对话框的标题
        builder.setTitle("退出登录");
        //设置警告显示的图片
//        builder.setIcon(android.R.drawable.ic_dialog_alert);
        //设置警告对话框的提示信息
        builder.setMessage("确认要退出登录并清除当前用户信息？");
        //设置”正面”按钮，及点击事件
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                SharedPreferencedUtils.setBool("isproprety",false);
                ActivityUtil.getInstance().openActivity(getActivity(), PropertLoginActivity.class);
            }
        });
        //设置“反面”按钮，及点击事件
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
  /*      //设置“中立”按钮，及点击事件
        builder.setNeutralButton("等等看吧", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });*/
        //显示对话框
        builder.show();
    }

    public void show_call(){
        //实例化建造者
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        //设置警告对话框的标题
        //设置警告显示的图片
//        builder.setIcon(android.R.drawable.ic_dialog_alert);
        //设置警告对话框的提示信息
        builder.setMessage("拨打"+customer_service_phone);
        //设置”正面”按钮，及点击事件
        builder.setPositiveButton("呼叫", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(Intent.ACTION_DIAL);

                Uri data = Uri.parse("tel:" +customer_service_phone);
                intent.setData(data);
                startActivity(intent);
            }
        });
        //设置“中立”按钮，及点击事件
        builder.setNeutralButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        //显示对话框
        builder.show();
    }
    @Override
    public void useNightMode(boolean isNight) {

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
    public void getSSMCode(String action) {

    }

    @Override
    public void loginEstates(PropertLoginBean bean) {

    }

    @Override
    public void noticeEstates(PropretyNoticeBean bean) {

    }

    @Override
    public void propretyLive(PropretyLiveBean list) {

    }

    @Override
    public void findVillagename(List<VillageNameBean> list) {
        adapter.getData().clear();
        adapter.setNewData(list);
    }

    @Override
    public void findLiveaddress(List<LiveAddressBean> list) {

    }

    public PopupWindow showTipPopupWindow(final View anchorView) {

        final View contentView = LayoutInflater.from(anchorView.getContext()).inflate(R.layout.select_qu_popu, null);
        contentView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
                RecyclerView recyclerView = contentView.findViewById(R.id.select_recycle);
         initAdapter(recyclerView);
        final PopupWindow popupWindow = new PopupWindow(contentView, contentView.getMeasuredWidth(), contentView.getMeasuredHeight(), false);

        recyclerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow.dismiss();
            }
        });
        contentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });

        contentView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                // 自动调整箭头的位置
               // autoAdjustArrowPos(popupWindow, contentView, anchorView);
                contentView.getViewTreeObserver().removeGlobalOnLayoutListener(this);
            }
        });
        popupWindow.setBackgroundDrawable(this.getResources().getDrawable(R.color.transparent));
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                RotateAnimation animation = new RotateAnimation(180, 0, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                animation.setDuration(350);
                animation.setFillAfter(true);
                select_qu_im.startAnimation(animation);
                gray_layout.setVisibility(View.GONE);
            }
        });
        // 如果希望showAsDropDown方法能够在下面空间不足时自动在anchorView的上面弹出
        // 必须在创建PopupWindow的时候指定高度，不能用wrap_content
        popupWindow.showAsDropDown(anchorView);
        return popupWindow;
    }
    private void autoAdjustArrowPos(PopupWindow popupWindow, View contentView, View anchorView) {
  /*      View upArrow = contentView.findViewById(R.id.up_arrow);
        View downArrow = contentView.findViewById(R.id.down_arrow);

        int pos[] = new int[2];
        contentView.getLocationOnScreen(pos);
        int popLeftPos = pos[0];
        anchorView.getLocationOnScreen(pos);
        int anchorLeftPos = pos[0];
        int arrowLeftMargin = anchorLeftPos - popLeftPos + anchorView.getWidth() / 2 - upArrow.getWidth() / 2;
        upArrow.setVisibility(popupWindow.isAboveAnchor() ? View.INVISIBLE : View.VISIBLE);
        downArrow.setVisibility(popupWindow.isAboveAnchor() ? View.VISIBLE : View.INVISIBLE);

        RelativeLayout.LayoutParams upArrowParams = (RelativeLayout.LayoutParams) upArrow.getLayoutParams();
        upArrowParams.leftMargin = arrowLeftMargin;
        RelativeLayout.LayoutParams downArrowParams = (RelativeLayout.LayoutParams) downArrow.getLayoutParams();
        downArrowParams.leftMargin = arrowLeftMargin;*/
    }
    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        POPU.dismiss();
        vid = this.adapter.getData().get(position).getVid();
        p_imageurl = this.adapter.getData().get(position).getHeadurl();
        qu_name.setText(this.adapter.getData().get(position).getVillagename());
        SharedPreferencedUtils.setStr("vid",this.adapter.getData().get(position).getVid());
        SharedPreferencedUtils.setStr("proprety_cityid",this.adapter.getData().get(position).getCityid()+"");
        SharedPreferencedUtils.setStr("villagename",this.adapter.getData().get(position).getVillagename()+"");
        SharedPreferencedUtils.setStr("address_vill",this.adapter.getData().get(position).getCityname()+this.adapter.getData().get(position).getVillagename());
        RotateAnimation animation = new RotateAnimation(180, 0, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        animation.setDuration(350);
        animation.setFillAfter(true);
        select_qu_im.startAnimation(animation);
        gray_layout.setVisibility(View.GONE);
    }
}
