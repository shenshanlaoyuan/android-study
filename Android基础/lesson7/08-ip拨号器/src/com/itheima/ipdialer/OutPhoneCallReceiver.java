package com.itheima.ipdialer;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

public class OutPhoneCallReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		
		System.out.println("OutPhoneCallReceiver 收到了 外拨 电话的 广播 ");
		String data = getResultData();
		
		//拿到 sharedPreference --- 如何拿 呢 ?
		SharedPreferences sp = context.getSharedPreferences("config", 0);
		
		String prefix = sp.getString("prefix", "");
		if(data.startsWith("0")){
			//说明是打 长途 电话 ,那么 就 在 拨打的长途 电话 号码前 加拨  prefix 	前缀的 值 
			setResultData(prefix+data);
//			abortBroadcast();
		}
		
	}

}
