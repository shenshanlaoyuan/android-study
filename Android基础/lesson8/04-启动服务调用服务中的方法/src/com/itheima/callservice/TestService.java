package com.itheima.callservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

public class TestService extends Service {

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}
	
	@Override
	public void onCreate() {
		System.out.println("服务创建了 ");
		super.onCreate();
	}

	@Override
	public void onDestroy() {
		System.out.println("服务销毁 了 ");
		super.onDestroy();
	}
	
	//服务中的方法 
	public void methodInService(){
		Toast.makeText(this, "服务中的方法被调用到了", 0).show();
		System.out.println("服务中的方法被调用到了 ....");
	}
	
}
