package org.itheima62.color;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class MainActivity extends Activity implements OnSeekBarChangeListener {

	private ImageView iv;

	private SeekBar skbRed;
	private SeekBar skbGreen;
	private SeekBar skbBlue;
	private float redPercent = 1;
	private float greenPercent = 1;
	private float bluePercent = 1;

	private Bitmap srcBitmap;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		iv = (ImageView) findViewById(R.id.iv);
		skbRed = (SeekBar) findViewById(R.id.skb_red);
		skbGreen = (SeekBar) findViewById(R.id.skb_green);
		skbBlue = (SeekBar) findViewById(R.id.skb_blue);

		skbRed.setOnSeekBarChangeListener(this);
		skbGreen.setOnSeekBarChangeListener(this);
		skbBlue.setOnSeekBarChangeListener(this);

		// 加载图片显示

		String path = "mnt/sdcard/img_small_1.jpg";
		srcBitmap = BitmapFactory.decodeFile(path);
		iv.setImageBitmap(srcBitmap);
	}

	@Override
	public void onProgressChanged(SeekBar seekBar, int progress,
			boolean fromUser) {
		// seekbar进度改变时的回调
	}

	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {
		// 开始拖动seekbar的回调
	}

	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {
		// 停止拖动seekbar的回调

		// 0-100;//0-2
		int progress = seekBar.getProgress();
		float percent = progress / 50f;// (0-2f)

		if (seekBar == skbRed) {
			this.redPercent = percent;
		} else if (seekBar == skbGreen) {
			this.greenPercent = percent;
		} else if (seekBar == skbBlue) {
			this.bluePercent = percent;
		}

		// 去改变图片的颜色
		// 1.去获得图片的拷贝

		Bitmap copyBitmap = Bitmap.createBitmap(srcBitmap.getWidth(),
				srcBitmap.getHeight(), srcBitmap.getConfig());
		Canvas canvas = new Canvas(copyBitmap);
		Matrix matrix = new Matrix();

		// 2.去处理图片的中的颜色数据
		Paint paint = new Paint();
		// 设置画笔的颜色过滤
		// vector:0-2 0:没有 2：最多
		float[] cm = new float[] { 1 * redPercent, 0, 0, 0, 0, // red vector
				0, 1 * greenPercent, 0, 0, 0, // green vector
				0, 0, 1 * bluePercent, 0, 0, // blue vector
				0, 0, 0, 1, 0 // alpha vector
		};
		paint.setColorFilter(new ColorMatrixColorFilter(new ColorMatrix(cm)));

		canvas.drawBitmap(srcBitmap, matrix, paint);

		

		// 3.将处理的结果展示
		iv.setImageBitmap(copyBitmap);

	}
}
