package cn.karlwu.common.mail;

import java.util.Date;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;


/**
 * 简单邮件（不带附件的邮件）发送器
 */
public class SimpleMailSender {
	private static MailAuthor mailAuthor = null; // 判断是否需要身份认证
	private static Session sendMailSession = null; // 根据邮件会话属性和密码验证器构造一个发送邮件的session
	private static Message mailMessage = null; // 根据session创建一个邮件消息
	private static Properties pro = null;
	private static Address from = null; // 创建邮件发送者地址
	private static Address[] to = null; // 创建邮件的接收者地址
	private static Address[] toCC = null; // 创建邮件的抄送者地址

	public static boolean sendTextMail(MailSenderInfo mailInfo) {
		// 如果需要身份认证，则创建一个密码验证器
		if (mailInfo.isValidate()) {
			mailAuthor = MailAuthor.getInstance();
		}
		try {
			pro = mailInfo.getProperties();
			sendMailSession = Session.getDefaultInstance(pro, mailAuthor);
			mailMessage = new MimeMessage(sendMailSession);
			// 设置邮件消息的发送者
			from = new InternetAddress(mailInfo.getFromAddress());
			mailMessage.setFrom(from);
			// 设置收件人到邮件消息中
			String[] toAddress = mailInfo.getToAddress();
			to = new InternetAddress[toAddress.length];
			for (int i = 0; i < toAddress.length; i++) {
				to[i] = new InternetAddress(toAddress[i]);
			}
			mailMessage.setRecipients(Message.RecipientType.TO, to);
			// 设置邮件消息的主题
			mailMessage.setSubject(mailInfo.getSubject());
			// 设置邮件消息发送的时间
			mailMessage.setSentDate(new Date());
			//MimeMultipart类是一个容器类，包含MimeBodyPart类型的对象  
            Multipart mainPart = new MimeMultipart();  
            MimeBodyPart messageBodyPart = new MimeBodyPart();//创建一个包含附件内容的MimeBodyPart  
            //设置HTML内容  
            messageBodyPart.setContent(mailInfo.getContent(),"text/html; charset=utf-8");  
            mainPart.addBodyPart(messageBodyPart);  
            // 设置邮件消息的主要内容
            mailMessage.setContent(mainPart);  
			// 发送邮件
			Transport.send(mailMessage);
			return true;
		} catch (MessagingException ex) {
			ex.printStackTrace();
		}
		return false;
	}

	/**
	 * 以HTML格式发送邮件
	 * 
	 * @param mailInfo
	 *            待发送的邮件信息
	 */
	public static boolean sendHtmlMail(MailSenderInfo mailInfo) {
		// 如果需要身份认证，则创建一个密码验证器
		if (mailInfo.isValidate()) {
			mailAuthor = MailAuthor.getInstance();
		}
		try {
			pro = mailInfo.getProperties();
			sendMailSession = Session.getDefaultInstance(pro, mailAuthor);
			mailMessage = new MimeMessage(sendMailSession);
			// 设置邮件消息的发送者
			from = new InternetAddress(mailInfo.getFromAddress());
			mailMessage.setFrom(from);
			// 设置收件人到邮件消息中
			String[] toAddress = mailInfo.getToAddress();
			to = new InternetAddress[toAddress.length];
			for (int i = 0; i < toAddress.length; i++) {
				to[i] = new InternetAddress(toAddress[i]);
			}
			mailMessage.setRecipients(Message.RecipientType.TO, to);
			// 设置抄送人到邮件消息中
			if (mailInfo.getToAddressCC() != null) {
				String[] toAddressCC = mailInfo.getToAddressCC();
				toCC = new InternetAddress[toAddressCC.length];
				for (int i = 0; i < toAddressCC.length; i++) {
					toCC[i] = new InternetAddress(toAddressCC[i]);
				}
				mailMessage.setRecipients(Message.RecipientType.CC, toCC);
			}
			// 设置邮件消息的主题
			mailMessage.setSubject(mailInfo.getSubject());
			// 设置邮件消息发送的时间
			mailMessage.setSentDate(new Date());
			// MiniMultipart类是一个容器类，包含MimeBodyPart类型的对象
			Multipart mainPart = new MimeMultipart();
			// 创建一个包含HTML内容的MimeBodyPart
			BodyPart html = new MimeBodyPart();
			// 设置HTML内容
			html.setContent(mailInfo.getContent(), "text/html; charset=utf-8");
			mainPart.addBodyPart(html);
			// 将MiniMultipart对象设置为邮件内容
			mailMessage.setContent(mainPart);
			// 发送邮件
			Transport.send(mailMessage);
			return true;
		} catch (MessagingException ex) {
			ex.printStackTrace();
		}
		return false;
	}
}
