package com.example.jack.myapp.wanandroid.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.ScrollView;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.example.jack.myapp.R;
import com.example.jack.myapp.UserManager;
import com.example.jack.myapp.bean.UserBean;
import com.example.jack.myapp.mvp.contract.LoginAndRegisContract;
import com.example.jack.myapp.mvp.presenter.LoginAndRegisPresenter;
import com.example.jack.myapp.wanandroid.BaseActivity;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends BaseActivity implements View.OnClickListener,LoginAndRegisContract.View{
    private ScrollView loginForm;
    private LinearLayout emailLoginForm;
    private ImageView ivCancel;
    private AutoCompleteTextView tvUsername;
    private EditText etPassword;
    private Button btnRegister;
    private Button btnLogin;
    private LoginAndRegisPresenter mPresenter;
    private String pwd;
    private String username;

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
        mPresenter = new LoginAndRegisPresenter(this,this);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_login;
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                if (checkUserInfo()){
                    mPresenter.login(username,pwd);
                }
                break;
            case R.id.btn_register:
                if (checkUserInfo()){
                    mPresenter.register(username,pwd,pwd);
                }
                break;
            case R.id.iv_cancel:
                this.finish();
                break;
        }
    }

    private boolean checkUserInfo() {
      username = tvUsername.getText().toString();
      pwd = etPassword.getText().toString();
        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(pwd)){
            ToastUtils.showShort("用户名或密码不能为空");
            return false;
        }
        if (username.length()<6 || pwd.length()<6) {
            ToastUtils.showShort("用户名或者密码长度不能小于6位");
            return false;
        }
        return true;
    }

    @Override
    public void showLoading() {
        loadingDailog.show();
    }

    @Override
    public void hideLoading() {
        loadingDailog.dismiss();
    }

    @Override
    public void showMessage(String msg) {
        ToastUtils.showShort(msg);
    }

    @Override
    public void launchActivity(Intent intent) {

    }

    @Override
    public void killMySelf() {
        LoginActivity.this.finish();
    }

    @Override
    public void register(UserBean userBean) {
        ToastUtils.showShort("注册成功");
        killMySelf();
    }

    @Override
    public void login(UserBean userBean) {
            ToastUtils.showShort("登录成功");
            killMySelf();

        UserBean userBean1 = UserManager.getUserBean();
        LogUtils.a("lcy",gson.toJson(userBean));
    }
}

