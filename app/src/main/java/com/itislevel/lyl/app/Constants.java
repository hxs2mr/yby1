package com.itislevel.lyl.app;

import android.os.Environment;

import com.itislevel.lyl.utils.SharedPreferencedUtils;

import java.io.File;

/**
 * **********************
 * 功 能:全局常量
 * 创建人:itisi
 * 邮  箱:itisivip@qq.com
 * 创建时间:2017/7/5 16:28
 * 修改人:itisi
 * 修改时间: 2017/7/5 16:28
 * 修改内容:itisi
 * *********************
 */

public class Constants {
    //项目名称 很多地方 都应该以这个大头
    public static final String PROJECT_NAME = "lyl";

    // http://readimg.yobangyo.com/yby/img_server/
    //    public static final String IMG_SERVER_PATH="http://119.27.169.152:8020/yby/img_server/";
    public static final String IMG_SERVER_PATH_KEY="IMG_SERVER_PATH_KEY";
    public static final String IMG_SERVER_PATH= "http://readimg.yobangyo.com/yby/img_server/";

    //分页加载的时候 每次加载多少 默认10条
    public static final Integer PAGE_NUMBER10 = 10;
    public static final Integer PAGE_NUMBER20 = 20;
    public static final Integer PAGE_NUMBER5 = 5;
    //数据库 名称 realm
    public static final String DB_NAME = PROJECT_NAME + ".realm";
    //sharedpreference 名称
    public static final String SHARE_PREFERENCE_NAME = PROJECT_NAME;
    //软键盘高度
    public static final String SOFT_INPUT_HEIGHT = "soft_input_height";

    //相册

    //搞什么的 已经搞忘了
    public static final String IT_GANK_TYPE = "gank_type";
    //福利缓存路径
    public static final String PATH_SDCARD = Environment.getExternalStorageDirectory()
            .getAbsolutePath() + File.separator + PROJECT_NAME + File.separator + "lyl";
    //相册的路径
    public static final String PATH_GALLERY = PROJECT_NAME;
    //相册的裁剪路径
    public static final String PATH_GALLERY_CROP = PROJECT_NAME + "/crop";

    //用户信息

    //用户token
    public static final String USER_TOKEN = "USER_TOKEN";
    public static final String RONGCLOUD_TOKEN = "RONGCLOUD_TOKEN";
    public static final String IS_REN = "0";
    //用户id
    public static final String USER_ID = "USER_ID";
    //用户编号
    public static final String USER_NUM = "USER_NUM";
    //用户电话
    public static final String USER_PHONE = "USER_PHONE";
    //用户名
    public static final String USER_NAME = "USER_NAME";
    //用户真实姓名
    public static final String USER_REAL_NAME = "USER_REAL_NAME";
    //用户身份证
    public static final String USER_IDCARD = "USER_IDCARD";
    //用户昵称
    public static final String USER_NICK_NAME = "USER_NICK_NAME";
    //用户性别
    public static final String USER_GENDER = "USER_GENDER";
    //用户生日
    public static final String USER_BIRTHDAY = "USER_BIRTHDAY";
    //用户头像
    public static final String USER_HEADER = "USER_HEADER";
    //推荐人号码
    public static final String USER_RECOMMEND_PHONE = "USER_RECOMMEND_PHONE";
    public static final String USER_IS_CUSTOMER = "USER_IS_CUSTOMER";
    public static final String USER_IS_ADVISER = "USER_IS_ADVISER";

    // ActivityForResult
    public static final int RESULT_CODE_QR_SCAN = 10000;


    public static final String START_FLAGE="START_FLAGE";
    //省市县 的名称
    public static final String PROVINCE_NAME = "PROVINCE_NAME";
    public static final String CITY_NAME = "CITY_NAME";
    public static final String COUNTY_NAME = "COUNTY_NAME";

    //省市县 的标题
    public static final String PROVINCE_TITLE = "PROVINCE_TITLE";
    public static final String PROVINCE_ID = "PROVINCE_ID";

    public static final String CITY_TITLE = "CITY_TITLE";
    public static final String CITY_ID = "CITY_ID";

    public static final String COUNTY_TITLE = "COUNTY_TITLE";
    public static final String COUNTY_ID = "COUNTY_ID";

    public static final String IS_SHOW_COUNTY = "IS_SHOW_COUNTY";

    //省市县 选择以后跳转的 界面

    public static final String ACTIVITY_TARGET = "ACTIVITY_TARGET";

    public static final int ACTIVITY_FUNSHARING = 1000;//乐趣分析新增界面
    public static final int ACTIVITY_TROUBLE = 1001;//烦恼解答新增界面
    public static final int ACTIVITY_LIVING = 1002;//生活缴费 --- 这个用后面的解决
    public static final int ACTIVITY_HOUSE = 1003;//家政服务
    public static final int ACTIVITY_TEAM = 1004;//顾问团队
    public static final int ACTIVITY_SPECIAL = 1005;//礼品特产
    public static final int ACTIVITY_FAMILY = 1006;//亲情祭祀
    public static final int ACTIVITY_WEDDING = 1007;//喜事祝福

    public static final int ACTIVITY_LIVING_WATER = 1008;//水费 电费燃气费 闭路电费
    public static final int ACTIVITY_LIVING_PROPERTY = 1009;//物业费


    public static final String PROPERTY_SELECT_MONTH = "PROPERTY_SELECT_MONTH";//物业费自助缴费的时候 选择的 是几个月


    //烦恼解答
    //(注：type:1.官司咨询,2.税务咨询,3,装修咨询,4.情感咨询,5.病痛咨询,6.教育咨询,7.婚姻咨询)


    public static final String TROUBLE_TYPE = "TROUBLE_TYPE";//烦恼类型 参数名称
    public static final String TROUBLE_TITLE = "TROUBLE_TITLE";//烦恼类型 参数名称

    public static final int TROUBLE_LAWSUIT = 1;//官司资讯
    public static final int TROUBLE_TAX = 2;//税务资讯
    public static final int TROUBLE_RENOVATION = 3;//装修咨询
    public static final int TROUBLE_EMOTION = 4;//情感资讯
    public static final int TROUBLE_SICKNESS = 5;//病痛资讯
    public static final int TROUBLE_EDUCATION = 6;//教育资讯
    public static final int TROUBLE_MARRIAGE = 7;//婚姻资讯

    //烦恼解答里面的咨询 类型 顾问团队的类型 传递的类型名称
    public static final String TROUBLE_TEAM_TYPE_FIRST_ID = "TROUBLE_TEAM_TYPE_FIRST_ID";
    public static final String TROUBLE_TEAM_TYPE_SECOND_ID = "TROUBLE_TEAM_TYPE_SECOND_ID";
    public static final String TROUBLE_TEAM_TYPE_NAME = "TROUBLE_TEAM_TYPE_NAME";
    public static final String TROUBLE_TEAM_TYPE_ID_LIST = "TROUBLE_TEAM_TYPE_ID_LIST";
    public static final String TROUBLE_TEAM_TYPE_NAME_LIST = "TROUBLE_TEAM_TYPE_NAME_LIST";

    //亲情祭祀里面存入相框 背景的类型 相框:100 背景:200
    public static final int FAMILY_ADD_TYPE_FRAME= 100;//相框
    public static final int FAMILY_ADD_TYPE_BACK= 200;//背景


    //购物车 类型
    public static final String CART_TYPE_FAMILY = "1000";//祭祀购物车类型
    public static final String CART_TEAM_BLESS = "1001";//喜事祝福 购物车类型
    public static final String CART_TEAM_LIVING = "1002";//生活购物车 特产礼品商城

    //购物车模块
    public static final String CART_GENERATE_ORDER_TXT = "生成订单...";//祭祀


    public static final String CART_MODEL_FAMILY = "fete";//祭祀

    public static final String CART_MODEL_BLESS = "xishi";//喜事
    public static final String CART_MODEL_ZIXUN = "zixun";//咨询
    public static final String CART_MODEL_GIFT = "lipin";//礼品
    public static final String CART_MODEL_PROPERTY = "estate";//物业
    public static final String CART_MODEL_FAN = "recharge";//物业
    public static final  String CART_MODEL_DYNAMIC = "dynamic";

    //支付

    public static final String PAY_ORDERNUM="PAY_ORDERNUM";


    public static final String PAY_TOTALPRICE="PAY_TOTALPRICE";
    public static final String PAY_GOODS_DESC="PAY_GOODS_DESC";
    public static final String PAY_GOODS_DETAIL="PAY_GOODS_DETAIL";
    public static final String LYL_DETAIL="LYL";

    public static final String PAY_MODULE_ID="PAY_MODULE_ID";
    public static final String PAY_MODULE_NAME="PAY_MODULE_NAME";

    public static final String PAY_CART_STR="PAY_CART_STR";//购物车 拼接的参数
    public static final String PAY_RECEIVE_USERID="PAY_RECEIVE_USERID";//购物车 送礼的对象的id

    public static final String PAY_TYPE="PAY_TYPE";//0立即支付 1从购物车过来的
    public static final String PAY_GOODS_ID="PAY_GOODS_ID";//商品id
    public static final String PAY_GOODS_NAME="PAY_GOODS_NAME";//商品名称
    public static final String PAY_CATE_ID="PAY_CATE_ID";//商品类型
    public static final String PAY_PRICE="PAY_PRICE";//商品价格
    public static final String PAY_COUNT="PAY_COUNT";//商品数量
    public static final String PAY_IMGURL="PAY_IMGURL";//商品图片地址




    //从哪个页面跳到支付页面的

    public static final String PAY_FROM_ACTIVITY = "PAY_FROM_ACTIVITY";//来自于祭祀相框

    public static final int PAY_FROM_FETE_PHTO = 666;//来自于祭祀相框
    public static final int PAY_FROM_FETE_GIFT = 667;//来自于祭祀礼物
    public static final int PAY_FROM_HAPPY_GIFT = 668;//来自于喜事礼物
    public static final int PAY_FROM_LIVE_PROPERTY = 669;//来自于生活交费-物业
    public static final int PAY_FROM_SPECIAL_GIFT = 888;//来自于特产礼品
    public static final int PAY_FROM_TROUBLE_ADD = 889;//来自于烦恼提问
    public static final int PAY_FROM_SPECIAL_ORDER = 991;//来自于特产礼品的订单
    public static final int PAY_FROM_FAN_PROPERTY = 992;//来自于商家在线充值

    //订单状态
    public static final int SPECIAL_ORDER_ALL = -1;//所有订单
    public static final int SPECIAL_ORDER_WAITNG_PAY = 101;//待付款
    public static final int SPECIAL_ORDER_PAYED = 201;//已付款
    public static final int SPECIAL_ORDER_SENDED = 202;//已发货-待收货
    public static final int SPECIAL_ORDER_RECEIVED = 203;//已收货
    public static final int SPECIAL_ORDER_COMPLETE = 204;//已完成
    public static final int SPECIAL_ORDER_REFUNDED = 301;//已退款

    public static final String BADGE_COUNT_TEAM = "BADGE_COUNT_TEAM";
    public static final String BADGE_COUNT_SHARE = "BADGE_COUNT_SHARE";
    public static final String BADGE_COUNT_BLESS = "BADGE_COUNT_BLESS";
    public static final String BADGE_COUNT_FEGE = "BADGE_COUNT_FEGE";


}
