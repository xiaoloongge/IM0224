package com.atguigu.im0224.modle.table;

/**
 * Created by Administrator on 2017/7/3.
 */

public class InvitationTable {

    public static final String TABLE_NAME = "invitation";
    public static final String COL_USER_HXID = "userhxid";
    public static final String COL_USER_NAME = "username";
    public static final String COL_REASON = "reason";
    public static final String COL_STATE = "state";

    public static final String CREATE_TABLE ="create table "+TABLE_NAME +"("
            +COL_USER_HXID + " text primary key,"
            +COL_USER_NAME +" text,"
            +COL_REASON +" text,"
            +COL_STATE +" integer)";


}
