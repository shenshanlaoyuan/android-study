package com.itheima.callservice;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.widget.Toast;

public class TestService extends Service {

	
	// 小蜜 
	//代理人 
	private class MyAgent extends Binder implements IService{
		
		public void callMethodInService(String name,int money){
			if(money<150){
				Toast.makeText(TestService.this, "对不起, 俺们 要按 制度 去办事儿...", 0).show();
			}else{
				methodInService(name,money);
			}
			
		}
		
		public void 洗桑拿(){
			System.out.println("一起去洗桑拿 ... ");
		}
		
		public void 打麻将(){
			System.out.println("一起去打麻将 ... ");
			
		}
		
	}
	
	
	// 当   绑定了 服务的时候, 就 会调用  onBind ,返回 内线 (代理人 )
	@Override
	public IBinder onBind(Intent intent) {
		return new MyAgent();
	}
	
	@Override
	public void onCreate() {
		System.out.println("服务创建了 ");
		super.onCreate();
	}

	@Override
	public void onDestroy() {
		System.out.println("服务销毁 了 ");
		super.onDestroy();
	}
	
	//服务中的方法 
	public void methodInService(String name , int money){
		
		Toast.makeText(this,  name + ", 你是暂住证 办好了 ....", 0).show();
	}
	
}
