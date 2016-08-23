package org.itheima.takephoto;

import java.io.File;

import org.itheima.takevideo.R;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;

public class MainActivity extends Activity {

	private static final int CAPTURE_VIDEO_ACTIVITY_REQUEST_CODE = 100;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

	}

	public void takephoto(View view) {
		// create Intent to take a picture and return control to the calling
		// application
		// 意图
		Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);// 去打开系统的照相机-录制

		// 准备一个接收系统拍好照片后的文件路径
		File file = new File(Environment.getExternalStorageDirectory(),
				System.currentTimeMillis() + ".3gp");

		intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file)); // set the
																		// image
																		// file
		intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);
		// name

		// start the image capture Intent
		// 请求code
		startActivityForResult(intent, CAPTURE_VIDEO_ACTIVITY_REQUEST_CODE);

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		if (requestCode == CAPTURE_VIDEO_ACTIVITY_REQUEST_CODE) {
			// 为你的某个请求返回的结果

			// 给你数据的Activity设置的结果标记
			// resultCode
			switch (resultCode) {
			case Activity.RESULT_OK:
				// 获取数据成功
				System.out.println("ok");
				break;
			case Activity.RESULT_CANCELED:
				// 用户取消操作
				System.out.println("cancel");
				break;
			default:
				break;
			}

		}

	}
}
