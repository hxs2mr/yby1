package com.itislevel.lyl.widget;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.flyco.animation.FlipEnter.FlipVerticalSwingEnter;
import com.flyco.dialog.widget.base.BottomBaseDialog;
import com.itislevel.lyl.R;
import com.itislevel.lyl.adapter.AddressAdapter;
import com.itislevel.lyl.mvp.model.bean.AddressBean;
import com.itislevel.lyl.mvp.ui.myaddress.MyAddressActivity;
import com.itislevel.lyl.utils.ToastUtil;
import com.itislevel.lyl.utils.rxbus.RxBus;

import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * desc:功能说明必写
 * user:itisi
 * date:2017/12/8.15:52
 * path:com.itislevel.lyl.widget.AddressBottomDoalog
 **/
public class AddressBottomDoalog extends BottomBaseDialog<AddressBottomDoalog> {

    private String selectId="";
    private String selectName="";

    @BindView(R.id.tv_ok)
    TextView tv_ok;
    @BindView(R.id.tv_cancel)
    TextView tv_cancel;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private AddressAdapter adapter;
    private GridLayoutManager layoutManager;
    private Context mContext;
    List<AddressBean> addressBeans;

    private int tag;

    public AddressBottomDoalog(Context context, View animateView,int tag) {
        super(context, animateView);
        mContext=context;
        this.tag=tag;
    }

    @Override
    public View onCreateView() {
        showAnim(new FlipVerticalSwingEnter());
        dismissAnim(null);
        View inflate = View.inflate(mContext, R.layout.dialog_province, null);
        ButterKnife.bind(this, inflate);

        return inflate;
    }

    @Override
    public void setUiBeforShow() {
        tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               dismiss();

            }
        });
        tv_ok.setVisibility(View.GONE);
        tv_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(selectId)){
                    String msg=tag+","+selectId+","+selectName;
                    RxBus.getInstance().post(RxBus.getInstance().getTag(MyAddressActivity.class,
                            RxBus.TAG_UPDATE),msg);
                }
                dismiss();
            }
        });
        layoutManager = new GridLayoutManager(mContext,4);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new DividerGridItemDecoration(mContext, 4));

    }


    public void setAddressBeans(List<AddressBean> addressBeans) {
        this.addressBeans = addressBeans;
        province();
    }

    public void province() {

        if (addressBeans.get(0).getName().equals("中国")){
            addressBeans.remove(0);
        }
        adapter=new AddressAdapter(addressBeans,mContext);
        adapter.setClickListener(new AddressAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                View parentLayout;
                TextView viewById;
                for (int i=0;i<addressBeans.size();i++){
                    parentLayout= layoutManager.findViewByPosition(i);
                    viewById= (TextView) parentLayout.findViewById(R.id.tv_name);
                    viewById.setTextColor(mContext.getResources().getColor(R.color.colorNav));
                }
                parentLayout= layoutManager.findViewByPosition(position);
                viewById= (TextView) parentLayout.findViewById(R.id.tv_name);
                viewById.setTextColor(mContext.getResources().getColor(R.color.colorBtn));

                selectId=addressBeans.get(position).getId()+"";
                selectName=addressBeans.get(position).getName()+"";

                String msg=tag+","+selectId+","+selectName;
                RxBus.getInstance().post(RxBus.getInstance().getTag(MyAddressActivity.class,
                        RxBus.TAG_UPDATE),msg);
                dismiss();


            }
            @Override
            public void onItemLongClick(View view, int position) {
            }
        });
        recyclerView.setAdapter(adapter);

    }


}
