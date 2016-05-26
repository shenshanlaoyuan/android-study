package com.itheima.setttings;

import android.os.Bundle;
import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.view.Menu;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class MainActivity extends Activity {

	private CheckBox cbx;
	private SeekBar sb;
	private SharedPreferences sp;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		cbx = (CheckBox) findViewById(R.id.cbx);
		sb = (SeekBar) findViewById(R.id.seekBar);

		sp = this.getSharedPreferences("config", 0);
		boolean isCheckStatus = sp.getBoolean("isChecked", false);
		int pgs = sp.getInt("progress", 0);
		sb.setProgress(pgs);
		
		cbx.setChecked(isCheckStatus);
		cbx.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override				// buttonView  ---就是当前的 cbx ,  isChecked , 就是当前  cbx 的实时的状态
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
					
					//保存到 sharedPreference中
					Editor edit = sp.edit();
					edit.putBoolean("isChecked", isChecked);
					//要 commit
					edit.commit();
				}
			}
		);

		
		
		sb.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			
			
			//停止 拖动 seek bar 
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				
				//拿到 停止 拖动的时刻的 进度 
				int progress = seekBar.getProgress();
				//将进度 保存到 sharedPreference 中 
				Editor edit = sp.edit();
				edit.putInt("progress", progress);
				edit.commit();
				
			}
			
			//开始 拖动   seek bar
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				
			}
			
			//  seek bar的 进度 改变了 --- 在拖动中 
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				
			}
		});
		
	}

}
