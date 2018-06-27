package com.itislevel.lyl.utils.update;

/**
 * **********************
 * 功 能:文件下载进度对象
 * 创建人:itisi
 * 邮  箱:itisivip@qq.com
 * 创建时间:2017/7/28 16:33
 * 修改人:itisi
 * 修改时间: 2017/7/28 16:33
 * 修改内容:itisi
 * *********************
 */

public class DownloadProgress {
    /**
     * 文件总的大小
     */
    private long mTotal;
    /**
     * 当前进度
     */
    private long mProgress;

    public DownloadProgress(long total, long progress) {
        mTotal = total;
        mProgress = progress;
    }

    /**
     * 获取文件大小
     * @return
     */
    public long getTotal() {
        return mTotal;
    }

    public void setTotal(long total) {
        mTotal = total;
    }

    /**
     * 获取当前下载进度
     * @return
     */
    public long getProgress() {
        return mProgress;
    }

    public void setProgress(long progress) {
        mProgress = progress;
    }

    /**
     * 当前是否已下载完成
     * @return
     */
    public boolean isDownloadFinished(){
        return mTotal==mProgress;
    }
}
