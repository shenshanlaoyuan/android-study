package com.itheima.launchmode;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class SecondActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main02);
	}

	//Æô¶¯ firstActivity
	public void open01(View v) {

		Intent intent = new Intent();
		intent.setClass(this, FirstActivity.class);
		startActivity(intent);
	}
	//Æô¶¯ secondActivity
	public void open02(View v) {
		
		Intent intent = new Intent();
		intent.setClass(this, SecondActivity.class);
		startActivity(intent);
	}


}
