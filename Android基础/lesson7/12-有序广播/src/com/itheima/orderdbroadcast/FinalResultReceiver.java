package com.itheima.orderdbroadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class FinalResultReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		
		// 当这里 执行的时候, 表示  是最终的接受者 收到的了 数据了 
		String data = getResultData();
		System.out.println("这个是 恩恩的内线 , 收到的  信息是 : " +data);
	}

}
