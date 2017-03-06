package com.dgq.utils;

import java.util.Date;

public class TestSendEmail {
	static final String userName = "service@ps.cn";
	static final String password = "wxyywxyy123";

	public static void main(String[] args) {// ("565820745@qq.com", "ww",
											// "1213132213dfssdfa");,dan@rntd.cn,vbqJ8NQ3A8
	// MailUtil mailUtil2 = MailUtil.getMailUtil("dan@rntd.cn", "vbqJ8NQ3A8",
	// "565820745@qq.com", "ww", "1213132213dfssdfa");
	// mailUtil2.sendMail();
		String ss = "~cut~weqweqeqw~cut~img123123132.png~cut~dsfafewfew~cut~dsfafewfew~cut~img123123132.png~cut~weqweqeqw~cut~img123123132.png~cut~dsfafewfew~cut~dsfafewfew~cut~img123123132.png";
		//
		// String remarkText = getRemarkText(ss.split("~cut~"));
		// System.out.println(remarkText.equals(""));
		// System.out.println(remarkText);

		System.out.println(getRemarkText(ss));
	}

	public static String getRemarkText(String remark) {//
		if (remark.indexOf("~cut~img") == -1 && remark.indexOf("~cut~") == -1) {// 老数据
			return remark;
		}
		String[] textOrImg = remark.split("~cut~img");
		remark = "";
		for (String content : textOrImg) {
			if (content.contains("~cut~")) {// 文本
				String[] text = content.split("~cut~");
				for (String str : text) {
					if (!str.endsWith(".png") && !str.endsWith(".jpg")
							&& !str.endsWith(".jpeg") && !str.endsWith(".bmp")
							&& !str.endsWith(".gif") && !str.equals("")) {// 文字
						remark += str + "。";
					}
				}
			}
		}
		return remark;
	}
}
