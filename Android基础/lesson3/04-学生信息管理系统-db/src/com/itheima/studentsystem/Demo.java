package com.itheima.studentsystem;

import java.util.ArrayList;
import java.util.List;

import com.itheima.studentsystem.domain.Student;

public class Demo {
	
	public static void main(String[] args) {
		
		List<Student> list =new ArrayList<Student>();
		
//		list.add(new Student());
//		list.add(new Student());
//		list.add(new Student());
//		list.add(new Student());
//		list.add(new Student());
		
		Student s1 = new Student();
		Student s2 = new Student();
		Student s3 = new Student();
		list.add(s1);
		list.add(s2);
		list.add(s3);
		
		list = null;
		s1=null;
		s2=null;
		s3=null;
		
		
		// 不用还个list 了
		
		
		//出现了 内存 的 泄漏 
		
		
	}
}
