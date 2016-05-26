package com.itheima.listview;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity {

	private ListView lv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// view
		lv = (ListView) findViewById(R.id.lv);

		lv.setAdapter(new MyAdapter());

		// mvc

		// m : 就是你要绑定到屏幕上的数据
		// v : 就是listview
		// c : 就是 MyAdapter
	}

	// ListAdapter--- Simplexxx, Basexxx.
	// ListAdapter
	class MyAdapter extends BaseAdapter {

		// 这个方法 是最开始 被 调用的, 用来 计算 到底 有多少个 item 需要显示在 lv (ListView )上
		@Override
		public int getCount() {
			 System.out.println(" getCount 被调用 了 ");
			return 100;
		}

		// 每个 item 要显示在 lv 上的时候, 会被调用到的方法
		// 谁调用 呢 ? ----- 是有 android 系统去调用, 不是你调用的
		// position : 当前的 item 是 处于第几个位置 给传递进来了.

		// convertView:　这个　用于做优化的
		// parent:　当前的　的 父 控件

		// 参考了现实 中卖 东西 , 找托儿的 事 ....
		// convertView -- 实际上 就是 这里 getView 返回的 textView对象 , 都是同一个 类型的对象 , 并且
		// 你会发现 convertView 就是
		// 之前的 textView
		// 内存中就 只有 需要 显示在 屏幕上的textView 了
		// 实现了 内存的 优化,

		// 如何 进行 优化 ?

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			if (convertView != null) {
				System.out.println("cv :" + convertView.toString());
			}

			View v;
			if (convertView == null) {
				v =  new TextView(MainActivity.this);
			} else {
				v = convertView;
			}

			System.out.println("getView 被调用了  : " + position);
			TextView tv = new TextView(MainActivity.this);
			tv.setText("我是 第 " + position + "个 item  ========");
			tv.setHeight(40);
			System.out.println("tv: " + tv.toString());
			return tv;
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return 0;
		}

	}

}
