package com.itheima.bank;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class BankDBOpenHelper extends SQLiteOpenHelper {

	
	//创建数据库 
	public BankDBOpenHelper(Context context) {
		super(context, "bank.db", null, 1);
	}

	//创建数据库 的表的 
	@Override
	public void onCreate(SQLiteDatabase db) {
		
		db.execSQL("create table accounts (_id integer primary key autoincrement, name varchar(30), money float )");
	}

	//数据库 表结构 发生变化, 升级的 时候用到的 
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}

}
