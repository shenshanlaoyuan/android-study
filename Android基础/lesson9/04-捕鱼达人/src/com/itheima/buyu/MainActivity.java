package com.itheima.buyu;

import com.itheima.alipay.IAlipayService;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	public void bindService(View v){
		
		//绑定 alipay的 服务
		
		Intent intent = new Intent();
		intent.setAction("com.itheima.alipay");
		bindService(intent, new MyConnection(), BIND_AUTO_CREATE);
		
	}
	
	private IAlipayService agent;
	
	private class MyConnection implements ServiceConnection{

		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			
			agent = IAlipayService.Stub.asInterface(service);
			
		}

		@Override
		public void onServiceDisconnected(ComponentName name) {
			
		}
		
	}
	
	public void call(View v){
		
		try {
			int result = agent.callPayInService("totemzl@xxx.com", 150f);
			System.out.println(result);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
}
