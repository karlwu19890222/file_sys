package cn.karlwu.common.mail;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class MailAuthor extends Authenticator {
	public static final String HOST = "mail.sunvou.com";
	public static final String PORT = "25";
	public static final String FORM_ADDRESS = "system@sunvou.com";
    public static final boolean validate = true;
	public static final String USERNAME = "system@sunvou.com";
	public static final String USERPASS = "system080520";
	public static MailAuthor mailAuthor = null;

	public static MailAuthor getInstance() {
		if (mailAuthor == null) {
			mailAuthor = new MailAuthor();
		}
		return mailAuthor;
	}

	@Override
	protected PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(USERNAME, USERPASS);
	}

}
