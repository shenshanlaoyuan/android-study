package com.itheima.link;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
/*
 * 
 *     
 * 
 * 
 */
public class MainActivity extends Activity {

	private TextView link2go;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		link2go = (TextView) findViewById(R.id.link2go);
		
		link2go.setOnClickListener(new OnClickListener() {
/*
 * 
<intent-filter>
        <action android:name="android.intent.action.VIEW" />
        <category android:name="android.intent.category.DEFAULT" />
        <category android:name="android.intent.category.BROWSABLE" />
        <data android:scheme="http" />
        <data android:scheme="https" />
        <data android:scheme="about" />
        <data android:scheme="javascript" />
</intent-filter>

 */
			@Override
			public void onClick(View v) {
				
				//发送一个intent 出去, 去 让 浏览器 跳到 黑马程序员的 网站 
				Intent intent = new Intent();
				intent.setAction("android.intent.action.VIEW");
				intent.addCategory("android.intent.category.DEFAULT");
				intent.addCategory("android.intent.category.BROWSABLE");
				intent.setData(Uri.parse("http://www.itheima.com"));
				startActivity(intent);
			}
		});
	}


}
