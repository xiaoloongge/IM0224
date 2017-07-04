package com.atguigu.im0224.controller.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.atguigu.im0224.R;
import com.atguigu.im0224.base.BaseActivity;
import com.atguigu.im0224.common.Modle;
import com.atguigu.im0224.utils.UiUtils;
import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;

import butterknife.Bind;
import butterknife.ButterKnife;

public class AddContactActivity extends BaseActivity {


    @Bind(R.id.invite_btn_search)
    Button inviteBtnSearch;
    @Bind(R.id.invite_et_search)
    EditText inviteEtSearch;
    @Bind(R.id.invite_tv_username)
    TextView inviteTvUsername;
    @Bind(R.id.invite_btn_add)
    Button inviteBtnAdd;
    @Bind(R.id.invite_ll_item)
    LinearLayout inviteLlItem;
    @Bind(R.id.activity_invite_acitivity)
    LinearLayout activityInviteAcitivity;
    private String username;

    @Override
    public void initListener() {

        inviteBtnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取输入的用户名
                username = inviteEtSearch.getText().toString().trim();
                if (TextUtils.isEmpty(username)){
                    UiUtils.showToast("用户名不能为空");
                    return;
                }
                Modle.getInstance().getGlobalThread().execute(new Runnable() {
                    @Override
                    public void run() {
                        //去本地服务进行查询联系人
                        if (getUser()){
                            //服务器查询到此人

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    inviteLlItem.setVisibility(View.VISIBLE);
                                    inviteTvUsername.setText(username);
                                }
                            });
                        }else{
                            //服务器查询不到此人
                            UiUtils.showToast("没有此联系人");
                        }
                    }
                });
            }
        });

        //


        inviteBtnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //访问环信服务器进行联系人添加
                //第一个参数是环信ID 第二个参数是添加的原因
                try {
                    EMClient.getInstance().contactManager()
                            .addContact(username,"小福我是志玲啊");
                    UiUtils.showToast("添加联系人成功");
                } catch (HyphenateException e) {
                    e.printStackTrace();
                    UiUtils.showToast(e.getMessage());
                }
            }
        });
    }

    //去服务器查询添加的联系人
    private boolean getUser() {
        return true;
    }

    @Override
    public void initData() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_add_contact;
    }

}
