package com.itheima.quickstartservice;

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
    
    //开启 服务
    public void start(View v){
    	
    	Intent intent = new Intent();
    	intent.setClass(this, QuickStartService.class);
    	startService(intent);
    }
    //关闭  服务
    public void stop(View v){
    	
    	Intent intent = new Intent();
    	intent.setClass(this, QuickStartService.class);
    	stopService(intent);
    }
    
}
