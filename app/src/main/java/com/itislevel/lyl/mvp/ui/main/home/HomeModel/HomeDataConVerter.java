package com.itislevel.lyl.mvp.ui.main.home.HomeModel;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.itislevel.lyl.adapter.recyclew.DataConVerter;
import com.itislevel.lyl.adapter.recyclew.ItemType;
import com.itislevel.lyl.adapter.recyclew.MultipleItemEntity;
import com.itislevel.lyl.mvp.model.bean.AdviserBean;
import com.itislevel.lyl.mvp.model.bean.BannBean;
import com.itislevel.lyl.mvp.model.bean.HeadlinesBean;
import com.itislevel.lyl.mvp.model.bean.HeathBean;
import com.itislevel.lyl.mvp.model.bean.HomeBean;
import com.itislevel.lyl.mvp.model.bean.ModularBean;

import java.util.ArrayList;
import java.util.List;

import static com.itislevel.lyl.mvp.ui.main.home.HomeFragment.HEAD_IMAGE_URL;
import static com.itislevel.lyl.mvp.ui.main.home.HomeFragment.HOME_BANN;
import static com.itislevel.lyl.mvp.ui.main.home.HomeFragment.HOME_MODULE;
import static com.itislevel.lyl.mvp.ui.main.home.HomeFragment.customer_service_phone;
/**
 * Created by Administrator on 2018\4\8 0008.
 */

public class HomeDataConVerter extends DataConVerter {

    @Override
    public ArrayList<MultipleItemEntity> CONVERT() {
        ENTITYES = new ArrayList<>();
        List<HeadlinesBean> LIST_HEAD = new ArrayList<>();
        List<ModularBean> LIST_MODULE= new ArrayList<>();
        List<AdviserBean> LIST_ADVISER= new ArrayList<>();
        List<HeathBean> LIST_HEATH= new ArrayList<>();
        List<BannBean> LIST_BANN = new ArrayList<>();
        final JSONObject object = JSON.parseObject(getJsonData()).getJSONObject("data");
        String start_image_url = object.getString("getImgUrl");//图片服务器起开始地址
        if(object.getString("customer_service_phone")!=null)
        {
             customer_service_phone= object.getString("customer_service_phone");//客户电话
        }
        HEAD_IMAGE_URL =start_image_url;
        final JSONArray toutiao = object.getJSONArray("toutiao");//头条
        final JSONArray bankuai = object.getJSONArray("bankuai");//功能
        JSONArray guwen = null;
        int guwen_size =0;
        if( object.getJSONArray("homemaking")!=null)
        {
            guwen = object.getJSONArray("homemaking");//顾问
            guwen_size = guwen.size();
        }
        JSONArray healthy = null;//顾问
        int healthy_size=0;
        if( object.getJSONArray("healthy")!=null){
            healthy = object.getJSONArray("healthy");//顾问
            healthy_size = healthy.size();
        }

        String saveImgUrl = object.getString("saveImgUrl");//图片保存地址
        final JSONArray guanggao = object.getJSONArray("guanggao");//广告
        int tou_size = toutiao.size();
        int bankuai_size = bankuai.size();
        int guanggao_size = guanggao.size();

        /*
        * int id, int pointnum, String logo, String title, long createdtime, String publisher) {
        * */
        for(int  a=0;a<healthy_size;a++)
        {
            final JSONObject heath_data = healthy.getJSONObject(a);
            int id = heath_data.getInteger("id");
            int pointnum = heath_data.getInteger("pointnum");
            String logo = heath_data.getString("logo");
            String title = heath_data.getString("title");
            long createdtime = heath_data.getLong("createdtime");
            String publisher = heath_data.getString("publisher");
            String static_htm_url="";
            if( heath_data.getString("flat_info_url")!=null)
            {
                static_htm_url = heath_data.getString("flat_info_url");
            }
            Object nosense = heath_data.get("nosense");
            int looknum = heath_data.getInteger("looknum");
            HeathBean bean = new HeathBean(id,pointnum,logo,title,createdtime,publisher,static_htm_url,nosense,looknum);
            LIST_HEATH.add(bean);
        }
        if(tou_size>6)
        {
            for(int a=0;a<6;a++)
            {
                final JSONObject tou_data = toutiao.getJSONObject(a);
                int code = tou_data.getInteger("id");
                int cateid = tou_data.getInteger("cateid");
                String title = tou_data.getString("title");
                String catename = tou_data.getString("catename");
                HeadlinesBean bean = new HeadlinesBean(code,cateid,title,catename);
                LIST_HEAD.add(bean);
            }
        }else {
            for(int  a=0;a<tou_size;a++)
            {
                final JSONObject tou_data = toutiao.getJSONObject(a);
                int code = tou_data.getInteger("id");
                int cateid = tou_data.getInteger("cateid");
                String title = tou_data.getString("title");
                String catename = tou_data.getString("catename");
                HeadlinesBean bean = new HeadlinesBean(code,cateid,title,catename);
                LIST_HEAD.add(bean);
            }
        }

        for(int  b=0;b<bankuai_size;b++)
        {
            final JSONObject ban_data = bankuai.getJSONObject(b);
            int code = ban_data.getInteger("id");
            String icon = ban_data.getString("icon");
            String catename = ban_data.getString("catename");
            ModularBean bean = new ModularBean(code,icon,catename);
            LIST_MODULE.add(bean);
        }
        for(int  c=0;c<guwen_size;c++)
        {
            final JSONObject guwen_data = guwen.getJSONObject(c);
            String companyrem = guwen_data.getString("companyrem");
            String provname = guwen_data.getString("provname");
            String companyname = guwen_data.getString("companyname");
            int id = guwen_data.getInteger("id");
            String companyimge = guwen_data.getString("companyimge");
            String contactname = guwen_data.getString("contactname");
            String address = guwen_data.getString("address");
            long createdtime = guwen_data.getLong("createdtime");
            String cityname = guwen_data.getString("cityname");
            String companylogo = guwen_data.getString("companylogo");
            String contactphone =  guwen_data.getString("contactphone");
            AdviserBean bean = new AdviserBean(companyrem,provname,companyname,id,companyimge,contactname,address,createdtime,cityname,companylogo,contactphone);
            LIST_ADVISER.add(bean);
        }

        for(int  d=0;d<guanggao_size;d++)
        {
            final JSONObject guan_data = guanggao.getJSONObject(d);
            String logo = guan_data.getString("logo");
            String name = guan_data.getString("name");
            int aid = guan_data.getInteger("aid");
            BannBean bean = new BannBean(logo,name,aid);
            LIST_BANN.add(bean);
        }

       /* HOME_MODULE = LIST_MODULE;
        HOME_BANN = LIST_BANN;*/
        //    final JSONObject data = array.getJSONObject(i);
        MultipleItemEntity entity = MultipleItemEntity.builder()
                .setItemType(ItemType.HOME_VIEW_ONE)
                .setField(HomeFields.LIST_HEAD,LIST_HEAD)
                .build();
        ENTITYES.add(entity);

        MultipleItemEntity entity1 = MultipleItemEntity.builder()
                .setItemType(ItemType.HOME_VIEW_TWO)
                .setField(HomeFields.LIST_ADVISER,LIST_ADVISER)
                .setField(HomeFields.START_IM_URL,start_image_url)
                .build();

        ENTITYES.add(entity1);

        MultipleItemEntity entity2 = MultipleItemEntity.builder()
                .setItemType(ItemType.HOME_VIEW_THREE)
                .setField(HomeFields.LIST_HEATH,LIST_HEATH)
                .setField(HomeFields.START_IM_URL,start_image_url)
                .build();
        ENTITYES.add(entity2);

        return ENTITYES;
    }
    public ArrayList<MultipleItemEntity> NEW_CONVERT(HomeBean message) {
        ArrayList<MultipleItemEntity> ENTITYES = new ArrayList<>();
        String start_image_url = message.getGetImgUrl();
        List<HomeBean.ToutiaoBean> LIST_HEAD  = new ArrayList<>();

        List<HomeBean.HomemakingBean> LIST_ADVISER= new ArrayList<>();
        List<HomeBean.HealthyBean> LIST_HEATH= new ArrayList<>();

        if(LIST_HEAD.size()>0)
        {
            LIST_HEAD.clear();
        }
        if(LIST_ADVISER.size()>0)
        {
            LIST_ADVISER.clear();
        }
        if(LIST_HEATH.size()>0)
        {
            LIST_HEATH.clear();
        }
        LIST_HEAD = message.getToutiao();
        LIST_ADVISER = message.getHomemaking();
        LIST_HEATH = message.getHealthy();
        MultipleItemEntity entity = MultipleItemEntity.builder()
                .setItemType(ItemType.HOME_VIEW_ONE)
                .setField(HomeFields.LIST_HEAD,LIST_HEAD)
                .build();
        ENTITYES.add(entity);

        MultipleItemEntity entity1 = MultipleItemEntity.builder()
                .setItemType(ItemType.HOME_VIEW_TWO)
                .setField(HomeFields.LIST_ADVISER,LIST_ADVISER)
                .setField(HomeFields.START_IM_URL,start_image_url)
                .build();

        ENTITYES.add(entity1);

        MultipleItemEntity entity2 = MultipleItemEntity.builder()
                .setItemType(ItemType.HOME_VIEW_THREE)
                .setField(HomeFields.LIST_HEATH,LIST_HEATH)
                .setField(HomeFields.START_IM_URL,start_image_url)
                .build();
        ENTITYES.add(entity2);
        return  ENTITYES;
    }
}
