package com.example.jack.myapp.wanandroid.activity;

import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import com.example.jack.myapp.R;
import com.example.jack.myapp.wanandroid.BaseActivity;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends BaseActivity implements View.OnClickListener{
    private ScrollView loginForm;
    private LinearLayout emailLoginForm;
    private ImageView ivCancel;
    private AutoCompleteTextView tvUsername;
    private EditText etPassword;
    private Button btnRegister;
    private Button btnLogin;
    @Override
    protected void initView() {
        loginForm = (ScrollView) findViewById(R.id.login_form);
        emailLoginForm = (LinearLayout) findViewById(R.id.email_login_form);
        ivCancel = (ImageView) findViewById(R.id.iv_cancel);
        tvUsername = (AutoCompleteTextView) findViewById(R.id.tv_username);
        etPassword = (EditText) findViewById(R.id.et_password);
        btnRegister = (Button) findViewById(R.id.btn_register);
        btnLogin = (Button) findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(this);
        btnRegister.setOnClickListener(this);
        ivCancel.setOnClickListener(this);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_login;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_login:
                break;
            case R.id.btn_register:
                break;
            case R.id.iv_cancel:
                this.finish();
                break;
        }
    }
}

