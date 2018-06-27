package com.itislevel.lyl.mvp.ui.property.payrecord.decoration;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextPaint;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;

import com.itislevel.lyl.R;
import com.itislevel.lyl.mvp.model.bean.PayLuBean;
import com.itislevel.lyl.mvp.model.bean.RecordBean;
import com.itislevel.lyl.utils.SAToast;

import java.util.List;

import static com.itislevel.lyl.utils.Dp_to_Px.dip2px;
import static com.itislevel.lyl.utils.SystemUtil.sp2px;

public class SectionItemDecoration extends RecyclerView.ItemDecoration {
    private List<PayLuBean>  mData;
    private Paint mBgPaint;
    private Paint mPaint;
    private TextPaint mTextPaint;
    private TextPaint mTextPaint_down;
    private Rect mBounds;

    private int mSectionHeight;
    private int mBgColor;
    private int mTextColor;
    private int mTextSize;
    private Activity mActivity;

    public SectionItemDecoration(Context context, List<PayLuBean>  data, Activity activity) {
        this.mData = data;
        this.mActivity = activity;
        TypedValue typedValue = new TypedValue();

        context.getTheme().resolveAttribute(R.attr.cpSectionBackground, typedValue, true);
        mBgColor =Color.parseColor("#f5f5f5");

        context.getTheme().resolveAttribute(R.attr.cpSectionHeight, typedValue, true);
        mSectionHeight =dip2px(mActivity,48);

        context.getTheme().resolveAttribute(R.attr.cpSectionTextSize, typedValue, true);
        mTextSize =sp2px(14);

        context.getTheme().resolveAttribute(R.attr.cpSectionTextColor, typedValue, true);

        mBgPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mBgPaint.setColor(mBgColor);
        mPaint = new Paint(Paint.DITHER_FLAG);
        mTextPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint_down = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setTextSize(mTextSize);
        mTextPaint.setColor(Color.parseColor("#333333"));

        mTextPaint_down.setTextSize(sp2px(11));
        mTextPaint_down.setColor(Color.parseColor("#999999"));
        mBounds = new Rect();
    }

    public void setData(List<PayLuBean>  data) {
        this.mData = data;
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        final int left = parent.getPaddingLeft();
        final int right = parent.getWidth() - parent.getPaddingRight();
        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            int position = params.getViewLayoutPosition();
            if (mData != null && !mData.isEmpty() && position <= mData.size() - 1 && position > -1) {
                if (position == 0) {
                    drawSection(c, left, right, child, params, position);
                } else {
                    if (null != mData.get(position).getMonth() && !mData.get(position).getMonth().equals(mData.get(position - 1).getMonth())) {
                        drawSection(c, left, right, child, params, position);
                    }
                }
            }
        }
    }

    private void drawSection(Canvas c, int left, int right, View child, RecyclerView.LayoutParams params, int position) {
        c.drawRect(left, child.getTop() - params.topMargin - mSectionHeight, right, child.getTop() - params.topMargin, mBgPaint);
        mTextPaint.getTextBounds(mData.get(position).getMonth(), 0, mData.get(position).getMonth().length(), mBounds);//mSectionHeight/2
        c.drawText(mData.get(position).getMonth(), child.getPaddingLeft()+40, child.getTop() - params.topMargin - (mSectionHeight / 2 - mBounds.height() / 2)-dip2px(mActivity,8), mTextPaint);
        c.drawText("支出"+mData.get(position).getMoney(), child.getPaddingLeft()+40, child.getTop() - params.topMargin - (mSectionHeight / 2 - mBounds.height() / 2)+dip2px(mActivity,8), mTextPaint_down);
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        int pos = ((LinearLayoutManager) (parent.getLayoutManager())).findFirstVisibleItemPosition();
        int width = ((LinearLayoutManager) (parent.getLayoutManager())).getWidth();
        int height = ((LinearLayoutManager) (parent.getLayoutManager())).getHeight();
        if (pos < 0) return;
        if (mData == null || mData.isEmpty()) return;
        String section = mData.get(pos).getMonth();
        String code = mData.get(pos).getMoney();
        View child = parent.findViewHolderForLayoutPosition(pos).itemView;

        boolean flag = false;
        if ((pos + 1) < mData.size()) {
            if (null != section && !section.equals(mData.get(pos + 1).getMonth())) {
                if (child.getHeight() + child.getTop() < mSectionHeight) {
                    c.save();
                    flag = true;
                    c.translate(0, child.getHeight() + child.getTop() - mSectionHeight);
                }
            }
        }
        c.drawRect(parent.getPaddingLeft(), parent.getPaddingTop(), parent.getRight() - parent.getPaddingRight(), parent.getPaddingTop() + mSectionHeight, mBgPaint);
        mTextPaint.getTextBounds(section, 0, section.length(), mBounds);
        Bitmap bitmap = BitmapFactory.decodeResource(mActivity.getResources(), R.mipmap.jiaofei_time, null);
        c.drawText(section, child.getPaddingLeft()+40, parent.getPaddingTop() + mSectionHeight - (mSectionHeight / 2 - mBounds.height() / 2)-dip2px(mActivity,8), mTextPaint);

        c.drawText("支出"+code, child.getPaddingLeft()+40, parent.getPaddingTop() + mSectionHeight - (mSectionHeight / 2 - mBounds.height() / 2)+dip2px(mActivity,8), mTextPaint_down);

        c.drawBitmap(bitmap,width-bitmap.getWidth()-40,mBounds.height() / 2+dip2px(mActivity,5),mPaint);
        if (flag)
            c.restore();
    }


    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        int position = ((RecyclerView.LayoutParams) view.getLayoutParams()).getViewLayoutPosition();
        if (mData != null && !mData.isEmpty() && position <= mData.size() - 1 && position > -1) {
            if (position == 0) {
                outRect.set(0, mSectionHeight, 0, 0);
            } else {
                if (null != mData.get(position).getMonth()
                        && !mData.get(position).getMonth().equals(mData.get(position - 1).getMonth())) {
                    outRect.set(0, mSectionHeight, 0, 0);
                }
            }
        }
    }

}
