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

        //好友请求被同意
        @Override
        public void onContactAgreed(String hxid) {

        }

        //好友请求被拒绝
        @Override
        public void onContactRefused(String hxid) {

        }

        //收到好友邀请
        @Override
        public void onContactInvited(String hxid, String reason) {

            Log.d("contact", "onContactInvited: "+hxid);
        }

        //被删除时回调此方法
        @Override
        public void onContactDeleted(String hxid) {

        }


        //增加了联系人时回调此方法
        @Override
        public void onContactAdded(String hxid) {

        }
    };

}
