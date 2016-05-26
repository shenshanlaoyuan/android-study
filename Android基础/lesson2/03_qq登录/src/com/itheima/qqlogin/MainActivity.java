package com.itheima.qqlogin;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.OutputStream;

import android.os.Bundle;
import android.app.Activity;
import android.text.TextUtils;
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
        
//        File file = new File("/data/data/com.itheima.qqlogin/info.txt");
        
        				//     /data/data/com.itheima.qqlogin/files
        File file = new File(this.getFilesDir(),"info.txt");
        		//     /data/data/com.itheima.qqlogin/cache
//        File file = new File(this.getCacheDir(),"info.txt");
        
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
    			
    			
//				File file = new File(this.getCacheDir(),"info.txt");
				File file = new File(this.getFilesDir(),"info.txt");
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
