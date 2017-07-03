package com.atguigu.im0224.controller.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.atguigu.im0224.R;
import com.atguigu.im0224.utils.UiUtils;
import com.hyphenate.easeui.ui.EaseContactListFragment;

/**
 * Created by Administrator on 2017/7/3.
 */

public class ContactListFragment extends EaseContactListFragment {


    @Override
    protected void initView() {
        super.initView();
    }

    @Override
    protected void setUpView() {
        super.setUpView();

        //初始化listView头布局
        initHeadView();

        titleBar.setRightImageResource(R.drawable.ease_blue_add);
        titleBar.setRightLayoutClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),AddContactActivity.class));
            }
        });
    }

    public void initHeadView(){
        View headView = View.inflate(getActivity(), R.layout.head_view,null);
        LinearLayout groups = (LinearLayout) headView.findViewById(R.id.ll_groups);
        LinearLayout friends = (LinearLayout) headView.findViewById(R.id.ll_new_friends);

        listView.addHeaderView(headView);

        groups.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UiUtils.showToast("groups");
            }
        });
        friends.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UiUtils.showToast("friends");
            }
        });
    }
}
