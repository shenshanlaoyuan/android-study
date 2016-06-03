package com.itheima.screenreceiver;

import android.os.Bundle;
import android.app.Activity;
import android.content.IntentFilter;
import android.view.Menu;

public class MainActivity extends Activity {

	private PhoneScreenListener phoneReceiver;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//注册 监听 手机 解锁/锁屏的 广播接收者 
		phoneReceiver = new PhoneScreenListener();
		
		IntentFilter filter = new IntentFilter();
		filter.addAction("android.intent.action.SCREEN_OFF");
		filter.addAction("android.intent.action.SCREEN_ON");
		
		//注册 广播接收者 
		registerReceiver(phoneReceiver, filter);
		
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		// 解除  注册 的 广播接收者 
		unregisterReceiver(phoneReceiver);
	}
	

}
