package com.itheima.phonelistener;

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

	//¿ªÆô·þÎñ
	public void startService(View v){
		
		Intent intent = new Intent();
		intent.setAction("com.itheima.recoder");
		
		startService(intent);
	}

}
