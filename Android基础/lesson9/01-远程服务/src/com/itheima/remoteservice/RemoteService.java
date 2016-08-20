package com.itheima.remoteservice;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
/*
 * 远程 服务 
 * 
 */
public class RemoteService extends Service {

	//  class Stub extends android.os.Binder implements com.itheima.remoteservice.IService 
	// 由于  Stub 类继承了 Binder , 实现了 IService 接口, 所以 这里 内线 类 MyAgent 只需要去 集成 IService.Stub 类就可以了 
	private class MyAgent extends IService.Stub{

		@Override
		public void callMethodInService() {
			methodInService();
		}
	}
	
	@Override
	public IBinder onBind(Intent intent) {
		return new MyAgent();
	}
	
	@Override
	public void onCreate() {
		super.onCreate();
		
		System.out.println("远程 服务创建了 ");
	}
	
	@Override
	public void onDestroy() {
		System.out.println("远程 服务销毁 了 ");
		super.onDestroy();
	}
	
	public void methodInService(){
		System.out.println("远程 服务  中的方法 被 调用了 ");
	}
	
}
