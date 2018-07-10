package com.itislevel.lyl.adapter;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.SpannableString;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.google.android.flexbox.FlexboxLayout;
import com.itislevel.lyl.R;
import com.itislevel.lyl.app.Constants;
import com.itislevel.lyl.mvp.model.bean.ListItemBean;
import com.itislevel.lyl.mvp.ui.chatkeyboardview.EmotionUtils;
import com.itislevel.lyl.mvp.ui.chatkeyboardview.SpanStringUtils;
import com.itislevel.lyl.utils.imageload.ImageLoadConfiguration;
import com.itislevel.lyl.utils.imageload.ImageLoadProxy;
import com.lzy.ninegrid.ImageInfo;
import com.lzy.ninegrid.NineGridView;
import com.lzy.ninegrid.preview.NineGridViewClickAdapter;

import java.util.ArrayList;
import java.util.List;

import static com.itislevel.lyl.mvp.ui.main.dynamic.childfragment.dfollow.DFollowFragment.DFollowcommentItemListener;
import static com.itislevel.lyl.mvp.ui.main.dynamic.childfragment.dfollow.DFollowFragment.followreceiveGiftClickListener;

/**
 * desc:功能说明必写
 * user:itisi
 * date:2017/12/5.14:29
 * path:com.itislevel.lyl.adapter.ProvinceAdapter
 **/
public class DynamicFollowAdapter extends BaseQuickAdapter<ListItemBean,
        BaseViewHolder> {
   public  static  List<DFollowCommentAdapter> DFollowComment_adapter;
    public static  List<DynamicFollowGiftAdapter> dynamicFollowGiftAdapters;
    private int frist=0;
    Activity mActivity;
    private boolean isScrolling = false;

    public DynamicFollowAdapter(int layoutResId, Activity activity) {
        super(layoutResId);
        openLoadAnimation(BaseQuickAdapter.ALPHAIN);
        DFollowComment_adapter = new ArrayList<>();
        this.mActivity = activity;
        dynamicFollowGiftAdapters = new ArrayList<>();
    }
    public  void setScrolling(Boolean scrolling)
    {
        isScrolling = scrolling;
    }
    @Override
    protected void convert(BaseViewHolder helper, ListItemBean item) {
//                 ninegrid_share  多图的时候用
//   FlexboxLayout fbl_parent tv_like_txt_temp
    //    helper.setIsRecyclable(false);//禁止复用 简单粗暴
        helper.addOnClickListener(R.id.tv_like_num);
        helper.addOnClickListener(R.id.tv_comment_num);
        helper.addOnClickListener(R.id.iv_header);
        helper.addOnClickListener(R.id.tv_comment_input);
        helper.addOnClickListener(R.id.tv_title);
        helper.addOnClickListener(R.id.tv_share);
        helper.addOnClickListener(R.id.share_guan_linear);
        helper.addOnClickListener(R.id.recycler_gift);
        helper.addOnClickListener(R.id.song2_iv);
        helper.addOnClickListener(R.id.genduo_linear);
        helper.addOnClickListener(R.id.song2_iv_zhu);
        LinearLayoutCompat  gift_linear = helper.getView(R.id.gift_linear);
        AppCompatTextView shou_gift_tv = helper.getView(R.id.shou_gift_tv);
        AppCompatImageView guan_zhu_image = helper.getView(R.id.guan_zhu_image);
        AppCompatTextView guan_zhu_tv = helper.getView(R.id.guan_zhu_tv);
        LinearLayoutCompat share_guan_linear = helper.getView(R.id.share_guan_linear);

        share_guan_linear.setVisibility(View.GONE);
        RecyclerView recycler_gift = helper.getView(R.id.recycler_gift);

        int size1 = item.getGiftList().size();
        DynamicFollowGiftAdapter detailGiftAdapter= new DynamicFollowGiftAdapter(R.layout.item_bless_detail_gift);
        detailGiftAdapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN); //切换动画
        detailGiftAdapter.setEnableLoadMore(false);
        detailGiftAdapter.setOnItemClickListener(followreceiveGiftClickListener);
        detailGiftAdapter.setOnItemChildClickListener(followreceiveGiftClickListener);
        StaggeredGridLayoutManager layoutManager1 = new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.HORIZONTAL);
        recycler_gift.setLayoutManager(layoutManager1);
        recycler_gift.setAdapter(detailGiftAdapter);
        if(helper.getAdapterPosition()==0)
        {
                if(size1!=0)
                {
                    gift_linear.setVisibility(View.VISIBLE);
                    shou_gift_tv.setVisibility(View.VISIBLE);
     /*       LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);*/
                    List<ListItemBean.GiftListBean> listBeans = new ArrayList<>();
                    int size = item.getGiftList().size();
                    for(int i = size-1; i >=0;i--)
                    {
                        listBeans.add(item.getGiftList().get(i));
                    }
                    detailGiftAdapter.setNewData(listBeans);

                }else {
                    gift_linear.setVisibility(View.GONE);
                    shou_gift_tv.setVisibility(View.GONE);
                }

        }else {
            if(size1!=0)
            {
                gift_linear.setVisibility(View.VISIBLE);
                shou_gift_tv.setVisibility(View.VISIBLE);
            }else {
                gift_linear.setVisibility(View.GONE);
                shou_gift_tv.setVisibility(View.GONE);
            }
     /*       LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);*/
            List<ListItemBean.GiftListBean> listBeans = new ArrayList<>();
            int size = item.getGiftList().size();
            for(int i = size-1; i >=0;i--)
            {
                listBeans.add(item.getGiftList().get(i));
            }
            detailGiftAdapter.setNewData(listBeans);
        }
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
        FlexboxLayout fbl_parent = helper.getView(R.id.fbl_parent);
        if (item.getNmpointlist() != null && item.getNmpointlist().size() > 0) {

            fbl_parent.removeAllViews();

            TextView likeICONTxt = new TextView(mActivity);
            Drawable rightDrawable = mActivity.getResources().getDrawable(R.mipmap
                    .icon_like_tip);
            rightDrawable.setBounds(0, 0, 40, 40);
            likeICONTxt.setTextSize(14);
            likeICONTxt.setCompoundDrawables(rightDrawable, null, null, null);
            likeICONTxt.setText(" ");
            likeICONTxt.setCompoundDrawablePadding(10);
            likeICONTxt.setTextColor(Color.parseColor("#034b71"));
            fbl_parent.addView(likeICONTxt);
            int index = 0;
            //                    index = index + 1;
//                    if (index < blessAddLikeBean.getS2().size()) {
//                        textView.setText(fab + "、");
//                    } else {
//                        textView.setText(fab);
//                    }

            for (String fab : item.getNmpointlist()) {
                TextView textView = new TextView(mActivity);
                index = index + 1;
           /*     if (index < item.getNmpointlist().size()) {
                    textView.setText(fab + "、");
                } else {*/
                    textView.setText(fab+"、");
                //}
                textView.setTextSize(14);
                textView.setTextColor(Color.parseColor("#034b71"));
                textView.setPadding(5, 5, 5, 5);
                fbl_parent.addView(textView);
            }

        } else {
            fbl_parent.removeAllViews();
        }
        helper.setText(R.id.tv_nickname, item.getNickname());
        helper.setTextColor(R.id.tv_nickname, Color.parseColor("#ff7800"));
        helper.setTextColor(R.id.tv_like_num, Color.parseColor("#00000000"));
        helper.setText(R.id.tv_time, item.getWritingDate());
        TextView tvConent = helper.getView(R.id.tv_title);

        SpannableString emotionContent;
        if(item.getContent().contains("[数量1]"))
        {
            emotionContent = SpanStringUtils.getEmotionContent_Dy(EmotionUtils
                    .EMOTION_CLASSIC_TYPE, mContext, tvConent,item.getContent(),0);
        }else {
            emotionContent = SpanStringUtils.getEmotionContent_Dy(EmotionUtils
                    .EMOTION_CLASSIC_TYPE, mContext, tvConent,item.getContent(),1);
        }
        tvConent.setText(emotionContent);

        //评论不显示数量
        TextView viewLike = helper.getView(R.id.tv_like_num);
        if (item.getIspoint().equals("1")) {
            item.setIsdianzan(true);
            Drawable rightDrawable = mActivity.getResources().getDrawable(R.mipmap.icon_like);
            rightDrawable.setBounds(0, 0, rightDrawable.getMinimumWidth(), rightDrawable
                    .getMinimumHeight());
            viewLike.setCompoundDrawables(rightDrawable, null, null, null);
        } else {
            item.setIsdianzan(false);
            Drawable rightDrawable = mActivity.getResources().getDrawable(R.mipmap.icon_like_gray);
            rightDrawable.setBounds(0, 0, rightDrawable.getMinimumWidth(), rightDrawable
                    .getMinimumHeight());
            viewLike.setCompoundDrawables(rightDrawable, null, null, null);
        }

        if(item.getNmpointlist()!=null)
        {
            helper.setText(R.id.tv_like_num, item.getNmpointlist().size() + "");
        }else {
            helper.setText(R.id.tv_like_num, item.getFabulousnumber()+"");
        }

        if (TextUtils.isEmpty(item.getNickname())) {
            helper.setText(R.id.tv_nickname, item.getUsername());
        } else {
            helper.setText(R.id.tv_nickname, item.getNickname());
        }


        if (!TextUtils.isEmpty(item.getImgurl())) {
            Glide.with(mActivity)
                    .load(Constants.IMG_SERVER_PATH + item.getImgurl())
                    .asBitmap()
                    .error(R.mipmap.person_head)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into((ImageView) helper.getView(R.id.iv_header));
        } else {
            ImageLoadProxy.getInstance()
                    .load(new ImageLoadConfiguration.Builder(mActivity)
                            .defaultImageResId(R.mipmap.icon_default_header_circle)
                            .url(R.mipmap.icon_default_header_circle)
                            .imageView((ImageView) helper.getView(R.id.iv_header)).build());
        }

        NineGridView ninegrid_imgs = helper.getView(R.id.ninegrid_share);

        // 图片 ninegrid_share
        List<ImageInfo> urlList = new ArrayList<>();
        ImageInfo imageInfo;
        //包含图片
        String imge = item.getImge();

        if (!TextUtils.isEmpty(imge)) {
            helper.setGone(R.id.ninegrid_share, true);
            String[] split = imge.split(",");
            for (String url : split) {
                if (!TextUtils.isEmpty(url) && url != null && url != "" && !url.equals(",")) {
                    imageInfo = new ImageInfo();
                    imageInfo.setBigImageUrl(Constants.IMG_SERVER_PATH + url.trim());
                    imageInfo.setThumbnailUrl(Constants.IMG_SERVER_PATH + url.trim());
                    urlList.add(imageInfo);
                }
            }
            ninegrid_imgs.setAdapter(new NineGridViewClickAdapter(mActivity, urlList));

        } else {
            helper.setGone(R.id.ninegrid_share, false);
        }

//  //评论列表
        RecyclerView recyclerViewComment = helper.getView(R.id.recyclerview_comment);
        List<ListItemBean.ListCommentItemBean> comments = item.getShmlist();
        LinearLayoutManager layoutManager = new LinearLayoutManager(mActivity);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerViewComment.setLayoutManager(layoutManager);

        if (comments != null && comments.size() > 0){
            DFollowCommentAdapter adapter = new DFollowCommentAdapter(R.layout
                    .item_comment, mActivity,item.getUserid());
            adapter.setEnableLoadMore(false);
            adapter.setNewData(comments);
            adapter.setOnItemClickListener(DFollowcommentItemListener);
            adapter.setOnItemChildClickListener(DFollowcommentItemListener);
            recyclerViewComment.setAdapter(adapter);
        } else {
            DFollowCommentAdapter adapter = new DFollowCommentAdapter(R.layout
                    .item_comment, mActivity,item.getUserid());
            recyclerViewComment.setAdapter(adapter);
        }

    }

}
