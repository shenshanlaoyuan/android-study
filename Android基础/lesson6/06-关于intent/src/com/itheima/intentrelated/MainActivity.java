package com.itheima.intentrelated;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	// 显示意图激活 02 
	public void click01(View v) {
		System.out.println("显示意图激活 02");
		Intent intent = new Intent();
		
		//明确 指定要激活 哪个应用中的, 哪个 activity
		intent.setClass(this, SecondActivity.class);
		startActivity(intent);
		
	}
/*
<intent-filter >
	    <action android:name="com.itheima.xxx"/>
	    <category android:name="android.intent.category.DEFAULT"/>
</intent-filter>
 * 	
 */
	
	// 隐式意图激活 02 
	public void click02(View v) {
		System.out.println("隐式意图激活 02 ");
		Intent intent = new Intent();
		intent.setAction("com.itheima.xxx");
		//intent.addCategory("android.intent.category.DEFAULT");
		startActivity(intent);
	}

	// 在激活其他 的组件的时候,  可以 使用intent 去激活, 
	// 使用的时候, 可以 显示 意图 , 也可以使用  隐式意图
	
	// 到底什么时候选哪种呢 ?
	// 如果 要激活的组件 在 本应用 内部, 那么 就推荐使用 显示 意图 , 如果 要激活 组件 不是在本 应用中,那么推荐 采用   隐式 意图 
	
	// 如果 要激活的组件 不再 本应用中, 则 只能够使用   隐式 意图 
	
	
}
