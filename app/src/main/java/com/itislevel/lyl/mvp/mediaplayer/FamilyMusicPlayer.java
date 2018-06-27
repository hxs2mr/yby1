package com.itislevel.lyl.mvp.mediaplayer;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.util.Log;
import android.widget.SeekBar;


import java.io.IOException;

public class FamilyMusicPlayer implements MediaPlayer.OnBufferingUpdateListener,MediaPlayer.OnCompletionListener,
        MediaPlayer.OnPreparedListener {

    public MediaPlayer mediaPlayer; // 媒体播放器



    // 初始化播放器
    public FamilyMusicPlayer() {
        super();
        try {
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);// 设置媒体流类型
            mediaPlayer.setOnBufferingUpdateListener(this);
            mediaPlayer.setOnPreparedListener(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void play() {
        mediaPlayer.start();
    }
    public void playUrl(String url) {
        try {
            mediaPlayer.reset();
            mediaPlayer.setDataSource(url); // 设置数据源
            mediaPlayer.prepare(); // prepare自动播放
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 暂停
    public void start() {
        mediaPlayer.start();
    }


    // 暂停
    public void pause() {
        mediaPlayer.pause();
    }
    // 停止
    public void stop() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    //播放准备
    @Override
    public void onPrepared(MediaPlayer mediaPlayer) {
        mediaPlayer.start();
        Log.e("mediaPlayer", "onPrepared");
    }
    // 播放完成
    @Override
    public void onCompletion(MediaPlayer mediaPlayer) {
        Log.e("mediaPlayer", "onCompletion");
    }
    /**
     * 缓冲更新
     */
    @Override
    public void onBufferingUpdate(MediaPlayer mp, int percent) {
    }

}
