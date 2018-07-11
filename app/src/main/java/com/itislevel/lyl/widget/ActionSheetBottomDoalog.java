package com.itislevel.lyl.widget;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.flyco.animation.FlipEnter.FlipVerticalSwingEnter;
import com.flyco.dialog.widget.base.BottomBaseDialog;
import com.itislevel.lyl.R;
import com.itislevel.lyl.adapter.ActionSheetReasonAdapter;
import com.itislevel.lyl.adapter.ReasonAdapter;
import com.itislevel.lyl.mvp.ui.special.OrderAllFragment;
import com.itislevel.lyl.mvp.ui.special.OrderNoPayFragment;
import com.itislevel.lyl.utils.ToastUtil;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

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
public class ActionSheetBottomDoalog extends BottomBaseDialog<ActionSheetBottomDoalog> implements
        BaseQuickAdapter.OnItemChildClickListener {


    @BindView(R.id.tv_ok)
    TextView tv_ok;
    @BindView(R.id.tv_status)
    TextView tv_status;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private ActionSheetReasonAdapter adapter;
    private LinearLayoutManager layoutManager;
    private Context mContext;

    List<String> reasonList;
    String title = "";
    private int selectPOs = -1;
    OnOKClickListener okClickListener;


    public ActionSheetBottomDoalog(Context context, View animateView, List<String> reasonList,
                                   OnOKClickListener okClickListener, String title) {
        super(context, animateView);
        mContext = context;
        this.reasonList = reasonList;
        this.okClickListener = okClickListener;
        this.title = title;
    }

    @Override
    public View onCreateView() {
        showAnim(new FlipVerticalSwingEnter());
        dismissAnim(null);
        View inflate = View.inflate(mContext, R.layout.dialog_action_sheet_bottom, null);
        ButterKnife.bind(this, inflate);
        return inflate;
    }

    @Override
    public void setUiBeforShow() {
        tv_status.setText(title);

        tv_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (selectPOs < 0) {
                    ToastUtil.Info("请选择原因");
                    return;
                }
                if (okClickListener != null) {
                    okClickListener.onOKClick(reasonList.get(selectPOs));
                }
                dismiss();
            }
        });
        layoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new HorizontalDividerItemDecoration.Builder(mContext)
                .build());

        adapter = new ActionSheetReasonAdapter(R.layout.item_reason);

        adapter.setNewData(reasonList);
        adapter.setOnItemChildClickListener(this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
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

        selectPOs = position;
    }

    public interface OnOKClickListener {
        void onOKClick(String msg);
    }

}
