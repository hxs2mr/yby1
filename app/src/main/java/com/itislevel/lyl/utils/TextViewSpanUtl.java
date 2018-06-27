package com.itislevel.lyl.utils;

import android.content.Context;
import android.graphics.Color;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.itislevel.lyl.R;
import com.itislevel.lyl.mvp.ui.chatkeyboardview.EmotionUtils;
import com.itislevel.lyl.mvp.ui.chatkeyboardview.SpanStringUtils;

/**
 * desc:功能说明必写
 * user:itisi
 * date:2018-02-09.20:20
 * path:com.itislevel.lyl.utils.TextViewSpanUtl
 **/
public class TextViewSpanUtl {

    public static void setCommentText(  Context context,String nickname, String comment, TextView textView) {
        NoLineClickSpan clickSpan = new NoLineClickSpan("#034b71");
        SpannableString spStr = new SpannableString(nickname+":");
        spStr.setSpan(clickSpan, 0, (TextUtils.isEmpty(nickname)?0:nickname.length())+1, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        textView.setText(spStr);
        SpannableString emotionContent;
        if(comment.contains("[数量1]"))
        {
            emotionContent = SpanStringUtils.getEmotionContent_Dy(EmotionUtils
                    .EMOTION_CLASSIC_TYPE, context, textView,comment,0);
        }else {
            emotionContent = SpanStringUtils.getEmotionContent_Dy(EmotionUtils
                    .EMOTION_CLASSIC_TYPE, context, textView, comment,1);
        }
         textView.append(emotionContent);

    }
    public static TextView  setFamilyWUext( Context context,String nickname, String comment, TextView textView) {
        int color = Color.parseColor("#034b71");
        int color1 = Color.parseColor("#ff7a00");
        SpannableString spStr = new SpannableString(nickname+":"+comment);
        spStr.setSpan(new ForegroundColorSpan(color), 0, nickname.length()+1, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        spStr.setSpan(new ForegroundColorSpan(color1), nickname.length()+1, nickname.length()+comment.length()+1, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        textView.setText(spStr);
/*        SpannableString emotionContent = SpanStringUtils.getEmotionContent(EmotionUtils
                .EMOTION_CLASSIC_TYPE, context, textView, comment);*/
        return textView;

    }

    public static void setFamilyReceive(String byusername, String shouname, TextView textView) {
        int color = Color.parseColor("#ff7a00");
        int color1 = Color.parseColor("#666666");
        SpannableString spStr = new SpannableString(byusername+"给"+shouname+"赠送祭品：");
        spStr.setSpan(new ForegroundColorSpan(color), 0, byusername.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        spStr.setSpan(new ForegroundColorSpan(color), byusername.length()+1, byusername.length()+shouname.length()+1, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        textView.setText(spStr);
/*        SpannableString emotionContent = SpanStringUtils.getEmotionContent(EmotionUtils
                .EMOTION_CLASSIC_TYPE, context, textView, comment);*/

    }
    public static TextView setFamilyYUext(Context context,String nickname, String comment, TextView textView) {
        int color = Color.parseColor("#034b71");
        int color1 = Color.parseColor("#666666");
        SpannableString spStr = new SpannableString(nickname+":"+comment);
        spStr.setSpan(new ForegroundColorSpan(color), 0, nickname.length()+1, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        spStr.setSpan(new ForegroundColorSpan(color1), nickname.length()+1, nickname.length()+comment.length()+1, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        textView.setText(spStr);
/*        SpannableString emotionContent = SpanStringUtils.getEmotionContent(EmotionUtils
                .EMOTION_CLASSIC_TYPE, context, textView, comment);*/
        return textView;

    }

    public static void setCommentReplayText( Context context,String nickname1,String nickname2, String comment, TextView textView) {
        int color = Color.parseColor("#666666");
        SpannableString spStr = new SpannableString(nickname2+" 回复 "+nickname1+":");
        if(nickname1!=null&&nickname2!=null)
        {
            spStr.setSpan(new ForegroundColorSpan(color),nickname2.length()+1,nickname2.length()+3,Spanned.SPAN_INCLUSIVE_INCLUSIVE);
            textView.setText(spStr);
        }
        SpannableString emotionContent;
        if(comment.contains("[数量1]"))
        {
            emotionContent = SpanStringUtils.getEmotionContent_Dy(EmotionUtils
                    .EMOTION_CLASSIC_TYPE, context, textView,comment,0);
        }else {
            emotionContent = SpanStringUtils.getEmotionContent_Dy(EmotionUtils
                    .EMOTION_CLASSIC_TYPE, context, textView, comment,1);
        }

        textView.append(emotionContent);
    }
//        textView.setMovementMethod(LinkMovementMethod.getInstance());

    //        textView.setMovementMethod(LinkMovementMethod.getInstance());


    private static class NoLineClickSpan extends ClickableSpan {
        String color;

        public NoLineClickSpan(String color) {
            super();
            this.color = color;
        }

        @Override
        public void updateDrawState(TextPaint ds) {
            //设置字体颜色
            ds.setColor(Color.parseColor(color));
            ds.setUnderlineText(false); //去掉下划线
        }

        @Override
        public void onClick(View widget) {
            //点击超链接时调用
//            Toast.makeText(context, "您点击了sunflowerseat", Toast.LENGTH_SHORT).show();
        }
    }

}
