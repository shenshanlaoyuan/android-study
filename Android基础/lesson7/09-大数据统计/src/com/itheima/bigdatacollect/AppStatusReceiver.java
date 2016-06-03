package com.itheima.bigdatacollect;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class AppStatusReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		String action = intent.getAction();
		
		System.out.println(action);
		if("android.intent.action.PACKAGE_ADDED".equals(action)){
			System.out.println("应用程序被 安装了  : " + intent.getData());
		}else if("android.intent.action.PACKAGE_REMOVED".equals(action)){
			System.out.println("应用程序被 卸载  了 " + intent.getData());
		}
		
	}

}
