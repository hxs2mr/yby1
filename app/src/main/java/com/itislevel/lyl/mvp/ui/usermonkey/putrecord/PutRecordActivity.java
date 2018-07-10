package com.itislevel.lyl.mvp.ui.usermonkey.putrecord;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;

import com.bigkoo.pickerview.TimePickerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.itislevel.lyl.R;
import com.itislevel.lyl.adapter.PutRecordListAdapter;
import com.itislevel.lyl.adapter.RecordListAdapter;
import com.itislevel.lyl.base.RootActivity;
import com.itislevel.lyl.mvp.model.bean.PayLuBean;
import com.itislevel.lyl.mvp.ui.property.payrecord.PropertyPayRecordActivity;
import com.itislevel.lyl.mvp.ui.property.payrecord.decoration.SectionItemDecoration;
import com.itislevel.lyl.utils.ActivityUtil;
import com.itislevel.lyl.utils.DateUtil;
import com.itislevel.lyl.utils.UtilsStyle;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import dagger.Provides;

import static com.itislevel.lyl.utils.Dp_to_Px.dip2px;

/**
 * 作者： XS
 * 邮箱：1363826037@qq.com
 * 描述:
 * 创建时间:  2018\7\9 0009 15:00
 */
public class PutRecordActivity extends RootActivity<PutRecordPresenter>implements PutRecordContract.View{

    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;

     private  PutRecordListAdapter adapter;
     public static  PutRecordListener putRecordListener;
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
        return R.layout.activity_putrecord;
    }

    @Override
    protected void initEventAndData() {
        putRecordListener = new PutRecordListener();
        initAdapter();
        initRecyclerOnTouch();
        setToolWight("返现记录");
        loadData();
    }

    private void loadData() {
        List<PayLuBean> list_bean = new ArrayList<>();
        PayLuBean bean = new PayLuBean();
        bean.setMonth("2018-5");
        bean.setMoney("200");
        list_bean.add(bean);

        PayLuBean bean1 = new PayLuBean();
        bean1.setMonth("2018-6");
        bean1.setMoney("650");
        list_bean.add(bean1);

        recyclerView.addItemDecoration(new SectionItemDecoration(this, list_bean,this), 0);
        adapter.setNewData(list_bean);
    }

    private void initAdapter() {
        LinearLayoutManager manager = new LinearLayoutManager(this);
        // ((SimpleItemAnimator)recyclerView.getItemAnimator()).setSupportsChangeAnimations(false);
        recyclerView.setLayoutManager(manager);
        if (adapter == null){
            adapter = new PutRecordListAdapter(R.layout.record_item, this);
            adapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_BOTTOM); //切换动画
            adapter.setEnableLoadMore(false);
            recyclerView.setHasFixedSize(true);
            recyclerView.setAdapter(adapter);
            View emptyView = View.inflate(this, R.layout.partial_empty_view, null);
            adapter.setEmptyView(emptyView);
        }
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
                        int withd =  recyclerView.getLayoutManager().getWidth()-120;
                        //如果坐标在我们的文字区域内，则将点击的文字改颜色
                        if(x>=withd&& (y1<dip2px(PutRecordActivity.this,45))){
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

    class PutRecordListener implements BaseQuickAdapter.OnItemClickListener, BaseQuickAdapter.OnItemChildClickListener{
        @Override
        public void onItemChildClick(BaseQuickAdapter adapter, View view, final int position) {
        }
        @Override
        public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
            ActivityUtil.getInstance().openActivity(PutRecordActivity.this,PutRecordDetailActivity.class);
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
               // loadData("",DateUtil.getCurrentYYYYMM(date)); //查询选择的时间下的缴费记录
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
}
