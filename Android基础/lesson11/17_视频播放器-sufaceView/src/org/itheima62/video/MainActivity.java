package org.itheima62.video;

import java.io.IOException;

import org.itheima62.video.suface.R;

import android.app.Activity;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.os.Bundle;
import android.os.Environment;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.widget.MediaController;
import android.widget.VideoView;

public class MainActivity extends Activity {

	private SurfaceView vv;
	private MediaPlayer mPlayer;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		vv = (SurfaceView) findViewById(R.id.vv);

		vv.getHolder().addCallback(new Callback() {

			@Override
			public void surfaceDestroyed(SurfaceHolder holder) {
				if (mPlayer != null) {
					System.out.println("stop");
					mPlayer.stop();
					mPlayer.release();
					mPlayer = null;
				}
			}

			@Override
			public void surfaceCreated(SurfaceHolder holder) {

				if (mPlayer == null) {
					mPlayer = new MediaPlayer();
				}
				mPlayer.reset();
				try {
					mPlayer.setDisplay(holder);//将SurfaceHolder关联mediaplayer
					String path = Environment.getExternalStorageDirectory()
							.getAbsolutePath() + "/areyouok.3gp";
					mPlayer.setOnErrorListener(new OnErrorListener() {

						@Override
						public boolean onError(MediaPlayer mp, int what,
								int extra) {
							// TODO Auto-generated method stub
							return false;
						}
					});
					mPlayer.setDataSource(path);
					mPlayer.setOnPreparedListener(new OnPreparedListener() {

						@Override
						public void onPrepared(MediaPlayer mp) {
							System.out.println("start");
							mPlayer.start();
						}
					});
					mPlayer.prepareAsync();
				} catch (Exception e) {
					e.printStackTrace();
				}

			}

			@Override
			public void surfaceChanged(SurfaceHolder holder, int format,
					int width, int height) {

			}
		});
	}
}
