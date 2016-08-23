package org.itheima62.surfaceview;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.Menu;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;

public class MainActivity extends Activity {

	// 用来处理渲染频繁的控件
	// 开启线程来处理 UI更新
	// 子线程--》去做数据的获取---》更新UI --->子线程---》获取数据
	private SurfaceView sv;

	private Paint paint = new Paint();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		sv = (SurfaceView) findViewById(R.id.sv);

		paint.setColor(Color.RED);

		sv.getHolder().addCallback(new Callback() {

			@Override
			public void surfaceDestroyed(SurfaceHolder holder) {
				System.out.println("destroyed");
			}

			@Override
			public void surfaceCreated(SurfaceHolder holder) {
				System.out.println("created");

				new Thread(new Runnable() {

					@Override
					public void run() {

						for (int i = 0; i <= 360; i++) {
							SurfaceHolder holder = sv.getHolder();
							Canvas canvas = holder.lockCanvas();// 锁住画布
							canvas.drawArc(new RectF(10, 10, 200, 200), 0, i,
									true, paint);
							// 通知更新
							holder.unlockCanvasAndPost(canvas);
						}
					}
				}).start();
			}

			@Override
			public void surfaceChanged(SurfaceHolder holder, int format,
					int width, int height) {
				System.out.println("surfaceChanged");
			}
		});

	}

	public void start(View view) {

		new Thread(new Runnable() {

			@Override
			public void run() {

				for (int i = 0; i <= 360; i++) {
					SurfaceHolder holder = sv.getHolder();
					Canvas canvas = holder.lockCanvas();// 锁住画布
					canvas.drawArc(new RectF(10, 10, 200, 200), 0, i, true,
							paint);
					// 通知更新
					holder.unlockCanvasAndPost(canvas);
				}
			}
		}).start();

	}

}
