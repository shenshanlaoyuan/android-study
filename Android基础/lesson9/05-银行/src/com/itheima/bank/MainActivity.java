package com.itheima.bank;

import android.os.Bundle;
import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		BankDBOpenHelper helper = new BankDBOpenHelper(this);
		SQLiteDatabase db = helper.getWritableDatabase();
		
	}


}
