package com.itheima.ipdialer;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

	private EditText ed_prefix;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ed_prefix = (EditText) findViewById(R.id.ed_prefix);
	}

	public void save(View v){
		
		String prefix = ed_prefix.getText().toString().trim();
		if(TextUtils.isEmpty(prefix)){
			Toast.makeText(this, "ip 拨号前缀不能为空 ...", 0).show();
			return;
		}
		
		SharedPreferences sp = getSharedPreferences("config", 0);
		Editor edit = sp.edit();
		edit.putString("prefix", prefix);
		edit.commit();
		Toast.makeText(this, "ip 拨号前缀  保存 成功 ", 0).show();
	}
	
	
}
