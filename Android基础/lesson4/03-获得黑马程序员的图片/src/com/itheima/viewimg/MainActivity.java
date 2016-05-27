package com.itheima.viewimg;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private EditText ed_path;
	private ImageView iv;
	private String path;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        ed_path = (EditText) findViewById(R.id.ed_path);
        iv = (ImageView) findViewById(R.id.iv);
    }

    public void gogetImage(View v){
    	
		path = ed_path.getText().toString().trim();
    	if(TextUtils.isEmpty(path)){
    		
    		Toast.makeText(this, "路径有错误...", 0).show();
    		return;
    	}
		
    	
    	// 连接网络的api  URL类 
    	
    	try {
			URL url = new URL(path);
			
			// http://www.itheima.com/images_new/logo.jpg --由于这里 使用的 是 http协议去 获得连接, 所以获得的是 
			// HttpURLConnection 的一个 实例 
			
			// ftp://www.itheima.com/images_new/logo.jpg
			// samba://www.itheima.com/images_new/logo.jpg
			// ssh://www.itheima.com/images_new/logo.jpg
			
			//建立一个 连接 --- Connection 对象
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			
			//设置请求的方式
			conn.setRequestMethod("GET");
			
			// 获得 服务器 返回的 状态吗 , 根据 状态码 去判断 是否 成功 
			int code = conn.getResponseCode();
			
			// 200 ,  404 ,500, 302, 304 ..
			
			if(code==200){
				
				//进来 则表示 成功的处理的 请求, 返回了 数据
				
				// 获得返回的图片的 流数据
				InputStream in = conn.getInputStream();
				
				//如果去解析呢 ???  --如何解析成一个 图片显示 
				
				// 这个事儿经常要做 ,谷歌 工程师 已经帮咱们提供好了现成 的类, 可以将一个 流数据转换为 一个图片
				
				//这已经是图片了
				Bitmap bitmap = BitmapFactory.decodeStream(in);
				
				iv.setImageBitmap(bitmap);
				in.close();
			}
			
			
			//这里要连接网络, 会 耗费用户的 money, 所以需要去申请连接网络的权限
			
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
		
    }
    

}
