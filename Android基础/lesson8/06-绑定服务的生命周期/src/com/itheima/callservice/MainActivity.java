package com.itheima.callservice;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	MyConn conn;
	
	//绑定  服务
	public void bindService(View v){
		
		Intent intent = new Intent();
		intent.setClass(this, TestService.class);
		
		if(conn==null){
			
			conn =new MyConn();
			bindService(intent, conn, BIND_AUTO_CREATE);
		}
		
		
	}
	
	private IService agent;
	
	private class MyConn implements ServiceConnection{

		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			System.out.println("=======服务 绑定的时候 执行了   onServiceConnected++++++");
			agent = (IService) service;
		}

		
		// memory leak 
		@Override
		public void onServiceDisconnected(ComponentName name) {
			agent = null; 
			System.out.println("=======服务 绑定的时候 执行了   onServiceDisconnected");
			
		}
	}
	
	//解绑  服务
	public void unbindService(View v){
		
		Intent intent = new Intent();
		intent.setClass(this, TestService.class);
		if(conn!=null){
			
			//解绑 服务 
			unbindService(conn);
			//这里 养成好习惯, 将 conn 
			conn =null;
		}
	}
	
	//调用服务 中的方法 
	public void callMethodInService(View v){
		
		agent.callMethodInService("二球", 1000);
	}

}
