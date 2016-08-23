package org.itheima62.canvas;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Paint.Style;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends Activity {
	private ImageView iv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		iv = (ImageView) findViewById(R.id.iv);
	}

	public void line(View view) {
		// 1.画线
		// 准备画纸
		Bitmap bitmap = Bitmap.createBitmap(320, 320, Config.ARGB_8888);
		// 准备画布
		Canvas canvas = new Canvas(bitmap);
		Paint paint = new Paint();

		canvas.drawLine(10, 10, 200, 200, paint);

		iv.setImageBitmap(bitmap);
	}

	public void rect(View view) {
		// 1.画矩形
		// 准备画纸
		Bitmap bitmap = Bitmap.createBitmap(320, 320, Config.ARGB_8888);
		// 准备画布
		Canvas canvas = new Canvas(bitmap);

		Paint paint = new Paint();
		// 设置画笔的颜色
		paint.setColor(Color.RED);
		// 设置画笔的样式
		paint.setStyle(Style.STROKE);
		paint.setStrokeWidth(10);// 设置粗细

		canvas.drawRect(30, 30, 200, 200, paint);

		iv.setImageBitmap(bitmap);
	}

	public void circle(View view) {
		// 1.画圆形
		// 准备画纸
		Bitmap bitmap = Bitmap.createBitmap(320, 320, Config.ARGB_8888);
		// 准备画布
		Canvas canvas = new Canvas(bitmap);

		Paint paint = new Paint();
		// 设置画笔的颜色
		paint.setColor(Color.RED);
		paint.setAntiAlias(true);// 设置抗锯齿

		float cx = 160;// 圆心的坐标X
		float cy = 160;// 圆心的坐标Y
		float radius = 100;// 半径
		canvas.drawCircle(cx, cy, radius, paint);

		iv.setImageBitmap(bitmap);
	}

	public void arc(View view) {
		// 1.扇形
		// 准备画纸
		Bitmap bitmap = Bitmap.createBitmap(320, 320, Config.ARGB_8888);
		// 准备画布
		Canvas canvas = new Canvas(bitmap);
		Paint paint = new Paint();
		paint.setColor(Color.RED);
		paint.setAntiAlias(true);// 设置抗锯齿

		// 1:矩形
		RectF oval = new RectF(20, 20, 200, 200);
		float startAngle = 0;// 起始角度
		float sweepAngle = 120;// 扫过的角度
		boolean useCenter = false;// 是否画中心部分
		canvas.drawArc(oval, startAngle, sweepAngle, useCenter, paint);

		iv.setImageBitmap(bitmap);
	}

	public void trangle(View view) {
		// 1.多边形
		// 准备画纸
		Bitmap bitmap = Bitmap.createBitmap(320, 320, Config.ARGB_8888);
		// 准备画布
		Canvas canvas = new Canvas(bitmap);
		Paint paint = new Paint();
		paint.setColor(Color.RED);
		paint.setAntiAlias(true);// 设置抗锯齿

		float x1 = 160;
		float y1 = 20;

		float x2 = 140;
		float y2 = 200;

		float x3 = 180;
		float y3 = 200;

		Path path = new Path();
		path.moveTo(x1, y1);// 将画笔移动到点1
		path.lineTo(x2, y2);// 连线点2
		path.arcTo(new RectF(140, 180, 180, 220), 0, 180);
		path.lineTo(x3, y3);// 连线点3
		path.lineTo(x1, y1);// 连线点1
		path.close();

		canvas.drawPath(path, paint);

		iv.setImageBitmap(bitmap);
	}

}
