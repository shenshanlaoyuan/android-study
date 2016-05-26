package com.itheima.studentsystem;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
/*
 *    直接 与 底层数据库打 交道的 
 * 
 */
public class StudentDbOpenHelper extends SQLiteOpenHelper {

	public StudentDbOpenHelper(Context context) {
		super(context, "student.db", null, 1);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {

		//创建数据库 
		db.execSQL("create table students (_id integer primary key autoincrement, name varchar(30),sex varchar(10))");
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		
	}

}
