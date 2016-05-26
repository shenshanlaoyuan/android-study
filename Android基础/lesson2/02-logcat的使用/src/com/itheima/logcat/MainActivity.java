package com.itheima.logcat;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;

public class MainActivity extends Activity {

	private final String TAG = "MainActivity";
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        Log.v(TAG, "俺是 一个 详细 的日志 信息");
        Log.i(TAG, "俺是 一个 一般的 的日志 信息");
        Log.w(TAG, "俺是 一个  警告  的日志 信息");  // warn
        Log.e(TAG, "俺是 一个 错误 的日志 信息");
        Log.wtf(TAG, "俺是 一个xxxxx 的日志 信息");
        
    }

    
}
