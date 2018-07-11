package com.hegongshan.mooc.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;

import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

public class MailUtils {
	public static String readHtml(String path) {
		InputStream input = MailUtils.class.getClassLoader().getResourceAsStream(path);
		BufferedReader br = null;
		StringBuilder html = new StringBuilder();
		String str = null;
		try {
			br = new BufferedReader(new InputStreamReader(input));
			while ((str = br.readLine()) != null) {
				html.append(str);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return html.toString();
	}

	/**
	 * 发送html格式的邮件
	 * 
	 * @param to
	 *            收件人邮箱
	 * @param from
	 *            寄件人邮箱
	 * @param subject
	 *            邮件主题
	 * @param html
	 *            邮件内容
	 * @throws MessagingException
	 */
	public static void sendHtmlMail(JavaMailSenderImpl mailSender, String to, InternetAddress from, String subject,
			String html) throws MessagingException {
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
		mimeMessageHelper.setTo(to);
		mimeMessageHelper.setFrom(from);
		mimeMessageHelper.setSubject(subject);
		mimeMessageHelper.setText(html, true);
		mailSender.send(mimeMessage);
	}

	public static void sendHtmlMailConfirmRegister(JavaMailSenderImpl mailSender, String to, String registerNickname,
			String registerUsername, String registerPassword, String confirmUrl)
			throws UnsupportedEncodingException, MessagingException {
		String html = readHtml("register.html");
		StringBuilder subject = new StringBuilder();
		int subjectStart = html.indexOf("<title>") + "<title>".length();
		int subjectEnd = html.indexOf("</title>");
		for (int i = subjectStart; i < subjectEnd; i++) {
			subject.append(html.charAt(i));
		}
		html = html.replace("#{nickname}", registerNickname);
		html = html.replace("#{username}", registerUsername);
		html = html.replace("#{password}", registerPassword);
		html = html.replace("#{confirmUrl}", confirmUrl);
		// 设置发送者昵称及邮箱
		String nickname = MimeUtility.encodeText("爱慕课网");
		InternetAddress from = new InternetAddress(nickname + "<" + mailSender.getUsername() + ">");

		sendHtmlMail(mailSender, to, from, subject.toString(), html);
	}
	
	public static void sendHtmlMailForForgetPassword(JavaMailSenderImpl mailSender, String to, String nickname,
			String randomPassword) throws MessagingException, UnsupportedEncodingException {
		String html = readHtml("forgetPassword.html");
		StringBuilder subject = new StringBuilder();
		int subjectStart = html.indexOf("<title>") + "<title>".length();
		int subjectEnd = html.indexOf("</title>");
		for (int i = subjectStart; i < subjectEnd; i++) {
			subject.append(html.charAt(i));
		}
		html = html.replace("#{nickname}", nickname);
		html = html.replace("#{username}", to);
		html = html.replace("#{randomPassword}", randomPassword);
		// 设置发送者昵称及邮箱
		String fromNickname = MimeUtility.encodeText("爱慕课网");
		InternetAddress from = new InternetAddress(fromNickname + "<" + mailSender.getUsername() + ">");
		
		sendHtmlMail(mailSender, to, from, subject.toString(), html);
	}
}
