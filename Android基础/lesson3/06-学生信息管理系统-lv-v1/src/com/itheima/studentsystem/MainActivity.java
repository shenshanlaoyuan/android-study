package com.itheima.studentsystem;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.itheima.studentsystem.domain.Student;

public class MainActivity extends Activity {

	private EditText ed_name;
	private RadioGroup rgb;
	
	private StudentDao sdao;
	
	//获得 lv 控件 ,  view 对象
	private ListView lv;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//初始化
		ed_name = (EditText) findViewById(R.id.ed_name);
		rgb= (RadioGroup) findViewById(R.id.rgb);
		lv = (ListView) findViewById(R.id.lv);
		
		
		
		
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

	List<Student> students;
	private void refreshView() {
		
		// 将现有的 全部给清空一下 
		students = sdao.getAll();
		
		//为 lv 设置 数据 适配器 --- 设置 控制器  controller
		lv.setAdapter(new MyAdapter());
		
	}
	
	private class MyAdapter extends BaseAdapter{

		
		//最开始 被调用的 , 用于 告诉 控制器 adapter 到底要显示 多少个  item 
		@Override
		public int getCount() {
			
			//告诉 控制   器 adapter  显示 多少个  item 
			return students.size();
		}

		
		//每个 item 要显示在屏幕上时 , 又 会调用 到这个方法 
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			
			LinearLayout ll = new LinearLayout(MainActivity.this);
			ll.setOrientation(LinearLayout.HORIZONTAL);
			
			// 根据这里的position 去  获得 具体要显示的list 中的 哪个 学生的信息
			Student student = students.get(position);
			
			TextView tv = new TextView(MainActivity.this);
			tv.setText(student.getName() +", 位置 :" + position);
			
			// 如果是男同学, 就 显示一个 男的 图标, 否是 就显示 代表 女同学的图标 
			String sex = student.getSex();
			ImageView iv = new ImageView(MainActivity.this);
			if("male".equals(sex)){
				
				// 弄个  ImageView  去设置 图片 为 
				iv.setImageResource(R.drawable.nan);
			}else{
				iv.setImageResource(R.drawable.nv);
			}
			
			ll.addView(iv,20,30);
			ll.addView(tv);
			
			return ll;
		}
		
		
		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return 0;
		}

		
		
	}
	
}
