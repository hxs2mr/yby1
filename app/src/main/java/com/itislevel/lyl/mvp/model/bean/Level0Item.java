package com.itislevel.lyl.mvp.model.bean;

import com.chad.library.adapter.base.entity.AbstractExpandableItem;
import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * desc:功能说明必写
 * user:itisi
 * date:2017/12/15.17:34
 * path:com.itislevel.lyl.mvp.model.bean.Level0Item
 **/
public class Level0Item extends AbstractExpandableItem<Level1Item> implements MultiItemEntity {
    @Override
    public int getLevel() {
        return 0;
    }

    @Override
    public int getItemType() {
        return 0;
    }
}
