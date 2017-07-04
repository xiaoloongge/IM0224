package com.atguigu.im0224.controller.activity;

import android.os.Bundle;
import android.widget.ListView;

import com.atguigu.im0224.R;
import com.atguigu.im0224.base.BaseActivity;
import com.atguigu.im0224.controller.adapter.InviteAdapter;
import com.atguigu.im0224.modle.bean.InvitationInfo;

import butterknife.Bind;
import butterknife.ButterKnife;

public class InviteActivity extends BaseActivity {


    @Bind(R.id.lv_invite)
    ListView lvInvite;

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {

        InviteAdapter adapter = new InviteAdapter(this, onInviteListener);
        lvInvite.setAdapter(adapter);
    }

    public void refreshData(){
        //获取数据
        
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
        public void invitedSuccess(InvitationInfo info) {
            //接受邀请

        }

        @Override
        public void invitedReject(InvitationInfo info) {
            //拒绝邀请

        }
    };

}
