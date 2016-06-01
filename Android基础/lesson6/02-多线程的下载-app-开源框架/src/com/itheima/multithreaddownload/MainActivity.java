package com.itheima.multithreaddownload;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;


import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {

	private EditText ed_path;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ed_path = (EditText) findViewById(R.id.ed_path);
	}
	
	String path;
	public void download(View v){
		
		// http://192.168.1.100:8080/ff.exe
		path = ed_path.getText().toString().trim();
		
		HttpUtils utils = new HttpUtils();
		utils.download("http://192.168.1.100:8080/ff.exe", "/mnt/sdcard/jjyy.exe", true, new RequestCallBack<File>(){

			@Override
			public void onStart() {
				super.onStart();
				System.out.println("开始下载了 ");
			}
			
			@Override
			public void onLoading(long total, long current, boolean isUploading) {
				super.onLoading(total, current, isUploading);
				
				System.out.println("正在 下载中  ");
			}
			
			@Override
			public void onSuccess(ResponseInfo responseInfo) {
				
				System.out.println("下载成功   ");
			}

			@Override
			public void onFailure(HttpException error, String msg) {
				
				System.out.println("下载失败    ");
			}
			
			
		});
		
	}
	
}
