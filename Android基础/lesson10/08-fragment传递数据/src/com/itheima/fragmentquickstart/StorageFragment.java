package com.itheima.fragmentquickstart;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

@SuppressLint("NewApi")
public class StorageFragment extends Fragment {
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		//为 fragment 声明  layout 文件, 然后 将layout 文件的 显示 转换为一个 view 对象
		
		
		return inflater.inflate(R.layout.storagefragment, null);
	}
	
}
