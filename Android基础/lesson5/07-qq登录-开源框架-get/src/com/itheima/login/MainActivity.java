package com.itheima.login;

import org.apache.http.Header;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

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

	public void login(View v) {

		// 获得 QQ号码和 密码 , 然后登录
		final String number = ed_number.getText().toString().trim();
		final String pwd = ed_pwd.getText().toString().trim();

		if (TextUtils.isEmpty(number) || TextUtils.isEmpty(pwd)) {
			Toast.makeText(this, "号码或者密码不能为空", 0).show();
			return;
		}

		String path = "http://192.168.1.100:8080/web_login/login?number="+number+"&pwd="+pwd;
		AsyncHttpClient client = new AsyncHttpClient();
		
		//封装 了 api , 使用到了handler 去 处理了 这些事儿 ...
		
		client.get(path, new AsyncHttpResponseHandler() {
			
			//请求成功的时候  会被调用的
			@Override
			public void onSuccess(int statusCode, Header[] headers,
					byte[] responseBody) {
//				System.out.println(new String(responseBody));
				tv_status.setText(new String(responseBody));
			}

			//请求 失败 的时候  会被调用的
			@Override
			public void onFailure(int statusCode, Header[] headers,
					byte[] responseBody, Throwable error) {
				error.printStackTrace(System.out);
				Toast.makeText(MainActivity.this, "对不起, 俺错误了...", 0).show();
			}
		});

	}

}
