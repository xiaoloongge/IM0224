package com.atguigu.im0224.controller.activity;

import android.os.Bundle;
import android.widget.ListView;

import com.atguigu.im0224.R;
import com.atguigu.im0224.base.BaseActivity;
import com.atguigu.im0224.common.Modle;
import com.atguigu.im0224.controller.adapter.InviteAdapter;
import com.atguigu.im0224.modle.bean.InvitationInfo;
import com.atguigu.im0224.utils.SPUtils;
import com.atguigu.im0224.utils.UiUtils;
import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class InviteActivity extends BaseActivity {


    @Bind(R.id.lv_invite)
    ListView lvInvite;
    private InviteAdapter adapter;

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {
        //设置小红点的状态
        SPUtils.getSpUtils().save(SPUtils.NEW_INVITE,false);
        adapter = new InviteAdapter(this, onInviteListener);
        lvInvite.setAdapter(adapter);
        refreshData(); //给适配器设置数据
    }
/*

  界面数据展示需要考虑三大数据源

* 网络
*
* 本地
*
* 内存和页面
*
*
* */
    public void refreshData(){
        //获取数据 从数据库
        List<InvitationInfo> invitations = Modle.getInstance().getHelperManager().getInvitationDAO().getInvitations();
        if (invitations != null){
            adapter.refresh(invitations);
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_invite;
    }


    /*
    * inviteAdapter的回调方法
    *
    * */
    InviteAdapter.OnInviteListener onInviteListener = new InviteAdapter.OnInviteListener() {
        @Override
        public void invitedSuccess(final InvitationInfo info) {
            //接受邀请
            // 网络 内存 本地和页面
            Modle.getInstance().getGlobalThread().execute(new Runnable() {
                @Override
                public void run() {
                    //网络

                    try {
                        String hxid = info.getUserInfo().getHxid();
                        EMClient.getInstance().contactManager().acceptInvitation(hxid);

                        //本地
                        Modle.getInstance().getHelperManager().getInvitationDAO()
                                .updateInvitationStatus(
                                        InvitationInfo.InvitationStatus.INVITE_ACCEPT,hxid);

                        //内存和网页
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                UiUtils.showToast("添加成功");
                                refreshData();
                            }
                        });
                    } catch (HyphenateException e) {
                        e.printStackTrace();
                        UiUtils.showToast(e.getMessage());
                    }
                }
            });
        }

        @Override
        public void invitedReject(final InvitationInfo info) {
            //拒绝邀请
            Modle.getInstance().getGlobalThread().execute(new Runnable() {
                @Override
                public void run() {

                    String hxid = info.getUserInfo().getHxid();
                    try {
                        //网络
                        EMClient.getInstance().contactManager()
                                .declineInvitation(hxid);

                        //本地
                        Modle.getInstance().getHelperManager().getInvitationDAO()
                                .removeInvitation(hxid);

                        //内存和网页
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                UiUtils.showToast("拒绝成功");
                                refreshData();
                            }
                        });
                    } catch (HyphenateException e) {
                        e.printStackTrace();
                        UiUtils.showToast(e.getMessage());
                    }
                }
            });
        }
    };

}
