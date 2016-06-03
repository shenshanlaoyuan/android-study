package com.itheima.orderdbroadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class ShjjiReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
	
		//拿到 上级  中央政府 发的 数据
		String data = getResultData();
		
		System.out.println("我是 市  级 政府 , 收到  中央的指令是 : " + data);
		
		//取消 发送广播 
//		abortBroadcast();
		setResultData("每人发 3斤蘑菇 ");
		
	}

}
