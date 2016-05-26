package com.itheima.dialogs;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
/*
 *  先掌握前 三种 
 * 
 */
public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	// 创建确认取消对话框
	public void dialog01(View v){
		
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		
		builder.setTitle("约会把...");
		builder.setMessage("告别单身, 你愿意吗 ?");
		
		builder.setPositiveButton("愿意,gogogo", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				Toast.makeText(MainActivity.this, "我也单身, 说不定 可以 来找我...", 0).show();
			}
		});
		
		builder.setNegativeButton("不愿意", null );
		builder.show();
	}
	
	//创建单选对话框
	public void dialog02(View v){
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("单选对话框 ");
		final String[] items = {"小丽","小红","小芳"};
		builder.setSingleChoiceItems(items, -1, new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				
				Toast.makeText(MainActivity.this, " 被点击了 : " + items[which] +",位置: " +which, 0	).show();
				
			}
		});
		builder.show();
	}
	
	//创建多选对话框
	public void dialog03(View v){
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("多选");
		// 这里不要设置 message 
//		builder.setMessage("哈哈,你可以 选 多个 有兴趣去学习的 了 ???");
		
		final String[] items ={"android","ios","javaee","php","C++"};
		boolean[] checkedItems = {true,true,false,false,false};
		
		builder.setMultiChoiceItems(items, checkedItems, new DialogInterface.OnMultiChoiceClickListener(){

			@Override
			public void onClick(DialogInterface dialog, int which,
					boolean isChecked) {
				Toast.makeText(MainActivity.this, " 被点击了 : " + items[which] +",位置: " +which+", 值是: "+ isChecked, 0	).show();
			}
		});
		
		builder.show();
		
	}
	
	//创建进度话框
	public void dialog04(View v){
		
		// 很多 地方 , 干了 比较耗时的事儿 时 会使用到的 
		ProgressDialog dialog = new ProgressDialog(this);
		dialog.setMessage("正在拼命加载中....");
		dialog.show();
//		
//		try {
//			Thread.sleep(3000);
//			dialog.dismiss();
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
		
		
		// 这行 代码 可以 让其 显示, 查看源代码 , 实际上是调用了  上面的这些代码 
		//ProgressDialog.show(this, "消息", "正在拼命加载中....").show();
		
		
	}
	
	//创建进度话框-带进度条的
	public void dialog05(View v){
		
		ProgressDialog dialog = new ProgressDialog(this);
		dialog.setMax(100);
		
//		dialog.set
		
		for (int i = 0; i < 100; i++) {
			dialog.setProgress(i);
		}
		dialog.show();
		
		// 隔 几秒中 去 消失 
	}

}
