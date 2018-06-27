package com.itislevel.lyl.adapter;

import android.text.SpannableString;
import android.text.TextUtils;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.itislevel.lyl.R;
import com.itislevel.lyl.app.App;
import com.itislevel.lyl.app.Constants;
import com.itislevel.lyl.mvp.model.bean.TroubleAdviserBean;
import com.itislevel.lyl.mvp.model.bean.TroubleAdviserMyBean;
import com.itislevel.lyl.mvp.ui.chatkeyboardview.EmotionUtils;
import com.itislevel.lyl.mvp.ui.chatkeyboardview.SpanStringUtils;
import com.itislevel.lyl.utils.DateUtil;
import com.lzy.ninegrid.ImageInfo;
import com.lzy.ninegrid.NineGridView;
import com.lzy.ninegrid.preview.NineGridViewClickAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * desc:功能说明必写
 * user:itisi
 * date:2017/12/19.14:30
 * path:com.itislevel.lyl.adapter.TroubleAdviserAdapter
 **/
public class TroubleAdviserMyAdapter extends BaseQuickAdapter<TroubleAdviserMyBean.ListBean, BaseViewHolder> {
    public TroubleAdviserMyAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, TroubleAdviserMyBean.ListBean item) {


        int index=0;

        List<TroubleAdviserMyBean.ListBean> data = this.getData();
        for (int i=0;i<data.size();i++){
            if (data.get(i).getQuestionid()==item.getQuestionid()){
                index=i;
                break;
            }
        }

        helper.setText(R.id.tv_time_order, (index+1)+". "+DateUtil.timeSpanToDate(item.getCreatedtime()));
//        helper.setText(R.id.tv_problem, item.getContent());

        TextView tvConent=helper.getView(R.id.tv_problem);
        SpannableString emotionContent = SpanStringUtils.getEmotionContent(EmotionUtils
                .EMOTION_CLASSIC_TYPE, mContext, tvConent, item.getContent());
        tvConent.setText(emotionContent);


        List<TroubleAdviserMyBean.ListBean.CommentsBean> comments = item.getComments();
        if (comments!=null&&comments.size()>0){
//            helper.setText(R.id.tv_answer, comments.get(0).getComment());
            TextView tv_answer=helper.getView(R.id.tv_answer);
            SpannableString emotionContent1 = SpanStringUtils.getEmotionContent(EmotionUtils
                    .EMOTION_CLASSIC_TYPE, mContext, tv_answer, comments.get(0).getComment());
            tv_answer.setText(emotionContent1);

//            tv_answer_temp tv_answer
            helper.setVisible(R.id.tv_answer_temp,true);
            helper.setVisible(R.id.tv_answer,true);

        }else{
            helper.setText(R.id.tv_answer, "");
            helper.setVisible(R.id.tv_answer_temp,false);
            helper.setVisible(R.id.tv_answer,false);

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


    }
}
