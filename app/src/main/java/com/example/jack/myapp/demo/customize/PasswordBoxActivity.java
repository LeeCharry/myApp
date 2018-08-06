package com.example.jack.myapp.demo.customize;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.blankj.utilcode.util.ToastUtils;
import com.example.jack.myapp.R;

/**
 * 仿支付宝支付弹出框
 */
public class PasswordBoxActivity extends AppCompatActivity {
    private String passStr = "123456";
    private PasswordBoxView pwd_box;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_input_box);

        pwd_box = findViewById(R.id.pwd_box);

        pwd_box.setOnInputCompleteListener(new PasswordBoxView.OnInputCompleteListener() {
            @Override
            public void inputComplete(String input) {
                if (!input.equals(passStr)) {
                    ToastUtils.showShort("密码不正确！");
                }else{
                    ToastUtils.showShort("恭喜，密码正确！");
                    pwd_box.setVisibility(View.GONE);
                }
            }
        });
    }

}
