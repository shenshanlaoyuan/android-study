package org.itheima59.opts.scale;

import org.itheima59.opts.rotate.R;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends Activity {

	private ImageView ivSrc;
	private ImageView ivDest;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		ivSrc = (ImageView) findViewById(R.id.iv_src);
		ivDest = (ImageView) findViewById(R.id.iv_dest);
	}

	public void opts(View view) {
		String path = "mnt/sdcard/img_small_1.jpg";
		// 1.显示原图
		Bitmap srcBitmap = BitmapFactory.decodeFile(path);
		ivSrc.setImageBitmap(srcBitmap);

		// 1. 准备画纸:(大小和材质需要参考原图)
		Bitmap copyBitmap = Bitmap.createBitmap(srcBitmap.getWidth(),
				srcBitmap.getHeight(), srcBitmap.getConfig());
		// 2. 准备画板，将画纸放到画板上
		Canvas canvas = new Canvas(copyBitmap);
		// 3. 准备画笔
		Paint paint = new Paint();
		// 4. 按照一定的规则
		Matrix matrix = new Matrix();// 1:1照着画
		// 按照比例缩放图片
		// matrix.setScale(0.6f, 0.8f);
		// 位移操作:dx:x方向的增量 dy：y方向的增量
		// matrix.setTranslate(50, 50);
		// matrix.setRotate(45);
		// px:围绕的中心点
		matrix.setRotate(45, srcBitmap.getWidth() / 2f,
				srcBitmap.getHeight() / 2f);

		// 5. 将原图像按照规则画到画板上
		canvas.drawBitmap(srcBitmap, matrix, paint);

		// 画纸有数据了
		ivDest.setImageBitmap(copyBitmap);
	}
}
