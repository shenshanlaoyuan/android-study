package com.itheima.studentsystem;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends Activity {

	private EditText ed_ssname;
	private EditText ed_ssnumber;
	private RadioGroup rgb;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// 控件的初始化

		ed_ssname = (EditText) findViewById(R.id.studentname);
		ed_ssnumber = (EditText) findViewById(R.id.studentnumber);
		rgb = (RadioGroup) findViewById(R.id.radiogb);

	}

	public void save(View v){
		
		String studentname = ed_ssname.getText().toString().trim();
		String studentnumber = ed_ssnumber.getText().toString().trim();
		
		if(TextUtils.isEmpty(studentname)||TextUtils.isEmpty(studentnumber)){
			
			Toast.makeText(this, "学生的姓名和 学号不能 为 空...", 0).show();
			return;
		}
		
		//获得学生的 性别
		int id = rgb.getCheckedRadioButtonId();
		
		String sex ="男";
		
		if(id==R.id.male){
			sex ="男";
		}else if(id==R.id.female){
			sex ="女";
		}
		
		//走到 这里, 学生的 信息 都 有了 .
		// 将学生的信息保存起来 
		
		/*
<?xml version="1.0" encoding="utf-8"?>
<student>
	<name>张三</name>
	<number>123456</number>
	<sex>男</sex>
</student>

		 * 
		 */
		
		try {
			File file = new File(getFilesDir(), studentname+".xml");
//		String str ="";
			StringBuilder sb = new StringBuilder();
			sb.append("<?xml version='1.0' encoding='utf-8'?>");
			sb.append("<student>");
			sb.append("<name>");
			sb.append(studentname);
			sb.append("</name>");
			sb.append("<number>");
			sb.append(studentnumber);
			sb.append("</number>");
			sb.append("<sex>");
			sb.append(sex);
			sb.append("</sex>");
			sb.append("</student>");
			
			OutputStream out = new FileOutputStream(file);
			
			out.write(sb.toString().getBytes());
			out.close();
			
			Toast.makeText(this, "保存"+studentname+"信息 成功 ...", 0).show();
			
		} catch (Exception e) {
			e.printStackTrace();
			Toast.makeText(this, "保存"+studentname+"信息  失败 .... ...", 0).show();
		}
		
		
		
	}
}
