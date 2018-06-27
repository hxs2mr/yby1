package com.itislevel.lyl.mvp.model.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.itislevel.lyl.mvp.ui.location.TreeBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018\4\12 0012.
 */

public class Location_Bean implements Parcelable {

    /**
     * citylist : [{"id":110100,"s_name":"北京"}]
     * id : 110000
     * s_name : 北京
     */


    public static final int ONELIST = 0;//一级列表
    public static final int TWOLIST = 1;//二级列表

    public int getType() {
        return type;
    }
    public void setType(int type) {
        this.type = type;
    }


    public int type = ONELIST;//默认一级
    private boolean expand = false;//是否展开子项




    private String id;
    private String s_name;
    private List<Location_Bean> list;
    private List<CitylistBean> list_city;

    String leaf1;//支叶内容
    String leaf2;

    public String getLeaf1() {
        return leaf1;
    }

    public void setLeaf1(String leaf1) {
        this.leaf1 = leaf1;
    }

    public String getLeaf2() {
        return leaf2;
    }

    public void setLeaf2(String leaf2) {
        this.leaf2 = leaf2;
    }

    public List<CitylistBean> getList_city() {
        return list_city;
    }

    public void setList_city(List<CitylistBean> list_city) {
        this.list_city = list_city;
    }

    public boolean isExpand() {
        return expand;
    }

    public void setExpand(boolean expand) {
        this.expand = expand;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getS_name() {
        return s_name;
    }

    public void setS_name(String s_name) {
        this.s_name = s_name;
    }

    public List<Location_Bean> getCitylist() {
        return list;
    }

    public void setCitylist(List<Location_Bean> citylist) {
        this.list = citylist;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int i) {
        dest.writeInt(this.type);
        dest.writeByte(this.expand ? (byte) 1 : (byte) 0);
        dest.writeList(this.list);
        dest.writeString(this.s_name);
        dest.writeString(this.id+"");
    }
    public Location_Bean() {
    }
    protected Location_Bean(Parcel in) {
        this.type = in.readInt();
        this.expand = in.readByte() != 0;
        this.s_name = in.readString();
        this.id = in.readString();
        this.list = new ArrayList<>();
        in.readList(this.list, Location_Bean.class.getClassLoader());
    }

    public static final Creator<Location_Bean> CREATOR = new Creator<Location_Bean>() {
        @Override
        public Location_Bean createFromParcel(Parcel source) {
            return new Location_Bean(source);
        }

        @Override
        public Location_Bean[] newArray(int size) {
            return new Location_Bean[size];
        }
    };

    public static class CitylistBean {
        public CitylistBean(int id, String s_name) {
            this.id = id;
            this.s_name = s_name;
        }
        public CitylistBean() {
            this.id = id;
            this.s_name = s_name;
        }
        /**
         * id : 110100
         * s_name : 北京
         */


        private int id;
        private String s_name;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getS_name() {
            return s_name;
        }

        public void setS_name(String s_name) {
            this.s_name = s_name;
        }
    }
}
