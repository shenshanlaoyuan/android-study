package com.itheima.callservice;

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

	//开启 服务
	public void startservice(View v){
		Intent intent = new Intent();
		intent.setClass(this, TestService.class);
		startService(intent);
		
//		bindService(service, conn, flags)
	}
	//关闭 服务
	public void stopservice(View v){
		
		Intent intent = new Intent();
		intent.setClass(this, TestService.class);
		stopService(intent);
	}
	//调用服务 中的方法 
	public void call(View v){
		
		//new  对象, 直接去调用, 是没有 Context 的, 所以 弹 土司失败了 .
		TestService service = new TestService();
		service.methodInService();
	}

}
