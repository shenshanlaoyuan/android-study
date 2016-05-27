package com.itheima.json.weather;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.itheima.json.weather.utils.StreamTool;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private EditText ed_city;
	private TextView city_result1;
	private TextView city_result2;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		ed_city = (EditText) findViewById(R.id.ed_city);
		city_result1 = (TextView) findViewById(R.id.city_result1);
		city_result2 = (TextView) findViewById(R.id.city_result2);
	}
	
	private final static String PATH = "http://wthrcdn.etouch.cn/weather_mini?city=";
	
	protected static final int SUCCESS = 0;
	protected static final int INVALID_CITY = 1;
	protected static final int ERROR = 2;

	private String city;
	String ul;
	
	private Handler mHandler = new Handler(){
		
		public void handleMessage(android.os.Message msg) {
			
			switch (msg.what) {
			case SUCCESS:
				
				try {
					JSONArray data = (JSONArray) msg.obj;
					String day01 = data.getString(0);
					city_result1.setText(day01);
					
					String day02 = data.getString(1);
					city_result2.setText(day02);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				break;
			case INVALID_CITY:
				Toast.makeText(MainActivity.this, "城市无效 ...", 0).show();
				break;
			case ERROR:
				
				Toast.makeText(MainActivity.this, "网络 问题 .... ...", 0).show();
				break;

			default:
				break;
			}
		};
	};
	
	public void searchCityWeather(View v){
		
		city = ed_city.getText().toString().trim();
		if(TextUtils.isEmpty(city)){
			Toast.makeText(this, "路径错误...", 0).show();
			return;
		}
		
		//http://wthrcdn.etouch.cn/weather_mini?city=%E6%B7%B1%E5%9C%B3
		
		// 发起请求 给 那个 网站了 
		new Thread(){
			public void run() {
				
				try {
					
					ul = PATH + URLEncoder.encode(city, "UTF-8");
					URL url = new URL(ul);
					
					HttpURLConnection conn = (HttpURLConnection) url.openConnection();
					
					// 设置 必要的参数信息
					conn.setConnectTimeout(5000);
					conn.setRequestMethod("GET");
					
					//判断 响应码 
					int code = conn.getResponseCode();
					
					if(code==200){
						
						InputStream in = conn.getInputStream();
						String data = StreamTool.decodeStream(in);
						
						System.out.println(data);
						
						//解析json 格式的数据 
						JSONObject jsonObj = new JSONObject(data);
						
						// 获得 desc 的值 
						String result = jsonObj.getString("desc");
						if("OK".equals(result)){
							
							//城市 有效, 返回了需要的数据
							JSONObject dataObj = jsonObj.getJSONObject("data");
							
							JSONArray jsonArray = dataObj.getJSONArray("forecast");
							
							//通知 更新 ui 
							Message msg = Message.obtain();
							msg.obj = jsonArray;
							msg.what=SUCCESS;
							mHandler.sendMessage(msg);
							
						/*	String value1 = jsonArray.getString(0);
							String value2 = jsonArray.getString(2);
							System.out.println(value1);
							System.out.println(value2);*/
							
						}else{
							
							//城市 无效
							Message msg = Message.obtain();
							msg.what=INVALID_CITY;
							mHandler.sendMessage(msg);
						}
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
