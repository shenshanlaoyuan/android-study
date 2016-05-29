package com.itheima.login;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

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
					
					String path = "http://192.168.1.100:8080/web_login/login?";
					
					//http://192.168.1.100:8080/web_login/login?number=123&pwd=456
					System.out.println(path);
					
					//面向对象 
					
					//客户端  浏览器
					HttpClient client = new DefaultHttpClient();
					
					// 指定了 发送的请求的方式 
					HttpPost post = new HttpPost(path);
					
					
					//传递给 服务器 要带过去的参数 的信息 
					
					List<NameValuePair> list = new ArrayList<NameValuePair>();
					
					// 将 带过去 的参数 放到 一个 nameValuePair 中, 然后再 放到 一个list 中,然后再将这个list 
					// 给 要带过去的数据 实体 
					list.add(new BasicNameValuePair("number", number));
					list.add(new BasicNameValuePair("pwd", pwd));
					
					// 设置 带给服务器的 参数的信息
					//  number=5201314&pwd=123
					
					//设置 要带给服务器的 数据 实体 
					post.setEntity(new UrlEncodedFormEntity(list,"UTF-8"));
					
					//   http://192.168.1.100:8080/web_login/login
					
					//收到的 来自于服务器端的响应的 数据
					HttpResponse response = client.execute(post);
					//http 的相应 分为 响应行, 响应头, 响应体 
					
					// HTTP/1.1 200 OK
					int code = response.getStatusLine().getStatusCode();
					
					if(code==200){
						
						InputStream in = response.getEntity().getContent();
						
						String data2 = StreamTool.decodeStream(in);
						
						Message msg = Message.obtain();
						msg.what=SUCCESS;
						msg.obj = data2;
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
