package com.gao.android.update.utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

public class AppUtil {

    /**
     * 获取当前app版本
     *
     * @return
     * @throws PackageManager.NameNotFoundException
     */
    public static PackageInfo getPackageInfo(Context context) {
        PackageInfo packageInfo = null;
        if (context != null) {
            try {
                 packageInfo = context.getPackageManager().getPackageInfo(
                        context.getPackageName(), 0);
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
        }
        return packageInfo;
    }

    /**
     * 安装apk
     * @param context
     * @param filePath
     */
    public static void installApk(Context context, String filePath) {
        if (context != null) {
            Uri data = Uri.parse("file://" + filePath);
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setDataAndType(data, "application/vnd.android.package-archive");
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);
        } else {
            Log.e("NullPointerException", "The context must not be null.");
        }

    }

    /**
     * 检测下载的apk的包名和应用的包名是否一致
     * @param context
     * @param apkPath
     * @return
     */
    public static boolean checkApkPackageName(Context context, String apkPath) {
        String apkName = getAPKPackageName(context, apkPath);
        String appName = getAppPackageName(context);
        if (apkName.equals(appName)) {
            Log.i("UpdateFun TAG", "apk检验:包名相同,安装apk");
            return true;
        } else {
            Log.i("UpdateFun TAG",
                    String.format("apk检验:包名不同。该app包名:%s，apk包名:%s", appName, apkName));
            Toast.makeText(context, "apk检验:包名不同,不进行安装,原因可能是运营商劫持", Toast.LENGTH_LONG).show();
            return false;
        }
    }

    public static String getAppName(Context context) {
        String appName = "";
        try {
            PackageInfo pi = context.getPackageManager()
                    .getPackageInfo(context.getPackageName(), 0);
            appName = pi.applicationInfo.loadLabel(context.getPackageManager()).toString();
            if (appName == null || appName.length() <= 0) {
                return "";
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return appName;
    }

    public static String getAppVersionName(Context context) {
        String versionName = "";
        try {
            PackageInfo pi = context.getPackageManager()
                    .getPackageInfo(context.getPackageName(), 0);
            versionName = pi.versionName;
            if (versionName == null || versionName.length() <= 0) {
                return "";
            }
        } catch (Exception e) {
            Log.e("VersionInfo", "Exception", e);
        }
        return versionName;
    }

    public static String getAppPackageName(Context context) {
        return context.getPackageName();
    }

    public static Drawable getAppIcon(Context context) {
        try {
            PackageManager pm = context.getPackageManager();
            ApplicationInfo info = pm.getApplicationInfo(context.getPackageName(), 0);
            return info.loadIcon(pm);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();

        }
        return null;
    }

    public static String getAPKPackageName(Context context, String apkPath) {
        PackageManager pm = context.getPackageManager();
        PackageInfo info = pm.getPackageArchiveInfo(apkPath, PackageManager.GET_ACTIVITIES);
        if (info != null) {
            ApplicationInfo appInfo = info.applicationInfo;
            return appInfo.packageName;
        }
        return null;
    }

}
