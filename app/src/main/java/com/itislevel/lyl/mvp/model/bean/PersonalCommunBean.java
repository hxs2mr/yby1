package com.itislevel.lyl.mvp.model.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Administrator on 2018\5\8 0008.
 */

public class PersonalCommunBean {
    /**
     * list1 : [{"COUNT(1)":3}]
     * isfollow : 1
     * fabulousnumber : 18.00
     */

    private String isfollow;
    private String fabulousnumber;
    private List<List1Bean> list1;

    public String getIsfollow() {
        return isfollow;
    }

    public void setIsfollow(String isfollow) {
        this.isfollow = isfollow;
    }

    public String getFabulousnumber() {
        return fabulousnumber;
    }

    public void setFabulousnumber(String fabulousnumber) {
        this.fabulousnumber = fabulousnumber;
    }

    public List<List1Bean> getList1() {
        return list1;
    }

    public void setList1(List<List1Bean> list1) {
        this.list1 = list1;
    }

    public static class List1Bean {
        @SerializedName("COUNT(1)")
        private int _$COUNT1108; // FIXME check this code

        public int get_$COUNT1108() {
            return _$COUNT1108;
        }

        public void set_$COUNT1108(int _$COUNT1108) {
            this._$COUNT1108 = _$COUNT1108;
        }
    }

    /**
     * list1 : [{"COUNT(1)":2}]
     * isfollow : 1
     * fabulousnumber : 17.00
     */

}
