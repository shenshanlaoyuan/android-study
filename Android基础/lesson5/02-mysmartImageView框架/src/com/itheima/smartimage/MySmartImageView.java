package com.itheima.smartimage;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;
import android.widget.Toast;

public class MySmartImageView extends ImageView {

	protected static final int SUCCESS = 0;
	protected static final int ERROR = 1;

	public MySmartImageView(Context context) {
		super(context);
	}
	
	private Handler mHandler = new Handler(){
		
		public void handleMessage(Message msg) {
			
			switch (msg.what) {
			case SUCCESS:
				Bitmap bitmap = (Bitmap) msg.obj;
//				siv.setI(bitmap);
				break;
			case ERROR:
				
//				Toast.makeText(context, text, duration).sho
				break;

			default:
				break;
			}
			
		};
	};
	
	public void setImageUrl(final String ul){
		
		new Thread(){
			
			public void run() {
				
				try {
					URL url = new URL(ul);
					HttpURLConnection conn = (HttpURLConnection) url.openConnection();
					
					conn.setConnectTimeout(5000);
					conn.setRequestMethod("GET");
					
					int code = conn.getResponseCode();
					
					if(code==200){
						
						// http://192.168.1.100:8080/img/b.jpg
						InputStream in = conn.getInputStream();
						
						Bitmap bitmap = BitmapFactory.decodeStream(in);
						
						Message msg = Message.obtain();
						msg.what=SUCCESS;
						msg.obj=bitmap;
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
