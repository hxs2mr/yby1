package com.itislevel.lyl.mvp.model.bus;

import com.itislevel.lyl.utils.SAToast;

/**
 * Created by Administrator on 2018\4\11 0011.
 */

public class HomeAdapaterSucc {
    String id;
    String name;
    String p_id;
    String p_name;


    public HomeAdapaterSucc(String id, String name, String p_id, String p_name) {
        this.id = id;
        this.name = name;
        this.p_id = p_id;
        this.p_name = p_name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
