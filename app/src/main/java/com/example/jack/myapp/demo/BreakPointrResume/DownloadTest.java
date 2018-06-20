package com.example.jack.myapp.demo.BreakPointrResume;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

import com.example.jack.myapp.R;

import java.io.ByteArrayInputStream;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.List;

/**
 * Created by lcy on 2018/6/20.
 */

public class DownloadTest extends Activity implements View.OnClickListener {
    private Intent downloadIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.down_file);
        Intent intent = new Intent(this, DownloadService.class);
        startService(intent);
        bindListener();
        getSingInfo();
        downloadIntent = new Intent(DownloadTest.this, DownloadService.class);

    }

    void bindListener() {
        findViewById(R.id.btn_load_btn_one).setOnClickListener(this);
        findViewById(R.id.btn_load_btn_two).setOnClickListener(this);
        findViewById(R.id.btn_load_btn_three).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_load_btn_one:
                downloadIntent.putExtra(DownloadService.URLSTR, "http://m.appchina.com/market/e/882602/0/16/44DFFDF6E89D0CC2F5CB41CE041E9BB7/packagename.apk?refererPage=m.cherry.soft_list");
                downloadIntent.putExtra(DownloadService.NOTIFICATIONID, 351);
                downloadIntent.putExtra(DownloadService.NAME, "百度知道");
                startService(downloadIntent);
//                DownloadService
//                        .downNewFile(
//                                "http://m.appchina.com/market/e/882602/0/16/44DFFDF6E89D0CC2F5CB41CE041E9BB7/packagename.apk?refererPage=m.cherry.soft_list",
//                                351, "百度知道");
                break;
            case R.id.btn_load_btn_two:
                downloadIntent.putExtra(DownloadService.URLSTR, "http://m.appchina.com/market/e/886657/feature/1/360F5D5A76A3E0E397451CE9EA503DE6/com.sogou.novel?refererPage=m.cherry.soft_main");
                downloadIntent.putExtra(DownloadService.NOTIFICATIONID, 1071);
                downloadIntent.putExtra(DownloadService.NAME, "搜狗小说");
                startService(downloadIntent);
//                DownloadService
//                        .downNewFile(
//                                "http://m.appchina.com/market/e/886657/feature/1/360F5D5A76A3E0E397451CE9EA503DE6/com.sogou.novel?refererPage=m.cherry.soft_main",
//                                1071, "搜狗小说");
                break;
            case R.id.btn_load_btn_three:
                downloadIntent.putExtra(DownloadService.URLSTR, "http://m.appchina.com/market/e/885495/feature/2/360F5D5A76A3E0E397451CE9EA503DE6/com.king2.yyh?refererPage=m.cherry.soft_main");
                downloadIntent.putExtra(DownloadService.NOTIFICATIONID, 1072);
                downloadIntent.putExtra(DownloadService.NAME, "君王2(新服开启)");
                startService(downloadIntent);
//               DownloadService
//                        .downNewFile(
//                                "http://m.appchina.com/market/e/885495/feature/2/360F5D5A76A3E0E397451CE9EA503DE6/com.king2.yyh?refererPage=m.cherry.soft_main",
//                                1072, "君王2(新服开启)");
                break;
            default:
                break;
        }
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            System.exit(0);
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    public void getSingInfo() {
        try {
            PackageInfo packageInfo = getPackageManager().getPackageInfo(
                    "com.air.sz", PackageManager.GET_SIGNATURES);
            Signature[] signs = packageInfo.signatures;
            Signature sign = signs[0];
            parseSignature(sign.toByteArray());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void parseSignature(byte[] signature) {
        try {
            CertificateFactory certFactory = CertificateFactory
                    .getInstance("X.509");
            X509Certificate cert = (X509Certificate) certFactory
                    .generateCertificate(new ByteArrayInputStream(signature));
            String pubKey = cert.getPublicKey().toString();
            String signNumber = cert.getSerialNumber().toString();
            System.out.println("signName:" + cert.getSigAlgName());
            System.out.println("pubKey:" + pubKey);
            System.out.println("signNumber:" + signNumber);
            System.out.println("subjectDN:" + cert.getSubjectDN().toString());
            List<String> extendKey = cert.getExtendedKeyUsage();
            System.out.println("subjectDN_one:" + cert.getSubjectAlternativeNames());
            System.out.println("subjectDN_two:" + cert.getSubjectX500Principal());
            System.out.println("subjectDN_three:" + cert.getExtensionValue("SubjectKeyIdentifier"));
        } catch (CertificateException e) {
            e.printStackTrace();
        }
    }
}
