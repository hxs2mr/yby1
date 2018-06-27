package com.itislevel.lyl.adapter;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.TextUtils;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.itislevel.lyl.R;
import com.itislevel.lyl.app.App;
import com.itislevel.lyl.app.Constants;
import com.itislevel.lyl.mvp.model.bean.BlessListBean;
import com.itislevel.lyl.mvp.model.bean.FunshingCommentItemBean;
import com.itislevel.lyl.mvp.model.bean.TeamListBean;
import com.itislevel.lyl.mvp.ui.bus.bless_home;
import com.itislevel.lyl.mvp.ui.chatkeyboardview.EmotionUtils;
import com.itislevel.lyl.mvp.ui.chatkeyboardview.SpanStringUtils;
import com.itislevel.lyl.utils.DateUtil;
import com.itislevel.lyl.utils.GsonUtil;
import com.itislevel.lyl.utils.SAToast;
import com.itislevel.lyl.utils.SharedPreferencedUtils;
import com.itislevel.lyl.utils.imageload.ImageLoadConfiguration;
import com.itislevel.lyl.utils.imageload.ImageLoadProxy;
import com.lzy.ninegrid.ImageInfo;
import com.lzy.ninegrid.NineGridView;
import com.lzy.ninegrid.preview.NineGridViewClickAdapter;

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * desc:功能说明必写
 * user:itisi
 * date:2017/12/21.19:19
 * path:com.itislevel.lyl.adapter.TeamTypeAdapter
 **/
public class BlessListAdapter extends BaseQuickAdapter<BlessListBean.ListBean, BaseViewHolder> {
    Activity mActivity ;
    public BlessListAdapter(int layoutResId,Activity activity) {
        super(layoutResId);
        this.mActivity = activity;
    }

    @Override
    protected void convert(BaseViewHolder helper, BlessListBean.ListBean item) {

//      iv_header tv_nickname tv_title  ninegrid_imgs
        //         tv_time tv_view_count tv_gift_count

        helper.addOnClickListener(R.id.tv_title);
        helper.addOnClickListener(R.id.tv_nickname);
        helper.addOnClickListener(R.id.iv_header);
        helper.addOnClickListener(R.id.tv_del);

        helper.addOnClickListener(R.id.tv_like_num);
        helper.addOnClickListener(R.id.tv_share);
        helper.addOnClickListener(R.id.tv_zan_count);
        helper.addOnClickListener(R.id.share_guan_linear);

        TextView tv_like_num = helper.getView(R.id.tv_like_num);
        AppCompatImageView guan_zhu_image = helper.getView(R.id.guan_zhu_image);
        AppCompatTextView guan_zhu_tv = helper.getView(R.id.guan_zhu_tv);
        LinearLayoutCompat share_guan_linear = helper.getView(R.id.share_guan_linear);
        if(item.getIsfollow()!=null)
        {
            if(item.getIsfollow().equals("0"))//表示未关注
            {
                guan_zhu_image.setBackgroundResource(R.mipmap.share_guan_add);
                guan_zhu_tv.setText("关注");
                guan_zhu_tv.setTextColor(Color.parseColor("#ff7a00"));
                share_guan_linear.setBackground(mActivity.getResources().getDrawable(R.drawable.share_item_shape));
            }else {
                share_guan_linear.setBackground(mActivity.getResources().getDrawable(R.drawable.share_item_yeguan));
                guan_zhu_image.setBackgroundResource(R.mipmap.share_yeguan);
                guan_zhu_tv.setText("已关注");
                guan_zhu_tv.setTextColor(Color.parseColor("#666666"));
            }
        }

//    点赞
        if ( item.getIspoint().equals("1")) {
            Drawable rightDrawable = mActivity.getResources().getDrawable(R.mipmap.icon_like);
            rightDrawable.setBounds(0, 0, rightDrawable.getMinimumWidth(), rightDrawable
                    .getMinimumHeight());
            tv_like_num.setCompoundDrawables(rightDrawable, null, null, null);
        } else {
            Drawable rightDrawable = mActivity.getResources().getDrawable(R.mipmap.icon_like_gray);
            rightDrawable.setBounds(0, 0, rightDrawable.getMinimumWidth(), rightDrawable
                    .getMinimumHeight());
            tv_like_num.setCompoundDrawables(rightDrawable, null, null, null);
        }
        if(item.getNmpointlist()!=null)
        {
            helper.setText(R.id.tv_like_num,item.getNmpointlist().size()+"");
        }else {
            helper.setText(R.id.tv_like_num,"0");
        }

        String imgurl = item.getImgurl();

        if (TextUtils.isEmpty(imgurl)){

        ImageLoadProxy.getInstance()
                .load(new ImageLoadConfiguration.Builder(App.getInstance())
                        .defaultImageResId(R.mipmap.icon_default_header_circle)
                        .url(R.mipmap.icon_default_header_circle)
                        .imageView((ImageView) helper.getView(R.id.iv_header)).build());
        }else{

        ImageLoadProxy.getInstance()
                .load(new ImageLoadConfiguration.Builder(App.getInstance())
                        .defaultImageResId(R.mipmap.icon_default_header_circle)
                        .url(Constants.IMG_SERVER_PATH + item.getImgurl())
                        .imageView((ImageView) helper.getView(R.id.iv_header)).build());
        }

//
        helper.setText(R.id.tv_nickname, item.getNickname());

//        helper.setText(R.id.tv_title, item.getContent());

        TextView tvConent=helper.getView(R.id.tv_title);
        SpannableString emotionContent = SpanStringUtils.getEmotionContent(EmotionUtils
                .EMOTION_CLASSIC_TYPE, mContext, tvConent, item.getContent());
        tvConent.setText(emotionContent);

        if(item.getNmpointlist()!=null)
        {
            helper.setText(R.id.tv_zan_count,item.getNmpointlist().size()+"");
        }else {
            helper.setText(R.id.tv_zan_count,"0");
        }
        if(item.getShmlist()!=null)
        {
            helper.setText(R.id.tv_pin_count,item.getShmlist().size()+"");
        }else {
            helper.setText(R.id.tv_pin_count,"0");
        }
        helper.setText(R.id.tv_time, DateUtil.timeSpanToDate(item.getCreatedtime()));
        helper.setText(R.id.tv_look_count, item.getLooknum()+"");
//
        helper.setText(R.id.tv_gift_count, TextUtils.isEmpty(item.getHappynum())?"0":item.getHappynum().toString());

        NineGridView ninegrid_imgs= helper.getView(R.id.ninegrid_imgs);

        List<ImageInfo> urlList=new ArrayList<>();
        ImageInfo imageInfo;
        //包含图片
        String imge = item.getImge();

//        TextView tv_del = helper.getView(R.id.tv_del);
//        if (tv_del!=null&&!TextUtils.isEmpty(imge)){
//            tv_del.setHeight(100);
//        }

        if (!TextUtils.isEmpty(imge)){
            helper.setGone(R.id.ninegrid_imgs,true);
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
            helper.setGone(R.id.ninegrid_imgs,false);
        }
        BlessHomeAdapter    commentAdapter = null;
        RecyclerView recyclerViewComment = helper.getView(R.id.recyclerview_comment);
        List<BlessListBean.ListBean.CommentsBean> comments = item.getShmlist();
        LinearLayoutManager layoutManager = new LinearLayoutManager(mActivity);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerViewComment.setLayoutManager(layoutManager);

        if (commentAdapter == null) {
            commentAdapter = new BlessHomeAdapter(R.layout.item_comment_bless, mActivity,item.getUserid()+"");
           // commentAdapter.setOnItemClickListener(commentItemListener);
           // commentAdapter.setOnItemChildClickListener(commentItemListener);
            commentAdapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN); //切换动画
            commentAdapter.setEnableLoadMore(false);
            recyclerViewComment.setAdapter(commentAdapter);
        }
        if (comments != null && comments.size() > 0) {
            commentAdapter.addData(comments);
        }

    }
}
