package com.itheima.gameboy;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

public class MainActivity extends Activity {

	private ImageView iv_player;
	private ImageView iv_boss;
	private ProgressBar blood;
	int blood_value=100;
	
	@Override
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		iv_player = (ImageView) findViewById(R.id.player);
		iv_boss = (ImageView) findViewById(R.id.boss);
		blood = (ProgressBar) findViewById(R.id.blood);
		blood.setProgress(blood_value);
	}

	public void qq(View v){
		
		iv_player.setImageResource(R.drawable.qq);
		
		int progress = blood.getProgress();
		blood_value -=5;
		blood.setProgress(blood_value);
		if(progress<=0){
			iv_boss.setImageResource(R.drawable.die);
		}
	}
	public void zq(View v){
		iv_player.setImageResource(R.drawable.zq);
		
		int progress = blood.getProgress();
		blood_value -=8;
		blood.setProgress(blood_value);
		
		if(progress<=0){
			iv_boss.setImageResource(R.drawable.die);
		}
		
	}
	public void zj(View v){
		iv_player.setImageResource(R.drawable.zj);
		
		int progress = blood.getProgress();
		blood_value -=10;
		blood.setProgress(blood_value);
		if(progress<=0){
			iv_boss.setImageResource(R.drawable.die);
		}
	}

}
