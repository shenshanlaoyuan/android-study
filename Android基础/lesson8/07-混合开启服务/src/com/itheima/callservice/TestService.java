package com.itheima.callservice;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.widget.Toast;

public class TestService extends Service {

	private class MyAgent extends Binder implements IService{

		@Override
		public void callMethodInService(String name, int money) {
			
			//调用 服务中的方法
			methodInService(name, money);
		}
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		System.out.println(" onStartCommand  执行了  ");
		return super.onStartCommand(intent, flags, startId);
	}
	
	@Override
	public IBinder onBind(Intent intent) {
		
		System.out.println("on bind 执行了 , 绑定了 服务  ");
		return new MyAgent();
	}
	
	@Override
	public boolean onUnbind(Intent intent) {
		System.out.println("on onUnbind  执行了 ,  解绑 了 服务  ");
		return super.onUnbind(intent);
	}
	
	@Override
	public void onCreate() {
		System.out.println(" 服务创建了 ");
		super.onCreate();
	}

	@Override
	public void onDestroy() {
		System.out.println("服务销毁 了 ");
		super.onDestroy();
	}
	
	//服务中的方法 
	public void methodInService(String name, int money){
		Toast.makeText(this, "服务中的方法被调用到了", 0).show();
		System.out.println("服务中的方法被调用到了 ....");
	}
	
}
