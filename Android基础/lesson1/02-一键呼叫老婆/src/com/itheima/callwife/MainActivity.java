package com.itheima.callwife;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

	
	private Button btn;
	
	//一般 做 初始化的 工作 ,主要用来 去 初始化一些 控件 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        //设置 当前的 应用程序 的 欢迎 页面 
        setContentView(R.layout.activity_main);
        
        //初始化 button 控件了 
         btn= (Button) findViewById(R.id.callwife);
        
        //设置响应的onclick回调的监听器 
         btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				System.out.println("====  呼叫老婆的 按钮被点击了 ..");
				
				// 给 老婆 打 电话  , 没必要自己去写个程序 给 老婆 打 电话 
				
				// 
				// 通过 意图  (intent)来 告诉 其他的应用程序 我想 打 电话 
				// 意图 :  intent 
				
				// 打 人 ,  打球 , 打 假 , 打给老婆 ,  .... 
				// 泡 茶, 泡 妞 , 泡 温泉 
				//前面 都是动作, 后面的 动作 具体需要的数据
				
				//让 可以 去 打电话的应用帮 我去完成这个事儿 
				Intent intent = new Intent();
				intent.setAction(Intent.ACTION_CALL);
				// 打 电话具体 需要 的数据
				//  http://www.itheima.com     ftp://192.68.1.100/1.html
				// chrome://settings
				//打电话 也需要 相应 的 协议  :   tel://5201314
				intent.setData(Uri.parse("tel://5201314"));
				
				startActivity(intent);
			}
		});
         
         
    }


}
