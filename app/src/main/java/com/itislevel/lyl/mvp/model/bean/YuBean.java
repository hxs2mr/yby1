package com.itislevel.lyl.mvp.model.bean;

/**
 * Created by Administrator on 2018\5\14 0014.
 */

public class YuBean {
    String name;
    String content;

    public YuBean(String name, String content) {
        this.name = name;
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
