package com.itheima.orderdbroadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class NongminReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
	
		//拿到 上级  中央政府 发的 数据
		String data = getResultData();
		
		System.out.println("我是  农民  , 收到  中央的指令是 : " + data);
		System.out.println("谢谢啊, 我 思密达  xxxxx");
	}

}
