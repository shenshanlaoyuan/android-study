package com.itheima.thread;

import android.os.Bundle;
import android.os.SystemClock;
import android.app.Activity;
import android.view.Menu;

public class MainActivity extends Activity {

	private boolean flag;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		new Thread(){
			public void run() {
				flag = true;
				while(flag){
					System.out.println("检查 usb  口 是否 有 新的设备 插入 ------------------ 子线程的 ");
					SystemClock.sleep(2000);
				}
			};
		}.start();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		flag =false;
	}
	
}
