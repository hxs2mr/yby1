package com.itislevel.lyl.mvp.ui.test;

import android.content.res.Configuration;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.itislevel.lyl.R;
import com.itislevel.lyl.base.RootActivity;
import com.itislevel.lyl.listener.SampleVideoListener;
import com.itislevel.lyl.mvp.model.bean.AppUpdate;
import com.itislevel.lyl.mvp.model.bean.UserHeaderNickInfo;
import com.itislevel.lyl.mvp.ui.main.MainContract;
import com.itislevel.lyl.mvp.ui.main.MainPresenter;
import com.itislevel.lyl.widget.video.SampleControlVideo;

import java.util.List;

import butterknife.BindView;

public class TestPlayerActivity extends RootActivity<MainPresenter> implements MainContract.View,View.OnClickListener {

//    @BindView(R.id.sample_video)
//    SampleControlVideo video_test;

    private String url = "http://192.168.1.222:8080/itisitest/chjkk.mp4";

    protected boolean isPlay;

    protected boolean isPause;

//    protected OrientationUtils orientationUtils;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_test_player;
    }

    @Override
    protected void initEventAndData() {
        resolveNormalVideoUI();

        initVideoBuilderMode();

    }

    private void resolveNormalVideoUI() {
        //增加title
//        video_test.getTitleTextView().setVisibility(View.GONE);
//        video_test.getBackButton().setVisibility(View.GONE);
    }

    /**
     * 选择普通模式
     */
    public void initVideo() {
//        //外部辅助的旋转，帮助全屏
//        orientationUtils = new OrientationUtils(this, video_test);
//        //初始化不打开外部的旋转
//        orientationUtils.setEnable(false);
//        if (video_test.getFullscreenButton() != null) {
//            video_test.getFullscreenButton().setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    //直接横屏
//                    orientationUtils.resolveByClick();
//                    //第一个true是否需要隐藏actionbar，第二个true是否需要隐藏statusbar
//                    video_test.startWindowFullscreen(TestPlayerActivity.this, true, true);
//
////                    clickForFullScreen(); //点击了全屏
//                }
//            });
//        }
    }

    /**
     * 选择builder模式
     */
    public void initVideoBuilderMode() {
        initVideo();
//        getGSYVideoOptionBuilder().
//                setVideoAllCallBack(new SampleVideoListener())
//                .build(video_test);
    }



//    public GSYVideoOptionBuilder getGSYVideoOptionBuilder() {
//        //内置封面可参考SampleCoverVideo
//        ImageView imageView = new ImageView(this);
//        loadCover(imageView, url);
//        return new GSYVideoOptionBuilder()
//                .setThumbImageView(imageView)
//                .setUrl(url)
//                .setCacheWithPlay(true)
//                .setVideoTitle(" ")
//                .setIsTouchWiget(true)
//                .setRotateViewAuto(false)
//                .setLockLand(false)
//                .setShowFullAnimation(true)//打开动画
//                .setNeedLockFull(true)
//                .setSeekRatio(1);
//    }

    private void loadCover(ImageView imageView, String url) {
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setImageResource(R.mipmap.ic_launcher);

        Glide.with(this)
                .load(url)
                .into(imageView);
    }



    @Override
    public void onBackPressed() {
//        if (orientationUtils != null) {
//            orientationUtils.backToProtVideo();
//        }
//        if (StandardGSYVideoPlayer.backFromWindowFull(this)) {
//            return;
//        }
        super.onBackPressed();
    }


    @Override
    protected void onPause() {
        super.onPause();
//        video_test.getCurrentPlayer().onVideoPause();
//        isPause = true;
    }

    @Override
    protected void onResume() {
        super.onResume();
//        video_test.getCurrentPlayer().onVideoResume();
//        isPause = false;
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        if (isPlay) {
//            video_test.getCurrentPlayer().release();
//        }
//        if (orientationUtils != null)
//            orientationUtils.releaseListener();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        //如果旋转了就全屏
//        if (isPlay && !isPause) {
//            video_test.onConfigurationChanged(this, newConfig, orientationUtils);
//        }
    }

//    @Override
    public void onPrepared(String url, Object... objects) {
//
//        if (orientationUtils == null) {
//            throw new NullPointerException("initVideo() or initVideoBuilderMode() first");
//        }
//        //开始播放了才能旋转和全屏
//        orientationUtils.setEnable(getDetailOrientationRotateAuto());
        isPlay = true;
    }

    /**
     * 是否启动旋转横屏，true表示启动
     * @return true
     */
    public boolean getDetailOrientationRotateAuto() {
        return false;
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void testShowView(String smg) {

    }

    @Override
    public void userInfoById(List<UserHeaderNickInfo> userHeaderNickInfos) {

    }

    @Override
    public void appupdate(AppUpdate appUpdate) {

    }

    @Override
    public void stateEmpty() {

    }

    @Override
    public void stateLoading() {

    }



    @Override
    public void stateSuccess() {

    }
}
