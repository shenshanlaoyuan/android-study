package com.itheima.rpcalc;

import java.io.UnsupportedEncodingException;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class SecondActivity extends Activity {
	
	private TextView rp_value;
	private ImageView iv;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main02);
		rp_value = (TextView) findViewById(R.id.rp_value);
		iv = (ImageView) findViewById(R.id.iv);
		
		//拿到了  激活 SecondActivity 的那个 意图 对象 
		Intent intent = getIntent();
		String name = intent.getStringExtra("name");
		int sex = intent.getIntExtra("sex", R.id.male);
//		byte[] byteArrayExtra = intent.getByteArrayExtra("img");
		
		Bitmap img = intent.getParcelableExtra("img");
		
		iv.setImageBitmap(img);
		
		System.out.println(name);
		System.out.println(sex);
		
		byte[] bb = null;
		
		try {
			switch (sex) {
			case R.id.male:
				//这里 将会 UTF-8编码 将 name 变为 二进制数据
				// android 中默认的编码是 utf-8
				bb = name.getBytes();
				break;
			case R.id.female:
				
				bb = name.getBytes("gbk");
				break;
			case R.id.unknown:
				
				bb= name.getBytes("iso8859-1");
				break;

			default:
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}


		byte[] result = bb;
		int total =0;
		for (byte b : result) {
			
			int data = b&0xff;   //  -127~128
			total = total+ Math.abs(data);
		}
		
		int rp = total%100;  
		
		String data = null;
		
		if(rp>90){
//			tv_rp.setText("你的人品很不错, 值得 拥有 ...");
			data = "你的人品很不错, 值得 拥有 ...";
		}else if(rp>60){
//			tv_rp.setText("你的人品 将将就就...");
			data = "你的人品 将将就就...";
		}else if(rp>30){
//			tv_rp.setText(" 垃圾人品.... 不要跟你交往 ");
			data = "垃圾人品.... 不要跟你交往 ...";
		}else{
			
			data = " 大家离他 远点....";
//			tv_rp.setText(" 大家离他 远点.... ");
		}
		
		rp_value.setText(data);
	}
}
