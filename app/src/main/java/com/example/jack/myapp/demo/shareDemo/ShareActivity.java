package com.example.jack.myapp.demo.shareDemo;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.example.jack.myapp.R;

import java.io.File;
import java.util.List;

/**
 * Created by lcy on 2018/8/10.
 */

public class ShareActivity extends AppCompatActivity {
    private Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);
        this.mContext = this;
         findViewById(R.id.btn0).setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.mipmap.ic_banner);
                 shareWechatFriend(mContext,bitmap,null);
             }
         });

        findViewById(R.id.btn1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.mipmap.ic_banner);
                shareWechatFriend(mContext,bitmap,null);
            }
        });
        findViewById(R.id.btn2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.mipmap.ic_banner);
                shareWechatFriend(mContext,bitmap,null);
            }
        });
        findViewById(R.id.btn3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.mipmap.ic_banner);
                shareWechatFriend(mContext,bitmap,null);
            }
        });
        findViewById(R.id.btn4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.mipmap.ic_banner);
                shareWechatFriend(mContext,bitmap,null);
            }
        });
    }

    /**
     * 直接分享纯文本内容至QQ好友
     *
     * @param mContext
     * @param content
     */
    public static void shareQQ(Context mContext, String content) {
        if (PlatformUtil.isInstallApp(mContext, PlatformUtil.PACKAGE_MOBILE_QQ)) {
            Intent intent = new Intent("android.intent.action.SEND");
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_SUBJECT, "分享");
            intent.putExtra(Intent.EXTRA_TEXT, content);
            intent.setData(Uri.parse("ssss"));
            intent.putExtra("ss","ss");

            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setComponent(new ComponentName("com.tencent.mobileqq", "com.tencent.mobileqq.activity.JumpActivity"));
            mContext.startActivity(intent);
        } else {
            Toast.makeText(mContext, "您需要安装QQ客户端", Toast.LENGTH_LONG).show();
        }
//
//        new ShareAction(MainActivity.this).withText("hello")
//                .setDisplayList(SHARE_MEDIA.SINA,SHARE_MEDIA.QQ,SHARE_MEDIA.WEIXIN)
//                .setCallback(umShareListener).open();
    }

//    private UMShareListener shareListener = new UMShareListener() {
//        /**
//         * @descrption 分享开始的回调
//         * @param platform 平台类型
//         */
//        @Override
//        public void onStart(SHARE_MEDIA platform) {
//        }

    /**
     * 分享图片给QQ好友
     *
     * @param bitmap
     */
    public void shareImageToQQ(Bitmap bitmap) {
        if (PlatformUtil.isInstallApp(mContext, PlatformUtil.PACKAGE_MOBILE_QQ)) {
            try {
                Uri uriToImage = Uri.parse(MediaStore.Images.Media.insertImage(
                        mContext.getContentResolver(), bitmap, null, null));
                Intent shareIntent = new Intent();
                shareIntent.setAction(Intent.ACTION_SEND);
                shareIntent.putExtra(Intent.EXTRA_STREAM, uriToImage);
                shareIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                shareIntent.setType("image/*");
                // 遍历所有支持发送图片的应用。找到需要的应用
                ComponentName componentName = new ComponentName("com.tencent.mobileqq", "com.tencent.mobileqq.activity.JumpActivity");

                shareIntent.setComponent(componentName);
                // mContext.startActivity(shareIntent);
                mContext.startActivity(Intent.createChooser(shareIntent, "Share"));
            } catch (Exception e) {
//            ContextUtil.getInstance().showToastMsg("分享图片到**失败");
            }
        }
    }

    public static void shareImageToQQZone(Context mContext, String photoPath) {
        if (PlatformUtil.isInstallApp(mContext, PlatformUtil.PACKAGE_QZONE)) {
//            photoPath = Environment.getExternalStorageDirectory() + "/UmeWeb/Bitmap/1.png";
            File file = new File(photoPath);
            if (!file.exists()) {
                String tip = "文件不存在";
                Toast.makeText(mContext, tip + " path = " + photoPath, Toast.LENGTH_LONG).show();
                return;
            }
            Intent intent = new Intent();
//            ComponentName componentName = new ComponentName("com.tencent.mobileqq","cooperation.qzone.QzonePublishMoodProxyActivity");// 无用代码
            ComponentName componentName = new ComponentName("com.qzone", "com.qzonex.module.operation.ui.QZonePublishMoodActivity");
            intent.setComponent(componentName);
            intent.setAction("android.intent.action.SEND");
            intent.setType("image/*");
            intent.putExtra(Intent.EXTRA_TEXT, "I'm so tired!!");//  分享文本
            intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(file));// 分享图片
            mContext.startActivity(intent);
        }
    }

    /**
     * 直接分享图片到微信好友
     *
     * @param context
     * @param picFile
     */
    public void shareWechatFriend(Context context, Bitmap  bitmap, File picFile) {
        if (PlatformUtil.isInstallApp(mContext, PlatformUtil.PACKAGE_WECHAT)) {
            Intent intent = new Intent();
            ComponentName cop = new ComponentName("com.tencent.mm", "com.tencent.mm.ui.tools.ShareImgUI");
            intent.setComponent(cop);
            Uri uriToImage = Uri.parse(MediaStore.Images.Media.insertImage(
                    mContext.getContentResolver(), bitmap, null, null));
            intent.putExtra(Intent.EXTRA_STREAM,uriToImage);
            intent.setAction(Intent.ACTION_SEND);
            intent.setType("image/*");

            if (picFile != null) {
                if (picFile.isFile() && picFile.exists()) {
                    Uri uri;
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        uri = FileProvider.getUriForFile(context, PlatformUtil.AUTHORITY, picFile);
                    } else {
                        uri = Uri.fromFile(picFile);
                    }
                    intent.putExtra(Intent.EXTRA_STREAM, uri);
                }
            }
//            intent.putExtra("Kdescription", !TextUtils.isEmpty(content) ? content : "");
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            // context.startActivity(intent);
            context.startActivity(Intent.createChooser(intent, "Share"));
        } else {
            Toast.makeText(context, "您需要安装微信客户端", Toast.LENGTH_LONG).show();
        }
    }

    /**
     * 直接分享文本和图片到微信朋友圈
     *
     * @param context
     * @param content
     */
    public static void shareWechatMoment(Context context, String content, File picFile) {
        if (PlatformUtil.isInstallApp(context, PlatformUtil.PACKAGE_WECHAT)) {
            Intent intent = new Intent();
            //分享精确到微信的页面，朋友圈页面，或者选择好友分享页面
            ComponentName comp = new ComponentName("com.tencent.mm", "com.tencent.mm.ui.tools.ShareToTimeLineUI");
            intent.setComponent(comp);
//            intent.setAction(Intent.ACTION_SEND_MULTIPLE);// 分享多张图片时使用
            intent.setAction(Intent.ACTION_SEND);
            intent.setType("image/*");
            //添加Uri图片地址--用于添加多张图片
            //ArrayList<Uri> imageUris = new ArrayList<>();
            //intent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, imageUris);
            if (picFile != null) {
                if (picFile.isFile() && picFile.exists()) {
                    Uri uri;
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        uri = FileProvider.getUriForFile(context, PlatformUtil.AUTHORITY, picFile);
                    } else {
                        uri = Uri.fromFile(picFile);
                    }
                    intent.putExtra(Intent.EXTRA_STREAM, uri);
                }
            }
            intent.putExtra("Kdescription", !TextUtils.isEmpty(content) ? content : "");
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        } else {
            Toast.makeText(context, "您需要安装微信客户端", Toast.LENGTH_LONG).show();
        }
    }
    public static void shareToSinaFriends(Context context, String photoPath) {
        if (!PlatformUtil.isInstallApp(context, PlatformUtil.PACKAGE_SINA)) {
            Toast.makeText(context, "新浪微博没有安装！", Toast.LENGTH_SHORT).show();
            return;
        }
        File file = new File(photoPath);
        if (!file.exists()) {
            String tip = "文件不存在";
            Toast.makeText(context, tip + " path = " + photoPath, Toast.LENGTH_LONG).show();
            return;
        }

        Intent intent = new Intent(Intent.ACTION_SEND);
        // 使用以下两种type有一定的区别，"text/plain"分享给指定的粉丝或好友 ；"image/*"分享到微博内容,下面这两个设置type的代码必须写在查询语句前面，否则找不到带有分享功能的应用。
//        intent.setType("text/plain");
        intent.setType("image/*");// 分享文本|文本+图片|图片 到微博内容时使用
        PackageManager packageManager = context.getPackageManager();
        List<ResolveInfo> matchs = packageManager.queryIntentActivities(intent,PackageManager.MATCH_DEFAULT_ONLY);
        ResolveInfo resolveInfo = null;
        for (ResolveInfo each : matchs) {
            String pkgName = each.activityInfo.applicationInfo.packageName;
            if ("com.sina.weibo".equals(pkgName)) {
                resolveInfo = each;
                break;
            }
        }
        intent.setClassName(PlatformUtil.PACKAGE_SINA, resolveInfo.activityInfo.name);// 这里在使用resolveInfo的时候需要做判空处理防止crash
        intent.putExtra(Intent.EXTRA_TEXT, "Test Text String !!");
        intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(file));
        context.startActivity(intent);
    }

}
