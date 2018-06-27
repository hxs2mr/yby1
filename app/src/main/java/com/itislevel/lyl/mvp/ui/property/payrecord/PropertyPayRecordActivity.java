package com.itislevel.lyl.mvp.ui.property.payrecord;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.bigkoo.pickerview.TimePickerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.zhouwei.library.CustomPopWindow;
import com.itislevel.lyl.R;
import com.itislevel.lyl.adapter.DFindCommentAdapter;
import com.itislevel.lyl.adapter.LiveAddressBean;
import com.itislevel.lyl.adapter.PropretyHouseListAdapter;
import com.itislevel.lyl.adapter.RecordListAdapter;
import com.itislevel.lyl.adapter.RecordNewListAdapter;
import com.itislevel.lyl.adapter.RecordTimeAdapter;
import com.itislevel.lyl.adapter.SelectDanAdapter;
import com.itislevel.lyl.adapter.SelectQuAdapter;
import com.itislevel.lyl.app.Constants;
import com.itislevel.lyl.base.RootActivity;
import com.itislevel.lyl.mvp.model.bean.ListFollowItemBean;
import com.itislevel.lyl.mvp.model.bean.PayLuBean;
import com.itislevel.lyl.mvp.model.bean.RecordBean;
import com.itislevel.lyl.mvp.model.bean.VillageNameBean;
import com.itislevel.lyl.mvp.ui.main.dynamic.childfragment.dfind.DFindFragment;
import com.itislevel.lyl.mvp.ui.property.payrecord.decoration.SectionItemDecoration;
import com.itislevel.lyl.utils.ActivityUtil;
import com.itislevel.lyl.utils.DateUtil;
import com.itislevel.lyl.utils.GsonUtil;
import com.itislevel.lyl.utils.SAToast;
import com.itislevel.lyl.utils.SharedPreferencedUtils;
import com.itislevel.lyl.utils.UtilsStyle;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

import static com.itislevel.lyl.utils.Dp_to_Px.dip2px;

/**
 * Created by Administrator on 2018\5\31 0031.
 */

public class PropertyPayRecordActivity extends RootActivity<PayRecordPresenter>implements PayRecordContract.View, BaseQuickAdapter.OnItemChildClickListener{

    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;


    @BindView(R.id.p_p_back)
    LinearLayoutCompat p_p_back;


    @BindView(R.id.select_qu_linear)
    LinearLayoutCompat select_qu_linear;

    @BindView(R.id.qu_name)
    AppCompatTextView qu_name;

    @BindView(R.id.select_qu_im)
    AppCompatImageView select_qu_im;

    @BindView(R.id.gray_layout)
    View gray_layout;

    private PopupWindow POPU;
    private RecordListAdapter adapter;

    private List<PayLuBean.ListBean> adapter_bean;
    private int start_flage = 0;

    private SelectDanAdapter Qu_Adapter;
    private List<LiveAddressBean> LD_LIST;
    private RecordNewListAdapter newListAdapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UtilsStyle.setStatusBarMode(this,true); //黑色的主题图标
    }

    // 评论与回复的点击事件 start
    public static PayRecordListener payRecordListener;
    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }
    @Override
    protected int getLayoutId() {
        return R.layout.activity_record;
    }
    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void initEventAndData() {
        payRecordListener = new PayRecordListener();
        qu_name.setText("全部");
        LD_LIST = new ArrayList<>();
        adapter_bean = new ArrayList<>();
      /*  RecordBean bean1 = new RecordBean("1","a","1月","100");

        RecordBean bean2 = new RecordBean("1","a","2月","330");

        RecordBean bean3 = new RecordBean("1","b","3月","440");

        RecordBean bean4 = new RecordBean("1","b","4月","1000");

        RecordBean bean5 = new RecordBean("1","c","5月","440");

        RecordBean bean6 = new RecordBean("1","c","6月","110");

        RecordBean bean7 = new RecordBean("1","c","7月","3330");

        RecordBean bean8 = new RecordBean("1","d","8月","1110");
        RecordBean bean9 = new RecordBean("1","e","9月","900");
        RecordBean bean10 = new RecordBean("1","e","10月","120");
        RecordBean bean11 = new RecordBean("1","f","11月","770");
        RecordBean bean12 = new RecordBean("1","f","12月","660");
        mAllCities.add(bean1);
        mAllCities.add(bean2);
        mAllCities.add(bean3);
        mAllCities.add(bean4);
        mAllCities.add(bean5);
        mAllCities.add(bean6);
        mAllCities.add(bean7);
        mAllCities.add(bean8);
        mAllCities.add(bean9);
        mAllCities.add(bean10);
        mAllCities.add(bean11);
        mAllCities.add(bean12);*/
        initAdapter();

        initRecyclerOnTouch();
        loadDanData();
        loadData("","");
    }
    private void loadDanData() {
        Map<String, Object> request1 = new HashMap<>();
        request1.put("token", SharedPreferencedUtils.getStr("p_token",""));
        request1.put("phone", SharedPreferencedUtils.getStr("proprety_phone",""));
        request1.put("vid", SharedPreferencedUtils.getStr("vid",""));
        mPresenter.findLiveaddress(GsonUtil.obj2JSON(request1));
    }
    private void loadData(String num,String date)
    {
        loadingDialog.show();
        Map<String, Object> request = new HashMap<>();
        request.put("token", SharedPreferencedUtils.getStr("p_token",""));
        request.put("phone", SharedPreferencedUtils.getStr("proprety_phone",""));
        request.put("vid",SharedPreferencedUtils.getStr("vid",""));
        request.put("usernum", num);
        request.put("inputDate", date);
        mPresenter.estatesPayList(GsonUtil.obj2JSON(request));
    }

    private void initRecyclerOnTouch() {
        recyclerView.setOnTouchListener(new View.OnTouchListener() {//设置点击区域的选择时间事件
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()){
                    case MotionEvent.ACTION_MOVE:
                        return false;
                    case MotionEvent.ACTION_DOWN:
                        //获取屏幕上点击的坐标
                        float x=motionEvent.getX();
                        float y1 = motionEvent.getY();
                        System.out.println("y****************************"+y1);
                        System.out.println("zai****************************"+dip2px(PropertyPayRecordActivity.this,20));
                        int withd =  recyclerView.getLayoutManager().getWidth()-120;
                        //如果坐标在我们的文字区域内，则将点击的文字改颜色
                        if(x>=withd&& (y1<dip2px(PropertyPayRecordActivity.this,45))){
                            show_time();
                        }
                        return  true;
                    case MotionEvent.ACTION_UP:
                        return  false;
                }
                //这句话不要修改
                return false;
            }
        });
    }
    private void initAdapter() {
        LinearLayoutManager manager = new LinearLayoutManager(this);
        // ((SimpleItemAnimator)recyclerView.getItemAnimator()).setSupportsChangeAnimations(false);
        recyclerView.setLayoutManager(manager);
        if (adapter == null){
            adapter = new RecordListAdapter(R.layout.record_item, this);
            adapter.setOnItemChildClickListener(this);
            adapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_BOTTOM); //切换动画
            adapter.setEnableLoadMore(false);
            recyclerView.setHasFixedSize(true);
            recyclerView.setAdapter(adapter);
            View emptyView = View.inflate(this, R.layout.partial_empty_view, null);
            adapter.setEmptyView(emptyView);
        }
    }

    @OnClick({R.id.p_p_back,R.id.select_qu_linear})
    public void onclick(View view)
    {
        switch (view.getId())
        {
         case R.id.p_p_back:
            ActivityUtil.getInstance().closeActivity(this);
            break;
         case R.id.select_qu_linear:
                gray_layout.setVisibility(View.VISIBLE);
                POPU = showTipPopupWindow(select_qu_linear);
                RotateAnimation animation = new RotateAnimation(0, 180, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                animation.setDuration(350);
                animation.setFillAfter(true);
                select_qu_im.startAnimation(animation);
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

    private  void show_time()
    {
        TimePickerView pvTime;

        Calendar selectedDate = Calendar.getInstance();

        Calendar startDate = Calendar.getInstance();
        startDate.set(1968, 5, 5);

        Calendar endDate = Calendar.getInstance();
        endDate.set(DateUtil.getCurrentYear(), DateUtil.getCurrentMonth(), DateUtil
                .getCurrentDay());

        //时间选择器
        pvTime = new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
                // 这里回调过来的v,就是show()方法里面所添加的 View 参数，如果show的时候没有添加参数，v则为null
                loadData("",DateUtil.getCurrentYYYYMM(date)); //查询选择的时间下的缴费记录
              /*  RecordBean bean1 = new RecordBean("1","a",DateUtil.getCurrentDateMM(date),"100");//测试的数据
                mAllCities_Time.add(bean1);
                adapter_time.setNewData(mAllCities_Time);*/
                }})
                //年月日时分秒 的显示与否，不设置则默认全部显示
                .setType(new boolean[]{true, true, false, false, false, false})
                .setLabel("", "", "", "", "", "")
                .isCenterLabel(false)
                .setDividerColor(Color.DKGRAY)
                .setContentSize(21)
                .setCancelColor(Color.parseColor("#282828"))
                .setSubmitColor(Color.parseColor("#282828"))
                .setDate(selectedDate)
                .setRangDate(startDate, endDate)
//                .setBackgroundId(0x00FFFFFF) //设置外部遮罩颜色
                .setDecorView(null)
                .build();
            pvTime.show();
    }
    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
    }

    @Override
    public void estatesPayList(List<PayLuBean> bean) {//缴费记录的结果
            loadingDialog.dismiss();
            this.adapter.getData().clear();
            if (bean.size() <= 0) {
                View emptyView = View.inflate(this, R.layout.partial_empty_view, null);
                TextView viewById = emptyView.findViewById(R.id.tv_message);
                adapter.setEmptyView(emptyView);
                return;
            }
            recyclerView.addItemDecoration(new SectionItemDecoration(this, bean,this), 0);
            adapter.setNewData(bean);
    }

    @Override
    public void findLiveaddress(List<LiveAddressBean> list) {
        LD_LIST = list;
    }

    @Override
    public void stateError() {
        super.stateError();
                 adapter.getData().clear();
                View emptyView = View.inflate(this, R.layout.partial_empty_view, null);
                adapter.setEmptyView(emptyView);
        loadingDialog.dismiss();
    }

    private void initAdapter_Qu(RecyclerView select_recycle){
        LinearLayoutManager manager = new LinearLayoutManager(this);
        // ((SimpleItemAnimator)recyclerView.getItemAnimator()).setSupportsChangeAnimations(false);
        select_recycle.setLayoutManager(manager);
        Qu_Adapter = new SelectDanAdapter(R.layout.select_dan_litem,this);
        Qu_Adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                POPU.dismiss();
                loadData(Qu_Adapter.getData().get(position).getUsernum()+"","");
                qu_name.setText(Qu_Adapter.getData().get(position).getLiveaddress());
                RotateAnimation animation = new RotateAnimation(180, 0, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                animation.setDuration(350);
                animation.setFillAfter(true);
                select_qu_im.startAnimation(animation);
                gray_layout.setVisibility(View.GONE);

                //在加载房屋列表界面的数据
            }
        });
        Qu_Adapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_BOTTOM); //切换动画
        Qu_Adapter.setEnableLoadMore(false);
        select_recycle.setAdapter(Qu_Adapter);
        Qu_Adapter.setNewData(LD_LIST);
        /*
        * 测试的数据
        * */
    }
    public PopupWindow showTipPopupWindow(final View anchorView) {
        final View contentView = LayoutInflater.from(anchorView.getContext()).inflate(R.layout.select_dan_popu, null);
        contentView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        RecyclerView recyclerView = contentView.findViewById(R.id.select_recycle);
        initAdapter_Qu(recyclerView);
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

    class PayRecordListener implements BaseQuickAdapter.OnItemClickListener
            , BaseQuickAdapter.OnItemChildClickListener{
        @Override
        public void onItemChildClick(BaseQuickAdapter adapter, View view, final int position) {
            newListAdapter = (RecordNewListAdapter) adapter;
            Bundle bundle = new Bundle();
            String ordernum = "";
            String liveaddress = "";
            String paytotalfee = "";
            String types = "";
            String paymethod = "";
            long creattime = 0;
            String username = "";
                ordernum = newListAdapter.getData().get(position).getOrdernum();
                liveaddress =newListAdapter.getData().get(position).getLiveaddress();
                paytotalfee =newListAdapter.getData().get(position).getPaytotalfee();
                types =newListAdapter.getData().get(position).getTypes();
                creattime = newListAdapter.getData().get(position).getCreatedtime();
                paymethod = newListAdapter.getData().get(position).getPaymethod();
                username =newListAdapter.getData().get(position).getUsername();
            bundle.putString("ordernum",ordernum);
            bundle.putString("liveaddress",liveaddress);
            bundle.putString("paytotalfee",paytotalfee);
            bundle.putString("types",types);
            bundle.putLong("creattime",creattime);
            bundle.putString("paymethod",paymethod);
            bundle.putString("username",username);
            ActivityUtil.getInstance().openActivity(PropertyPayRecordActivity.this,PayRecordDetailActivity.class,bundle);
        }
        @Override
        public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//            closeCommentInput();
        }
    }
}
