package com.example.jack.myapp.demo.takephoto;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;

import com.blankj.utilcode.util.ToastUtils;
import com.example.jack.myapp.R;
import com.example.jack.myapp.bean.ArticalBean;
import com.flyco.dialog.entity.DialogMenuItem;
import com.flyco.dialog.listener.OnBtnClickL;
import com.flyco.dialog.listener.OnOperItemClickL;
import com.flyco.dialog.widget.ActionSheetDialog;
import com.flyco.dialog.widget.NormalDialog;
import com.flyco.dialog.widget.NormalListDialog;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
//@Route(path = "/takephoto/TakePhotoActivity")
public class TakePhotoActivity extends AppCompatActivity implements View.OnClickListener {
    private static final int PICK_PICTURE = 3;
    private ImageView ivAvatar;
    private Button btnTakePhoto;
    private static final int TAKE_PHOTO = 1;
    private static  final int CROP_PHOTO = 2;
    private Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_take_photo);

        ivAvatar = (ImageView) findViewById(R.id.iv_avatar);
        btnTakePhoto = (Button) findViewById(R.id.btn0);
        btnTakePhoto.setOnClickListener(this);
        findViewById(R.id.btn1).setOnClickListener(this);
        findViewById(R.id.btn2).setOnClickListener(this);
        findViewById(R.id.btn3).setOnClickListener(this);
        findViewById(R.id.btn4).setOnClickListener(this);
        findViewById(R.id.btn5).setOnClickListener(this);

//        PackageManager packageManager = this.getPackageManager();
//        String packageName = this.getPackageName();
//        try {
//            PackageInfo packageInfo = packageManager.getPackageInfo(packageName, 0);
//            int versionCode = packageInfo.versionCode;
//            String versionName = packageInfo.versionName;
//            LogUtils.a("lcy",versionCode+"  "+versionName);
//        } catch (PackageManager.NameNotFoundException e) {
//            e.printStackTrace();
//        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn0:
                showActionSheetDialog();
                break;
            case R.id.btn1:
                showListDialog();
                break;
            case R.id.btn2:
                showNormalDialog();
                break;
            case R.id.btn3:
                showBubbleDialog();
                break;
            case R.id.btn4:
                pictureSelect();
                break;
            case R.id.btn5:
                intent2Main();
                break;
            default:
                break;
        }
    }

    private void intent2Main() {
        ArticalBean articalBean = new ArticalBean();
        ArticalBean.DataBean dataBean = new ArticalBean.DataBean();
        dataBean.setCurPage(0);
        dataBean.setOffset(120);
        dataBean.setPageCount(1090);
        dataBean.setSize(200);
        dataBean.setOver(true);
        articalBean.setErrorCode(-1);
        articalBean.setData(dataBean);
        articalBean.setErrorMsg("错误信息");

        ARouter.getInstance().build("/myapp/MainActivity")
                .withString("string","str")
                .withSerializable("articalBean",articalBean)
                .navigation();
    }

    /**
     * 调用pictureSelector控件
     */
    private void pictureSelect() {
        final String[] items = {"拍照", "相册"};
        final ActionSheetDialog actionSheetDialog = new ActionSheetDialog(TakePhotoActivity.this, items, null);
        actionSheetDialog.isTitleShow(false).show();
        actionSheetDialog.setOnOperItemClickL(new OnOperItemClickL() {
            @Override
            public void onOperItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        PictureSelector.create(TakePhotoActivity.this)
                                .openGallery(PictureMimeType.ofImage())
                                .setOutputCameraPath("/haha")
                                .enableCrop(true)
                                .compress(true)
                                .compressSavePath("/hahaha/")
                                .forResult(5);
                        break;
                    case 1:
                        break;
                }
            }
        });
    }

    private void showNormalDialog() {
        final NormalDialog normalDialog = new NormalDialog(this);
        normalDialog.style(NormalDialog.STYLE_ONE)
                .content("是否确认支付？")
                .titleTextSize(23)
                .show();
        normalDialog.setOnBtnClickL(new OnBtnClickL() {
                                        @Override
                                        public void onBtnClick() {
                                            ToastUtils.showShort("0");
                                            normalDialog.dismiss();
                                        }

                                    },
                new OnBtnClickL() {
                    @Override
                    public void onBtnClick() {
                        ToastUtils.showShort("1");
                        normalDialog.dismiss();

                    }
                });
    }

    private void showListDialog() {
        final ArrayList<DialogMenuItem> mMenuItems = new ArrayList<>();
        mMenuItems.add(new DialogMenuItem("收藏", R.mipmap.ic_launcher));
        mMenuItems.add(new DialogMenuItem("下载", R.mipmap.ic_launcher));
        mMenuItems.add(new DialogMenuItem("分享", R.mipmap.ic_launcher));
        mMenuItems.add(new DialogMenuItem("删除", R.mipmap.ic_launcher));
        mMenuItems.add(new DialogMenuItem("歌手", R.mipmap.ic_launcher));
        mMenuItems.add(new DialogMenuItem("专辑", R.mipmap.ic_launcher));
        final NormalListDialog dialog = new NormalListDialog(this, mMenuItems);
        dialog.title("请选择")//
//                .showAnim(mBasIn)//
//                .dismissAnim(mBasOut)//
                .show();
        dialog.setOnOperItemClickL(new OnOperItemClickL() {
            @Override
            public void onOperItemClick(AdapterView<?> parent, View view, int position, long id) {
                ToastUtils.showShort(mMenuItems.get(position).mOperName);
                dialog.dismiss();
            }
        });
    }
    private void showBubbleDialog() {
        final NormalDialog dialog = new NormalDialog(TakePhotoActivity.this);
        dialog.isTitleShow(false)//
                .bgColor(Color.parseColor("#383838"))//
                .cornerRadius(5)//
                .content("是否确定退出程序?")//
                .contentGravity(Gravity.CENTER)//
                .contentTextColor(Color.parseColor("#ffffff"))//
                .dividerColor(Color.parseColor("#222222"))//
                .btnTextSize(15.5f, 15.5f)//
                .btnTextColor(Color.parseColor("#ffffff"), Color.parseColor("#ffffff"))//
                .btnPressColor(Color.parseColor("#2B2B2B"))//
                .widthScale(0.85f)//
//                .showAnim(mBasIn)//
//                .dismissAnim(mBasOut)//
                .show();
        dialog.setOnBtnClickL(
                new OnBtnClickL() {
                    @Override
                    public void onBtnClick() {
                        ToastUtils.showShort("left");
                        dialog.dismiss();
                    }
                },
                new OnBtnClickL() {
                    @Override
                    public void onBtnClick() {
                        ToastUtils.showShort("right");
                        dialog.dismiss();
                    }
                });
    }

    private void showActionSheetDialog() {
        final String[] items = {"拍照", "相册"};
        final ActionSheetDialog actionSheetDialog = new ActionSheetDialog(TakePhotoActivity.this, items, null);
        actionSheetDialog.isTitleShow(false).show();
        actionSheetDialog.setOnOperItemClickL(new OnOperItemClickL() {
            @Override
            public void onOperItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        //动态权限申请
                      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                          if (ContextCompat.checkSelfPermission(TakePhotoActivity.this, Manifest.permission.CAMERA) !=
                                  PackageManager.PERMISSION_GRANTED) {
                              ActivityCompat.requestPermissions(TakePhotoActivity.this, new String[]{Manifest.permission.CAMERA}, 1);
                          } else {
                              takeCamera();
                          }
                      }else{
                          takeCamera();
                      }
                        actionSheetDialog.dismiss();
                        break;
                    case 1:

                        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                            setImgOutPath();
                            Intent intent = new Intent(Intent.ACTION_PICK, null);
                            intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,"image/*");
                            startActivityForResult(intent,PICK_PICTURE);
                        }

                        break;
                }
            }
        });
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            for (int i = 0; i < permissions.length; i++) {
                if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                    takeCamera();
                } else {
                    ToastUtils.showShort("权限申请失败");
                }
            }
        }
    }

    /**
     * 拍照
     */
    private void takeCamera() {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            File file = setImgOutPath();
            imageUri = Uri.fromFile(file);
            Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
            intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
            startActivityForResult(intent, TAKE_PHOTO);
        }
    }

    /**
     * 设置图片本地保存路径
     * @return
     */
    @NonNull
    private File setImgOutPath() {
        String imagePath = Environment.getExternalStorageDirectory() + File.separator + "img" ;
        File file = new File(imagePath);
        if (!file.exists()) {
            file.mkdir();
        }
        imagePath+= File.separator + "avatar.jpg";
        try {
            file = new File(imagePath);
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case TAKE_PHOTO:
                if (resultCode == RESULT_OK){
                    Intent intent = new Intent("com.android.camera.action.CROP");
                    intent.setDataAndType(imageUri, "image/*");
                    intent.putExtra("scale", true);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                    startActivityForResult(intent, CROP_PHOTO); // 启动裁剪程序
                }
                break;
            case PICK_PICTURE:
                if (resultCode == RESULT_OK){
                    Uri uri = data.getData();
                    startPhotoZoom(uri);
                }
                break;
            case 4:
            case CROP_PHOTO:
                if (resultCode == RESULT_OK){
                    try {
                        Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri));
                       ivAvatar.setImageBitmap(bitmap);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case 5:
                if (resultCode == RESULT_OK) {
                    List<LocalMedia> localMedia = PictureSelector.obtainMultipleResult(data);
                    String compressPath = localMedia.get(0).getCompressPath();
                    Bitmap bitmap = BitmapFactory.decodeFile(compressPath);
                    ivAvatar.setImageBitmap(bitmap);

                }
                break;
            default:
                break;
        }
    }
    /**
     * 裁剪所选取的图片
     * @param uri
     */
    public void startPhotoZoom(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        // 下面这个crop=true是设置在开启的Intent中设置显示的VIEW可裁剪
        intent.putExtra("crop", "true");
        //该参数可以不设定用来规定裁剪区的宽高比
        intent.putExtra("aspectX", 2);
        intent.putExtra("aspectY", 1);
        //该参数设定为你的imageView的大小
        intent.putExtra("outputX", 600);
        intent.putExtra("outputY", 300);
        intent.putExtra("scale", true);
        //是否返回bitmap对象
        intent.putExtra("return-data", false);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());//输出图片的格式
        intent.putExtra("noFaceDetection", true); // 头像识别
        startActivityForResult(intent, 4);
    }
}
