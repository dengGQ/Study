package com.dgq.utils;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailUtil {
	private final Properties props = System.getProperties();
	private static MailUtil mailUtil = null;
	private String userName;//
	private String password;//
	private String from; //
	private String to;//
	private String smtpType;// 
	private String mailTitle;//
	private String mailContent;//
	
	public static MailUtil getMailUtil(String userName, String password,
			String to, String mailTile, String content) {
		if (mailUtil == null) {
			mailUtil = new MailUtil();
		}

		mailUtil.setUserName(userName);
		mailUtil.setPassword(password);
		mailUtil.setSmtpType("mail." + userName.split("@")[1]);
		mailUtil.setFrom(userName);
		mailUtil.setTo(to);
		mailUtil.setMailTitle(mailTile);
		mailUtil.setMailContent(content);
		return mailUtil;
	}

	public String sendMail() {
		props.setProperty("mail.smtp.host", smtpType);
		props.setProperty("mail.smtp.auth", "true");
		Session session = Session.getInstance(props);
		session.setDebug(true);
		MimeMessage mimeMessage = new MimeMessage(session);

		try {
			InternetAddress fromAddress = new InternetAddress(from);
			mimeMessage.setFrom(fromAddress);

			InternetAddress toAddress = new InternetAddress(to);
			mimeMessage.setRecipient(Message.RecipientType.TO, toAddress);

			mimeMessage.setSubject(mailTitle);
			mimeMessage.setContent(mailContent, "text/html;charset=GBK");

			Transport transport = session.getTransport("smtp");

			transport.connect(smtpType, userName, password);
			transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
			transport.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
	
	public static void main(String[] args) {
		MailUtil mailUtil2 = getMailUtil("565820745@qq.com", "dgq1005!@#", "30397807@qq.com", "--", "閫熷洖鐢碉紒");
		
		mailUtil2.sendMail();
	}

	private MailUtil() {
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getSmtpType() {
		return smtpType;
	}

	public void setSmtpType(String smtpType) {
		this.smtpType = smtpType;
	}

	public String getMailTitle() {
		return mailTitle;
	}

	public void setMailTitle(String mailTitle) {
		this.mailTitle = mailTitle;
	}

	public String getMailContent() {
		return mailContent;
	}

	public void setMailContent(String mailContent) {
		this.mailContent = mailContent;
	}
}
