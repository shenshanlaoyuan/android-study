package com.itheima.callservice;


import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;

public class MainActivity extends Activity {

	IService agent;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	//绑定  服务
	public void startservice(View v){
		Intent intent = new Intent();
		intent.setClass(this, TestService.class);
		
		// intent : 绑定哪个 服务
		// conn : 通讯的 频道 -- 与内线 建立 起 联系 
		// BIND_AUTO_CREATE : 绑定的 时候, 就去 创建 服务 
		
		
		bindService(intent, new MyConnection(), BIND_AUTO_CREATE);
	}
	
	private class MyConnection implements ServiceConnection{

		//当 成功的 绑定了 服务, 返回 内线 的 时候 会调用的 方法 , 用于 返回 内线对象 
		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			
			//获得了 对 内线 引用 
			agent = (IService) service;
			
		}

		//当 服务  解除 绑定的  时候  ,  会调用的 方法 , 释放 资源 
		@Override
		public void onServiceDisconnected(ComponentName name) {
			
		}
		
	}
	
	//关闭 服务
	public void stopservice(View v){
		
		Intent intent = new Intent();
		intent.setClass(this, TestService.class);
		stopService(intent);
	}
	//调用服务 中的方法 
	public void call(View v){
		
//		agent.callMethodInService("博博", 250);
		agent.callMethodInService("小丽丽", 300);
		
	}

}
