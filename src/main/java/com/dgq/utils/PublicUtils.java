package com.dgq.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

public class PublicUtils {

	public static String downloadWeixinImg(String imgUrl, String downloadPath,
			String filename) throws Exception {

		URL url = new URL(imgUrl);
		URLConnection conn = url.openConnection();
		conn.setConnectTimeout(5 * 1000);

		String headerField = conn.getHeaderField("Content-Type");
		System.out.println(headerField);
		InputStream inputStream = conn.getInputStream();
		byte[] b = new byte[1024];
		int len;
		File dirName = new File(downloadPath);

		FileUtil.isDir(dirName);

		OutputStream outputStream = new FileOutputStream(dirName.getPath()
				+ "\\" + filename);
		while ((len = inputStream.read(b)) != -1) {
			outputStream.write(b, 0, len);
		}
		return null;
	};

	public static void main(String[] args) throws Exception {
		String imgUrl = "http://mmbiz.qpic.cn/mmbiz/eJQKh8wH4FEqPUibTlOAu6N1lEdiayctPESFNRAialvH1ZoicBcweZ0jYc3qbpCcz9NbE7qdxX1643bibKSRmE2w5bg/0";
		System.out.println(imgUrl);
		String downloadPath = "f:\\message\\";
		System.out.println(downloadPath);
		String filename = ImageUtil.getImageNameByTime("dns:" + Math.random()
				+ ".jpg");
		System.out.println(filename);
		downloadWeixinImg(imgUrl, downloadPath, filename);
	}
}
