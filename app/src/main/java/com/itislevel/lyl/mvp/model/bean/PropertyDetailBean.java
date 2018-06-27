package com.itislevel.lyl.mvp.model.bean;

/**
 * Created by Administrator on 2018\5\31 0031.
 */

public class PropertyDetailBean {

    /**
     * id : 11
     * managerid : 0
     * vid : null
     * cateid : 0
     * realname : 匿名
     * phone : null
     * content : 是是是

     * villagename : 花果园小区
     * catename : null
     * imgs : BACA25B8-059F-418A-9E62-16842F36B7BC.jpg
     * villageaddress : null
     * createdtime : 1527749049000
     */

    private int id;
    private int managerid;
    private Object vid;
    private int cateid;
    private String realname;
    private Object phone;
    private String content;
    private String villagename;
    private Object catename;
    private String imgs;
    private Object villageaddress;
    private long createdtime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getManagerid() {
        return managerid;
    }

    public void setManagerid(int managerid) {
        this.managerid = managerid;
    }

    public Object getVid() {
        return vid;
    }

    public void setVid(Object vid) {
        this.vid = vid;
    }

    public int getCateid() {
        return cateid;
    }

    public void setCateid(int cateid) {
        this.cateid = cateid;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public Object getPhone() {
        return phone;
    }

    public void setPhone(Object phone) {
        this.phone = phone;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getVillagename() {
        return villagename;
    }

    public void setVillagename(String villagename) {
        this.villagename = villagename;
    }

    public Object getCatename() {
        return catename;
    }

    public void setCatename(Object catename) {
        this.catename = catename;
    }

    public String getImgs() {
        return imgs;
    }

    public void setImgs(String imgs) {
        this.imgs = imgs;
    }

    public Object getVillageaddress() {
        return villageaddress;
    }

    public void setVillageaddress(Object villageaddress) {
        this.villageaddress = villageaddress;
    }

    public long getCreatedtime() {
        return createdtime;
    }

    public void setCreatedtime(long createdtime) {
        this.createdtime = createdtime;
    }
}
