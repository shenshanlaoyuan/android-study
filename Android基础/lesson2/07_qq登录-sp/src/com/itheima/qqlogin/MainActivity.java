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
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
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
	
	private SharedPreferences sp;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        //初始化控件 
        ed_qqnumber = (EditText) findViewById(R.id.qqnumber);
        ed_qqpassword = (EditText) findViewById(R.id.qqpassword);
        cbx = (CheckBox) findViewById(R.id.remember);
        
        sp = getSharedPreferences("config", 0);
        
        // 如果找到了 number的 值, 那么 就 返回 number的值, 否则 就返回 这里的默认值 
       String num= sp.getString("number", "");
       String pwd= sp.getString("password", "");
        
       ed_qqnumber.setText(num);
       ed_qqpassword.setText(pwd);
       
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
    		
    		
    		//在勾选了  checkbox的时候, 将 数据保存起来 
    		
    		try {
    			
    			// 将数据 保存起来, 使用 sharedPreference
    			// config文件将会生成 在 应用的文件夹下 --- 一个 xml 格式 的文件
    			// 一般 情况下, 应用自己的数据 只有 当前应用 自己可以去读,  所以 通常 会写  
    			
				sp = getSharedPreferences("config", 0);
    			
    			Editor editor = sp.edit();
    			
    			editor.putString("number", number);
    			editor.putString("password", password);
    			
    			editor.commit();
    			
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
