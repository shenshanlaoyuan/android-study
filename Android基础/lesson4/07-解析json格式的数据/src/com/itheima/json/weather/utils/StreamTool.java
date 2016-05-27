package com.itheima.json.weather.utils;

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
		
		return data;
	}

}
