package com.itheima.caller;

import com.itheima.remoteservice.IService;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //绑定远程 服务
    public void bindservice(View v){
    	Intent intent = new Intent();
    	intent.setAction("com.itheima.rms");
    	bindService(intent, new MyConnection(), BIND_AUTO_CREATE);
    }
    
    private IService agent;
    private class MyConnection implements ServiceConnection{

		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			
			// agent =  (IService )service
			agent = IService.Stub.asInterface(service);
		}

		@Override
		public void onServiceDisconnected(ComponentName name) {
			
		}
    	
    }
    
    //调用远程 服务中的方法
    public void call(View v){
    	
    	System.out.println("调用者 调用了 远程服务中的方法 ");
    	try {
			agent.callMethodInService();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
    	
    }
    
}
