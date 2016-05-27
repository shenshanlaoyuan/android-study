package com.itheima.phonejixiong.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class StreamTool {

	public static String decodeStream(InputStream in) throws IOException {
		
		// µ×²ãÁ÷
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		int len =0;
		byte[] buf = new byte[1024];
		
		while((len=in.read(buf))>0){
			baos.write(buf, 0, len);
		}
		
		String data = baos.toString();
		
		if(data.contains("gbk")){
			return baos.toString("gbk");
		}else if(data.contains("utf-8")){
			
			return baos.toString("utf-8");
		}else{
			return data;
		}
		
		
	}

}
