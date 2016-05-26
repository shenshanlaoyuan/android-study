package com.itheima.studentsystem;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.itheima.studentsystem.domain.Student;
/*
 * 
 *   增删 改查 有 两套 api 了 
 *   	
 *   第一套(你经常使用的):
 *   	增删改 : db.execSQL()
 *   	查询 : db.rawQuery();
 *   
 *   第二套 (推荐使用 ): 
 *   
 *   	增: db.insert
 *   	删: db.delete();
 *      改 :  db.update();
 *      查询:   db.query();
 * 		
 * 		实际开发过程 中 : 数据库 到底用的多不多 ?  不多 ...
 * 
 * 	提供的保存 数据的方式 很多 :
 * 
 * 		保存数据的方式 :
 * 			1. 应用 内部的 私有的 文件夹下 :  /data/data/com.itheima.hello/files   /cache
 * 			2. sharedPreference :  /data/data/com.itheima.hello/shar_prefs
 * 			3. 在 sd 卡 公共的部分 :  /mnt/sdcard
 * 			4. 存到数据库 中  : sqlite 数据库 
 * 
 * 	
 * 	
 * 
 */
public class StudentDao {
	
	StudentDbOpenHelper helper;
	
	public StudentDao(Context context){
		
		helper = new StudentDbOpenHelper(context);
	}
	
// "create table students (_id integer primary key autoincrement, name varchar(30),sex varchar(10))"
	public void add(Student st){
		
		//拿到 工具类的实例 , 然后去操作 数据库 
		SQLiteDatabase db = helper.getWritableDatabase();
		
		//执行 sql 语句						//  _id, name, sex
//		db.execSQL("insert into students values(null,?,?)",new Object[]{st.getName(),st.getSex()});
		
		// nullColumnHack :  用于指定 哪几列的值 不插入的时候 是 null
		// values:  实际上 内部一个 map的结构 
		
		ContentValues values = new ContentValues();
		values.put("name", st.getName());
		values.put("sex", st.getSex());
		
		db.insert("students", null, values);
		
	}
	
	public void delete(String id){
		
		SQLiteDatabase db = helper.getWritableDatabase();
//		db.execSQL("delete from students where _id=?",new Object[]{id});
		
		db.delete("students", "_id=?", new String[]{id});
		
	}
	public void update(Student st){
		
		SQLiteDatabase db = helper.getWritableDatabase();
		
//		db.execSQL("update students set name=?,sex=? where _id=?", new Object[]{st.getName(),st.getSex(),st.getId()});
		
		ContentValues values = new ContentValues();
		values.put("name", st.getName());
		values.put("sex", st.getSex());
		
		db.update("students", values, "_id=?", new String[]{st.getId()});
	}
	
	public Student find(String id){
		
		SQLiteDatabase db = helper.getReadableDatabase();
		
		// select * from users where id=?
		
		//叫做 游标 , 与 javaweb 中所学的  resultSet 结构是一样的  
		
		// limit ?,?
		
		// distinct  ---排重 用的  
		
		//  s...f...w...g...h....0... limit 
		db.query("students", new String[]{"_id","name","sex"}, "_id=?", new String[]{id}, null, null, null);
		
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
