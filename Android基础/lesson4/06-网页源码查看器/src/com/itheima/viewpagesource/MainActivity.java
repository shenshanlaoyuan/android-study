package com.itheima.viewpagesource;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import com.itheima.viewpagesource.utils.StreamTool;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.webkit.URLUtil;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	protected static final int SUCCESS = 0;
	protected static final int ERROR = 1;
	protected static final int NETWORK_ERROR = 2;


	private EditText ed_path;
	private TextView tv;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ed_path = (EditText) findViewById(R.id.ed_path);
		tv = (TextView) findViewById(R.id.tv);
	}

	//定义一个秘书
	Handler handler = new Handler(){
		
		public void handleMessage(android.os.Message msg) {
			
			switch (msg.what) {
			case SUCCESS:
				
				String value = (String) msg.obj;
				tv.setText(value);
				break;
			case ERROR:
				System.out.println("ERROR ==============");
				Toast.makeText(MainActivity.this, "错误发生了  ..  ERROR", 0).show();
				
				break;
			case NETWORK_ERROR:
				System.out.println("NETWORK_ERROR  ==============");
				Toast.makeText(MainActivity.this, "错误发生了  ..  NETWORK_ERROR ", 0).show();
				
				
				break;

			default:
				break;
			}
		};
	};
	
	
	String path = null;
	public void viewPageSource(View v){
		
		path = ed_path.getText().toString().trim();
		
		if(TextUtils.isEmpty(path)){
			Toast.makeText(this, "路径有错误...", 0).show();
			return;
		}
		
		//连接网络  , 要启动一个新的线程去  干 耗时的事儿
		new Thread(){
			public void run() {
				
				try {
					URL url = new URL(path);
					HttpURLConnection conn = (HttpURLConnection) url.openConnection();
					
					//设置连接超时为 5 秒钟
					conn.setConnectTimeout(5000);
					conn.setRequestMethod("GET");
					
					// User-Agent: Mozilla/5.0 (compatible; MSIE 10.0; Windows NT 6.2; WOW64; Trident/6.0)
					//通过 设置 user-agent http的请求头 , 来骗  网站, 返回  pc机上 使用 浏览器 返回 数据的一个 页面 源代码
					
					conn.setRequestProperty("User-Agent", "Mozilla/5.0 (compatible; MSIE 10.0; Windows NT 6.2; WOW64; Trident/6.0)");
					
					String contentType = conn.getContentType();
					
					int code = conn.getResponseCode();
					if(code==200){
						
						InputStream in = conn.getInputStream();
						
						// BitFactory.decodeStream(in)
						//如何一个将一个 流转换为 字符串 
						
						//这里的data是从  服务器 返回的
						String data = StreamTool.decodeStream(in);
						
						// 展示的屏幕上 
						Message msg = Message.obtain();
						msg.what =SUCCESS;
						msg.obj = data;
						
						handler.sendMessage(msg);
					}else{
						
						Message msg = Message.obtain();
						msg.what =ERROR;
						
						handler.sendMessage(msg);
						
					}
					
				} catch (Exception e) {
					e.printStackTrace();
					
					Message msg = Message.obtain();
					msg.what =NETWORK_ERROR;
					
					handler.sendMessage(msg);
				}
				
				
			};
		}.start();
		
	}
	
	
}
