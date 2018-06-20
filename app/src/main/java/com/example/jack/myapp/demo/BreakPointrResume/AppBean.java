package com.example.jack.myapp.demo.BreakPointrResume;

import java.io.Serializable;

/**
 * Created by lcy on 2018/6/10.
 */

public class AppBean implements Serializable {

    /**
     * appName : QQ
     * apkUrl : http://imtt.dd.qq.com/16891/2356E64D93524FB7A7DC0528DA06397D.apk
     * apkPicUrl : http://pp.myapp.com/ma_icon/0/icon_6633_1528385460/96
     */

    private String appName;
    private String apkUrl;
    private String apkPicUrl;


    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getApkUrl() {
        return apkUrl;
    }

    public void setApkUrl(String apkUrl) {
        this.apkUrl = apkUrl;
    }

    public String getApkPicUrl() {
        return apkPicUrl;
    }

    public void setApkPicUrl(String apkPicUrl) {
        this.apkPicUrl = apkPicUrl;
    }
}
