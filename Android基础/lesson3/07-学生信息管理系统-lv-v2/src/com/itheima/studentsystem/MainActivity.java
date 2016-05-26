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
/*
 *    listview 中 convertView是用来  干什么的 ? 
 * 
 * 		结论: 同学们, 经常 去翻翻 源码...
 * 
 */
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
		
		// convertView : 就是用来 进行 优化的 
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			
			//获得当前的 学生 
			Student st = students.get(position);
			
			// 现在 item 已经 定义 好了,  需要将 硬盘上 item.xml 布局 文件 转化为 一个 布局的 对象 返回 回去
			
			View v;
			if (convertView == null) {
				v =  View.inflate(MainActivity.this, R.layout.item, null);
			} else {
				v = convertView;
			}
			
			
			// 打气筒 的 对象 ,去 填充  xml , 使得 xml 布局文件 变为 一个 view  对象 
			//拿到 iv 和 tv 
			
			ImageView iv = (ImageView) v.findViewById(R.id.item_iv);
			
			String sex = st.getSex();
			if("male".equals(sex)){
				iv.setImageResource(R.drawable.nan);
			}else{
				
				iv.setImageResource(R.drawable.nv);
			}
			
			
			TextView tv = (TextView) v.findViewById(R.id.item_tv);
			tv.setText(st.getName());
			
			return v;
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
