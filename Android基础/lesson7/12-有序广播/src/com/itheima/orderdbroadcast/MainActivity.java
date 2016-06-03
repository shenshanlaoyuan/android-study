package com.itheima.orderdbroadcast;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	
	//发送有序广播 
	//给 全国人民 发 蘑菇 
	//中央政府 , 给 全国人民 发福利 
	public void sendMarshroom(View v){
		
		Intent intent = new Intent();
		intent.setAction("com.itheima.send.MARSHROOM");
/*
 * intent The Intent to broadcast; all receivers matching this Intent will receive the broadcast.
	receiverPermission String naming a permissions that a receiver must hold in order to receive your broadcast. If null, no permission is required.
	resultReceiver Your own BroadcastReceiver to treat as the final receiver of the broadcast.
	scheduler A custom Handler with which to schedule the resultReceiver callback; if null it will be scheduled in the Context's main thread.
	initialCode An initial value for the result code. Often Activity.RESULT_OK.
	initialData An initial value for the result data. Often null.
	initialExtras An initial value for the result extras. Often null.
 */
		// intent :　意图 对象 
		// receiverPermission :　　 接收广播的 组件需要什么样的权限  
		// resultReceiver : 广播事件的最终 接收者  --- 中央政府的 内线 --- 也是广播 接收者  
		// scheduler :  调度器 
		// initialCode :　发送广播时的初始码  
		// initialData : 广播 发出的 原始数据  -- 未被 修改过的原始数据
		// initialExtras: 广播发出时的一些额外的数据 
		
//		sendOrderedBroadcast(intent, null, new FinalResultReceiver(), null, 1, "每人发十斤蘑菇", null);
		sendBroadcast(intent);
	}
	
}
