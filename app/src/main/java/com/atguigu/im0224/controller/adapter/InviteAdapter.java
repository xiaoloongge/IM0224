package com.atguigu.im0224.controller.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.atguigu.im0224.modle.bean.InvitationInfo;

import java.util.List;

/**
 * Created by Administrator on 2017/7/4.
 */

public class InviteAdapter extends BaseAdapter {

    private Context context;
    public InviteAdapter(Context context){
        this.context = context;
    }

    public void refresh(List<InvitationInfo> list){

    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}
