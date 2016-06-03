package com.itheima.launchmode;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class FirstActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main01);
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
//		intent.setFlags(flags)
		intent.setClass(this, SecondActivity.class);
		startActivity(intent);
	}

	

}
