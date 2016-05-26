package com.itheima.studentsystem;

import com.itheima.studentsystem.domain.Student;

import android.test.AndroidTestCase;
/*
 * 
 *   注意 :写测试　程序　，需要在　清单　文件　ｍａｎｉｆｅｓ　中添加　 instrumentation 以及 use-library
 * 
 */
public class TestStudentDao extends AndroidTestCase{

	public void testAdd(){
		
		StudentDao sdao = new StudentDao(getContext());
		for (int i = 0; i < 50; i++) {
			Student st = new Student("8798", "二蛋"+i, "男");
			sdao.add(st);
			
		}
	}
	
	public void testDelete(){
		StudentDao sdao = new StudentDao(getContext());
		sdao.delete("1");
	}
	
	public void testUpdate(){
		
		Student s = new Student();
		s.setId("2");
		s.setName("二球的 姐姐");
		s.setSex("女");
		StudentDao sdao = new StudentDao(getContext());
		sdao.update(s);
	}
	
	public void testFind(){
		StudentDao sdao = new StudentDao(getContext());
		Student student = sdao.find("2");
		
		System.out.println(student.toString());
	}
}
