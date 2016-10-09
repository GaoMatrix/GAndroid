package com.gao.android.model;

/**
 * Created by GaoMatrix on 2016/10/9.
 */
public class UpdateInfo {

    /**
     * appName : update
     * packageName : com.gao.android.update
     * versionCode : 10
     * versionName : 1.1.8
     * clearData : true
     * mandatoryUpdate : false
     * apkUrl : http://192.168.12.7/Update-debug.apk
     * fileSize : 20031715
     * md5 : b8d0b00febf22665a04c53d2bf26a2cf
     * changeLog : 1、修复bug 2、优化性能
     * updateTips : 更新提示
     */

    private String appName;
    private String packageName;
    private int versionCode;
    private String versionName;
    private boolean clearData;
    private boolean mandatoryUpdate;
    private String apkUrl;
    private int fileSize;
    private String md5;
    private String changeLog;
    private String updateTips;

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public int getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(int versionCode) {
        this.versionCode = versionCode;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public boolean isClearData() {
        return clearData;
    }

    public void setClearData(boolean clearData) {
        this.clearData = clearData;
    }

    public boolean isMandatoryUpdate() {
        return mandatoryUpdate;
    }

    public void setMandatoryUpdate(boolean mandatoryUpdate) {
        this.mandatoryUpdate = mandatoryUpdate;
    }

    public String getApkUrl() {
        return apkUrl;
    }

    public void setApkUrl(String apkUrl) {
        this.apkUrl = apkUrl;
    }

    public int getFileSize() {
        return fileSize;
    }

    public void setFileSize(int fileSize) {
        this.fileSize = fileSize;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public String getChangeLog() {
        return changeLog;
    }

    public void setChangeLog(String changeLog) {
        this.changeLog = changeLog;
    }

    public String getUpdateTips() {
        return updateTips;
    }

    public void setUpdateTips(String updateTips) {
        this.updateTips = updateTips;
    }

    @Override
    public String toString() {
        return "UpdateInfo{" +
                "appName='" + appName + '\'' +
                ", packageName='" + packageName + '\'' +
                ", versionCode=" + versionCode +
                ", versionName='" + versionName + '\'' +
                ", clearData=" + clearData +
                ", mandatoryUpdate=" + mandatoryUpdate +
                ", apkUrl='" + apkUrl + '\'' +
                ", fileSize=" + fileSize +
                ", md5='" + md5 + '\'' +
                ", changeLog='" + changeLog + '\'' +
                ", updateTips='" + updateTips + '\'' +
                '}';
    }
}
