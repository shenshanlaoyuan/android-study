package com.itheima.orderdbroadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class XiangjiReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
	
		//拿到 上级  中央政府 发的 数据
		String data = getResultData();
		
		System.out.println("我是乡  级 政府 , 收到  中央的指令是 : " + data);
		setResultData("每人 人手 一个 蘑菇 ");
	}

}
