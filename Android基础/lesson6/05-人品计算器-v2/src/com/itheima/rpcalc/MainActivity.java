package com.itheima.rpcalc;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private EditText ed_name;
	private TextView tv_rp;
	private RadioGroup rgb;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ed_name = (EditText) findViewById(R.id.ed_name);
		tv_rp = (TextView) findViewById(R.id.rp_value); 
		rgb= (RadioGroup) findViewById(R.id.rgb);
	}

	public void cacl(View v){
		
		String name = ed_name.getText().toString().trim();
		
		if(TextUtils.isEmpty(name)){
			Toast.makeText(this, "姓名不能为空", 0).show();
			return;
		}
		
		//  由 这里 MainActivity  A 界面  --- 带给 SecondActivity  B 界面 
		
		// android 中, 谷歌 工程师设计的 时候, 就是考虑到  编程人员的方便,让大家好理解, 所以 就 定义了意图, 通过意图
		// 可以去激活 其他的 应用, 或者 传递数据 到 不同的 组件 . 
		
		// 泡  茶,  泡妞,  泡 桑拿 
		//  使用 intent 
		
		Intent intent = new Intent();
		intent.setClass(this, SecondActivity.class);
		//可以 在激活  SecondActivity 的时候, 带过去 一些数据  给 SecondActivity
		intent.putExtra("name", name);
		intent.putExtra("sex", rgb.getCheckedRadioButtonId());
		
		//带 一张 图片数据 给 SecondActivity
		intent.putExtra("img", BitmapFactory.decodeResource(getResources(), R.drawable.ouxiangpai));
		startActivity(intent);
	}
}
