package com.itislevel.lyl.mvp.ui.property.collection;

import com.itislevel.lyl.base.BasePresenter;
import com.itislevel.lyl.base.BaseView;
import com.itislevel.lyl.mvp.model.bean.CollectionListBean;

/**
 * Created by Administrator on 2018\6\12 0012.
 */

public interface CollectionContract {
    interface View extends BaseView{
        void collectMaintenanceList(CollectionListBean bean);
        void deleMaintenanceList(String data);
    }
    interface Presenter extends BasePresenter<View>
    {
        void collectMaintenanceList(String action);
        void deleMaintenanceList(String data);
    }
}
