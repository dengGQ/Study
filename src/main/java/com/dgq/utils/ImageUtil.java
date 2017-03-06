package com.dgq.utils;

public class ImageUtil {

	public static synchronized String getImageNameByTime(String imageName)
			throws Exception {
		Thread.sleep(1);

		StringBuilder sb = new StringBuilder();

		sb.append(System.currentTimeMillis());
		sb.append(imageName.substring(imageName.lastIndexOf("."),
				imageName.length()).toLowerCase());

		return sb.toString();
	}

}
