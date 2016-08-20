package com.itheima.neigui;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	public void add(View v){
		
		ContentResolver resolver = getContentResolver();
		//这里是 固定的 写法 
		Uri uri = Uri.parse("content://com.itheima.db/accounts");
		
		ContentValues values = new ContentValues();
		
		resolver.insert(uri, values);
		
	}
	
}
