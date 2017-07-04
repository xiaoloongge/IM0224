package com.atguigu.im0224.controller.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.atguigu.im0224.R;
import com.atguigu.im0224.common.Constant;
import com.atguigu.im0224.common.Modle;
import com.atguigu.im0224.controller.activity.AddContactActivity;
import com.atguigu.im0224.controller.activity.InviteActivity;
import com.atguigu.im0224.modle.bean.UserInfo;
import com.atguigu.im0224.utils.SPUtils;
import com.hyphenate.chat.EMClient;
import com.hyphenate.easeui.domain.EaseUser;
import com.hyphenate.easeui.ui.EaseContactListFragment;
import com.hyphenate.exceptions.HyphenateException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private BroadcastReceiver contactReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            //添加或者删除好友以后调用此方法

        }
    };

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
        //邀请信息发生改变
        manager.registerReceiver(inviteReciver,new IntentFilter(Constant.NEW_INVITE_CHANGE));
        //联系人发生改变
        manager.registerReceiver(contactReceiver,new IntentFilter(Constant.CONTACT_CHANGE));

        //展示联系人
        showContact();
    }


    //展示联系人
    private void showContact() {

        //判断是否是第一次进入应用 第一次需要从服务器获取联系人 否则直接从数据库
        refreshServer();
    }

    /*
    * 从服务器获取好友列表
    * */
    public void refreshServer(){

        Modle.getInstance().getGlobalThread().execute(new Runnable() {
            @Override
            public void run() {

                try {
                    //网络
                    List<String> contacts =
                            EMClient.getInstance().contactManager().getAllContactsFromServer();

                    //本地
                    //数据转换
                    List<UserInfo> userinfos = new ArrayList<UserInfo>();
                    for (String contacs:contacts) {
                        UserInfo userinfo = new UserInfo(contacs,contacs);
                        userinfos.add(userinfo);
                    }
                    //保存从服务器获取的联系人
                    Modle.getInstance().getHelperManager()
                            .getContactDAO().saveContacts(userinfos,true);

                    //内存和页面
                    if (getActivity() == null){
                        return;
                    }
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            refreshData();
                        }
                    });

                } catch (HyphenateException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    /*
    * 从本地获取联系人数据
    * */
    public void refreshData(){
        //从数据库获取所有联系人
        List<UserInfo> contacts =
                Modle.getInstance().getHelperManager().getContactDAO().getContacts();
        //校验
        if (contacts != null){
            //添加数据
            Map<String, EaseUser> map = new HashMap<>();
            //数据类型转换
            for (UserInfo info:contacts) {
                map.put(info.getHxid(),new EaseUser(info.getUsername()));
            }
            setContactsMap(map);
            //获取数据
            getContactList();
            //刷新数据
            contactListLayout.refresh();
        }

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
                startActivity(new Intent(getActivity(),InviteActivity.class));
            }
        });
    }

    /*
    * 当界面再次展示的时候进行回调
    * */
    @Override
    public void onResume() {
        super.onResume();
        isShowRedView();
    }
}
