package com.itislevel.lyl.mvp.ui.bus;

/**
 * Created by Administrator on 2018\4\17 0017.
 */

public class bless_home {
    String city_id;
    String city_name;
    String p_id;
    String p_name;

    public bless_home(String city_id, String city_name, String p_id, String p_name) {
        this.city_id = city_id;
        this.city_name = city_name;
        this.p_id = p_id;
        this.p_name = p_name;
    }

    public String getCity_id() {
        return city_id;
    }

    public void setCity_id(String city_id) {
        this.city_id = city_id;
    }

    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    public String getP_id() {
        return p_id;
    }

    public void setP_id(String p_id) {
        this.p_id = p_id;
    }

    public String getP_name() {
        return p_name;
    }

    public void setP_name(String p_name) {
        this.p_name = p_name;
    }
}
