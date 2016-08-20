package com.itheima.phonelistener;

import java.io.IOException;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaRecorder;
import android.os.IBinder;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;

public class PhoneService extends Service {

	private static final String LOG_TAG = "PhoneService";

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	TelephonyManager tm;
	@Override
	public void onCreate() {
		System.out.println("服务创建了 ");
		super.onCreate();

		// 监听电话的状态 , 如果电话来临 ,并且 接通了电话, 就 后台 录音

		tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);

		if(listener==null)
			listener = new MyPhoneStateListener();
		
		tm.listen(listener, PhoneStateListener.LISTEN_CALL_STATE);

	}
	private MyPhoneStateListener listener;
	
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		tm.listen(listener, PhoneStateListener.LISTEN_NONE);
	}

	private class MyPhoneStateListener extends PhoneStateListener {

		@Override
		public void onCallStateChanged(int state, String incomingNumber) {
			super.onCallStateChanged(state, incomingNumber);

			switch (state) {
			case TelephonyManager.CALL_STATE_IDLE: // 电话空闲 -- 就是正常的 待机, stand by

				// 停止 录音
				if(mRecorder!=null){
					stopRecording();
				}
				
				break;
			case TelephonyManager.CALL_STATE_OFFHOOK: // 电话接通的状态

				// 开启 录音
				startRecording();
				break;
			case TelephonyManager.CALL_STATE_RINGING: // 电话正在响

				break;

			default:
				break;
			}

		}
	}

	private MediaRecorder mRecorder;

	// 开始录音
	private void startRecording() {
		
		// 实例化  MediaRecorder
		mRecorder = new MediaRecorder();
		
		//  指定一个 源  
		mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
		//输出的 数据的 格式 
		mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);

		// 文件的 保存到哪里  
		mRecorder.setOutputFile("/mnt/sdcard/yy.3gp");
		//  使用 什么 解码器 
		mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

		try {
			mRecorder.prepare();
		} catch (IOException e) {
			Log.e(LOG_TAG, "prepare() failed");
		}

		mRecorder.start();
	}

	// 停止 录音
	private void stopRecording() {
		mRecorder.stop();
		mRecorder.release();
		mRecorder = null;
	}

}
