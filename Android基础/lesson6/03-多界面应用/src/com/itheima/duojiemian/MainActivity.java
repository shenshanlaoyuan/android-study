package com.itheima.duojiemian;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {

	private TextView tv;

	// 这个是每个 activity 启动的时候 最 先被调用的方法
	@Override
    protected void onCreate(Bundle savedInstanceState) {
    	System.out.println("onCreate===");
        super.onCreate(savedInstanceState);
        
        // 将一个 布局 文件  显示 出来 
        setContentView(R.layout.activity_main);
        
        tv = (TextView) findViewById(R.id.tv);
        
//        View v = View.inflate(context, resource, root);
//        		findViewById(R.id.yyy);
    }

}
