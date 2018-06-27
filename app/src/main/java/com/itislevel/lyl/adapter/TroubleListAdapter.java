package com.itislevel.lyl.adapter;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.TextUtils;
import android.view.TextureView;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.itislevel.lyl.R;
import com.itislevel.lyl.app.App;
import com.itislevel.lyl.app.Constants;
import com.itislevel.lyl.mvp.model.bean.FunshingCommentItemBean;
import com.itislevel.lyl.mvp.model.bean.FunshingItemBean;
import com.itislevel.lyl.mvp.model.bean.TroubleListBean;
import com.itislevel.lyl.mvp.ui.chatkeyboardview.EmotionUtils;
import com.itislevel.lyl.mvp.ui.chatkeyboardview.SpanStringUtils;
import com.itislevel.lyl.mvp.ui.funsharing.FunsharingHomeActivity;
import com.itislevel.lyl.mvp.ui.troublesolution.TroublesolutionListActivity;
import com.itislevel.lyl.utils.DateUtil;
import com.itislevel.lyl.utils.SharedPreferencedUtils;
import com.itislevel.lyl.utils.imageload.ImageLoadConfiguration;
import com.itislevel.lyl.utils.imageload.ImageLoadProxy;
import com.lzy.ninegrid.ImageInfo;
import com.lzy.ninegrid.NineGridView;
import com.lzy.ninegrid.preview.NineGridViewClickAdapter;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.finalteam.rxgalleryfinal.utils.Logger;

/**
 * desc:功能说明必写
 * user:itisi
 * date:2017/12/5.14:29
 * path:com.itislevel.lyl.adapter.ProvinceAdapter
 **/
public class TroubleListAdapter extends BaseQuickAdapter<TroubleListBean.ListBean,
        BaseViewHolder> {


    TroublesolutionListActivity funsharingHomeActivity ;
    public TroubleListAdapter(int layoutResId, TroublesolutionListActivity activity) {
        super(layoutResId);
        funsharingHomeActivity=activity;
    }

    @Override
    protected void convert(BaseViewHolder helper, TroubleListBean.ListBean item) {
//       tv_del_share          ninegrid_share  多图的时候用

        helper.addOnClickListener(R.id.iv_header);
        helper.addOnClickListener(R.id.tv_comment_input);
        helper.addOnClickListener(R.id.tv_title);

        helper.addOnClickListener(R.id.tv_del_share);
        String str = SharedPreferencedUtils.getStr(Constants.USER_ID);
        if (!TextUtils.isEmpty(str)){
            int loginUserId= Integer.parseInt(str);
            if (item.getUserid()==loginUserId){
                helper.setGone(R.id.tv_del_share,true);
            }else {
                helper.setGone(R.id.tv_del_share,false);
            }
        }

        helper.setText(R.id.tv_nickname, item.getNickname());

//        helper.setText(R.id.tv_time, item.getProvincename()+"."+item.getCityname());
        helper.setText(R.id.tv_time, DateUtil.timeSpanToDateTime1(item.getCreatedtime()));
        helper.setTextColor(R.id.tv_time,funsharingHomeActivity.getResources().getColor(R.color.colorBtn));
//        helper.setText(R.id.tv_title, item.getContent());
        TextView tvConent=helper.getView(R.id.tv_title);
        SpannableString emotionContent = SpanStringUtils.getEmotionContent(EmotionUtils
                .EMOTION_CLASSIC_TYPE, mContext, tvConent, item.getContent());
        tvConent.setText(emotionContent);

        helper.setTextColor(R.id.tv_nickname,funsharingHomeActivity.getResources().getColor(R.color.colorRed));
        if (TextUtils.isEmpty(item.getNickname())) {
            helper.setText(R.id.tv_nickname, item.getUsername());
        } else {
            helper.setText(R.id.tv_nickname, item.getNickname());
        }
        //头像
        if (item.getImgurl() != null) {
            String imgUrl = Constants.IMG_SERVER_PATH + item.getImgurl().trim();
            Logger.w(imgUrl);
            ImageLoadProxy.getInstance()
                    .load(new ImageLoadConfiguration.Builder(funsharingHomeActivity)
                            .defaultImageResId(R.mipmap.icon_default_header_circle)
                            .url(imgUrl)
                            .imageView((ImageView) helper.getView(R.id.iv_header)).build());
        } else {
            ImageLoadProxy.getInstance()
                    .load(new ImageLoadConfiguration.Builder(funsharingHomeActivity)
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


////        //评论列表
        RecyclerView  recyclerViewComment= helper.getView(R.id.recyclerview_comment);
        List<TroubleListBean.ListBean.CommentsBean> comments = item.getComments();
        LinearLayoutManager layoutManager = new LinearLayoutManager(funsharingHomeActivity);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerViewComment.setLayoutManager(layoutManager);

        if (comments!=null&&comments.size()>0){
            TroubleCommentAdapter adapter=new TroubleCommentAdapter(R.layout.item_comment,funsharingHomeActivity);
            adapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN); //切换动画
            adapter.setEnableLoadMore(false);
            adapter.setNewData(comments);
            adapter.setOnItemClickListener(funsharingHomeActivity.getCommentItemListener());
            adapter.setOnItemChildClickListener(funsharingHomeActivity.getCommentItemListener());

            recyclerViewComment.setAdapter(adapter);
        }else{
            TroubleCommentAdapter adapter=new TroubleCommentAdapter(R.layout.item_comment,funsharingHomeActivity);
            recyclerViewComment.setAdapter(adapter);
        }

    }
}
