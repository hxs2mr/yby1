package com.itislevel.lyl.mvp.ui.usermonkey.setting;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.AppCompatTextView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Toast;

import com.bigkoo.pickerview.OptionsPickerView;
import com.flyco.animation.SlideEnter.SlideBottomEnter;
import com.google.gson.Gson;
import com.itislevel.lyl.R;
import com.itislevel.lyl.app.Constants;
import com.itislevel.lyl.base.RootActivity;
import com.itislevel.lyl.mvp.model.bean.BankCardBean;
import com.itislevel.lyl.mvp.model.bean.BaseProvaceBean;
import com.itislevel.lyl.mvp.model.bean.BlankListBean;
import com.itislevel.lyl.mvp.model.bean.BlankNameBean;
import com.itislevel.lyl.mvp.model.bean.CityBean;
import com.itislevel.lyl.mvp.ui.myaddress.MyAddressActivity;
import com.itislevel.lyl.utils.ActivityUtil;
import com.itislevel.lyl.utils.GetJsonDataUtil;
import com.itislevel.lyl.utils.GsonUtil;
import com.itislevel.lyl.utils.SharedPreferencedUtils;
import com.itislevel.lyl.utils.ToastUtil;
import com.itislevel.lyl.utils.UtilsStyle;
import com.itislevel.lyl.widget.DownTimer;
import com.itislevel.lyl.widget.SettingUserDataDalog;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

import static com.itislevel.lyl.utils.FormatUtil.checkBankCard;
import static com.itislevel.lyl.utils.FormatUtil.isIdCardNo;
import static com.itislevel.lyl.utils.FormatUtil.isMobileNO;

/**
 * 作者： XS
 * 邮箱：1363826037@qq.com
 * 描述:
 * 创建时间:  2018\7\10 0010 11:10
 */
public class SettingUserDataActivity extends RootActivity<MonkeySettingPresenter>implements MonkeySettingContract.View{
    @BindView(R.id.blank_dian)
    AppCompatTextView blank_dian;

    @BindView(R.id.blank_open_name)
    TextInputEditText blank_open_name;

    @BindView(R.id.blank_idcard)
    TextInputEditText blank_idcard;

    @BindView(R.id.blank_number)
    TextInputEditText blank_number;

    @BindView(R.id.blank_name)
    AppCompatTextView blank_name;

    @BindView(R.id.blank_address)
    AppCompatTextView blank_address;

    @BindView(R.id.blank_phone)
    TextInputEditText blank_phone;

    @BindView(R.id.blank_code)
    TextInputEditText blank_code;//验证码


    @BindView(R.id.tv_validate)
    AppCompatTextView tv_validate;//获取验证码


    private SettingUserDataDalog dataDalog;
    private SlideBottomEnter mBasIn = new SlideBottomEnter();
    //城市
    private ArrayList<BaseProvaceBean> options1Items = new ArrayList<>();
    private ArrayList<ArrayList<CityBean>> options2Items = new ArrayList<>();

    private ArrayList<String> options11Items = new ArrayList<>();
    private ArrayList<ArrayList<String>> options22Items = new ArrayList<>();

    private Bundle bundle;
    private String flage="0";
    private String p_name;
    private String c_name;
    private String zBlank_name="";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UtilsStyle.setStatusBarMode(this, true); //黑色的主题图标
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_msettinguser;
    }

    @Override
    protected void initEventAndData() {
        setToolWight("账户信息");
        blank_idcard.addTextChangedListener(new EditTextIdcardChangeListener());
        blank_number.addTextChangedListener(new EditTextNumberChangeListener());
        bundle = getIntent().getExtras();
        flage = bundle.getString("flage");
        if(flage.equals("1"))
        {
            blank_open_name.setFocusable(false);
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                //解析城市的数据
                initJsonData();
            }
        }).start();
    }


    private boolean checkform()
    {
        String idcard = blank_idcard.getText().toString();
        String number = blank_number.getText().toString();
        String name =  blank_open_name.getText().toString();
        String blank_name1 =  blank_name.getText().toString();
        String blank_address1 =  blank_address.getText().toString();
        //检查输入的文本是否正确
        boolean isPass = true;
        if(idcard.isEmpty())
        {
            blank_idcard.setError("请输入身份证号");
            isPass = false;
        }else {
            if(!isIdCardNo(idcard))
            {
                blank_idcard.setError("身份证不合法！");
            }else {
                blank_idcard.setError(null);
            }
        }
        if(number.isEmpty())
        {
            blank_number.setError("请输入银行卡");
            isPass = false;
        }else {
            if(!checkBankCard(number))
            {
                blank_number.setError("银行卡不合法！");
            }else {
                blank_number.setError(null);
            }
        }
        if(name.isEmpty())
        {
            blank_open_name.setError("请输入姓名");
            isPass = false;
        }else {
             blank_open_name.setError(null);
        }
        if(blank_name1.isEmpty())
        {
            blank_name.setError("请重新填写银行卡");
            isPass = false;
        }else {
            blank_name.setError(null);
        }
        if(blank_address1.isEmpty())
        {
            blank_address.setError("请选择银行卡所属地区!");
            isPass = false;
        }else {
            blank_address.setError(null);
        }

        return isPass;
    }
    private boolean checkform2(){
        String blank_dian1 = blank_dian.getText().toString();
        String phone = blank_phone.getText().toString();
        String code =  blank_code.getText().toString();
        boolean isPass = true;
        if(blank_dian1.isEmpty())
        {
            blank_address.setError("请选择归属地!");
            isPass = false;
        }else {
            blank_address.setError(null);
        }
        if(code.isEmpty())
        {
            blank_code.setError("请输入验证码!");
            isPass = false;
        }else {
            blank_code.setError(null);
        }
        if(phone.isEmpty())
        {
            blank_phone.setError("请输入手机号");
            isPass = false;
        }else {
            if(!isMobileNO(phone))
            {
                blank_phone.setError("手机号不合法！");
            }else {
                blank_phone.setError(null);
            }
        }
        return isPass;
    }

    @OnClick({R.id.blank_dian_linear,R.id.address_linear,R.id.setting_next_linear,R.id.tv_validate})
    public void Onclick(View view){
        switch (view.getId())
        {
            case R.id.blank_dian_linear:
                if(checkform())//检查前面几项是否也填写
                {
                    loadingDialog.show();
                    Map<String,String> request = new HashMap<>();
                    request.put("token", SharedPreferencedUtils.getStr(Constants.USER_TOKEN,""));
                    request.put("usernum", SharedPreferencedUtils.getStr(Constants.USER_NUM,""));
                    request.put("bankname", blank_name.getText().toString());
                    request.put("provname", p_name);
                    request.put("cityname", c_name);
                    request.put("keyword", "");
                    request.put("pageIndex", 1+"");
                    request.put("pageSize", 999+"");
                    mPresenter.queryBankBranchList(GsonUtil.obj2JSON(request));
                }
                break;
            case R.id.address_linear://选择地区
                String number = blank_number.getText().toString();
                if(number.isEmpty())
                {
                    blank_number.setError("请输入银行卡");
                }else {
                    if(!checkBankCard(number))
                    {
                        blank_number.setError("银行卡不合法！");
                    }else {
                        blank_number.setError(null);
                        showPickerView();
                    }
                }
                break;
            case R.id.setting_next_linear://下一步
                if(checkform()&&checkform2())
                {
                    loadingDialog.show();
                    Map<String,String> request = new HashMap<>();
                    request.put("token", SharedPreferencedUtils.getStr(Constants.USER_TOKEN,""));
                    request.put("usernum", SharedPreferencedUtils.getStr(Constants.USER_NUM,""));
                    request.put("idcard", blank_idcard.getText().toString());
                    request.put("bankcardnum", blank_number.getText().toString());
                    request.put("realname",blank_open_name.getText().toString());
                    request.put("bankname",  blank_name.getText().toString());
                    request.put("location", p_name+c_name);
                    request.put("branchname", zBlank_name);
                    request.put("phone", blank_phone.getText().toString().trim());
                    request.put("randcode", blank_code.getText().toString().trim());
                    mPresenter.bankCardVerification(GsonUtil.obj2JSON(request));
                    System.out.println("json数据888888*******************"+GsonUtil.obj2JSON(request));
                }
                break;
            case R.id.tv_validate:
                if(isMobileNO( blank_phone.getText().toString()))
                {
                    getValidate();
                }else {
                    blank_phone.setError("手机格式不合法！");
                }
                break;
        }
    }
    private void getValidate() {//获取验证码
        String phone = blank_phone.getText().toString().trim();
        if (TextUtils.isEmpty(phone)) {
            ToastUtil.Info("号码不能为空");
            return;
        }
        //判断号码的合法性

        Map<String, Object> request = new HashMap<>();

        request.put("token","");
        request.put("usernum", "");
        request.put("phone", phone);

        mPresenter.getSSMCode(GsonUtil.obj2JSON(request));
    }

    private void showPickerView(){// 弹出选择器
        OptionsPickerView pvOptions = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {

                String tx = options1Items.get(options1).getS_name() +
                        options2Items.get(options1).get(options2).getS_name() ;

                p_name = options1Items.get(options1).getS_name();
                c_name = options2Items.get(options1).get(options2).getS_name() ;
                blank_address.setError(null);
                blank_address.setText(p_name+c_name);
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
        pvOptions.setPicker(options11Items, options22Items);//三级选择器
        pvOptions.show();
    }

    @Override
    public void queryBankNameByIdCard(BlankNameBean blankNameBean) {
        loadingDialog.dismiss();
        blank_name.setText(blankNameBean.getBankname());
        blank_name.setError(null);
    }
    @Override
    public void queryBankBranchList(BlankListBean blankNameBean) {
        List<BlankListBean.ListBean> list = blankNameBean.getList();
        dataDalog = new SettingUserDataDalog(this, null, list, new SettingUserDataDalog.OnUserOnclickListener() {
            @Override
            public void OnItemClick(String msg) {
                blank_dian.setText(msg);
                zBlank_name = msg;
                blank_dian.setError(null);
            }
        });
        loadingDialog.dismiss();;
        dataDalog.showAnim(mBasIn).show();
    }

    @Override
    public void stateError() {
        super.stateError();
        loadingDialog.dismiss();
    }

    @Override
    public void stateError(Throwable e) {
        super.stateError(e);
        loadingDialog.dismiss();
    }

    @Override
    public void bankCardVerification(BankCardBean data) {
        loadingDialog.dismiss();
   /*     {
            "reason": "成功",
                "result": {
            "jobid": "2015120913503797592",*//*本次查询流水号*//*
                    "realname": "商世界",*//*姓名*//*
                    "bankcard": "6259656360701234",*//*银行卡卡号*//*
                    "idcard": "310329198103050011",*//*身份证号码*//*
                    "mobile": "18912341234",*//*预留手机号码*//*
                    "res": "2",*//*验证结果，1:匹配 2:不匹配*//*
                    "message": "认证信息不匹配"*//*描述*//*
        },
            "error_code": 0
        }
        */
        if(data.getError_code()==0)
        {
                if(data.getResult().getRes().equals("1"))
                {
                    ActivityUtil.getInstance().openActivity(this,SettingUserDataNextActivity.class);
                }else {
                    ToastUtil.Info(data.getResult().getMessage());
                }
        }else {
            ToastUtil.Info(data.getReason());
        }

    }

    @Override
    public void finishVerification(String data) {

    }

    @Override
    public void getSSMCode(String action) {
        DownTimer timer = new DownTimer();
        int totalTime = 60 * 1000;
        timer.setTotalTime(totalTime);
        timer.setIntervalTime(1000);
        timer.setTimerLiener(new DownTimer.TimeListener() {
            @Override
            public void onFinish() {
                if (tv_validate != null) {
                    tv_validate.setClickable(true);
                    tv_validate.setText("获取验证码");
                    tv_validate.setBackground(getResources().getDrawable(R.drawable
                            .pro_yan_shape));
                }
            }

            @Override
            public void onInterval(long remainTime) {
                if (tv_validate != null) {
                    tv_validate.setText("还有" + (remainTime / 1000) + "秒");
                    if (tv_validate.isClickable()) {
                        tv_validate.setBackground(getResources().getDrawable(R.drawable
                                .shape_btn_getvalidatecode_disable));
                        tv_validate.setClickable(false);
                    }
                }

            }
        });
        timer.start();
    }

    class EditTextIdcardChangeListener implements TextWatcher {//身份证验证

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }
        @Override
        public void onTextChanged(CharSequence charSequence, int start, int before, int count) {

        }
        @Override
        public void afterTextChanged(Editable s) {
            String idcard = blank_idcard.getText().toString();
            if(!idcard.equals(""))
            {
                if(!isIdCardNo(idcard))
                {
                    blank_idcard.setError("身份证不合法！");
                }else {
                    blank_idcard.setError(null);
                }
            }

        }
    }

    class EditTextNumberChangeListener implements TextWatcher {//银行卡号验证

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }
        @Override
        public void onTextChanged(CharSequence charSequence, int start, int before, int count) {

        }
        @Override
        public void afterTextChanged(Editable s) {
            String number = blank_number.getText().toString();
            if(!number.equals(""))
            {
                if(!checkBankCard(number))
                {
                    blank_number.setError("银行卡不合法！");
                }else {//调用获取银行卡名称的接口
                    loadingDialog.show();
                    Map<String,String> request = new HashMap<>();
                    request.put("token", SharedPreferencedUtils.getStr(Constants.USER_TOKEN,""));
                    request.put("usernum", SharedPreferencedUtils.getStr(Constants.USER_NUM,""));
                    request.put("bankcardnum",number);
                    mPresenter.queryBankNameByIdCard(GsonUtil.obj2JSON(request));
                    blank_number.setError(null);
                }
            }
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
        }
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

}
