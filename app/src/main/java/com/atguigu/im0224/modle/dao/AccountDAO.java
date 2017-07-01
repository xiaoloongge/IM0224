package com.atguigu.im0224.modle.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;

import com.atguigu.im0224.modle.bean.UserInfo;
import com.atguigu.im0224.modle.db.AccountDB;
import com.atguigu.im0224.modle.table.AccountTable;

/**
 * Created by Administrator on 2017/7/1.
 */

public class AccountDAO {

    private final AccountDB accountDB;

    public AccountDAO(Context context){
        accountDB = new AccountDB(context);
    }

    /*
    * 添加用户
    * */
    public void addAccount(UserInfo userInfo){
        //校验
        if (userInfo == null){
            throw new NullPointerException("userInfo你敢为空搞死你");
        }

        SQLiteDatabase database = accountDB.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(AccountTable.COL_HXID,userInfo.getHxid());
        contentValues.put(AccountTable.COL_NICK,userInfo.getNick());
        contentValues.put(AccountTable.COL_PHOTO,userInfo.getPhoto());
        contentValues.put(AccountTable.COL_USERNAME,userInfo.getUsername());
        database.replace(AccountTable.TABLE_NAME,null,contentValues);
    }


    /*
    * 根据hxid 获取对应的用户
    * */
    public UserInfo getUserInfo(String hxid){

        //校验
        if (TextUtils.isEmpty(hxid)){
            return null;
        }

        SQLiteDatabase database = accountDB.getWritableDatabase();

        String sql = "select * from "+AccountTable.TABLE_NAME
                +" where "+AccountTable.COL_HXID+"=?";

        Cursor cursor = database.rawQuery(sql, new String[]{hxid});
        UserInfo userInfo = new UserInfo();
        if (cursor.moveToNext()){
            userInfo.setHxid(cursor.getString(cursor.getColumnIndex(AccountTable.COL_HXID)));
            userInfo.setUsername(cursor.getString(cursor.getColumnIndex(AccountTable.COL_USERNAME)));
            userInfo.setPhoto(cursor.getString(cursor.getColumnIndex(AccountTable.COL_PHOTO)));
            userInfo.setNick(cursor.getString(cursor.getColumnIndex(AccountTable.COL_NICK)));
        }
        cursor.close();//记着关闭游标

        return userInfo;
    }

}
