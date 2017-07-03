package com.atguigu.im0224.modle.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;

import com.atguigu.im0224.modle.bean.UserInfo;
import com.atguigu.im0224.modle.db.DBHelper;
import com.atguigu.im0224.modle.table.ContactTable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/7/3.
 */

public class ContactDAO {

    private DBHelper dbHelper;
    public ContactDAO(DBHelper dbHelper){
        this.dbHelper = dbHelper;
    }

    // 获取所有联系人
    public List<UserInfo> getContacts(){
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        //"=1"是联系人的状态
        String sql = "select * from "+ ContactTable.TABLE_NAME
                + " where "+ContactTable.COL_IS_CONTACT +"=1";
        Cursor cursor = database.rawQuery(sql, null);
        List<UserInfo> userInfos = new ArrayList<>();
        while (cursor.moveToNext()){
            UserInfo userInfo = new UserInfo();
            userInfo.setNick(cursor.getString(
                    cursor.getColumnIndex(ContactTable.COL_USER_NICK)));
            userInfo.setPhoto(cursor.getString(cursor.getColumnIndex(ContactTable.COL_USER_PHOTO)));
            userInfo.setHxid(cursor.getString(cursor.getColumnIndex(ContactTable.COL_USER_HXID)));
            userInfo.setUsername(cursor.getString(cursor.getColumnIndex(ContactTable.COL_USER_NAME)));
            //将单个用户添加到集合中
            userInfos.add(userInfo);
        }
        cursor.close();//关掉游标
        return userInfos;
    }

    // 通过环信id获取联系人单个信息
    public UserInfo getContactByHx(String hxId){
        //校验
        if (TextUtils.isEmpty(hxId)){
            return null;
        }
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        String sql = "select * from "+ContactTable.TABLE_NAME
                + " where "+ContactTable.COL_USER_HXID+"=?";
        Cursor cursor = database.rawQuery(sql, new String[]{hxId});
        UserInfo userInfo = new UserInfo();
        if (cursor.moveToNext()){
            userInfo.setHxid(cursor.getString(cursor.getColumnIndex(ContactTable.COL_USER_HXID)));
            userInfo.setNick(cursor.getString(cursor.getColumnIndex(ContactTable.COL_USER_NICK)));
            userInfo.setUsername(cursor.getString(cursor.getColumnIndex(ContactTable.COL_USER_NAME)));
            userInfo.setPhoto(cursor.getString(cursor.getColumnIndex(ContactTable.COL_USER_PHOTO)));

        }
        cursor.close();
        return userInfo;
    }

    // 通过环信id获取用户联系人信息
    public List<UserInfo> getContactsByHx(List<String> hxIds){
        //校验
        if (hxIds == null || hxIds.size()==0){
            return null;
        }
        List<UserInfo> userInfos = new ArrayList<>();
        for (int i = 0; i < hxIds.size(); i++) {
            UserInfo userinfo = getContactByHx(hxIds.get(i));
            if (userinfo!=null){
                userInfos.add(userinfo);
            }
        }
        return userInfos;
    }

    // 保存单个联系人
    public void saveContact(UserInfo user, boolean isMyContact){
        //校验
        if (user == null){
            //return;
//            throw new NullPointerException("user不能为空，别想害我");
            //throw new Exception("aaa");
            return;
        }
        SQLiteDatabase database = dbHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(ContactTable.COL_USER_HXID,user.getHxid());
        contentValues.put(ContactTable.COL_USER_NAME,user.getUsername());
        contentValues.put(ContactTable.COL_USER_NICK,user.getNick());
        contentValues.put(ContactTable.COL_USER_PHOTO,user.getPhoto());
        contentValues.put(ContactTable.COL_IS_CONTACT,isMyContact?1:0);

        database.replace(ContactTable.TABLE_NAME,null,contentValues);


    }


    // 保存联系人信息
    public void saveContacts(List<UserInfo> contacts, boolean isMyContact){
        //校验
        if (contacts == null || contacts.size() == 0){
            return;
        }
        for (int i = 0; i < contacts.size(); i++) {
            saveContact(contacts.get(i),isMyContact);
        }
    }

    // 删除联系人信息
    public void deleteContactByHxId(String hxId){
        if (TextUtils.isEmpty(hxId)){
            return;
        }
        SQLiteDatabase database = dbHelper.getWritableDatabase();

        database.delete(ContactTable.TABLE_NAME,
                ContactTable.COL_USER_HXID+"=?",new String[]{hxId});
    }

}
