package org.itheima62.player;

import org.itheima62.player.async.R;

import android.app.Activity;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class MainActivity extends Activity implements OnSeekBarChangeListener {
	private EditText etPath;
	private SeekBar skbProgress;
	private MediaPlayer player;
	private boolean tracking = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		etPath = (EditText) findViewById(R.id.et_path);
		skbProgress = (SeekBar) findViewById(R.id.skb_progress);

		skbProgress.setOnSeekBarChangeListener(this);
	}

	public void play(View view) {
		String path = etPath.getText().toString().trim();
		if (TextUtils.isEmpty(path)) {
			return;
		}

		// 播放音乐
		if (player == null) {
			player = new MediaPlayer();
		}
		// 重置播放器
		player.reset();
		try {
			player.setOnErrorListener(new OnErrorListener() {

				@Override
				public boolean onError(MediaPlayer mp, int what, int extra) {
					System.out.println("what : " + what);
					return false;
				}
			});

			// 设置播放的资源
			player.setDataSource(path);

			// player.prepare();// 准备播放-->阻塞方法

			player.setOnPreparedListener(new OnPreparedListener() {

				@Override
				public void onPrepared(MediaPlayer mp) {
					// 准备完成时的回调

					player.start();// 开始播放
					performProgress();
				}
			});
			player.prepareAsync();// 异步准备
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void performProgress() {
		skbProgress.setMax(player.getDuration());// 音乐文件的时长

		new Thread(new Runnable() {

			@Override
			public void run() {
				while (player != null && player.isPlaying()) {

					if (!tracking) {
						// 获得当前的进度
						int currentPosition = player.getCurrentPosition();
						skbProgress.setProgress(currentPosition);// 子线程中更新UI
					}
				}
			}
		}).start();
	}

	public void pause(View view) {
		// 暂停
		if (player != null && player.isPlaying()) {
			player.pause();
			((Button) view).setText("继续");
		} else if (player != null) {
			player.start();
			performProgress();
			((Button) view).setText("暂停");
		}

	}

	public void stop(View view) {
		if (player != null) {
			player.stop();
			player.release();// 释放资源
			player = null;
		}
	}

	@Override
	public void onProgressChanged(SeekBar seekBar, int progress,
			boolean fromUser) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {
		// 开始拖动
		tracking = true;
	}

	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {
		// 结束拖动
		tracking = false;
		// 播放对应的位置
		if (player != null) {
			player.seekTo(seekBar.getProgress());
		}
	}
}
