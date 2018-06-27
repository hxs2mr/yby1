package com.itislevel.lyl.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.EditText;

/**
 * Created by Administrator on 2018\5\14 0014.
 */

@SuppressLint("AppCompatCustomView")
public class LineEditText extends EditText {

        private Paint linePaint;
        private float margin=30f;
         private float frist_margin=30f;
        private int paperColor= Color.parseColor("#ffffff");

        private  int is_frist=0;
        public LineEditText(Context paramContext, AttributeSet paramAttributeSet) {
            super(paramContext, paramAttributeSet);
            this.linePaint = new Paint();
        }
        public LineEditText(Context context,int color,float width) {
        super(context);
        //设置颜色和横线宽度
        this.paperColor = color;
        }

        @Override
        protected void onDraw(Canvas paramCanvas) {
            linePaint.setColor(Color.parseColor("#999999"));
            paramCanvas.drawColor(this.paperColor);
            int i = getLineCount();
            int j = getHeight();
            int k = getLineHeight();
            int m = 1 + j / k;
            int top =  this.getPaddingTop();//获取框内顶部留白
            float size = this.getTextSize();//获取字体大小
            float baseTop = top + size /4;//从上向下第一条线的位置
            if (i < m)
            {
                i = m;
            }
            int n = getCompoundPaddingTop();
            for (int i2 = 1;; i2++) {
                    if (i2 >= i) {
                        setPadding(0, 0, 0, 0);
                        super.onDraw(paramCanvas);
                        paramCanvas.restore();
                        return;
                    }
                    if(i2==1)
                    {
                        paramCanvas.drawLine(80f, baseTop+k*i2-25, getRight(), baseTop+k*i2-25, this.linePaint);
                    }else {
                        setPadding(0, 0, 0, 0);
                        paramCanvas.drawLine(0, baseTop+k*i2-25, getRight(), baseTop+k*i2-25, this.linePaint);
                    }
                        paramCanvas.save();

            }
        }

}
