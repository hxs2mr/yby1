package com.itislevel.lyl.mvp.ui.location;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.itislevel.lyl.R;
import com.itislevel.lyl.mvp.model.bean.HouseBean;
import com.itislevel.lyl.mvp.model.bean.Location_Bean;
import com.itislevel.lyl.mvp.model.bus.HomeAdapaterSucc;
import com.itislevel.lyl.mvp.ui.bus.bless_home;
import com.itislevel.lyl.utils.SAToast;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import static com.itislevel.lyl.mvp.ui.location.Location_CityPickerActivity.loaction_p_id;
import static com.itislevel.lyl.mvp.ui.location.Location_CityPickerActivity.location_p_name;
import static com.itislevel.lyl.mvp.ui.location.Location_CityPickerActivity.start_flage;


/**
 * Created by hbh on 2017/4/20.
 * 子布局ViewHolder
 */

public class LeafViewHolder extends BaseTelecomHolder {

    private Context mContext;
    private View view;
 private Activity mactivity;
    public LeafViewHolder(Context context, View itemView,Activity activity) {
        super(itemView);
        this.mContext = context;
        this.view = itemView;
        this.mactivity = activity;
    }

    public void bindView(Location_Bean bean, final int pos) {
        TextView l1 = view.findViewById(R.id.leaf_1);
        TextView l2 = view.findViewById(R.id.leaf_2);
        l1.setText(bean.getLeaf1());
        l2.setText(bean.getLeaf2());
        //子布局OnClick监听
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(start_flage.equals("home"))
                {
                    EventBus.getDefault().post(new HomeAdapaterSucc(l2.getText().toString(),l1.getText().toString(),loaction_p_id,location_p_name));
                }else if(start_flage.equals("toncity")){
                    EventBus.getDefault().post(new bless_home(l2.getText().toString(),l1.getText().toString(),loaction_p_id,location_p_name));
                }else {
                    EventBus.getDefault().post(new HouseBean(l2.getText().toString(),l1.getText().toString(),loaction_p_id,location_p_name));
                }
                mactivity.finish();
            }
        });
    }
}
