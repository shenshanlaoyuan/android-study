package com.itheima.domain;

import junit.framework.Assert;


import android.test.AndroidTestCase;
/*
 *  在android 中测试 的 环境 变了, 由 以前的 jvm , 变为了 现在 手机 环境 ...
 * 	
 * 
 * 		谷歌 将 junit 测试 框架集成了 进来,  并且 提供好了 一个 现成的类, 咱们去写 测试 程序的时候, 
 * 		只需要去继承 这个类就可以了. 
 * 
 * 
 */
public class TestPerson extends AndroidTestCase{
	
	//这个地方 不要 加 注解了 
	
	public void testAdd() throws Exception{
		
		Person p = new Person();
		p.add(1, 2);
		
	}
	public void testAdd2(){
		
		Person p = new Person();
		int result = p.add(1, 2);
		
		Assert.assertEquals(0, result);
		
		
	}
	
}
