package com.itislevel.lyl.mvp.ui.property.repair;

import com.itislevel.lyl.base.BasePresenter;
import com.itislevel.lyl.base.BaseView;
import com.itislevel.lyl.mvp.model.bean.ProperCommentList;
import com.itislevel.lyl.mvp.model.bean.RepairCityListBean;
import com.itislevel.lyl.mvp.model.bean.RepairListBean;
import com.itislevel.lyl.mvp.model.bean.RepairTypeListBean;
import com.itislevel.lyl.mvp.model.bean.SeleBean;

import java.util.List;

/**
 * Created by Administrator on 2018\6\1 0001.
 */

public interface RepairContract {
    interface  View extends BaseView{
        void  maintenanceList(RepairListBean bean);
        void  queryarealist(List<RepairCityListBean> bean);
        void  queryrepairallcatelist(List<RepairCityListBean> bean);
        void commentEstatesList(ProperCommentList bean);
        void addCommentEstates(String data);
        void addCollectMaintenance(String action);
        void seleCommentConunt(SeleBean bean);
        void prolooknumcount(String data);

    }
    interface Presenter extends BasePresenter<View>
    {
        void  maintenanceList(String bean);
        void  queryarealist(String bean);
        void  queryrepairallcatelist(String bean);
        void commentEstatesList(String data);
        void addCommentEstates(String action);
        void addCollectMaintenance(String action);
        void seleCommentConunt(String bean);
        void prolooknumcount(String action);
    }
}
