package com.atguigu.im0224.modle.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.atguigu.im0224.modle.table.ContactTable;
import com.atguigu.im0224.modle.table.InvitationTable;

/**
 * Created by Administrator on 2017/7/3.
 */

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context, String name) {
        super(context, name, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(ContactTable.CREATE_TABLE); //联系人表
        db.execSQL(InvitationTable.CREATE_TABLE); //邀请表
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
