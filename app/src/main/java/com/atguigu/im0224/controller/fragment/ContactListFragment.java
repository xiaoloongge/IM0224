package com.atguigu.im0224.controller.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.atguigu.im0224.R;
import com.atguigu.im0224.common.Constant;
import com.atguigu.im0224.utils.SPUtils;
import com.atguigu.im0224.utils.UiUtils;
import com.hyphenate.easeui.ui.EaseContactListFragment;

/**
 * Created by Administrator on 2017/7/3.
 */

public class ContactListFragment extends EaseContactListFragment {


    private BroadcastReceiver inviteReciver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d("invited", "onReceive: ");
            //接收广播
           isShowRedView();
        }
    };
    private ImageView redView;

    @Override
    protected void initView() {
        super.initView();
    }

    @Override
    protected void setUpView() {
        super.setUpView();

        //初始化listView头布局
        initHeadView();

        isShowRedView();

        titleBar.setRightImageResource(R.drawable.ease_blue_add);
        titleBar.setRightLayoutClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),AddContactActivity.class));
            }
        });

        //注册监听
        LocalBroadcastManager manager = LocalBroadcastManager.getInstance(getActivity());
        manager.registerReceiver(inviteReciver,new IntentFilter(Constant.NEW_INVITE_CHANGE));
    }

    /*
    * 判断小红点是否展示
    * */
    private void isShowRedView() {
        //获取小红点的状态
        Boolean bolValue = SPUtils.getSpUtils().getBolValue(SPUtils.NEW_INVITE);
        //是否显示小红点
        redView.setVisibility(bolValue?View.VISIBLE:View.GONE);
    }

    public void initHeadView(){
        View headView = View.inflate(getActivity(), R.layout.head_view,null);
        LinearLayout friends = (LinearLayout) headView.findViewById(R.id.ll_new_friends);
        redView = (ImageView) headView.findViewById(R.id.contanct_iv_invite);

        listView.addHeaderView(headView);

        friends.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UiUtils.showToast("groups");
            }
        });
    }
}
