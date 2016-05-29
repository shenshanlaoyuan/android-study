package com.itheima.news.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class StreamTool {

	public static String decodeStream(InputStream in) throws Exception {
		
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		byte[] buf = new byte[1024];
		int len=0;
		
		while((len=in.read(buf))>0){
			baos.write(buf, 0, len);
		}
		
		return baos.toString();
	}

}
