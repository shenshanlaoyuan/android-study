package com.itheima.bootcompletion;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class BootCompletionReceiver extends BroadcastReceiver {

	
	// 监听 开机启动完成 
	@Override
	public void onReceive(Context context, Intent intent) {

		String action = intent.getAction();
		System.out.println("开机完成了 .... : " + action);
		
		/// 干我 想干的 事儿,  后台 发 一些 数据 给 服务器 ...
		
	}

}
