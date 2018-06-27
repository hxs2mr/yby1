package com.itislevel.lyl.widget;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.flyco.animation.BaseAnimatorSet;
import com.flyco.dialog.widget.base.BottomBaseDialog;
import com.itislevel.lyl.R;
import com.itislevel.lyl.adapter.BlessCartAdapter;
import com.itislevel.lyl.adapter.BlessYuListDialogAdapter;
import com.itislevel.lyl.mvp.model.bean.BlessCartListBean;
import com.itislevel.lyl.mvp.model.bean.FamilyBlessListBean;
import com.itislevel.lyl.mvp.ui.blessing.BlessingDetailActivity;
import com.itislevel.lyl.utils.DecimalUtils;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BlessYuListDialog extends BottomBaseDialog<BlessYuListDialog> implements
        BaseQuickAdapter.OnItemChildClickListener {
//    @BindView(R.id.ll_bless_yu_parent)
//    LinearLayout ll_bless_yu_parent;
//
//    @BindView(R.id.fbl_parent)
//    FlexboxLayout fbl_parent;
//
//    @BindView(R.id.et_content)
//    EditText et_content;
//    @BindView(R.id.tv_send)
//    TextView tv_send;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;


    BlessYuListDialogAdapter adapter;

    Activity activity;

    BlessGiftDialog giftDialog;
    List<FamilyBlessListBean.ListBean> beanList;

    public BlessYuListDialog(Activity activity, View animateView,
                             FamilyBlessListBean blessListBean) {
        super(activity, animateView);
        this.activity = activity;
        beanList = blessListBean.getList();
    }


    @Override
    public View onCreateView() {
        View inflate = View.inflate(mContext, R.layout.item_bless_yu_dialog_layout, null);
        ButterKnife.bind(this, inflate);
        mLlTop.setPadding(0, 0, 0, 80);
        return inflate;
    }

    @Override
    public void setUiBeforShow() {
        initAdapter();
        if (beanList != null && beanList.size() > 0) {
            adapter.setNewData(beanList);
        }



    }

    /**
     * 初始化adapter
     */
    private void initAdapter() {
        if (adapter == null) {
            adapter = new BlessYuListDialogAdapter(R.layout.item_bless_yu_dialog);
//            adapter.setOnItemClickListener(this);
            adapter.setOnItemChildClickListener(this);
            adapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN); //切换动画
            adapter.setEnableLoadMore(false);
            LinearLayoutManager layoutManager = new LinearLayoutManager(activity);

//            recyclerView.addItemDecoration(new HorizontalDividerItemDecoration.Builder(activity)
//                    .build());
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(adapter);

        }
    }


    @Override
    public void show() {
        super.show();

    }

    private BaseAnimatorSet mWindowInAs;
    private BaseAnimatorSet mWindowOutAs;

    @Override
    protected BaseAnimatorSet getWindowInAs() {
        if (mWindowInAs == null) {
            mWindowInAs = new WindowsInAs();
        }
        return mWindowInAs;
    }

    @Override
    protected BaseAnimatorSet getWindowOutAs() {
        if (mWindowOutAs == null) {
            mWindowOutAs = new WindowsOutAs();
        }
        return mWindowOutAs;
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
      dismiss();

    }


    class WindowsInAs extends BaseAnimatorSet {
        @Override
        public void setAnimation(View view) {
            ObjectAnimator rotationX = ObjectAnimator.ofFloat(view, "rotationX", 10, 0f)
                    .setDuration(150);
            rotationX.setStartDelay(200);
            animatorSet.playTogether(
                    ObjectAnimator.ofFloat(view, "scaleX", 1.0f, 0.8f).setDuration(350),
                    ObjectAnimator.ofFloat(view, "scaleY", 1.0f, 0.8f).setDuration(350),
//                    ObjectAnimator.ofFloat(view, "alpha", 1.0f, 0.5f).setDuration(350),
                    ObjectAnimator.ofFloat(view, "rotationX", 0f, 10).setDuration(200),
                    rotationX,
                    ObjectAnimator.ofFloat(view, "translationY", 0, -0.1f * mDisplayMetrics
                            .heightPixels).setDuration(350)
            );
        }
    }

    class WindowsOutAs extends BaseAnimatorSet {
        @Override
        public void setAnimation(View view) {
            ObjectAnimator rotationX = ObjectAnimator.ofFloat(view, "rotationX", 10, 0f)
                    .setDuration(150);
            rotationX.setStartDelay(200);
            animatorSet.playTogether(
                    ObjectAnimator.ofFloat(view, "scaleX", 0.8f, 1.0f).setDuration(350),
                    ObjectAnimator.ofFloat(view, "scaleY", 0.8f, 1.0f).setDuration(350),
//                    ObjectAnimator.ofFloat(view, "alpha", 1.0f, 0.5f).setDuration(350),
                    ObjectAnimator.ofFloat(view, "rotationX", 0f, 10).setDuration(200),
                    rotationX,
                    ObjectAnimator.ofFloat(view, "translationY", -0.1f * mDisplayMetrics
                            .heightPixels, 0).setDuration(350)
            );
        }
    }
}
