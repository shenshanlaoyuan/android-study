package com.itheima.quickstartservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.SystemClock;
/*
 * 	服务的父类  是 activity的 父类的 父类 
 * 
 *   编写 服务类 
 * 
 * 	activity---- 是有 用户界面的 组件 
 *  Service ---- 实际上就是一个 没有 界面的 activity
 * 
 */
public class QuickStartService extends Service {

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	private boolean flag;
	
	@Override
	public void onCreate() {
		super.onCreate();
		
		System.out.println("oncreate 服务 创建了  : " + Thread.currentThread().getName());
		/*new Thread(){
			
			public void run() {

				flag = true;
				while(flag){
					
					System.out.println("监听 usb 口 是否 插入了 u盘 设备  ++++++++  服务的");
					SystemClock.sleep(2000);
				}
				
			};
		}.start();*/
		
	}
	
	@Override
	public void onStart(Intent intent, int startId) {
		super.onStart(intent, startId);
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		
		System.out.println("服务 收到 了  开启的 指令了 :  onStartCommand"  );
		return super.onStartCommand(intent, flags, startId);
	}
	
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		flag =false;
		System.out.println("onDestroy 服务 销毁 了 ");
	}
	
}
