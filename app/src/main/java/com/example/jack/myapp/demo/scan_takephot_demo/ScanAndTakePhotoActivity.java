package com.example.jack.myapp.demo.scan_takephot_demo;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.example.jack.myapp.R;
import com.example.jack.myapp.google.zxing.activity.CaptureActivity;

/**
 * 扫描二维码，拍照上传(acc誉得demo)
 */
public class ScanAndTakePhotoActivity extends AppCompatActivity {
    private Button btnScan;
    private TextView textView2;
    private Button btnTakephoto;
    private ImageView imageView2;

    //打开扫描界面请求码
    private int REQUEST_CODE = 0x01;
    //扫描成功返回码
    private int RESULT_OK = 0xA1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_and_take_photo);

        initView();
        btnScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scan();
            }
        });

    }

    private void scan() {
        startActivityForResult(new Intent(ScanAndTakePhotoActivity.this, CaptureActivity.class), REQUEST_CODE);
    }

    private void initView() {
        btnScan = findViewById(R.id.btn_scan);
        textView2 = findViewById(R.id.textView2);
        btnTakephoto = findViewById(R.id.btn_takephoto);
        imageView2 = findViewById(R.id.imageView2);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_CODE) {
                Bundle extras = data.getExtras();
                if (null != extras.getString("qr_scan_result")) {
                    textView2.setText(extras.getString("qr_scan_result"));
                    ToastUtils.showShort("扫描成功");
                }
            }
//        }
    }
}
