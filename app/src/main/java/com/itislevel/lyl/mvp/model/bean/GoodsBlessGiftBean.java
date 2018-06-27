package com.itislevel.lyl.mvp.model.bean;

/**
 * desc:功能说明必写
 * user:itisi
 * date:2017/12/27.19:24
 * path:com.itislevel.lyl.mvp.model.bean.GoodsBlessGiftBean
 **/
public class GoodsBlessGiftBean {
    private int id;
    private String name;
    private double price;
    private  String img;

    public GoodsBlessGiftBean() {
    }

    public GoodsBlessGiftBean(int id, String name, double price, String img) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.img = img;
    }

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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
