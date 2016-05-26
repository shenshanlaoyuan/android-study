package com.itheima.studentsystem;

import java.util.List;

import com.itheima.studentsystem.domain.Student;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private EditText ed_name;
	private RadioGroup rgb;
	private LinearLayout ll;
	
	private StudentDao sdao;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//初始化
		ed_name = (EditText) findViewById(R.id.ed_name);
		rgb= (RadioGroup) findViewById(R.id.rgb);
		ll = (LinearLayout) findViewById(R.id.ll);
		
		sdao = new StudentDao(this);
		
		refreshView();
		
	}
	
	public void save(View v){
		
		String name = ed_name.getText().toString().trim();
		
		if(TextUtils.isEmpty(name)){
			Toast.makeText(this, "学生信息不能为空", 0).show();
			return;
		}
		
		String sex ="male";
		
		int id = rgb.getCheckedRadioButtonId();
		if(id==R.id.male){
			sex ="male";
		}else{
			sex ="female";
		}
		
		//将数据 保存到数据库 中去 
		
		// 拿到 dao 
		
		Student st = new Student("xjlkj",name,sex);
		sdao.add(st);
		Toast.makeText(this, "保存  "+ name+"成功", 0).show();
		//将 所有的 数据 同步的显示 到 屏幕上去 
		
		//查询 现有的数据
		refreshView();
	}

	private void refreshView() {
		
		// 将现有的 全部给清空一下 
		ll.removeAllViews();
		List<Student> students = sdao.getAll();
		for (Student student : students) {
			
			TextView tv = new TextView(this);
			tv.setText(student.toString());
			ll.addView(tv);
		}
	}
}
