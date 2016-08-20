package com.itheima.mms;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	//插入 短信 到  系统的  短信的数据库 中 
	public void add(View v){
		
		ContentResolver resolver = getContentResolver();
		
		// contentProvider使用的 时候 必须是  以   content:// 打头  
		Uri uri = Uri.parse("content://sms");
		ContentValues values = new ContentValues();
		values.put("address", "5201314");   // address
		values.put("date", System.currentTimeMillis());   // address
		values.put("type", "1");   // address
		values.put("body", "亲爱的,我想你了...");   // address
		
		resolver.insert(uri, values);
		
		System.out.println("插入 成功了 ....");
	}
	
	//删除 在 系统的  短信的数据库 中 的 短消息 
	public void delete(View v){
		
		ContentResolver resolver = getContentResolver();
		Uri uri = Uri.parse("content://sms");
		
		//  delete from a where id=? 
		resolver.delete(uri, "address=?", new String[]{"1 008-6"});
		
		System.out.println("删除  成功了 ....");
	}
	
}
