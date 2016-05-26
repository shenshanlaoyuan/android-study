package com.itheima.filemode;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
/*
 *   了解了解 
 * 
 * 
 */
public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	/*
	 * 创建一个 私有的文件
	 * 
	 * 每个应用程序 都一个私有的文件夹, /data/data/com.itheima.filemode
	 * 
	 * 
	 * 私有的文件 ,只有 当前的应用 自己可以去访问, 其他的应用没有 办法 去访问 　－－　其他的应用既不　可以　读，也不可以写　
	 */
	public void getPrivateFile(View v) {

		try {
			File file = new File(this.getFilesDir(), "private.txt");
			OutputStream out = new FileOutputStream(file);
			out.write("private".getBytes());
			out.close();
			Toast.makeText(this, "写入私有文件成功", 0).show();

		} catch (Exception e) {
			e.printStackTrace();
			Toast.makeText(this, "写入私有文件  失败 ", 0).show();
		}

	}

	// 谷歌 不推荐 咱们这样做 :  引入了 新的组件 --- contentProvider
	
	
	// 创建 一个 只读 的文件 :　　其他的应用可以去　　读，　但是　不能去写　
	public void getReadOnlyFile(View v) {

		try {
			FileOutputStream out = openFileOutput("readonly.txt",
					Context.MODE_WORLD_READABLE);

			out.write("readonly".getBytes());
			out.close();
			Toast.makeText(this, "写入只读文件成功", 0).show();
		} catch (Exception e) {
			e.printStackTrace();
			Toast.makeText(this, "写入只读文件 失败 ", 0).show();
		}

	}

	
	//写入 只可 写 文件 
	public void getWriteOnlyFile(View v) {

		try {
			FileOutputStream out = openFileOutput("writeonly.txt",
					Context.MODE_WORLD_WRITEABLE);

			out.write("writeonly".getBytes());
			out.close();
			Toast.makeText(this, "写入  只可写 文件成功", 0).show();
		} catch (Exception e) {
			e.printStackTrace();
			Toast.makeText(this, "写入只可写 文件 失败 ", 0).show();
		}

	}

	
	// 其他应用 可以读 也可以写的文件 
	public void getPublicFile(View v) {

		try {
			FileOutputStream out = openFileOutput("public.txt",
					Context.MODE_WORLD_WRITEABLE+Context.MODE_WORLD_READABLE);

			out.write("public".getBytes());
			out.close();
			Toast.makeText(this, "写入可读可写 成功", 0).show();
		} catch (Exception e) {
			e.printStackTrace();
			Toast.makeText(this, "写入可读可写 失败 ", 0).show();
		}

	}

}
