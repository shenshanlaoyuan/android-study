package org.itheima62.siyifu;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.MonthDisplayHelper;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;

public class MainActivity extends Activity implements OnTouchListener {
	private ImageView ivAfter;
	private ImageView ivPre;

	private Bitmap copyBitmap;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		ivAfter = (ImageView) findViewById(R.id.iv_after);
		ivPre = (ImageView) findViewById(R.id.iv_pre);

		// 设置默认的图片
		ivAfter.setImageResource(R.drawable.after);
		ivPre.setImageResource(R.drawable.pre);

		// 监听上面图片的touch事件
		ivPre.setOnTouchListener(this);

		// 内存中的图片
		Bitmap bitmap = BitmapFactory.decodeResource(getResources(),
				R.drawable.pre);

		copyBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(),
				bitmap.getConfig());
		Canvas canvas = new Canvas(copyBitmap);
		Paint paint = new Paint();
		Matrix matrix = new Matrix();
		canvas.drawBitmap(bitmap, matrix, paint);
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {

		// 滑动的过程中，取到 原图的拷贝，修改拷贝中的颜色，将拷贝的图片设置给imageView

		int action = event.getAction();
		switch (action) {
		case MotionEvent.ACTION_DOWN:
			// 按下，颜色去除
			int downX = (int) (event.getX() + 0.5f);
			int downY = (int) (event.getY() + 0.5f);

			// 颜色去除
			clearColor(downX, downY);
			break;
		case MotionEvent.ACTION_MOVE:
			// 移动，颜色去除
			int moveX = (int) (event.getX() + 0.5f);
			int moveY = (int) (event.getY() + 0.5f);

			// 颜色去除
			clearColor(moveX, moveY);
			break;
		case MotionEvent.ACTION_UP:
			break;
		default:
			break;
		}

		// move
		return true;
	}

	private void clearColor(int x, int y) {
		// 颜色去除
		for (int i = -3; i <= 3; i++) {
			for (int j = -3; j <= 3; j++) {
				try {
					if (i * i + j * j <= 9) {
						copyBitmap.setPixel(x + i, y + j, Color.TRANSPARENT);// 单个像素点
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		}

		ivPre.setImageBitmap(copyBitmap);
	}
}
