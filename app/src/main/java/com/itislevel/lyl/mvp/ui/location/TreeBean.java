package com.itislevel.lyl.mvp.ui.location;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class TreeBean implements Parcelable {
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

    private String trunk1;//主干内容
    private String trunk2;
    private List<TreeBean> treeBeanList;
    String leaf1;//支叶内容
    String leaf2;

    public List<TreeBean> getTreeBeanList() {
        return treeBeanList;
    }

    public void setTreeBeanList(List<TreeBean> treeBeanList) {
        this.treeBeanList = treeBeanList;
    }

    public boolean isExpand() {
        return expand;
    }

    public void setExpand(boolean expand) {
        this.expand = expand;
    }


    public static int getONELIST() {
        return ONELIST;
    }

    public static int getTWOLIST() {
        return TWOLIST;
    }

    public String getTrunk1() {
        return trunk1;
    }

    public void setTrunk1(String trunk1) {
        this.trunk1 = trunk1;
    }

    public String getTrunk2() {
        return trunk2;
    }

    public void setTrunk2(String trunk2) {
        this.trunk2 = trunk2;
    }

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


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.type);
        dest.writeByte(this.expand ? (byte) 1 : (byte) 0);
        dest.writeString(this.trunk1);
        dest.writeString(this.trunk2);
        dest.writeList(this.treeBeanList);
        dest.writeString(this.leaf1);
        dest.writeString(this.leaf2);
    }

    public TreeBean() {
    }

    protected TreeBean(Parcel in) {
        this.type = in.readInt();
        this.expand = in.readByte() != 0;
        this.trunk1 = in.readString();
        this.trunk2 = in.readString();
        this.treeBeanList = new ArrayList<TreeBean>();
        in.readList(this.treeBeanList, TreeBean.class.getClassLoader());
        this.leaf1 = in.readString();
        this.leaf2 = in.readString();
    }

    public static final Creator<TreeBean> CREATOR = new Creator<TreeBean>() {
        @Override
        public TreeBean createFromParcel(Parcel source) {
            return new TreeBean(source);
        }

        @Override
        public TreeBean[] newArray(int size) {
            return new TreeBean[size];
        }
    };
}
