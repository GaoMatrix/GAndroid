package com.gao.android.update.pojo;


public class UpdateInfo {

    /**
     * appName : 升级程序
     * packageName : com.gao.android.update
     * versionCode : 10
     * versionName : 1.1.8
     * clearData : true
     * mandatoryUpdate : false
     * apkUrl : http://t.tongshuai.com/res/download/launcher_update/Launcher_1.1.8_XiaoHe_201510261001.apk
     * fileSize : 20031715
     * md5: C7:FE:DF:BC:99:5D:A6:CA:C1:A8:A2:BD:99:95:89:CC
     * changeLog : 1、修复bug 2、优化性能
     * updateTips : 更新提示
     */

    private String appName;
    private String packageName;
    private int versionCode;
    private String versionName;
    /**
     * 是否清除缓存数据
     */
    private boolean clearData;
    /**
     * 是否强制升级
     */
    private boolean mandatoryUpdate;
    private String apkUrl;
    private int fileSize;
    private String md5;
    private String changeLog;
    /**
     * 升级提示
     */
    private String updateTips;

    public UpdateInfo() {

    }

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

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }
}
