package com.itheima.notepad;

import android.os.Bundle;
import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.view.Menu;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

	private EditText ed_content;
	
	SharedPreferences sp;
	
	//创建时 调用
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ed_content = (EditText) findViewById(R.id.ed_content);
        
        sp = getSharedPreferences("config", 0);
        String vl = sp.getString("content", "");
        
        ed_content.setText(vl);
    }

    //销毁 时 调用
    @Override
    protected void onDestroy() {
    	super.onDestroy();
    	
    	String content = ed_content.getText().toString().trim();
    	
    	//将数据 保存到 sharedPrederence中
		sp = getSharedPreferences("config", 0);
    	Editor edit = sp.edit();
    	edit.putString("content", content);
    	edit.commit();
    	Toast.makeText(this, "哥们, 俺帮你保存了 ", 0).show();
    }
    
    
}
