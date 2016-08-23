package org.itheima59.soundpool;

import android.app.Activity;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

	private SoundPool pool;
	private int soundID;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		int maxStreams = 10;// 声音池中同时可以播放声音的数量
		int streamType = AudioManager.STREAM_MUSIC;
		int srcQuality = 0;
		pool = new SoundPool(maxStreams, streamType, srcQuality);

		// 加载一个声音文件
		soundID = pool.load(this, R.raw.shoot, 1);
	}

	public void shoot(View view) {

		float leftVolume = 1f;
		float rightVolume = 1f;
		int priority = 0;
		int loop = 0;
		float rate = 1;// 播放的速率
		pool.play(soundID, leftVolume, rightVolume, priority, loop, rate);
	}

}
