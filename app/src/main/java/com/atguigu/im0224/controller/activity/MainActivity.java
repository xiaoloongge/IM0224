package com.atguigu.im0224.controller.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.atguigu.im0224.R;
import com.hyphenate.chat.EMClient;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        EMClient.getInstance().isLoggedInBefore();
    }
}
