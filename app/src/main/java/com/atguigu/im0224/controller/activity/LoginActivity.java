package com.atguigu.im0224.controller.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.atguigu.im0224.R;
import com.atguigu.im0224.base.BaseActivity;
import com.atguigu.im0224.common.Modle;
import com.atguigu.im0224.modle.bean.UserInfo;
import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;

import butterknife.Bind;
import butterknife.ButterKnife;

public class LoginActivity extends BaseActivity {


    @Bind(R.id.login_et_username)
    EditText loginEtUsername;
    @Bind(R.id.login_et_password)
    EditText loginEtPassword;
    @Bind(R.id.login_btn_register)
    Button loginBtnRegister;
    @Bind(R.id.login_btn_login)
    Button loginBtnLogin;

    @Override
    public void initListener() {

        loginBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取输入的值
                final String pwd = loginEtPassword.getText().toString().trim();
                final String username = loginEtUsername.getText().toString().trim();

                //校验
                if (TextUtils.isEmpty(pwd) || TextUtils.isEmpty(username)){
                    showToast("用户名或密码不能为空");
                    return;
                }

                //连网
                Modle.getInstance().getGlobalThread().execute(new Runnable() {
                    @Override
                    public void run() {

                        EMClient.getInstance().login(username, pwd, new EMCallBack() {
                            @Override
                            public void onSuccess() {
                                //登录成功 处理一些特殊的信息
                                String currentUser = EMClient.getInstance().getCurrentUser();

                                Modle.getInstance().loginSuccess(new UserInfo(currentUser,currentUser));
                                //跳转界面
                                startActivity(new Intent(LoginActivity.this,MainActivity.class));

                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        showToast("登录成功");
                                    }
                                });
                                //结束当前页面
                                finish();
                            }

                            @Override
                            public void onError(int i, final String s) {
                               runOnUiThread(new Runnable() {
                                   @Override
                                   public void run() {
                                       showToast(s);
                                   }
                               });
                            }

                            @Override
                            public void onProgress(int i, String s) {

                            }
                        });
                    }
                });
            }
        });

        loginBtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String username = loginEtUsername.getText().toString().trim();
                final String pwd = loginEtPassword.getText().toString().trim();

                //校验
                if (TextUtils.isEmpty(pwd) || TextUtils.isEmpty(username)){
                    showToast("用户名或密码不能为空");
                    return;
                }

                Modle.getInstance().getGlobalThread().execute(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            EMClient.getInstance().createAccount(username,pwd);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    showToast("注册成功");
                                }
                            });
                        } catch (final HyphenateException e) {
                            e.printStackTrace();
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    showToast(e.getMessage());
                                }
                            });
                        }
                    }
                });
            }
        });
    }

    @Override
    public void initData() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

}
