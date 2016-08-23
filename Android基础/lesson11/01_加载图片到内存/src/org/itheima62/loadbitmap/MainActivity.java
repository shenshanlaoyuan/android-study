package org.itheima62.loadbitmap;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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

	public void loadBitmap(View view) {

		String path = "mnt/sdcard/img_small_1.jpg";
//		String path = "mnt/sdcard/img_big_1.jpg";
		// 加载图片
		Bitmap bitmap = BitmapFactory.decodeFile(path);

		iv.setImageBitmap(bitmap);

	}
}
