package com.itheima.login;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;

import com.itheima.login.utils.StreamTool;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	protected static final int SUCCESS = 0;

	protected static final int ERROR = 1;

	private  String path = "http://192.168.1.100:8080/web_login/login?";

	private EditText ed_number;
	private EditText ed_pwd;
	private TextView tv_status;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ed_number = (EditText) findViewById(R.id.ed_number);
		ed_pwd = (EditText) findViewById(R.id.ed_pwd);
		tv_status = (TextView) findViewById(R.id.login_status);
	}

	private Handler mHandler= new Handler(){
		public void handleMessage(Message msg) {
			
			switch (msg.what) {
			case SUCCESS:
				String data = (String) msg.obj;
				tv_status.setText(data);
				break;
			case ERROR:
				Toast.makeText(MainActivity.this, "连接错误 ....", 0).show();
				break;
			default:
				break;
			}
			
		};
		
	};
	
	public void login(View v) {

		//获得 QQ号码和 密码 , 然后登录
		final String number = ed_number.getText().toString().trim();
		final String pwd = ed_pwd.getText().toString().trim();
		
		if(TextUtils.isEmpty(number)||TextUtils.isEmpty(pwd)){
			Toast.makeText(this, "号码或者密码不能为空", 0).show();
			return;
		}
		
		//如果在 发送数据的过程中, 传输了中文数据, 那么是需要进行url 编码的, 否则 带不过去
		// http://192.168.1.100:8080/web_login/login?number=%E5%93%88%E5%93%88&pwd=520itheima
		// http://192.168.1.100:8080/web_login/login?number=5201314&pwd=520itheima
		
		
		//走到这里 , 则说明 qq号码和密码 都输入了 ,然后 需要去连接 网络 
		new Thread(){
			
			public void run() {
				
				try {
					path = path+"number="+URLEncoder.encode(number, "UTF-8")+"&pwd="+URLEncoder.encode(pwd, "UTF-8");
					System.out.println(path);
					URL url = new URL(path);
					HttpURLConnection conn = (HttpURLConnection) url.openConnection();
					
					conn.setRequestMethod("GET");
					conn.setConnectTimeout(5000);
					
					int code = conn.getResponseCode();
					
					if(code==200){
						
						InputStream in = conn.getInputStream();
						
						String data = StreamTool.decodeStream(in);
						
						Message msg = Message.obtain();
						msg.what=SUCCESS;
						msg.obj = data;
						mHandler.sendMessage(msg);
					}
					
				} catch (Exception e) {
					e.printStackTrace();
					Message msg = Message.obtain();
					msg.what=ERROR;
					mHandler.sendMessage(msg);
				}
				
			};
		}.start();
		
		
	}

}
