package com.atguigu.im0224.modle.table;

/**
 * Created by Administrator on 2017/7/3.
 */

public class ContactTable {


    /*
    * 注意 ：
    *       1 把sql语句一定要确定没有问题
    *       2 数据库创建有问题的时候，如果需要重新创建 一定要卸载原来的应用
    *
    * */
    public static final String TABLE_NAME = "contact";
    public static final String COL_USER_HXID = "userhxid";
    public static final String COL_USER_NAME = "username";
    public static final String COL_USER_PHOTO = "userphoto";
    public static final String COL_USER_NICK = "usernick";

    public static final String COL_IS_CONTACT="contact";

    public static final String CREATE_TABLE = "create table "+TABLE_NAME+"("
            + COL_USER_HXID +" text primary key,"
            +COL_USER_NAME + " text,"
            +COL_USER_PHOTO + " text,"
            +COL_USER_NICK + " text,"
            +COL_IS_CONTACT + " integer)";

}
