package com.itheima;

import java.io.StringReader;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

/*
 *    需要导入 第三方 jar 包 
 * 		
 * 		可以去 pull 解析器的官方文档中 找 , 也可以 到 谷歌 集成  进来的api 中去找 , 还可以 到 你自己下载的
 * 		文档中去找 
 * 
 * 	PullParser		
 * 
 * 
 * 
 */
public class Demo {
	
	
	public static void main(String[] args) throws Exception {
		
		//拿到 一个工厂 类 
		XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
		
		//  是否支持 名称 空间 的解析 
        factory.setNamespaceAware(true);
        XmlPullParser xpp = factory.newPullParser();

        xpp.setInput ( new StringReader ( "<foo>Hello World!</foo>" ) );
        
        // 事件的 类型 
        int eventType = xpp.getEventType();
        
        // while --true的循环 
        while (eventType != xpp.END_DOCUMENT) {
         if(eventType == xpp.START_DOCUMENT) {
             System.out.println("Start document");
         } else if(eventType == xpp.END_DOCUMENT) {
             System.out.println("End document");
         } else if(eventType == xpp.START_TAG) {
             System.out.println("Start tag "+xpp.getName());
         } else if(eventType == xpp.END_TAG) {
             System.out.println("End tag "+xpp.getName());
         } else if(eventType == xpp.TEXT) {
             System.out.println("Text "+xpp.getText());
         }
         
         //手动 移动了 那个 指针 
         eventType = xpp.next();
        }
	}
	
}
