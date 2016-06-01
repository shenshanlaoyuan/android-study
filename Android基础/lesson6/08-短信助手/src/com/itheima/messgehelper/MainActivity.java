package com.itheima.messgehelper;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.telephony.SmsManager;
import android.test.suitebuilder.annotation.SmallTest;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
/*
 * 
 *   面试 题 , 如果 在启动一个 activity的时候, 拿到  被启动的activity 返回的数据 
 * 
 */
public class MainActivity extends Activity {

	private EditText sms_body;
	private EditText ed_contact;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		sms_body = (EditText) findViewById(R.id.sms_body);
		ed_contact = (EditText) findViewById(R.id.ed_contact);
	}

	//选择 联系人 
	public void selectContact(View v){
		
		Intent intent = new Intent();
		intent.setClass(this, ContactListActivity.class);
		startActivityForResult(intent, 1);
	}
	
	//选择 短信 
	public void selectMsg(View v){
		
		//激活 ,跳到 B activity 所在 界面 ,
		
		// 使用 显式 意图直接去激活  SmsAcitivityList
		Intent intent = new Intent();
		intent.setClass(this, SmsAcitivityList.class);
//		startActivity(intent);
		
		//开启某个  activity 为了 某个结果 
		startActivityForResult(intent,2);
	}
	
	public void sendmsg(View v){
		
		// 
		SmsManager smsManager = SmsManager.getDefault();
		
		smsManager.sendTextMessage(ed_contact.getText().toString(), null, sms_body.getText().toString(), null, null);
		
	}
	
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		
		System.out.println(" onActivityResult 被调用了,  回到了之前的 activity ");
		if(requestCode==1){
			
			System.out.println("======1    ContactListActivity");
			if(data!=null){
				
				String contact = data.getStringExtra("contact");
				
				ed_contact.setText(contact);
			}
			
		}else if (requestCode==2){
			
			System.out.println("======1    SmsAcitivityList");
			if(data!=null){
				
				String msg = data.getStringExtra("msg");
				sms_body.setText(msg);
			}
		}
		
		
		super.onActivityResult(requestCode, resultCode, data);
	}
}
