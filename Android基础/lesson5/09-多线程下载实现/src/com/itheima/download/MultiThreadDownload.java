package com.itheima.download;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
/* 
 *    写代码的功底  
 * 	
 */
public class MultiThreadDownload {

	// 规定下载 服务器端的资源 使用 3 条 线程 去下载
	private static int threadCount = 3;
	private static int currentRunningThread = 3;
	// private static String path ="http://192.168.1.100:8080/file.txt";
	private static String path = "http://192.168.1.100:8080/ff.exe";

	public static void main(String[] args) {

		// 1. 服务器 发送 请求, 拿到 要下载的文件的 长度是多少

		try {
			URL url = new URL(path);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();

			conn.setConnectTimeout(5000);
			conn.setRequestMethod("GET");

			int code = conn.getResponseCode();

			if (code == 200) {

				// 拿到文件的长度 大小
				int length = conn.getContentLength();
				File file = new File(getFileName(path));

				RandomAccessFile raf = new RandomAccessFile(file, "rw");
				raf.setLength(length);
				raf.close();

				// 启动线程去下载文件了

				// 每块 线程 下载的平均的大小
				int blockSize = length / threadCount;

				// threadId :线程的id号
				// threadCount : 开几条线程 下载
				for (int threadId = 0; threadId < threadCount; threadId++) {

					int startIndex = threadId * blockSize;
					int endIndex = (threadId + 1) * blockSize - 1;

					if (threadId == (threadCount - 1)) {
						endIndex = length - 1;
					}

					new DownloadFilePartThread(threadId, startIndex, endIndex)
							.start();
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static class DownloadFilePartThread extends Thread {

		// 线程的 id 号
		private int threadId;

		// 线程的 下载的开始 位置
		private int startIndex;

		// 线程的 下载的结束 位置
		private int endIndex;

		// 当前线程 下载到的位置
		private int currentPostion;

		public DownloadFilePartThread(int threadId, int startIndex, int endIndex) {

			this.threadId = threadId;
			this.startIndex = startIndex;
			this.endIndex = endIndex;
			currentPostion = startIndex;
		}

		@Override
		public void run() {

			System.out.println("第 " + threadId + "线程开始 下载了   : 下载  从 "
					+ startIndex + "~ " + endIndex);
			// 去干 下载的 事儿 -- 要下载 目标 部分的数据
			// 需要 连接上 服务器
			try {
				URL url = new URL(path);
				HttpURLConnection conn = (HttpURLConnection) url
						.openConnection();

				conn.setConnectTimeout(5000);
				conn.setRequestMethod("GET");

				// 在多线 程 下载的时候 , 每条线程 只需要 目标 文件的 一部分的数据
				// 需要 告诉 服务器, 只需要 那一段的数据
				// 通过 设置 http的 请求头 可以去实现 , 告诉 服务器, 只需要目标 段的数据

				// startIndex ---- endIndex
				conn.setRequestProperty("range", "bytes=" + startIndex + "-"
						+ endIndex);

				// 获得 服务器返回的目标段的数据

				File file = new File(getFileName(path));
				RandomAccessFile raf = new RandomAccessFile(file, "rw");

				File ilf = new File(threadId + ".position");

				if (ilf.exists() && ilf.length() > 0) {

					BufferedReader br = new BufferedReader(new FileReader(ilf));
					String vl = br.readLine();

					int alreadyWritePosition = Integer.parseInt(vl);
					// 告诉 服务器要数据的时候 ,从这个位置 开始 要
					conn.setRequestProperty("range", "bytes="
							+ alreadyWritePosition + "-" + endIndex);
					// 要 告诉 从哪个位置开始写
					raf.seek(alreadyWritePosition);
					System.out.println("表示 之前下载过 ");
				} else {

					System.out.println("表示 之前 没有   下载过 ");
					conn.setRequestProperty("range", "bytes=" + startIndex
							+ "-" + endIndex);
					// 要 告诉 从哪个位置开始写
					raf.seek(startIndex);
				}

				// 206 ---
				int code = conn.getResponseCode();
				if (code == 206) {

					// 拿到 数据
					InputStream in = conn.getInputStream();

					int len = 0;
					byte[] buf = new byte[1024*1024];
					while ((len = in.read(buf)) > 0) {
						raf.write(buf, 0, len);

						// 将 实时的位置 给 记录了 下载了, 记录了之后, 方便 下面紧接着 去 往 文件中去写
						currentPostion = currentPostion + len;
						File info = new File(threadId + ".position");
						
						RandomAccessFile rf = new RandomAccessFile(info, "rwd");
						
						// 硬盘设备 :　机械硬盘(小马达 ) , 固态硬盘(没有 所谓的马达 , ssd ) 
						
						// 达到一定的次数 就会报废 
						
						// out.write((currentPostion+"").getBytes());
						rf.write(String.valueOf(currentPostion).getBytes());
						rf.close();
					}

					in.close();
					raf.close();
				}

				System.out.println("第 " + threadId + "线程 下载  结束   了 ");

				// 等着 所有的线程 都下载完成后 再去 删 文件
				// 弄一个 计数器, 记住 总共有 多少条线程 正在下载, 每当一条线程 下载完成, 走到 了这里的时候, 就将计数器-1
				// 一下,
				// 当发现 计数器 小于 或者 等于0 的时候, 说明没有线程正在下载了, 所以 这个时候, 再去删 记录了下载位置的文件

				synchronized (MultiThreadDownload.class) {

					currentRunningThread--;
					if (currentRunningThread <= 0) {

						// 将记录下载位置的文件给删掉

						for (threadId = 0; threadId < threadCount; threadId++) {
							File fff = new File(threadId + ".position");
							
							fff.renameTo(new File(threadId + ".position.finish"));
							File fll = new File(threadId + ".position.finish");
							fll.delete();
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static String getFileName(String path) {
		int index = path.lastIndexOf("/");

		return path.substring(index + 1);
	}

}
