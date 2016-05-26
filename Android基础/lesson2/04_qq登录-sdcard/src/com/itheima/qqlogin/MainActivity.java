package com.itheima.qqlogin;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.OutputStream;

import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.text.TextUtils;
import android.text.format.Formatter;
import android.view.Menu;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

	private EditText ed_qqnumber;
	private EditText ed_qqpassword;
	private CheckBox cbx;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        //初始化控件 
        ed_qqnumber = (EditText) findViewById(R.id.qqnumber);
        ed_qqpassword = (EditText) findViewById(R.id.qqpassword);
        cbx = (CheckBox) findViewById(R.id.remember);
        
        // android手机 特别多,  不同的手机 oem 厂商 改 了之后 sdcard 名称 不一样的
      //   /mnt/sdcard/emulated01
        //   /mnt/sdcard/storage01
        //   /mnt/sdcard/storage02
        // Environment.getExternalStorageDirectory() --- 动态的 返回  sdcard 路径名称 
        File file = new File(Environment.getExternalStorageDirectory(),"info.txt");
        if(file.exists()&&file.length()>0){
        	
        	// 读取 file 中数据 , 然后回显 
        	try {
				BufferedReader br = new BufferedReader(new FileReader(file));
				
				// 111#itheima#111
				 String line = br.readLine();  
				
				String num= line.split("#itheima#")[0];
				String pwd = line.split("#itheima#")[1];
				ed_qqnumber.setText(num);
				ed_qqpassword.setText(pwd);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
        	
        	
        	
        }
        
    }

    
    //点击 登录 后会执行 login 方法
    public void login(View v){
    	
    	//获得 输入 框 中  写入的 值 
    	String number = ed_qqnumber.getText().toString().trim();
    	String password = ed_qqpassword.getText().toString().trim();
    	
    	// 判断 是否 填入了 number以及 password的值 
    	if(TextUtils.isEmpty(number)||TextUtils.isEmpty(password)){
    		
    		//给 用户提示,  输入 号码 和 密码
    		Toast.makeText(this, "请输入 qq号码 和密码 ", 0).show();
    		return;
    	}
    	
    	//判断 是否勾选了checkbox ,如果勾选了checkbox ,那么就 将 qq号和密码 保存起来 
    	boolean isChecked = cbx.isChecked();
    	
    	if(isChecked){
    		
    		//说明勾选了 
    		
    		//经常会 给 用户 弹 一个  toast(土司) 
    		
    		// context : 上下文 
    		// text :  你要给用户 提示的信息 
    		// duration :  消息 持续的时间   
//    		Toast.makeText(this, "勾选了", 0).show();
    		
    		//在勾选了  checkbox的时候, 将 数据保存起来 
    		
    		try {
    			
    			// sdcard 做为 一个外部的 存储 设备, 有时候 sdcard 是处于 弹出状态, 未插入状态, 强制拔出 状态. 
    			// 那么在这些 情况下,如果 要向 sdcard 中写数据, 为了 保证 数据 确切的存储, 提高用户的感受, 所以 , 在存储到
    			// sd 卡中之前, 会去动态 判断 sdcard 的状态,只有 在sdcard 是处于 挂载 的状态下 , 再去 写 sd 卡数据 
    			
    			//细节 一 : 
    			// 判断sdcar的 状态 
    			String status = Environment.getExternalStorageState();
    			
    			// 这里 status  动态的返回的 sd 卡的状态 
    			
    			// 如果是mouted --- 挂载 , 那么这个时候 可以去 写 数据到 sd 卡中
    			if(!Environment.MEDIA_MOUNTED.equals(status)){
    				
    				// 表示   sd 卡 未 挂载, 那么 这个时候 就提示用户  检查sd 卡的状态 
    				
    				Toast.makeText(this, "请检查 sd 卡的状态 ", 0).show();
    				return;
    			}
    			
    			// 细节二 :
    			
    			// 返回可用的 空闲的 空间 大小   ---- in bytes
    			long freeSpace = Environment.getExternalStorageDirectory().getFreeSpace();
    			
    			//拿到 sd 卡的总的大小,   in bytes
    			Environment.getExternalStorageDirectory().getTotalSpace();
    			
    			//拿到 sd 卡 已经使用的 的大小,   in bytes
    			Environment.getExternalStorageDirectory().getUsableSpace();
    			
    			// 调用这个api 去获得sd 卡的 可用 控件, 这里还做了一个事, 将 返回的字节 空间 做了单位的
    			// 转换 
    			String avalableSize = Formatter.formatFileSize(this, freeSpace);
    			
    			Toast.makeText(this,"可用的 空间 是 : "+ avalableSize, 0).show();
    			
    			
    			
				File file = new File(Environment.getExternalStorageDirectory(),"info.txt");
				OutputStream out = new FileOutputStream(file);
				String value = number+"#itheima#"+password;
				
				out.write(value.getBytes());
				out.close();
				
				Toast.makeText(this, "勾选了, 保存成功", 0).show();
			} catch (Exception e) {
				e.printStackTrace();
				Toast.makeText(this, "勾选了, 没  保存成功", 0).show();
			}
    		
    	}else{
    		//在没 勾选 checkbox的时候, 不保存数据 
    		//说明没 勾选 
    		Toast.makeText(this, "没 勾选了", 0).show();
    		
    	}
    	
    }
    
}
