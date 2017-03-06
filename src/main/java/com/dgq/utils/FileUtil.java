package com.dgq.utils;

import java.io.File;

public class FileUtil {

	/**
	 * 判断目录是否存在，不存在就创建，存在就返回
	 * 
	 * @param dirName
	 * @return
	 */
	public static File isDir(File dirName) {
		if (!dirName.exists()) {
			dirName.mkdirs();
		}
		return dirName;
	}

}
