package com.itheima.qvod;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		System.out.println("快播 播放器 被 创建了 ..onCreate ");
	}

	@Override
	protected void onStart() {
		super.onStart();
		System.out.println("播放 界面   可见了, 继续 视频的 播放  ");
	}
	
	@Override
	protected void onStop() {
		super.onStop();
		System.out.println("播放 界面 不可见了, 暂停视频的 播放  ");
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		System.out.println("快播 播放器 被 销毁了 ..onCreate ");
	}
	
}
