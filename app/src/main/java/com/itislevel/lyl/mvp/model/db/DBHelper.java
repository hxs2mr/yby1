package com.itislevel.lyl.mvp.model.db;

/**
 * **********************
 * 功 能:数据库操作基类
 * 创建人:itisi
 * 邮  箱:itisivip@qq.com
 * 创建时间:2017/7/5 17:27
 * 修改人:itisi
 * 修改时间: 2017/7/5 17:27
 * 修改内容:itisi
 * *********************
 */

public interface DBHelper {
    /**
     * 例如
     * 查询 阅读记录
     * @param id
     * @return
     */
    boolean queryNewsId(int id);
    void closeDB();
    int test_insert();
    int test_delete();
    int test_update();
    int test_select();

}
