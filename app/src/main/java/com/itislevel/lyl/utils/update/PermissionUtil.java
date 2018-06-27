package com.itislevel.lyl.utils.update;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.support.v4.content.FileProvider;
import android.text.TextUtils;


import com.itislevel.lyl.app.App;

import java.io.File;
import java.util.List;

/**
 ***********************
 * 功 能:必填必填必填必填必填必填
 * 创建人:itisi
 * 邮  箱:itisivip@qq.com
 * 创建时间:2017/7/28 16:56
 * 修改人:itisi
 * 修改时间: 2017/7/28 16:56
 * 修改内容:itisi
 * *********************
 */
class PermissionUtil {
    private PermissionUtil() {
    }

    /**
     * 获取FileProvider的auth
     *
     * @return
     */
    static String getFileProviderAuthority() {
        try {
            for (ProviderInfo provider : App.getInstance().getPackageManager().getPackageInfo(App.getInstance().getPackageName(), PackageManager.GET_PROVIDERS).providers) {
                if (FileProvider.class.getName().equals(provider.name) && provider.authority.endsWith(".bga_update.file_provider")) {
                    return provider.authority;
                }
            }
        } catch (PackageManager.NameNotFoundException ignore) {
        }
        return null;
    }

    /**
     * 授予对某个意向的 uri 授权
     *
     * @param intent 要授予访问某个 uri 的 intent 意向
     * @param uri    要授予权限的 uri
     */
    static void grantUriPermission(Intent intent, Uri uri) {
        List<ResolveInfo> resolvedIntentActivities = App.getInstance().getPackageManager().queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
        for (ResolveInfo resolvedIntentInfo : resolvedIntentActivities) {
            String packageName = resolvedIntentInfo.activityInfo.packageName;
            App.getInstance().grantUriPermission(packageName, uri, Intent.FLAG_GRANT_WRITE_URI_PERMISSION | Intent.FLAG_GRANT_READ_URI_PERMISSION);
        }
    }

    /**
     * 撤销对某个文件夹的读写权限
     *
     * @param dir
     */
    static void revokeUriPermission(File dir) {
        if (dir == null || dir.listFiles() == null || dir.listFiles().length == 0) {
            return;
        }

        String fileProviderAuthority = getFileProviderAuthority();
        if (TextUtils.isEmpty(fileProviderAuthority)) {
            return;
        }

        for (File file : dir.listFiles()) {
            revokeUriPermission(FileProvider.getUriForFile(App.getInstance(), fileProviderAuthority, file));
        }
    }

    /**
     * 撤销对某个 uri 的读写权限
     *
     * @param uri
     */
    static void revokeUriPermission(Uri uri) {
        if (uri == null) {
            return;
        }

        App.getInstance().revokeUriPermission(uri, Intent.FLAG_GRANT_WRITE_URI_PERMISSION | Intent.FLAG_GRANT_READ_URI_PERMISSION);
    }
}
