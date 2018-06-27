package com.itislevel.lyl.widget;

import android.content.Context;
import android.support.v4.app.Fragment;
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
import com.itislevel.lyl.adapter.ReasonAdapter;
import com.itislevel.lyl.mvp.model.bean.AddressBean;
import com.itislevel.lyl.mvp.ui.myaddress.MyAddressActivity;
import com.itislevel.lyl.mvp.ui.special.OrderAllFragment;
import com.itislevel.lyl.mvp.ui.special.OrderNoPayFragment;
import com.itislevel.lyl.utils.ToastUtil;
import com.itislevel.lyl.utils.rxbus.RxBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * desc:功能说明必写
 * user:itisi
 * date:2017/12/8.15:52
 * path:com.itislevel.lyl.widget.AddressBottomDoalog
 **/
public class OrderCancelReasonBottomDoalog extends BottomBaseDialog<OrderCancelReasonBottomDoalog> {


    @BindView(R.id.tv_ok)
    TextView tv_ok;
    @BindView(R.id.tv_cancel)
    TextView tv_cancel;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private ReasonAdapter adapter;
    private LinearLayoutManager layoutManager;
    private Context mContext;

    List<String> reasonList;
    Fragment fragment;

    private String reason;

    public OrderCancelReasonBottomDoalog(Context context, View animateView,Fragment fragment) {
        super(context, animateView);
        mContext = context;
        this.fragment=fragment;
    }

    @Override
    public View onCreateView() {
        showAnim(new FlipVerticalSwingEnter());
        dismissAnim(null);
        View inflate = View.inflate(mContext, R.layout.dialog_reason, null);
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
        tv_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (TextUtils.isEmpty(reason)){
                    ToastUtil.Info("请选择原因");
                    return;
                }

                if (fragment instanceof OrderAllFragment){
                    ((OrderAllFragment) fragment).addCancel(reason);
                }
                else if (fragment instanceof OrderNoPayFragment){
                    ((OrderNoPayFragment) fragment).addCancel(reason);
                }


                dismiss();
            }
        });
//        layoutManager = new GridLayoutManager(mContext,4);
//        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        layoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);

        recyclerView.setLayoutManager(layoutManager);


//        recyclerView.addItemDecoration(new DividerGridItemDecoration(mContext, 4));
        if (reasonList == null) reasonList = new ArrayList<>();
        reasonList.add("不太喜欢");
        reasonList.add("信息填写错误,需要重拍");
        reasonList.add("买家缺货");
        reasonList.add("其他原因");
        adapter = new ReasonAdapter(reasonList, mContext);

        adapter.setClickListener(new ReasonAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                View parentLayout;
                TextView viewById;
                for (int i = 0; i < reasonList.size(); i++) {
                    parentLayout = layoutManager.findViewByPosition(i);
                    viewById = (TextView) parentLayout.findViewById(R.id.tv_reason);
                    viewById.setTextColor(mContext.getResources().getColor(R.color.colorNav));
                }
                parentLayout = layoutManager.findViewByPosition(position);
                viewById = (TextView) parentLayout.findViewById(R.id.tv_reason);
                viewById.setTextColor(mContext.getResources().getColor(R.color.colorPrimary));

                reason = reasonList.get(position);
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        });
        recyclerView.setAdapter(adapter);
    }

}
