package com.atguigu.im0224.modle.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.atguigu.im0224.modle.table.AccountTable;

/**
 * Created by Administrator on 2017/7/1.
 */

/*
*
create table student(id text primary key,name text)

insert into student(id,name) values('10','小福')

insert into student(name) values('小福')

insert into student(id,name) values('1','小仓')

insert into student(id,name) values('2','志玲')

update student set  id = '3' where name = '志玲'

select name from student where id = '1'

delete from student where id = '1'
*
* */
public class AccountDB extends SQLiteOpenHelper {

    public AccountDB(Context context) {
        super(context, "account.db", null, 1);
    }


    /*
    * 创建数据库
    * */
    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(AccountTable.CREATE_TABLE);
    }

    /*
    * 更新数据库
    * */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
