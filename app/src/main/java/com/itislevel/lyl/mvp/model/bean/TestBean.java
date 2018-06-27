package com.itislevel.lyl.mvp.model.bean;

/**
 * desc:功能说明必写
 * user:itisi
 * date:2018-02-10.11:50
 * path:com.itislevel.lyl.mvp.model.bean.TestBean
 **/
public class TestBean {
    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TestBean(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public TestBean() {
    }
}
