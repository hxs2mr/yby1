package com.itislevel.lyl.mvp.ui.location;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.itislevel.lyl.adapter.recyclew.DataConVerter;
import com.itislevel.lyl.adapter.recyclew.ItemType;
import com.itislevel.lyl.adapter.recyclew.MultipleItemEntity;
import com.itislevel.lyl.mvp.model.bean.Location_Bean;
import com.itislevel.lyl.mvp.ui.main.home.HomeModel.HomeFields;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018\4\12 0012.
 */

public class LocationDataConvter extends DataConVerter {
    @Override
    public ArrayList<MultipleItemEntity> CONVERT() {
         List<Location_Bean> list_data = new ArrayList<>();
         JSONObject object = JSON.parseObject(getJsonData()).getJSONObject("data");
        JSONArray prolist = object.getJSONArray("prolist");//得到省份得到array
        int size = prolist.size();
        for(int i =0 ; i < size ; i++)
        {
             JSONObject data = prolist.getJSONObject(i);
              Location_Bean bean = new Location_Bean();
              bean.setS_name(data.getString("s_name"));
             bean.setId(data.getInteger("id")+"");
            JSONArray city_data = data.getJSONArray("citylist");
            int city_size = city_data.size();
            List<Location_Bean.CitylistBean> list_city = new ArrayList<>();
            for(int  j =0 ; j < city_size ;j++)
            {
                  JSONObject city_ob = city_data.getJSONObject(j);
                   Location_Bean.CitylistBean bean1  =new Location_Bean.CitylistBean(city_ob.getInteger("id"),city_ob.getString("s_name"));
                   list_city.add(bean1);
            }
            bean.setList_city(list_city);

            list_data.add(bean);
        }
        MultipleItemEntity entity = MultipleItemEntity.builder()
                .setField(LocationCityFields.P_C_LIST,list_data)
                .build();
        ENTITYES.add(entity);
        return ENTITYES;
    }
}
