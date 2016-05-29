package com.itheima.news.domain;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;

import android.util.Xml;

public class NewsService {
	
	public static List<NewsItem> getAllNewsItem(final String path){
		
		final List<NewsItem> items = new ArrayList<NewsItem>();
		
		 new Thread(){
	        	
	        	public void run() {
	        		
	        		try {
						URL url = new URL(path);
						
						HttpURLConnection conn = (HttpURLConnection) url.openConnection();
						
						//设置超时时间
						conn.setConnectTimeout(5000);
						conn.setRequestMethod("GET");
						
						int code = conn.getResponseCode();
						
						if(code==200){
							
							InputStream in = conn.getInputStream();
							
							//将 xml 格式的数据转换为 字符串返回 
//							String data = StreamTool.decodeStream(in);
//							
//							// 需要对照 着  xml 格式的数据 进行 解析 
//							System.out.println(data);
							
							XmlPullParser parser = Xml.newPullParser();
							parser.setInput(in, "utf-8");
	/*

	<item>
	  <title>军报评徐才厚</title> 
	  <description>人死账不消 反腐步不停，支持，威武，顶，有希望了。</description>
	  <image>http://192.168.1.100:8080/img/a.jpg</image>
	  <type>1</type>
	  <comment>163</comment>
	</item>

	面向对象的思想, 会将数据 封装到  javabean 中 --- newsItem

	 * 						
	 */
							
							//开始解析
							int type = parser.getEventType();
							NewsItem item=null;
							while(type!=XmlPullParser.END_DOCUMENT){
								
								if(type==XmlPullParser.START_TAG){
									if("item".equals(parser.getName())){
										item = new NewsItem();
									}else if("title".equals(parser.getName())){
										item.setTitle(parser.nextText());
									}else if("description".equals(parser.getName())){
										item.setDescription(parser.nextText());
									}else if("image".equals(parser.getName())){
										item.setImage(parser.nextText());
									}else if("type".equals(parser.getName())){
										item.setType(parser.nextText());
									}else if("comment".equals(parser.getName())){
										item.setComment(parser.nextText());
									}
								}else if(type==XmlPullParser.END_TAG){
									
									//将 item 添加到一个 list集合中
									if(item!=null){
										items.add(item);
									}
								}
								type = parser.next();
							}
						}
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	        	};
	        }.start();
	        
	        return items;
	        
	}
}
