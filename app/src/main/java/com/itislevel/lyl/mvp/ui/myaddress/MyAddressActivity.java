package com.itislevel.lyl.mvp.ui.myaddress;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.os.Message;
import android.support.v7.widget.AppCompatTextView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.OptionsPickerView;
import com.flyco.animation.BaseAnimatorSet;
import com.flyco.animation.SlideEnter.SlideBottomEnter;
import com.google.gson.Gson;
import com.itislevel.lyl.R;
import com.itislevel.lyl.app.Constants;
import com.itislevel.lyl.base.RootActivity;
import com.itislevel.lyl.mvp.model.bean.AddressBean;
import com.itislevel.lyl.mvp.model.bean.BaseProvaceBean;
import com.itislevel.lyl.mvp.model.bean.CityBean;
import com.itislevel.lyl.mvp.model.bean.MyReceiveAddrBean;
import com.itislevel.lyl.utils.ActivityUtil;
import com.itislevel.lyl.utils.GetJsonDataUtil;
import com.itislevel.lyl.utils.GsonUtil;
import com.itislevel.lyl.utils.SAToast;
import com.itislevel.lyl.utils.SharedPreferencedUtils;
import com.itislevel.lyl.utils.ToastUtil;
import com.itislevel.lyl.utils.rxbus.RxBus;
import com.itislevel.lyl.utils.rxbus.annotation.Subscribe;
import com.itislevel.lyl.utils.rxbus.annotation.UseRxBus;
import com.itislevel.lyl.utils.rxbus.event.EventThread;
import com.itislevel.lyl.widget.AddressBottomDoalog;
import com.kyleduo.switchbutton.SwitchButton;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

@UseRxBus
public class MyAddressActivity extends RootActivity<MyAddressPresenter> implements
        MyAddressContract.View {

    @BindView(R.id.sb_setting_default_addr)
    SwitchButton sb_setting_default_addr;

    private BaseAnimatorSet mBasIn;
    private AddressBottomDoalog provinceBottomDoalog;
    private AddressBottomDoalog cityBottomDoalog;
    private AddressBottomDoalog countyBottomDoalog;

    private String provinceName;
    private String provinceId;

    private String cityName;
    private String cityId;

    private String countyName;
    private String countyId;

    private String streetName;
    private String streetId;

    @BindView(R.id.select_privce_linear)
    LinearLayout select_privce_linear;//选择地区

    @BindView(R.id.our_city)
    AppCompatTextView our_city;

    @BindView(R.id.et_name)
    EditText et_name;

    @BindView(R.id.et_phone)
    EditText et_phone;

    @BindView(R.id.et_detail_address)
    EditText et_detail_address;

    private InputMethodManager inputMethodManager;

    private String sign = "0"; // 标识（0：添加地址，1：修改地址）
    android.os.Handler handler;

    //城市

    private ArrayList<BaseProvaceBean> options1Items = new ArrayList<>();
    private ArrayList<ArrayList<CityBean>> options2Items = new ArrayList<>();
    private ArrayList<ArrayList<ArrayList<BaseProvaceBean.CitylistBean.ArealistBean>>> options3Items = new ArrayList<>();


    private ArrayList<String> options11Items = new ArrayList<>();
    private ArrayList<ArrayList<String>> options22Items = new ArrayList<>();
    private ArrayList<ArrayList<ArrayList<String>>> options33Items = new ArrayList<>();
    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_address;
    }

    @SuppressLint("HandlerLeak")
    @Override
    protected void initEventAndData() {
        setToolbarTvTitle("收获地址");
        mBasIn = new SlideBottomEnter();
        inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

        setToolbarMoreTxt("保存");

        setToolbarMoreClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                put_data();
            }
        });
        loadReceAddr();
        new Thread(new Runnable() {
            @Override
            public void run() {
                //解析城市的数据
                initJsonData();
            }
        }).start();

        handler = new android.os.Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                loadingDialog.dismiss();
            }
        };
    }

    private void loadReceAddr() {
        loadingDialog.show();
        HashMap<String, Object> request = new HashMap<>();
        request.put("token", SharedPreferencedUtils.getStr(Constants.USER_TOKEN));
        request.put("usernum", SharedPreferencedUtils.getStr(Constants.USER_NUM));
        request.put("userid", SharedPreferencedUtils.getStr(Constants.USER_ID));

        mPresenter.userFindRecAddress(GsonUtil.obj2JSON(request));

    }

    @OnClick(R.id.select_privce_linear)
    public void click(View view) {
        InputMethodManager imm = (InputMethodManager) et_name
                .getContext().getSystemService(
                        Context.INPUT_METHOD_SERVICE);
        if (imm.isActive()) {
            imm.hideSoftInputFromWindow(
                    et_name.getApplicationWindowToken(), 0);
        }

        HashMap<String, Object> request = new HashMap<>();
        request.put("token", SharedPreferencedUtils.getStr(Constants.USER_TOKEN));
        request.put("usernum", SharedPreferencedUtils.getStr(Constants.USER_NUM));
        switch (view.getId()) {
            case R.id.select_privce_linear://城市三级联动
                showPickerView();
                break;
        }
    }


    @Subscribe(tag = RxBus.TAG_UPDATE, thread = EventThread.MAIN_THREAD)
    public void selected(String message) {
        if (!TextUtils.isEmpty(message)) {
            String[] split = message.split(",");
            int tag = Integer.parseInt(split[0]);
            switch (tag) {
                case 0:
                    if (!TextUtils.isEmpty(provinceId) && !provinceId.equals(split[1])) {
                        cityId = "";
                        cityName = "";
                        countyId = "";
                        countyName = "";
                        streetId = "";
                        streetName = "";

                    }
                    provinceId = split[1];
                    provinceName = split[2];
                    break;
                case 1:
                    if (!TextUtils.isEmpty(cityId) && !cityId.equals(split[1])) {
                        countyId = "";
                        countyName = "";
                        streetId = "";
                        streetName = "";
                    }
                    cityId = split[1];
                    cityName = split[2];
                    break;
                case 2:
                    if (!TextUtils.isEmpty(countyId) && !countyId.equals(split[1])) {
                        streetId = "";
                        streetName = "";
                    }
                    countyId = split[1];
                    countyName = split[2];
                    break;
                case 3:
                    // 街道
                    if (!TextUtils.isEmpty(streetId) && !streetId.equals(split[1])) {

                    }
                    break;
            }
        }

    }


    @Override
    public void showContent(String msg) {

    }

    @Override
    public void province(List<AddressBean> addressBeans) {
        provinceBottomDoalog.setAddressBeans(addressBeans);
    }

    @Override
    public void city(List<AddressBean> addressBeans) {
        cityBottomDoalog.setAddressBeans(addressBeans);
    }

    @Override
    public void county(List<AddressBean> addressBeans) {
        if (addressBeans.size()<=0){
            countyBottomDoalog.hide();

        }else{
            countyBottomDoalog.setAddressBeans(addressBeans);
        }
    }

    @Override
    public void userFindRecAddress(MyReceiveAddrBean address) {
        if (address.getList() != null && address.getList().size() > 0) {
            MyReceiveAddrBean.ListBean listBean = address.getList().get(0);
            if (listBean.getProvname() == null) {
                sign = "0";
            } else {
                sign = "1";

                et_name.setText(listBean.getUsername());
                et_phone.setText(listBean.getPhone());
                et_detail_address.setText(listBean.getDetailaddress());

                provinceId = String.valueOf(listBean.getProvid());
                provinceName = listBean.getProvname();

                cityId = String.valueOf(listBean.getCityid());
                cityName = listBean.getCityname();


                countyId = String.valueOf(listBean.getCountid());
                countyName = listBean.getCountname();

                our_city.setText(provinceName+"|"+cityName+"|"+countyName);
            }
        }else{
            sign = "0";
        }

    }

    @Override
    public void userUpdateRecAddress(String message) {

        ToastUtil.Success("地址更新成功");
        ActivityUtil.getInstance().closeActivity(this);
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
    public void stateError(Throwable e) {
        super.stateError(e);
        sign = "0";
    }
    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
    }

    private void showPickerView(){// 弹出选择器
        OptionsPickerView pvOptions = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                provinceId = options1Items.get(options1).getId()+"";
                provinceName = options1Items.get(options1).getS_name();
                cityId = options2Items.get(options1).get(options2).getId()+"";
                cityName = options2Items.get(options1).get(options2).getS_name();

                countyId  = options3Items.get(options1).get(options2).get(options3).getId()+"";
                countyName  = options3Items.get(options1).get(options2).get(options3).getS_name();

                our_city.setText(provinceName+"|"+cityName+"|"+countyName);

                String tx = options1Items.get(options1).getS_name() +
                        options2Items.get(options1).get(options2).getS_name() +
                        options3Items.get(options1).get(options2).get(options3).getS_name();

                Toast.makeText(MyAddressActivity.this, tx, Toast.LENGTH_SHORT).show();
            }
        })
                .setTitleText("城市选择")
                .setTitleColor(Color.parseColor("#282828"))
                .setDividerColor(Color.BLACK)
                .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
                .setSubmitColor(Color.parseColor("#ff7900"))
                .setCancelColor(Color.parseColor("#282828"))
                .setContentTextSize(20)
                .build();

        //*pvOptions.setPicker(options1Items);//一级选择器
        //pvOptions.setPicker(options1Items, options2Items);//二级选择器*//*
        pvOptions.setPicker(options11Items, options22Items, options33Items);//三级选择器
        pvOptions.show();
    }
    private void initJsonData() {//解析数据
        String JsonData = new GetJsonDataUtil().getJson(this, "prov_city_area.json");//获取assets目录下的json文件数据

        ArrayList<BaseProvaceBean> jsonBean = parseData(JsonData);//用Gson 转成实体

        /**
         * 添加省份数据
         *
         * 注意：如果是添加的JavaBean实体，则实体类需要实现 IPickerViewData 接口，
         * PickerView会通过getPickerViewText方法获取字符串显示出来。
         */
        options1Items = jsonBean;
        int start_size = options1Items.size();
        for(int x= 0;x<start_size;x++)
        {
            options11Items.add(options1Items.get(x).getS_name());
        }
        for (int i = 0; i < jsonBean.size(); i++) {//遍历省份
            ArrayList<CityBean> CityList = new ArrayList<>();//该省的城市列表（第二级）
            ArrayList<String> CityList1 = new ArrayList<>();//该省的城市列表（第二级）
            ArrayList<ArrayList<BaseProvaceBean.CitylistBean.ArealistBean>> Province_AreaList = new ArrayList<>();//该省的所有地区列表（第三极）
            ArrayList<ArrayList<String>> Province_AreaList1 = new ArrayList<>();//该省的所有地区列表（第三极）

            for (int c = 0; c < jsonBean.get(i).getCitylist().size(); c++) {//遍历该省份的所有城市
                String CityName = jsonBean.get(i).getCitylist().get(c).getS_name();
                CityList1.add(CityName);
                int CityId = jsonBean.get(i).getCitylist().get(c).getId();
                CityBean  cityBean= new CityBean(CityName,CityId);
                CityList.add(cityBean);//添加城市

                ArrayList<BaseProvaceBean.CitylistBean.ArealistBean> City_AreaList = new ArrayList<>();//该城市的所有地区列表
                ArrayList<String> City_AreaList1 = new ArrayList<>();//该城市的所有地区列表

                //如果无地区数据，建议添加空字符串，防止数据为null 导致三个选项长度不匹配造成崩溃
                if (jsonBean.get(i).getCitylist().get(c).getArealist() == null
                        || jsonBean.get(i).getCitylist().get(c).getArealist().size() == 0) {
                    BaseProvaceBean.CitylistBean.ArealistBean countyBean  = new BaseProvaceBean.CitylistBean.ArealistBean(0,"");
                    City_AreaList.add(countyBean);
                    City_AreaList1.add("");
                } else {
                    City_AreaList.addAll(jsonBean.get(i).getCitylist().get(c).getArealist());
                    int size = jsonBean.get(i).getCitylist().get(c).getArealist().size();
                    for(int h =0 ; h < size ;h++)
                    {
                        City_AreaList1.add(jsonBean.get(i).getCitylist().get(c).getArealist().get(h).getS_name());
                    }
                }
                Province_AreaList.add(City_AreaList);//添加该省所有地区数据
                Province_AreaList1.add(City_AreaList1);//添加该省所有地区数据
            }

            /**
             * 添加城市数据
             */
            options2Items.add(CityList);
            options22Items.add(CityList1);
            /**
             * 添加地区数据
             */
            options3Items.add(Province_AreaList);
            options33Items.add(Province_AreaList1);
        }
        handler.sendEmptyMessage(1);
    }

    public ArrayList<BaseProvaceBean> parseData(String result) {//Gson 解析
        ArrayList<BaseProvaceBean> detail = new ArrayList<>();
        try {
            JSONArray data = new JSONArray(result);
            Gson gson = new Gson();
            for (int i = 0; i < data.length(); i++) {
                BaseProvaceBean  entity = gson.fromJson(data.optJSONObject(i).toString(), BaseProvaceBean.class);
                detail.add(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
          System.out.println("解析数据失败***********************************");
        }
        return detail;
    }
    private void put_data()
    {     TextView tvMorView = getTvMorView();
        String moreTitle = tvMorView.getText().toString();
        int pos = et_name.getText().length();//记录光标的位置
        if (moreTitle.equals("编辑")) {
            setToolbarMoreTxt("保存");
            et_name.setFocusable(true);
            et_name.setFocusableInTouchMode(true);
            et_name.setGravity(Gravity.LEFT | Gravity.CENTER);
            et_name.requestFocus();
//                    et_name.setBackground(getResources().getDrawable(R.drawable
// .shape_et_user_focus));
            et_name.setSelection(pos);
            et_phone.setFocusable(true);
            et_phone.setFocusableInTouchMode(true);
            et_phone.setGravity(Gravity.LEFT | Gravity.CENTER);
//                    et_phone.setBackground(getResources().getDrawable(R.drawable
// .shape_et_user_focus));
            et_detail_address.setFocusable(true);
            et_detail_address.setFocusableInTouchMode(true);

        } else {
            setToolbarMoreTxt("保存");
            et_name.setFocusable(false);
            et_name.setFocusableInTouchMode(false);
            et_name.setGravity(Gravity.RIGHT | Gravity.CENTER);
//                    et_name.setBackground(getResources().getDrawable(R.drawable
// .shape_et_user_normal));
            et_phone.setFocusable(false);
            et_phone.setFocusableInTouchMode(false);
            et_phone.setGravity(Gravity.RIGHT | Gravity.CENTER);
//                    et_phone.setBackground(getResources().getDrawable(R.drawable
// .shape_et_user_normal));
            et_detail_address.setFocusable(false);
            et_detail_address.setFocusableInTouchMode(false);

            //提交数据 数据校验
            HashMap<String, Object> request = new HashMap<>();
            request.put("token", SharedPreferencedUtils.getStr(Constants.USER_TOKEN));
            request.put("usernum", SharedPreferencedUtils.getStr(Constants.USER_NUM));
            request.put("userid", SharedPreferencedUtils.getStr(Constants.USER_ID));
            request.put("username", et_name.getText().toString().trim());
            request.put("phone", et_phone.getText().toString().trim());
            request.put("detailaddress", et_detail_address.getText().toString().trim());
            request.put("sign", sign);
            request.put("provid", provinceId);
            request.put("provname", provinceName);
            request.put("cityid", cityId);
            request.put("cityname", cityName);
            request.put("countid", countyId);
            request.put("countname", countyName);
            mPresenter.userUpdateRecAddress(GsonUtil.obj2JSON(request));
        }
    }
}
