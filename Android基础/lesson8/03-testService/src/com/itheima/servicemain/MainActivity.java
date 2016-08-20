package com.itheima.servicemain;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.telephony.TelephonyManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
//		View.inflate(context, resource, root);
		
		//android系统 系统 启动的时候, 会启动 很多 很多的 服务 
		// 这些服务就在后台运行着, 如何去与这些服务  服务 进行 交互呢 ,
		// 如果要与 系统的这些服务进行 交互, 谷歌工程 统一的 暴露 给应用层层 各种 XXXManager
		
		getSystemService(Context.POWER_SERVICE);
		
		PackageManager packageManager = getPackageManager();
		
//		TelephonyManager managere = getSystemService(Context.TELEPHONY_SERVICE);
		
		
//		 LayoutInflater LayoutInflater =
//	                (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

}
