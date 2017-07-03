package com.atguigu.im0224.common;

import android.content.Context;
import android.util.Log;

import com.hyphenate.EMContactListener;
import com.hyphenate.chat.EMClient;

/**
 * Created by Administrator on 2017/7/3.
 */

public class GlobalListener {

    public GlobalListener(Context context){

        EMClient.getInstance().contactManager().setContactListener(emContactListener);
    }

    /*
       设置全局监听
    * */
    EMContactListener emContactListener = new EMContactListener() {

        //收到好友邀请  别人加你
        @Override
        public void onContactInvited(String username, String reason) {

        }

        //好友请求被同意  你加别人的时候 别人同意了
        @Override
        public void onContactAgreed(String username) {

        }



        //被删除时回调此方法
        @Override
        public void onContactDeleted(String username) {

        }


        //增加了联系人时回调此方法  当你同意添加好友
        @Override
        public void onContactAdded(String username) {

        }

        //好友请求被拒绝  你加别人 别人拒绝了
        @Override
        public void onContactRefused(String username) {

        }
    };

}
