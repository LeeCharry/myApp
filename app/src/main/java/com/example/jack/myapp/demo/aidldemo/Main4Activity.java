package com.example.jack.myapp.demo.aidldemo;

import android.Manifest;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.example.jack.myapp.R;
import com.example.jack.myapp.demo.circleProgressbar.CircleProgressBar;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.Map;

/**
 * 自定义圆环进度条 &&  微信授权登录
 */
public class Main4Activity extends AppCompatActivity {
    private Button btnWx;
    private CircleProgressBar circleProgressBar;
    private SeekBar seekbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        if(Build.VERSION.SDK_INT>=23){
            String[] mPermissionList = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.CALL_PHONE,Manifest.permission.READ_LOGS,Manifest.permission.READ_PHONE_STATE, Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.SET_DEBUG_APP,Manifest.permission.SYSTEM_ALERT_WINDOW,Manifest.permission.GET_ACCOUNTS,Manifest.permission.WRITE_APN_SETTINGS};
            ActivityCompat.requestPermissions(this,mPermissionList,123);
        }

        initView();


         findViewById(R.id.btn_wx).setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {

                 // 注意：在微信授权的时候，必须传递appSecret

                 /**
                  * 微信登录权限
                  */
//                 mController = UMServiceFactory.getUMSocialService("com.umeng.login");
//                 // 添加微信平台，APP_ID、APP_SECRET都是在微信开放平台，移动应用通过审核后获取到的
//                 UMWXHandler wxHandler = new UMWXHandler(Main4Activity.this, APP_ID, APP_SECRET);
//                 //微信授权页面每次都出现，便于切换微信用户
//                 wxHandler.setRefreshTokenAvailable(false);
//                 wxHandler.addToSocialSDK();

//                 Messenger messenger = new Messenger();
//                 try {
//                     messenger.send(new Message());
//                 } catch (RemoteException e) {
//                     e.printStackTrace();
//                 }
                 wxLogin();

//                 final SendAuth.Req req = new SendAuth.Req();
//                 req.scope = "snsapi_userinfo";
//                 req.state = "diandi_wx_login";
//                 BaseApp.mWxApi.sendReq(req);
////                 public void wxLogin() {
////                     if (!MyApp.mWxApi.isWXAppInstalled()) {
//                         UIUtils.showToast("您还未安装微信客户端");
//                         return;
//                     }
//                 }
             }
         });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

    }

    private void initView() {
        btnWx = findViewById(R.id.btn_wx);
        circleProgressBar = findViewById(R.id.circle_progress_bar);
        seekbar = findViewById(R.id.seekbar);
        seekbar.setMax(100);

        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (progress >= 0 && progress <= 100) {
                    circleProgressBar.setProgress(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        UMShareAPI.get(this).release();
    }

    private void wxLogin() {
//        UMShareAPI.get(Main4Activity.this).doOauthVerify();
        UMShareAPI.get(this).doOauthVerify(this, SHARE_MEDIA.SINA, new UMAuthListener() {
            @Override
            public void onStart(SHARE_MEDIA share_media) {
                ToastUtils.showShort("授权开始");
            }

            @Override
            public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
                ToastUtils.showShort("授权成功");
                LogUtils.a("lcy",map.get("profile_image_url").toString());
                LogUtils.a("lcy",map.get("gender").toString());
                LogUtils.a("lcy",map.get("screen_name").toString());
                LogUtils.a("lcy",map.get("unionid").toString());
            }

            @Override
            public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {
                ToastUtils.showShort("授权失败");
                LogUtils.a("lcy",throwable.getMessage().toString());
            }

            @Override
            public void onCancel(SHARE_MEDIA share_media, int i) {
                ToastUtils.showShort("授权取消");
            }
        });
    }
//        UMShareAPI.get(this).getPlatformInfo(Main4Activity.this, SHARE_MEDIA.WEIXIN, new UMAuthListener() {
//            @Override
//            public void onStart(SHARE_MEDIA share_media) {
//                ToastUtils.showShort("授权开始");
//            }
//
//            @Override
//            public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
//                ToastUtils.showShort("授权成功");
//            }
//
//            @Override
//            public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {
//                ToastUtils.showShort("授权失败");
//                LogUtils.a("lcy",throwable.getMessage().toString());
//            }
//
//            @Override
//            public void onCancel(SHARE_MEDIA share_media, int i) {
//                ToastUtils.showShort("授权取消");
//            }
//        });
//    }
}
