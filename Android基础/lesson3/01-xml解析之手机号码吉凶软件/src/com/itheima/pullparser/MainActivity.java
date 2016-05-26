package com.itheima.pullparser;

import java.io.IOException;
import java.io.InputStream;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import com.itheima.pullparser.domain.Product;

import android.os.Bundle;
import android.app.Activity;
import android.util.Xml;
import android.view.Menu;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        //使用 xml  pull 解析器 去 解析 xml 文件的内容
        
        // Xml
        XmlPullParser pullParser = Xml.newPullParser();
        
        try {
			InputStream in = getAssets().open("result.xml");
			
			// 解析的源 是什么 ???  --- result.xml
			pullParser.setInput(in, "gbk");
			
			//获得 一个 事件的 类型 
			int eventType = pullParser.getEventType();
/*
<?xml version="1.0" encoding="gbk"?>
<smartresult>
  <product type="mobile">
	<phonenum>13512345678</phonenum>
	<location>重庆移动神州行卡</location>
	<phoneJx>有得有失，华而不实，须防劫财，始保平安 吉带凶</phoneJx>
 </product>
</smartresult>
 * 			
 */
			Product p=null;
			while(eventType!=XmlPullParser.END_DOCUMENT){
				
				if(eventType==XmlPullParser.START_TAG){
					
					//判断是否是 元素的开始 , 只要是某个 元素的开始位置, 那么就会进入这里 
					//获得 当前解析到的元素的名称
					if("product".equals(pullParser.getName())){
						p = new Product();
						// sax 解析 
						
						//准备 product 类的一个实例 , 去 封装数据
						String type = pullParser.getAttributeValue(0);
						p.setType(type);
					}else if("phonenum".equals(pullParser.getName())){
						
						//获得   13512345678
						
						// <phonenum>13512345678</phonenum>
						String phonenum = pullParser.nextText();
						p.setPhonenum(phonenum);
					}else if("location".equals(pullParser.getName())){
						
						//<location>重庆移动神州行卡</location>
						String location = pullParser.nextText();
						p.setLocation(location);
					}else if("phoneJx".equals(pullParser.getName())){
						
//						<phoneJx>有得有失，华而不实，须防劫财，始保平安 吉带凶</phoneJx>
						String phoneJx = pullParser.nextText();
						pullParser.getText();
						p.setPhoneJx(phoneJx);
					}
				}
				//手动 挪动 "指针 "
				eventType = pullParser.next();
			}
			
			if(p!=null){
				System.out.println(p.toString());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
        
        
    }

}
