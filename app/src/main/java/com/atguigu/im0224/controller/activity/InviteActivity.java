package com.atguigu.im0224.controller.activity;

import android.os.Bundle;
import android.widget.ListView;

import com.atguigu.im0224.R;
import com.atguigu.im0224.base.BaseActivity;

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

        lvInvite.setAdapter();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_invite;
    }

}
