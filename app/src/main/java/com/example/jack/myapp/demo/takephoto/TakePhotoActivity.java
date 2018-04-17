package com.example.jack.myapp.demo.takephoto;

import android.Manifest;
import android.content.Intent;
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
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;

import com.blankj.utilcode.util.EncodeUtils;
import com.blankj.utilcode.util.EncryptUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.example.jack.myapp.AppConstant;
import com.example.jack.myapp.R;
import com.flyco.dialog.entity.DialogMenuItem;
import com.flyco.dialog.listener.OnBtnClickL;
import com.flyco.dialog.listener.OnOperItemClickL;
import com.flyco.dialog.widget.ActionSheetDialog;
import com.flyco.dialog.widget.NormalDialog;
import com.flyco.dialog.widget.NormalListDialog;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class TakePhotoActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView ivAvatar;
    private Button btnTakePhoto;
    private static final int TAKE_PHOTO = 1;
    private static  final int CROP_PHOTO = 2;
    private Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_photo);
        ivAvatar = (ImageView) findViewById(R.id.iv_avatar);
        btnTakePhoto = (Button) findViewById(R.id.btn0);
        btnTakePhoto.setOnClickListener(this);
        findViewById(R.id.btn1).setOnClickListener(this);
        findViewById(R.id.btn2).setOnClickListener(this);
        findViewById(R.id.btn3).setOnClickListener(this);
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
            default:
                break;
        }
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
                        ToastUtils.showShort(items[position]);
                        actionSheetDialog.dismiss();
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
            imageUri = Uri.fromFile(file);
            Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
            intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
            startActivityForResult(intent, TAKE_PHOTO);
        }
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
            default:
                break;
        }
    }
}
