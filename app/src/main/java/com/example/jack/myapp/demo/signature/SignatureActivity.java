package com.example.jack.myapp.demo.signature;

import android.content.DialogInterface;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.blankj.utilcode.util.ToastUtils;
import com.example.jack.myapp.R;
import com.example.tulib.util.utils.DeviceUtil;

/**
 * 自定义签名控件
 */
public class SignatureActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnShowSignature;
    private Button btnClear;
    private ImageView ivSig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signature);

        btnShowSignature = findViewById(R.id.btn_show_signature);
        ivSig = findViewById(R.id.iv_sig);
        btnClear = findViewById(R.id.btn_clear);
        btnShowSignature.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_show_signature:
                showSignatureDialog();
                break;
            case R.id.btn_clear:

                break;
        }
    }

    private void showSignatureDialog() {
        View contentView = LayoutInflater.from(this).inflate(R.layout.dialog_signature, null);
        LinearLayout llSig = contentView.findViewById(R.id.ll_sig);
        Display defaultDisplay = getWindowManager().getDefaultDisplay();
        final SignatureView signatureView = new SignatureView(this, defaultDisplay.getWidth(), DeviceUtil.dip2px(this, 200), new SignatureView.OnSavePhotoListener() {
            @Override
            public void onSuccess() {
                ToastUtils.showShort("签名保存成功");
            }
            @Override
            public void onError() {
                ToastUtils.showShort("签名保存失败");
            }
        });
        signatureView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, DeviceUtil.dip2px(this, 200)));
        llSig.addView(signatureView);
        final AlertDialog mDialog = new AlertDialog.Builder(SignatureActivity.this)
                .setTitle("签名")
                .setView(contentView)
                .setNegativeButton("清空", null).setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        //保存签名图片  并显示
                        ivSig.setImageBitmap(signatureView.getmBitmap());
                        signatureView.savePicture();
                    }
                }).create();
        mDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                Button button = mDialog.getButton(AlertDialog.BUTTON_NEGATIVE);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                            //清空签名内容
                        signatureView.clear();
                    }
                });
            }
        });
        mDialog.show();

    }
}
