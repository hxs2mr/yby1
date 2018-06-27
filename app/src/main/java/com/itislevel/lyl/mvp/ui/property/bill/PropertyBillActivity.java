package com.itislevel.lyl.mvp.ui.property.bill;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CompoundButton;

import com.bigkoo.pickerview.OptionsPickerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.itislevel.lyl.R;
import com.itislevel.lyl.adapter.PropretyBillAdapter;
import com.itislevel.lyl.adapter.PropretyHouseListAdapter;
import com.itislevel.lyl.app.Constants;
import com.itislevel.lyl.base.RootActivity;
import com.itislevel.lyl.mvp.model.bean.BillsBean;
import com.itislevel.lyl.mvp.model.bean.PayBillBean;
import com.itislevel.lyl.mvp.model.bean.PropertyBillBean;
import com.itislevel.lyl.mvp.ui.family.FamilyAddActivity;
import com.itislevel.lyl.mvp.ui.livingexpensess.LivingExpensesPayActivity;
import com.itislevel.lyl.mvp.ui.pay.PayInfoActivity;
import com.itislevel.lyl.mvp.ui.user.UpdateUserInfoActivity;
import com.itislevel.lyl.utils.ActivityUtil;
import com.itislevel.lyl.utils.GsonUtil;
import com.itislevel.lyl.utils.SAToast;
import com.itislevel.lyl.utils.SharedPreferencedUtils;
import com.itislevel.lyl.utils.ToastUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import retrofit2.http.PUT;

import static com.itislevel.lyl.utils.DateUtil.stringToDate;
import static com.itislevel.lyl.utils.DateUtil.timeStrSpanToDate;

/**
 * Created by Administrator on 2018\5\28 0028.
 */

public class PropertyBillActivity extends RootActivity<PropertyBillPresenter>implements PropertyBillContract.View, BaseQuickAdapter.OnItemChildClickListener, View.OnClickListener {

    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;

    @BindView(R.id.add_button)
     AppCompatButton add_button;

    @BindView(R.id.tv_count)
    AppCompatTextView tv_count;//合计多少个

    @BindView(R.id.checkbox_all)
    AppCompatCheckBox checkbox_all;

    @BindView(R.id.tv_temp)
    AppCompatTextView tv_temp;//多少钱

    private PropretyBillAdapter adapter;
    private Bundle bundle;
    public static String HOME_LOAATION;

    private String home_number;
    private String managerid;
    private String userid;
    private String vid;
    private String provinceid;
    private String cityid;
    private String countyid;
    private String username;
    private String usernum;

    private AppCompatCheckBox checkBox;
    private String types="";
    private String billds="";
    private String wu_p="";
    private String c_p="";
    private float total_price=0;
    private AppCompatTextView bill_select_time;
    private AppCompatTextView bill_date;
    private AppCompatTextView bill_monkey;
    private String danwei;
    private float pay_number;
    private int mPostation;
    private String end_date;
    private int change_type;
    private int bei ;
    private int billd_id=0;
    private AppCompatCheckBox onclick_checkbox;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected int getLayoutId() {
            return R.layout.activity_propertybill;
    }
    @Override
    protected void initEventAndData() {
        add_button.setOnClickListener(this);
        bundle = getIntent().getExtras();
        home_number = bundle.getString("homenumber");
        HOME_LOAATION =  bundle.getString("homelocation");
        managerid= bundle.getString("managerid");
        userid= bundle.getString("userid");
        vid= bundle.getString("vid");
        provinceid= bundle.getString("provinceid");
        cityid= bundle.getString("cityid");
        countyid= bundle.getString("countyid");
        usernum= bundle.getString("usernum");
        username =  bundle.getString("username");
        setToolbarTvTitle("账单详细");
        initAdapter();
        initData();
        checkbox_all.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b)
                {
                    init_del_true(true);
                }else {
                    init_del_true(false);
                }
            }
        });
    }

    @SuppressLint("SetTextI18n")
    private void init_del_true(boolean is_onclick) {
        total_price = 0;
        int size = adapter.getData().size();
        SAToast.makeText(this,""+size).show();
        if(is_onclick)
        {
            for(int i = 0 ; i < size; i++)
            {
                AppCompatCheckBox box;
                 box = (AppCompatCheckBox)adapter.getViewByPosition(recyclerView,i,R.id.checkbox);
                 if(box!=null)
                 {
                     box.setChecked(true);
                 }
                wu_p = adapter.getData().get(i).getPayfee();
                total_price =total_price+ Float.parseFloat(wu_p);
            }
            tv_count.setText("("+size+")");
            DecimalFormat df = new DecimalFormat("0.00");//格式化小数
            tv_temp.setText("￥"+ df.format(total_price));
        }else {
            for(int i = 0 ; i < size; i++)
            {
                AppCompatCheckBox box;
                box = (AppCompatCheckBox)adapter.getViewByPosition(recyclerView,i,R.id.checkbox);
                if(box!=null)
                {
                    box.setChecked(false);
                }
            }
            tv_count.setText("("+0+")");
            tv_temp.setText("￥"+0.00);
        }
        this.adapter.notifyDataSetChanged();
    }
    private void initData() {
        loadingDialog.show();
        Map<String, Object> request = new HashMap<>();
        request.put("token", SharedPreferencedUtils.getStr("p_token",""));
        request.put("phone", SharedPreferencedUtils.getStr("proprety_phone",""));
        request.put("usernumNO",home_number);
        mPresenter.propertyBill(GsonUtil.obj2JSON(request));//获取账单
    }

    private void initAdapter() {
        LinearLayoutManager manager = new LinearLayoutManager(this);
        // ((SimpleItemAnimator)recyclerView.getItemAnimator()).setSupportsChangeAnimations(false);
        recyclerView.setLayoutManager(manager);
        if (adapter == null){
            adapter = new PropretyBillAdapter(R.layout.propertybill_item, this);
            adapter.setOnItemChildClickListener(this);
            adapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN); //切换动画
            adapter.setEnableLoadMore(false);
            recyclerView.setAdapter(adapter);
            View emptyView = View.inflate(this, R.layout.partial_empty_view, null);
            adapter.setEmptyView(emptyView);
        }
    }
    @Override
    public void propertyBill(List<PropertyBillBean> billBeans) {
        loadingDialog.dismiss();
        adapter.setNewData(billBeans);
    }
    @Override
    public void propertyadd(String data) {//生成订单的dindh
        loadingDialog.dismiss();
        String detail="";
        bundle.putString(Constants.PAY_ORDERNUM, data);
        bundle.putString(Constants.PAY_MODULE_NAME, Constants.CART_MODEL_PROPERTY);
        bundle.putInt(Constants.PAY_FROM_ACTIVITY, Constants.PAY_FROM_LIVE_PROPERTY);

        bundle.putString(Constants.PAY_TOTALPRICE, total_price+"");
//                payfeefinishtime +
//                payfeefinishtime +
        if(types.equals("1"))
        {
            detail = "物业费";
        }else  if(types.equals("2")){
            detail = "停车费";
        }else {
            detail = "物业费/停车费";
        }
        bundle.putString(Constants.PAY_GOODS_DETAIL, detail);
        bundle.putString(Constants.PAY_GOODS_DESC,detail);
        total_price=0.0f;
        wu_p="";
        c_p="";
        types="";
        billds="";
        ActivityUtil.getInstance().openActivity(this,
                PayInfoActivity.class, bundle);
    }
    @Subscribe
    public void Onevent(PayBillBean billBean)
    {
        tv_count.setText("("+0+")");
        tv_temp.setText("￥"+0.00);
        ToastUtil.Info("付款成功");
        this.adapter.getData().clear();
        this.adapter.notifyDataSetChanged();
        initData();
    }
    @Override
    public void stateError(Throwable e) {
        super.stateError(e);
        loadingDialog.dismiss();
    }
    @Override
    public void findBillsMonth(String data) {
        System.out.println("到这来了**********************************************");
        initData();
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
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        mPostation = position;
        onclick_checkbox = (AppCompatCheckBox) this.adapter.getViewByPosition(recyclerView,position,R.id.checkbox);
        change_type = this.adapter.getData().get(position).getType();
        billd_id = this.adapter.getData().get(position).getBillid();
        float total = Float.parseFloat( this.adapter.getData().get(position).getPayfee());
        DecimalFormat df = new DecimalFormat("0.00");//格式化小数
        end_date = this.adapter.getData().get(position).getPayfeefinishtime();
        pay_number= this.adapter.getData().get(position).getPaytype();
        danwei = df.format((float)total/pay_number);//返回的是String类型
        bill_select_time = (AppCompatTextView) this.adapter.getViewByPosition(recyclerView,position,R.id.bill_select_time);
        bill_date = (AppCompatTextView) this.adapter.getViewByPosition(recyclerView,position,R.id.bill_date);
        bill_monkey =  (AppCompatTextView) this.adapter.getViewByPosition(recyclerView,position,R.id.bill_monkey);
        List<String> genderItems = new ArrayList<>();
        genderItems.add("1个月付");
        genderItems.add("2个月付");
        genderItems.add("3个月付");
        genderItems.add("4个月付");
        genderItems.add("5个月付");
        genderItems.add("6个月付");
        genderItems.add("7个月付");
        genderItems.add("8个月付");
        genderItems.add("9个月付");
        genderItems.add("10个月付");
        genderItems.add("11个月付");
        genderItems.add("12个月付");

        switch (view.getId())
        {
            case R.id.bill_select_time:
                OptionsPickerView optionsPickerView = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
                            @Override
                            public void onOptionsSelect(int options1, int options2, int options3, View v) {

                                bill_select_time.setText(genderItems.get(options1));
                                if(options1<9)
                                {
                                    bei = Integer.parseInt(genderItems.get(options1).substring(0,1));
                                }else {
                                    bei = Integer.parseInt(genderItems.get(options1).substring(0,2));
                                }
                                 loadingDialog.show();
                                Map<String, Object> request = new HashMap<>();
                                request.put("token", SharedPreferencedUtils.getStr("p_token",""));
                                request.put("phone", SharedPreferencedUtils.getStr("proprety_phone",""));
                                request.put("billid",billd_id);
                                request.put("moths",bei+"");
                                mPresenter.findBillsMonth(GsonUtil.obj2JSON(request));
                            }
                        }).build();

                optionsPickerView.setPicker(genderItems);

                optionsPickerView.show();
                break;

            case R.id.checkbox:
                    if(onclick_checkbox.isChecked())//添加
                    {
                        add_del(1,position);
                    }else {//减少
                        add_del(0,position);
                    }
                break;
        }
    }
    private void add_del(int i,int postion)
    {
        String data = tv_count.getText().toString().replace("(","");
        data = data.replace(")","");
        int toal = Integer.parseInt(data);
        total_price =Float.parseFloat(this.adapter.getData().get(postion).getPayfee());
        String monkey = tv_temp.getText().toString().replace("￥","");

        if(i == 1)
        {
            toal++;
            total_price = Float.parseFloat(monkey)+total_price;
            DecimalFormat df = new DecimalFormat("0.00");//格式化小数
            df.format(total_price);
            tv_temp.setText("￥"+  df.format(total_price));
        }else {
            total_price = Float.parseFloat(monkey)-total_price;
            DecimalFormat df = new DecimalFormat("0.00");//格式化小数
            df.format(total_price);
            tv_temp.setText("￥"+df.format(total_price));
            toal--;
        }
        tv_count.setText("("+toal+")");
        total_price =0.0f;
    }
    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.add_button://生成订单
                total_price=0.0f;
                for (int i =0 ; i< this.adapter.getData().size();i++)
                {
                    checkBox = (AppCompatCheckBox) this.adapter.getViewByPosition(recyclerView,i,R.id.checkbox);
                    if(checkBox!=null)
                    {
                        if(checkBox.isChecked())//判断是否被选中
                        {
                            if(types.equals(""))
                            {
                                billds = this.adapter.getData().get(i).getBillid()+"";
                                types = this.adapter.getData().get(i).getType()+"";
                                if(types.equals("1"))
                                {
                                    wu_p = this.adapter.getData().get(i).getPayfee();
                                    total_price = Float.parseFloat(wu_p);
                                }else {
                                    c_p = this.adapter.getData().get(i).getPayfee();
                                    total_price = Float.parseFloat(c_p);
                                }
                            }else if(!types.equals(this.adapter.getData().get(i).getType()))
                            {
                                if(this.adapter.getData().get(i).getType()==1)
                                {
                                    wu_p = this.adapter.getData().get(i).getPayfee();
                                    total_price =total_price+ Float.parseFloat(wu_p);
                                }else {
                                    c_p = this.adapter.getData().get(i).getPayfee();
                                    total_price =total_price+ Float.parseFloat(c_p);
                                }
                                types = types+","+this.adapter.getData().get(i).getType();
                                billds =billds+","+this.adapter.getData().get(i).getBillid();
                            }
                        }
                    }
                }
                if(types.equals(""))
                {
                    SAToast.makeText(this,"请选择缴费的类型!").show();
                    return;
                }
                add_own(wu_p,c_p,types);
                break;
        }
    }

    private void add_own(String wu_p,String c_p,String type) {
        loadingDialog.show();
        Map<String, Object> request = new HashMap<>();
        request.put("token", SharedPreferencedUtils.getStr("p_token",""));
        request.put("phone", SharedPreferencedUtils.getStr("proprety_phone",""));
        request.put("billids", billds);
        mPresenter.propertyadd(GsonUtil.obj2JSON(request));
    }
}
