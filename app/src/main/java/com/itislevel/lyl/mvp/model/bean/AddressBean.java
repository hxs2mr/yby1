package com.itislevel.lyl.mvp.model.bean;

/**
 * desc:功能说明必写
 * user:itisi
 * date:2017/12/5.10:04
 * path:com.itislevel.lyl.mvp.model.bean.AddressBean
 **/
public class AddressBean {

    /**
     * name : 云南省
     * pid : 0
     * id : 530000
     */

    private String name;
    private int pid;
    private int id;

    public AddressBean(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
