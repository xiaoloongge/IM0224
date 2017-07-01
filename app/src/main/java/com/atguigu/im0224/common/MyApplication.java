package com.atguigu.im0224.common;

import android.app.Application;

import com.hyphenate.chat.EMOptions;
import com.hyphenate.easeui.controller.EaseUI;

/**
 * Created by Administrator on 2017/7/1.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        //初始化环信sdk
        EMOptions options = new EMOptions();//配置一些功能
        //是否自动接受群组邀请
        options.setAutoAcceptGroupInvitation(false);
        //是否自动接受邀请
        options.setAcceptInvitationAlways(false);
        EaseUI.getInstance().init(this,options);

        //初始化modle
        Modle.getInstance().init(this);
    }
}
