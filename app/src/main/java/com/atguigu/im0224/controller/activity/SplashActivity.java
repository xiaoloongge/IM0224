package com.atguigu.im0224.controller.activity;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.atguigu.im0224.R;
import com.atguigu.im0224.base.BaseActivity;
import com.atguigu.im0224.common.Modle;
import com.atguigu.im0224.modle.bean.UserInfo;
import com.hyphenate.chat.EMClient;

public class SplashActivity extends BaseActivity {


    private CountDownTimer countDownTimer;

    @Override
    public void initListener() {

        //第一个参数是倒计时的总时长，倒计时时间间隔
        //倒计时结束
        countDownTimer = new CountDownTimer(2000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                //倒计时结束
                selectChageActivity();
            }
        }.start();

    }

    //选择进入哪个界面
    private void selectChageActivity() {

        /*new Thread(){
            @Override
            public void run() {
                super.run();
                //是否登录过环信服务器
                boolean loggedInBefore = EMClient.getInstance().isLoggedInBefore();
                if (loggedInBefore){
                    //登录过
                    startActivity(new Intent(SplashActivity.this,MainActivity.class));
                }else{
                    //没有登录过
                    startActivity(new Intent(SplashActivity.this,LoginActivity.class));
                }
            }
        }.start();*/

        Modle.getInstance().getGlobalThread().execute(new Runnable() {
            @Override
            public void run() {
                //是否登录过环信服务器
                boolean loggedInBefore = EMClient.getInstance().isLoggedInBefore();
                if (loggedInBefore){
                    //登录过
                    //初始化登录成功后的操作
                    String currentUser = EMClient.getInstance().getCurrentUser();
                    Modle.getInstance().loginSuccess(new UserInfo(currentUser,currentUser));
                    startActivity(new Intent(SplashActivity.this,MainActivity.class));
                    finish();
                }else{
                    //没有登录过
                    startActivity(new Intent(SplashActivity.this,LoginActivity.class));
                    finish();
                }
            }
        });
    }


    @Override
    protected void onPause() {
        super.onPause();
        //取消倒计时
        countDownTimer.cancel();
    }

    @Override
    protected void onStop() {
        super.onStop();
        //执行耗时的操作 因为第二个界面启动后才调用此方法
    }

    @Override
    public void initData() {

        //跳转 到主界面 或者登录界面



    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_splash;
    }
}
