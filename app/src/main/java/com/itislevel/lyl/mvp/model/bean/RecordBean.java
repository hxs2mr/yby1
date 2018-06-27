package com.itislevel.lyl.mvp.model.bean;

import android.text.TextUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2018\5\31 0031.
 */

public class RecordBean {
    private String name;
    private String province;
    private String pinyin;
    private String code;


    public RecordBean(String name, String province, String pinyin, String code) {
        this.name = name;
        this.province = province;
        this.pinyin = pinyin;
        this.code = code;
    }
    /***
     * 获取悬浮栏文本，（#、定位、热门 需要特殊处理）
     * @return
     */
    public String getSection(){
            //在添加定位和热门数据时设置的section就是‘定’、’热‘开头
                return pinyin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
