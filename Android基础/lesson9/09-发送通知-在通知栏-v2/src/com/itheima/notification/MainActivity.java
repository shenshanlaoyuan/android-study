package com.itheima.notification;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
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
		
		Notification notification = new Notification(R.drawable.ic_launcher, "xxxx标题", System.currentTimeMillis());
		Intent intent = new Intent();
		intent.setAction(Intent.ACTION_CALL);
		intent.setData(Uri.parse("tel://110"));
		
		PendingIntent pendingIntent = PendingIntent.getActivity(this, 1, intent, 1);
		
		notification.setLatestEventInfo(this, "俺是标题", "标题中文本", pendingIntent);
		nm.notify(1, notification);
		
	}
	
}
