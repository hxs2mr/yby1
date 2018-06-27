package com.itislevel.lyl.mvp.ui.bus;

/**
 * Created by Administrator on 2018\5\19 0019.
 */

public class TonEdBus {
    String name;
    int postion;

    public TonEdBus(String name, int postion) {
        this.name = name;
        this.postion = postion;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPostion() {
        return postion;
    }

    public void setPostion(int postion) {
        this.postion = postion;
    }
}
