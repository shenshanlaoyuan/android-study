package com.itheima.studentsystem;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.itheima.studentsystem.domain.Student;

public class StudentDao {
	
	StudentDbOpenHelper helper;
	
	public StudentDao(Context context){
		
		helper = new StudentDbOpenHelper(context);
	}
	
// "create table students (_id integer primary key autoincrement, name varchar(30),sex varchar(10))"
	public void add(Student st){
		
		//拿到 工具类的实例 , 然后去操作 数据库 
		SQLiteDatabase db = helper.getWritableDatabase();
		
		//执行 sql 语句
		db.execSQL("insert into students values(null,?,?)",new Object[]{st.getName(),st.getSex()});
		
	}
	
	public void delete(String id){
		
		SQLiteDatabase db = helper.getWritableDatabase();
		db.execSQL("delete from students where _id=?",new Object[]{id});
		
	}
	public void update(Student st){
		
		SQLiteDatabase db = helper.getWritableDatabase();
		
		db.execSQL("update students set name=?,sex=? where _id=?", new Object[]{st.getName(),st.getSex(),st.getId()});
	}
	
	public Student find(String id){
		
		SQLiteDatabase db = helper.getReadableDatabase();
		
		// select * from users where id=?
		
		//叫做 游标 , 与 javaweb 中所学的  resultSet 结构是一样的  
		Cursor cursor = db.rawQuery("select * from students where _id=?", new String[]{id});
		
		boolean result = cursor.moveToNext();
		
		Student st = null;
		if(result){
//			st = new Student();
			/*int _id = cursor.getInt(0);
			String name = cursor.getString(1);
			String sex = cursor.getString(2);
			st.setId(id);
			st.setName(name);
			st.setSex(sex);*/
			
			int _id = cursor.getInt(cursor.getColumnIndex("_id"));
			String name = cursor.getString(cursor.getColumnIndex("name"));
			String sex =  cursor.getString(cursor.getColumnIndex("sex"));
			
			st = new Student(String.valueOf(_id),name,sex);
			
		}
		
		// 最后会释放 资源 
		cursor.close();
		
		return st;
	}
	
}
