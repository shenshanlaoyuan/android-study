package com.itheima.fragmentquickstart;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import java.util.List;



@SuppressLint("NewApi")
public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	//声音的 
	public void sound(View v){
		
		//在 右侧的 FrameLayout 去显示  sound 相关 的fragment 数据
		
		// fragment 可以直接 new 出来, 并且不需要到 清单文件中进行注册 
		SoundFragment sf = new SoundFragment();
		
		//拿到一个frament的 manager 对象
		FragmentManager manager = getFragmentManager();
		
		//事务 
		FragmentTransaction transaction = manager.beginTransaction();
		
		// 表示 使用 SoundFragment 去替换掉 之前的 framelayout
		transaction.replace(R.id.container, sf);
		transaction.commit();
	}
	
	//显示的 
	public void display(View v){
		
		DisplayFragment df = new DisplayFragment();
		FragmentManager manager = getFragmentManager();
		FragmentTransaction transaction = manager.beginTransaction();
				
		transaction.replace(R.id.container, df);
		transaction.commit();
	}
	
	//存储 的 
	public void storage(View v){
		
		StorageFragment ssf = new StorageFragment();
		FragmentManager manager = getFragmentManager();
		FragmentTransaction transaction = manager.beginTransaction();
				
		transaction.replace(R.id.container, ssf);
		transaction.commit();
		
	}
	
}
