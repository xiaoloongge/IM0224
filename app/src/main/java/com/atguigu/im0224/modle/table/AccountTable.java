package com.atguigu.im0224.modle.table;

/**
 * Created by Administrator on 2017/7/1.
 */

//账号表
public class AccountTable {

    public static final String TABLE_NAME = "account";
    public static final String COL_USERNAME = "username";
    public static final String COL_HXID = "hxid";
    public static final String COL_PHOTO = "photo";
    public static final String COL_NICK = "nick";

    public static final String CREATE_TABLE = "create table "+TABLE_NAME+"("
            +COL_HXID+" text primary key, "
            +COL_USERNAME +" text, "
            +COL_PHOTO +" text, "
            +COL_NICK +" text)";

}
