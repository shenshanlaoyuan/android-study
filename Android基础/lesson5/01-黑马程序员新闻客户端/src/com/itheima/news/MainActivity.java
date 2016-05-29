package com.itheima.news;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;

import com.itheima.news.domain.NewsItem;
import com.itheima.news.domain.NewsService;
import com.itheima.news.utils.StreamTool;
import com.loopj.android.image.SmartImageView;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Color;
import android.util.Xml;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity {
						//http://192.168.1.100:8080/news.xml
	private String path ="http://192.168.1.100:8080/news.xml";
	private List<NewsItem> items  =null;
	private ListView lv;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        lv = (ListView) findViewById(R.id.lv);
        
        //就去 访问 服务器 , 将 数据 给 拿下来 
       
        items = NewsService.getAllNewsItem(path);
        
        // 将item中的数据 绑定到屏幕显示
        
        loadData();
        
    }

    //声明adapter 
    private MyAdapter myadapter;
    
	private void loadData() {
		
		if(myadapter==null){
			
			myadapter = new MyAdapter();
			lv.setAdapter(myadapter);
		}else{
			//通知 数据 改变
			myadapter.notifyDataSetChanged();
		}
	}

	private class MyAdapter extends BaseAdapter{

		//指定 到底有 多少个item 要显示在 lv 中
		@Override
		public int getCount() {
			return items.size();
		}

		//这个方法是在每次 显示一个item 时 会被调用到的 
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View v ;
			if(convertView==null){
				v=View.inflate(MainActivity.this, R.layout.item, null);
			}else{
				v = convertView;
			}
			
			//拿到 当前位置 newsItem对象
			NewsItem newsItem = items.get(position);
			
			
			
			//找到 item 中 每个 控件 
			SmartImageView siv = (SmartImageView) v.findViewById(R.id.item_iv);
			TextView title= (TextView) v.findViewById(R.id.item_title);
			TextView desc = (TextView) v.findViewById(R.id.item_desc);
			TextView type = (TextView) v.findViewById(R.id.item_type);
			
			title.setText(newsItem.getTitle());
			desc.setText(newsItem.getDescription());
			
			String tp = newsItem.getType();
			if("1".equals(tp)){
				//就是评论
				type.setText("评论: "+ newsItem.getComment());
				type.setTextColor(Color.YELLOW);
				
			}else if("2".equals(tp)){
				
				//就是视频
				type.setText("视频");
				type.setTextColor(Color.RED);
				
			}else if("3".equals(tp)){
				
				// 就是直播 
				type.setText("Live直播 ");
				type.setTextColor(Color.BLUE);
			}
			
			System.out.println(newsItem.getImage());
			
			//由于 显示互联网上的图片是一个非常常见的操作.  所以  就很好兄弟 已经实现好了现成的 类库, 咱们 只需要拿过来用一下就可以了 ...
			// github  :  
			//全球 最大的 开源项目 托管网站 
//			iv.setI
			
			siv.setImageUrl(newsItem.getImage());
			
			return v;
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
