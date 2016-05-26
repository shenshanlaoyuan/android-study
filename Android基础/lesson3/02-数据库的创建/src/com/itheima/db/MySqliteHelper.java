package com.itheima.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class MySqliteHelper extends SQLiteOpenHelper {

	public MySqliteHelper(Context context) {
		// context :应用上下文 
		// name : 数据库的名称 
		// factory :  创建游标的工厂  
		// version :  数据库的版本 
		
		super(context, "mydb1", null,4);
	}
/*
 mysql 
 
create table users(
   id int primary key auto_increment,
   name varchar(30)
);

android 中 : 

create table students(
   id integer primary key autoincrement,
   name varchar(30)
);

 */
	
	// 在 数据库 首次 被 创建时 会调用
	@Override
	public void onCreate(SQLiteDatabase db) {
		
		System.out.println("onCreate 执行了 -================= ");
		db.execSQL("create table students ( _id integer primary key autoincrement,name varchar(30), sex varchar(10))");
		
	}

	// 在 数据库  升级时 会调用  -- 当 版本 比 之前的版本 高 的时候 就  会执行这个方法 
	// 这里 可以 去 修改表的结构
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		
		System.out.println("onUpgrade 执行了 ==========");
		//该 一下 表的结构
		db.execSQL("alter table students add number varchar(10)");
		
		/*switch (key) {
		case 1:
			
			break;
		case 2:
			
			break;

		default:
			break;
		}
		*/
		
	}

}
