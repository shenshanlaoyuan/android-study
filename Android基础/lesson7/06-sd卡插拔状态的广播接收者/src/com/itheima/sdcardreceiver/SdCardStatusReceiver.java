package com.itheima.sdcardreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class SdCardStatusReceiver extends BroadcastReceiver {

	
	// sd卡的 插拔 状态 的广播是 由 底层 linux  系统 监听到  sd 卡插入 后, 通知 framework层 
	// framework层的 mediaServer发出的 
	
	//当 每次 接收 到 广播是 会被调用到 的方法 
	@Override
	public void onReceive(Context context, Intent intent) {

		String action = intent.getAction();
		
		System.out.println(" 接收到 sd 卡的 插拔状态 修改了 ...: "+ action);
	}

}
