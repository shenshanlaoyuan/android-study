package com.itheima.notification;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}


	public void sendNotification(View v){
		
		// 首先 需要 拿到 一个叫做 NotificationManager的对象 
		NotificationManager nm = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		
		//构建一个Notification对象 
		
		// 链式调用,  方法串 儿 
		Notification notification = new Notification.Builder(this)
        .setContentTitle("我是一个 标题")
        .setContentText("xzxxxxx")
        .setSmallIcon(R.drawable.ic_launcher)
        .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher))
        .build();
				
		// 这个 api , 在低版本 是用不了 的 ,  得 找 一种 过时的写法 
		
		nm.notify(1, notification);
		
	}
	
}
