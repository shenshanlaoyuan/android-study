package com.itheima.messgehelper;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class SmsAcitivityList extends Activity {

	private ListView lv;
	String[] msgs = { 
			"想到你的名字心就会砰砰跳！看到你的容颜脸就会火辣辣烧！牵你的手像云朵轻轻飘！你可知道？我被你彻底迷倒！发誓要和你一起变老",
			"你是否愿意给我一个依靠：可以让我在红尘的烦恼与喧嚣中一想到你，就会有甜蜜的平静和无穷的动力，就会有足够的勇气继续向前走。",
			"命运让我们相遇，让我沉醉在你的眼眸里。我想陪着你，为你阻挡一生的风雨。请你相信，我会让你的生命，充满快乐回忆！",
			"我会把心里最好的地方留给你，只要你敲敲门，我就拥你入怀。因为爱你，愿意为你在这世界造一处平台，与你纵观爱情的古往今来。",
			"不经意间，相遇；不经意间，相惜；不经意间，刻骨；不经意间，铭记；不经意间，爱上了你。看似不经意，但我真的很在意。",
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.smslist);
		lv = (ListView) findViewById(R.id.lv);

		// 设置 一个 适配器
		lv.setAdapter(new ArrayAdapter<String>(this, R.layout.smsitem, msgs));
		
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				
				System.out.println("某个item 被 点击 了  :　" + position);
				
				//这里的position 就是 被点击的item 
				// 刚好 就是  被选中的 短消息 所在的 item 
				String msg = msgs[position];
				
				Intent intent = new Intent();
				intent.putExtra("msg", msg);
				
//				//这里 就又 开启了一个 新的  MainActivity ...
//				intent.setClass(SmsAcitivityList.this, MainActivity.class);
//				startActivity(intent);
				
				// 一旦调用 了  setResult 之后, 那么 就会  回到 启动 当前 activity 所在的 
				setResult(0, intent);
				
				//介绍当前的   SmsAcitivityList 
				
// Call this when your activity is done and should be closed. The ActivityResult is
//	propagated back to whoever launched you via onActivityResult().
// 
				finish();
			}
		});
	}

}
