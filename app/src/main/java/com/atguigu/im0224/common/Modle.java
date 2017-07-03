package com.atguigu.im0224.common;

import android.content.Context;

import com.atguigu.im0224.modle.HelperManager;
import com.atguigu.im0224.modle.bean.UserInfo;
import com.atguigu.im0224.modle.dao.AccountDAO;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Administrator on 2017/7/1.
 */

public class Modle {

    private AccountDAO accountDAO;
    private HelperManager manager;

    private Modle(){}

    private Context context;

    private static Modle modle = new Modle();

    public static Modle getInstance(){
        return modle;
    }

    public void init(Context context){
        this.context = context;
        accountDAO = new AccountDAO(context);
        //初始化全局监听
        new GlobalListener(context);
    }

    private ExecutorService service = Executors.newCachedThreadPool();

    public ExecutorService getGlobalThread(){
        return service;
    }

    //登录成功以后保存用户数据
    public void loginSuccess(UserInfo userInfo) {
        //添加用户
        accountDAO.addAccount(userInfo);

        if (manager != null){
            manager.close();
        }
        //创建HelperManager
        manager = new HelperManager(context,userInfo.getUsername()+".db");
    }

    public AccountDAO getAccountDAO(){
        return accountDAO;
    }

    /*
    * 返回HelperManager的对象
    * */
    public HelperManager getHelperManager(){

        return manager;
    }
}
