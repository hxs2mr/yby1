package com.itislevel.lyl.utils.update;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.v4.content.FileProvider;


import com.itislevel.lyl.app.App;
import com.itislevel.lyl.utils.rxbus.RxBus;

import java.io.File;
import java.io.InputStream;
import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * **********************
 * 功 能:APP升级工具类
 * 创建人:itisi
 * 邮  箱:itisivip@qq.com
 * 创建时间:2017/7/28 16:32
 * 修改人:itisi
 * 修改时间: 2017/7/28 16:32
 * 修改内容:itisi
 * *********************
 */

public class RxUpdateUtil {
    private static final String MIME_TYPE_APK = "application/vnd.android.package-archive";

    public RxUpdateUtil() {
    }

    /**
     * 监听下载进度
     *
     * @return
     */
    public static Observable<DownloadProgress> getDownloadProgressObservable() {
        return RxBus.getInstance().getDownloadObservable();
    }

    /**
     * apk 是否已下载完成,如果已完成则直接安装
     *
     * @param version
     * @return
     */
    public static boolean isApkDownloaded(String version) {
        File apkFile = StorageUtil.getApkFile(version);
        if (apkFile.exists()) {
            installApp(apkFile);
            return true;
        }
        return false;
    }

    public static Observable<File>downloadApkFile(final String url, final String version){
        return Observable.defer(new Callable<ObservableSource<InputStream>>() {
            @Override
            public ObservableSource<InputStream> call() throws Exception {
                try {
                    return Observable.just(Engine.getInstance().getDownloadApi().downloadFile(url).execute().body().byteStream());
                } catch (Exception e) {
                    return Observable.error(e);
                }
            }
        }).map(new Function<InputStream, File>() {
            @Override
            public File apply(@NonNull InputStream inputStream) throws Exception {
                return StorageUtil.saveApk(inputStream, version);
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 安装APP
     *
     * @param apkFile APP本地路径
     */
    public static void installApp(File apkFile) {
        Intent installApkIntent = new Intent();
        installApkIntent.setAction(Intent.ACTION_VIEW);
        installApkIntent.addCategory(Intent.CATEGORY_DEFAULT);
        installApkIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
            installApkIntent.setDataAndType(FileProvider.getUriForFile(App.getInstance(),
                    PermissionUtil.getFileProviderAuthority(), apkFile), MIME_TYPE_APK);
            installApkIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        } else {
            installApkIntent.setDataAndType(Uri.fromFile(apkFile), MIME_TYPE_APK);
        }
        if (App.getInstance().getPackageManager().queryIntentActivities(installApkIntent, 0).size() > 0) {
            App.getInstance().startActivity(installApkIntent);
        }
    }

    /**
     * 删除旧的的apk
     */
    public static void delOldApk() {
        File apkDir = StorageUtil.getApkFileDir();
        if (apkDir == null || apkDir.listFiles() == null || apkDir.listFiles().length == 0) {
            return;
        }
        // 删除文件
        StorageUtil.deleteFile(apkDir);
    }
}
