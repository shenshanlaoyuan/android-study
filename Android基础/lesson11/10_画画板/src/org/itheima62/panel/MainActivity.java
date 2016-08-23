package org.itheima62.panel;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class MainActivity extends Activity implements OnClickListener,
		OnSeekBarChangeListener, OnTouchListener {

	private static final String TAG = "MainActivity";
	private View redView;
	private View greenView;
	private View blueView;
	private View yellowView;
	private View pinkView;

	private SeekBar skbStroke;
	private ImageView iv;

	private Bitmap bitmap;
	private Canvas canvas;
	private Paint paint;
	private float startX;
	private float startY;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		redView = findViewById(R.id.color_red);
		greenView = findViewById(R.id.color_green);
		blueView = findViewById(R.id.color_blue);
		yellowView = findViewById(R.id.color_yellow);
		pinkView = findViewById(R.id.color_pink);
		skbStroke = (SeekBar) findViewById(R.id.skb_stroke);

		iv = (ImageView) findViewById(R.id.iv);

		redView.setOnClickListener(this);
		greenView.setOnClickListener(this);
		blueView.setOnClickListener(this);
		yellowView.setOnClickListener(this);
		pinkView.setOnClickListener(this);

		skbStroke.setOnSeekBarChangeListener(this);

		iv.setOnTouchListener(this);

		// 准备画纸画布画笔
		bitmap = Bitmap.createBitmap(320, 320, Config.ARGB_8888);
		canvas = new Canvas(bitmap);
		paint = new Paint();
		canvas.drawColor(Color.WHITE);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		// 更改画笔的颜色
		case R.id.color_red:
			paint.setColor(Color.RED);
			break;
		case R.id.color_green:
			paint.setColor(Color.GREEN);
			break;
		case R.id.color_blue:
			paint.setColor(Color.BLUE);
			break;
		case R.id.color_yellow:
			paint.setColor(Color.YELLOW);
			break;
		case R.id.color_pink:
			paint.setColor(0xffff99ff);
			break;
		default:
			break;
		}
	}

	@Override
	public void onProgressChanged(SeekBar seekBar, int progress,
			boolean fromUser) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {
		// 改变画笔的粗细
		int progress = seekBar.getProgress();// 0-100(1sp--->10sp)
		// paint.setStrokeWidth(progress);
		paint.setStrokeWidth(10 * progress / 100f);
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// 触摸Imageview的回调

		// 触摸的时候需要绘制图像,并且显示

		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:// 1次
			// 1. 手指按下

			startX = event.getX();// 触摸的x坐标
			startY = event.getY();// 触摸的y坐标

			break;
		case MotionEvent.ACTION_MOVE:// 0-多次
			// 2. 手指移动
			float stopX = event.getX();
			float stopY = event.getY();

			// 绘制图像，并且显示到imgeview上

			canvas.drawLine(startX, startY, stopX, stopY, paint);

			// 画纸上有数据了
			iv.setImageBitmap(bitmap);

			// 更新起始点
			startX = stopX;
			startY = stopY;
			break;
		case MotionEvent.ACTION_UP:// 1次
			// 3. 手指抬起
			break;
		default:
			break;
		}

		// 消费触摸事件
		return true;
	}

	// 对应的菜单按钮
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return super.onCreateOptionsMenu(menu);
	}

	// 菜单按钮的点击事件
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		int itemId = item.getItemId();
		switch (itemId) {
		case R.id.action_save:
			Log.d(TAG, "点击了保存按钮!");
			// 将bitmap存储到本地

			File file = new File(Environment.getExternalStorageDirectory(),
					System.currentTimeMillis() + ".jpg");
			FileOutputStream fos = null;
			try {
				fos = new FileOutputStream(file);
				// 将bitmap压缩到流中
				bitmap.compress(CompressFormat.JPEG, 100, fos);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} finally {
				if (fos != null) {
					try {
						fos.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
					fos = null;
				}
			}

			// 模拟 sdcard的挂载
			Intent intent = new Intent(Intent.ACTION_MEDIA_MOUNTED);
			intent.setData(Uri.fromFile(Environment
					.getExternalStorageDirectory()));
			sendBroadcast(intent);

			break;

		default:
			break;
		}

		return super.onOptionsItemSelected(item);
	}

}
