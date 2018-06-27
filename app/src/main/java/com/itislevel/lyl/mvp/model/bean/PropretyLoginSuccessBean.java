package com.itislevel.lyl.mvp.model.bean;

/**
 * Created by Administrator on 2018\5\23 0023.
 */

public class PropretyLoginSuccessBean {
    String vid;
    String city_id;
    String user_id;
    String villagename;


    public PropretyLoginSuccessBean(String vid, String city_id, String user_id, String villagename) {
        this.vid = vid;
        this.city_id = city_id;
        this.user_id = user_id;
        this.villagename = villagename;
    }

    public String getVid() {
        return vid;
    }

    public void setVid(String vid) {
        this.vid = vid;
    }

    public String getCity_id() {
        return city_id;
    }

    public void setCity_id(String city_id) {
        this.city_id = city_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getVillagename() {
        return villagename;
    }

    public void setVillagename(String villagename) {
        this.villagename = villagename;
    }

}
