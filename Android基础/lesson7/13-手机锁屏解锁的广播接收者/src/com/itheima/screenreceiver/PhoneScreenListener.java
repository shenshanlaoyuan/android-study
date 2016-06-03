package com.itheima.screenreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class PhoneScreenListener extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		
		System.out.println("========= 手机  屏幕 解锁或者锁屏了 ");
		
	}

}
