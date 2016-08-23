package org.itheima62.video;

import android.app.Activity;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

public class MainActivity extends Activity {

	private VideoView vv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		vv = (VideoView) findViewById(R.id.vv);

		MediaController mc = new MediaController(this);
		mc.setAnchorView(vv);

		vv.setMediaController(mc);
		vv.setVideoPath("mnt/sdcard/areyouok.3gp");
		vv.start();
	}

}
