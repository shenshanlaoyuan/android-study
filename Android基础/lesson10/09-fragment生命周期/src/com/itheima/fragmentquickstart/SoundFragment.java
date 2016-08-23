package com.itheima.fragmentquickstart;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
/*
 *   fragment的生命周期与 之前的acitvity 是一样的,  无非多了一个 
 *   oncreateView方法, 并且这个 方法是在 oncreate 之后 调用的, 
 *   
 *   如果 最小化窗口, 再次回到 当前的activity的时候, fragment不会重新创建, 而是复用已经有的 fragment
 *   对象 .
 * 
 * 	  fragment实际上就是一个 轻量级的activity 
 * 
 */
public class SoundFragment extends Fragment {
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		System.out.println("onCreateView");
		View view = inflater.inflate(R.layout.soundfragment, null);
		return view;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		System.out.println("onCreate");
		super.onCreate(savedInstanceState);
	}

	@Override
	public void onStart() {
		System.out.println("onStart");
		super.onStart();
	}

	@Override
	public void onResume() {
		System.out.println("onResume");
		super.onResume();
	}

	@Override
	public void onPause() {
		System.out.println("onPause");
		super.onPause();
	}

	@Override
	public void onStop() {
		System.out.println("onStop");
		super.onStop();
	}

	@Override
	public void onDestroy() {
		System.out.println("onDestroy");
		super.onDestroy();
	}
	
	
	
}
