package com.itislevel.lyl.adapter;

import android.graphics.drawable.Drawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.google.android.flexbox.FlexboxLayout;
import com.itislevel.lyl.R;
import com.itislevel.lyl.app.App;
import com.itislevel.lyl.app.Constants;
import com.itislevel.lyl.mvp.model.bean.FunshingCommentItemBean;
import com.itislevel.lyl.mvp.model.bean.FunshingItemBean;
import com.itislevel.lyl.mvp.ui.chatkeyboardview.EmotionUtils;
import com.itislevel.lyl.mvp.ui.chatkeyboardview.SpanStringUtils;
import com.itislevel.lyl.mvp.ui.funsharing.FunsharingDetailActivity;
import com.itislevel.lyl.mvp.ui.funsharing.FunsharingHomeActivity;
import com.itislevel.lyl.utils.DateUtil;
import com.itislevel.lyl.utils.imageload.ImageLoadConfiguration;
import com.itislevel.lyl.utils.imageload.ImageLoadProxy;
import com.lzy.ninegrid.ImageInfo;
import com.lzy.ninegrid.NineGridView;
import com.lzy.ninegrid.preview.NineGridViewClickAdapter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * desc:功能说明必写
 * user:itisi
 * date:2017/12/5.14:29
 * path:com.itislevel.lyl.adapter.ProvinceAdapter
 **/
public class FunsharingDetailAdapter extends BaseQuickAdapter<FunshingItemBean,
        BaseViewHolder> {


    FunsharingDetailActivity funsharingDetailActivity;
    public FunsharingDetailAdapter(int layoutResId, FunsharingDetailActivity activity) {
        super(layoutResId);
        funsharingDetailActivity=activity;
    }

    @Override
    protected void convert(BaseViewHolder helper, FunshingItemBean item) {
//                 ninegrid_share  多图的时候用

        helper.addOnClickListener(R.id.tv_like_num);
        helper.addOnClickListener(R.id.tv_comment_num);
        helper.addOnClickListener(R.id.iv_header);
        helper.addOnClickListener(R.id.tv_comment_input);
        helper.addOnClickListener(R.id.tv_title);
        FlexboxLayout fbl_parent = helper.getView(R.id.fbl_parent);
        if (item.getNmpointlist() != null && item.getNmpointlist().size() > 0) {

            fbl_parent.removeAllViews();

            TextView likeICONTxt = new TextView(App.getInstance());
            Drawable rightDrawable = funsharingDetailActivity.getResources().getDrawable(R.mipmap.icon_like_tip);
            rightDrawable.setBounds(0, 0, rightDrawable.getMinimumWidth(), rightDrawable
                    .getMinimumHeight());
            likeICONTxt.setTextSize(14);
            likeICONTxt.setCompoundDrawables(rightDrawable, null, null, null);
            likeICONTxt.setText("、");
            likeICONTxt.setCompoundDrawablePadding(10);
            likeICONTxt.setTextColor(App.getInstance().getResources().getColor(R.color.colorNav));
            fbl_parent.addView(likeICONTxt);
            int index = 0;

            for (String fab : item.getNmpointlist()) {
                TextView textView = new TextView(App.getInstance());
                index = index + 1;
                if (index < item.getNmpointlist().size()) {
                    textView.setText(fab + "、");
                } else {
                    textView.setText(fab);
                }
                textView.setTextSize(14);
                textView.setTextColor(App.getInstance().getResources().getColor(R.color.colorNav));
                textView.setPadding(5, 5, 5, 5);
                fbl_parent.addView(textView);
            }

        }else {
            fbl_parent.removeAllViews();
        }


        helper.setText(R.id.tv_nickname, item.getNickname());

//        helper.setText(R.id.tv_time, DateUtil.getFriendlytime(new Date(item.getCreatedtime())));
        helper.setText(R.id.tv_time,  DateUtil.timeStrToDateTime(new Date(item.getCreatedtime())));
        helper.setTextColor(R.id.tv_nickname,funsharingDetailActivity.getResources().getColor(R.color.colorRed));



        TextView tvConent = helper.getView(R.id.tv_title);
        SpannableString emotionContent = SpanStringUtils.getEmotionContent(EmotionUtils
                .EMOTION_CLASSIC_TYPE, mContext, tvConent, item.getContent());
        tvConent.setText(emotionContent);

        //评论不显示数量
        TextView viewLike = helper.getView(R.id.tv_like_num);
        if (item.getIspoint().equals("1")) {
            Drawable rightDrawable = mContext.getResources().getDrawable(R.mipmap.icon_like);
            rightDrawable.setBounds(0, 0, rightDrawable.getMinimumWidth(), rightDrawable
                    .getMinimumHeight());
            viewLike.setCompoundDrawables(rightDrawable, null, null, null);
        } else {
            Drawable rightDrawable = mContext.getResources().getDrawable(R.mipmap.icon_like_gray);
            rightDrawable.setBounds(0, 0, rightDrawable.getMinimumWidth(), rightDrawable
                    .getMinimumHeight());
            viewLike.setCompoundDrawables(rightDrawable, null, null, null);
        }

        helper.setText(R.id.tv_like_num, item.getFabulousnumber() + "");

        if (TextUtils.isEmpty(item.getNickname())) {
            helper.setText(R.id.tv_nickname, item.getUsername());
        } else {
            helper.setText(R.id.tv_nickname, item.getNickname());
        }

        if (!TextUtils.isEmpty(item.getImgurl())) {
            ImageLoadProxy.getInstance()
                    .load(new ImageLoadConfiguration.Builder(funsharingDetailActivity)
                            .defaultImageResId(R.mipmap.icon_default_header_circle)
                            .url(Constants.IMG_SERVER_PATH + item.getImgurl())
                            .imageView((ImageView) helper.getView(R.id.iv_header)).build());
        } else {
            ImageLoadProxy.getInstance()
                    .load(new ImageLoadConfiguration.Builder(funsharingDetailActivity)
                            .defaultImageResId(R.mipmap.icon_default_header_circle)
                            .url(R.mipmap.icon_default_header_circle)
                            .imageView((ImageView) helper.getView(R.id.iv_header)).build());
        }

        NineGridView ninegrid_imgs= helper.getView(R.id.ninegrid_share);
        // 图片 ninegrid_share
        List<ImageInfo> urlList=new ArrayList<>();
        ImageInfo imageInfo;
        //包含图片
        String imge = item.getImge();


        if (!TextUtils.isEmpty(imge)){
            helper.setGone(R.id.ninegrid_share,true);
            String[] split = imge.split(",");
            for (String url:split){
                if (!TextUtils.isEmpty(url)&&url!=null&&url!=""&&!url.equals(",")){
                    imageInfo=new ImageInfo();
                    imageInfo.setBigImageUrl(Constants.IMG_SERVER_PATH+url.trim());
                    imageInfo.setThumbnailUrl(Constants.IMG_SERVER_PATH+url.trim());
                    urlList.add(imageInfo);
                }
            }
            ninegrid_imgs.setAdapter(new NineGridViewClickAdapter(App.getInstance(), urlList));

        }
        else{
            helper.setGone(R.id.ninegrid_share,false);
        }

//        //评论列表
        RecyclerView  recyclerViewComment= helper.getView(R.id.recyclerview_comment);
        List<FunshingCommentItemBean> comments = item.getShmlist();
        LinearLayoutManager layoutManager = new LinearLayoutManager(funsharingDetailActivity);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerViewComment.setLayoutManager(layoutManager);

        if (comments!=null&&comments.size()>0){
            FunsharingDetailCommentAdapter adapter=new FunsharingDetailCommentAdapter(R.layout.item_comment,funsharingDetailActivity,item.getUserid()+"");
            adapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN); //切换动画
            adapter.setEnableLoadMore(false);
            adapter.setNewData(comments);
            adapter.setOnItemClickListener(funsharingDetailActivity.getCommentItemListener());
            adapter.setOnItemChildClickListener(funsharingDetailActivity.getCommentItemListener());

            recyclerViewComment.setAdapter(adapter);
        }else{
            FunsharingDetailCommentAdapter adapter=new FunsharingDetailCommentAdapter(R.layout.item_comment,funsharingDetailActivity,item.getUserid()+"");
            recyclerViewComment.setAdapter(adapter);
        }

    }
}
