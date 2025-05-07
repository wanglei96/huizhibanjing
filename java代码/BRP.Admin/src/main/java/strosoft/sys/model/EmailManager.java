package strosoft.sys.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.activation.CommandMap;
import javax.activation.MailcapCommandMap;
import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import javax.mail.internet.MimeMultipart;

import strosoft.app.common.SysConfig;

public class EmailManager {
	private static String smtpFromAddress;
	private static String smtpUser;
	private static String smtpPassword;
	private static String smtpHost;
	private static int smtpPort = 25;
	private static Boolean starttlsEnabled = false;

	private static EmailManager _instance;

	public static EmailManager getInstance() throws IOException {
		if (_instance == null) {
			_instance = new EmailManager();
		}
		return _instance;
	}

	private EmailManager() throws IOException {
		loadConfig();
	}

	/**
	 * 加载配置
	 * 
	 * @throws IOException
	 */
	private void loadConfig() throws IOException {
		// 发邮件地址
		smtpFromAddress = SysConfig.getConfig("smtpFromAddress");
		// 发邮件用户
		smtpUser = SysConfig.getConfig("smtpUser");
		// 发邮件密码
		smtpPassword = SysConfig.getConfig("smtpPassword");
		// 发邮件主机
		smtpHost = SysConfig.getConfig("smtpHost");
		// 发邮件地址
		smtpPort = SysConfig.getIntConfig("smtpPort", 25);
		// 发邮件地址
		starttlsEnabled = SysConfig.getBoolConfig("starttlsEnabled", false);
		MailcapCommandMap mc = (MailcapCommandMap) CommandMap.getDefaultCommandMap();
		mc.addMailcap("text/html;; x-java-content-handler=com.sun.mail.handlers.text_html");
		mc.addMailcap("text/xml;; x-java-content-handler=com.sun.mail.handlers.text_xml");
		mc.addMailcap("text/plain;; x-java-content-handler=com.sun.mail.handlers.text_plain");
		mc.addMailcap("multipart/*;; x-java-content-handler=com.sun.mail.handlers.multipart_mixed");
		mc.addMailcap("message/rfc822;; x-java-content-handler=com.sun.mail.handlers.message_rfc822");
		CommandMap.setDefaultCommandMap(mc);
		Thread.currentThread().setContextClassLoader(getClass().getClassLoader());

	}

	/**
	 * 发送邮件
	 * 
	 * @param toAddress
	 * @param subject
	 * @param text
	 * @throws MessagingException
	 */
	public void send(String toAddress, String subject, String content) throws MessagingException {
		// 发送邮件
		InternetAddress[] toAddresses = new InternetAddress[] { new InternetAddress(toAddress) };
		send(toAddresses, subject, content);
	}

	/**
	 * 发送邮件
	 * 
	 * @param toAddresses
	 * @param subject
	 * @param content
	 * @throws MessagingException
	 */
	public void send(String[] toAddresses, String subject, String content) throws MessagingException {
		ArrayList<InternetAddress> lstInternetAddress = new ArrayList<InternetAddress>();
		for (int i = 0; i < toAddresses.length; i++) {
			InternetAddress newInternetAddress = new InternetAddress(toAddresses[i]);
			lstInternetAddress.add(newInternetAddress);
		}
		InternetAddress[] arrInternetAddress = (InternetAddress[]) lstInternetAddress.toArray();
		send(arrInternetAddress, subject, content);
	}

	/**
	 * 发送邮件
	 * 
	 * @param lstToAddress
	 * @param subject
	 * @param content
	 * @throws MessagingException
	 */
	public void send(List<String> lstToAddress, String subject, String content) throws MessagingException {
		ArrayList<InternetAddress> lstInternetAddress = new ArrayList<InternetAddress>();
		for (int i = 0; i < lstToAddress.size(); i++) {
			InternetAddress newInternetAddress = new InternetAddress(lstToAddress.get(i));
			lstInternetAddress.add(newInternetAddress);
		}
		InternetAddress[] arrInternetAddress = lstInternetAddress.toArray(new InternetAddress[lstToAddress.size()]);
		send(arrInternetAddress, subject, content);
	}

	/**
	 * 发送邮件
	 * 
	 * @param toAddresses
	 * @param subject
	 * @param content
	 * @throws MessagingException
	 */
	public void send(InternetAddress[] toAddresses, String subject, String content) throws MessagingException {
		Properties theProperties = new Properties();
		// 开启debug调试
		theProperties.setProperty("mail.debug", "true");
		// 发送服务器需要身份验证
		theProperties.setProperty("mail.smtp.auth", "true");
		// 设置邮件服务器主机名
		theProperties.setProperty("mail.host", smtpHost);
		// 设置邮件服务器主机端口
		theProperties.setProperty("mail.smtp.port", String.valueOf(smtpPort));
		// 发送邮件协议名称
		theProperties.setProperty("mail.transport.protocol", "smtp");
		// 使用SSL
		if (starttlsEnabled) {
			theProperties.put("mail.smtp.starttls.enable", "true");
		}
		// 设置环境信息
		Session session = Session.getInstance(theProperties);
		// 创建邮件对象
		MimeMessage msg = new MimeMessage(session);
		// 设置发件人
		msg.setFrom(new InternetAddress(smtpFromAddress));
		msg.addRecipient(RecipientType.CC, new InternetAddress(smtpFromAddress));
		// 邮件标题
		msg.setSubject(subject, "utf-8");
		// 设置邮件内容
		// msg.setContent(content, "text/html; charset=utf-8");
		// msg.setText(content);

		Multipart mpContent = new MimeMultipart();
		BodyPart bpContent = new MimeBodyPart();
		bpContent.setContent(content, "text/html; charset=utf-8");
		mpContent.addBodyPart(bpContent);
		msg.setContent(mpContent);

		Transport transport = session.getTransport();
		// 连接邮件服务器
		transport.connect(smtpUser, smtpPassword);
		// 发送邮件
		transport.sendMessage(msg, toAddresses);
		// 关闭连接
		transport.close();
	}
//
//	public static void main(String[] args) {
//
//		String sender = "lr.beijing@lr.org";
//		String password = "BJLR234!";
//
//		// 收件人邮箱地址
//		String receiver = "12638986@qq.com";
//		receiver = "nicole.hu@lr.org";
//
//		// office365 邮箱服务器地址及端口号
//		String host = "smtp.office365.com";
//		String prot = "587";
//
//		try {
//
//			Properties props = new Properties();
//			// 开启debug调试
//			props.setProperty("mail.debug", "true"); // false
//			// 发送服务器需要身份验证
//			props.setProperty("mail.smtp.auth", "true");
//			// 设置邮件服务器主机名
//			props.setProperty("mail.host", host);
//			// 发送邮件协议名称
//			props.setProperty("mail.transport.protocol", "smtp");
//			props.setProperty("mail.smtp.port", prot);
//			props.put("mail.smtp.starttls.enable", "true");
//
//			// 设置环境信息
//			Session session = Session.getInstance(props);
//			// 创建邮件对象
//			MimeMessage msg = new MimeMessage(session);
//			// 设置发件人
//			msg.setFrom(new InternetAddress(sender));
//			// 设置收件人
//			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(receiver));
//			// 设置邮件主题
//			msg.setSubject("测试邮件");
//			// 设置邮件内容
//			Multipart multipart = new MimeMultipart();
//			MimeBodyPart textPart = new MimeBodyPart();
//			textPart.setText("测试邮件内容。。。");
//			multipart.addBodyPart(textPart);
//
//			// 添加附件
//			MimeBodyPart attachPart = new MimeBodyPart();
//			DataSource source = new FileDataSource("c:\\log.txt");
//			attachPart.setDataHandler(new DataHandler(source));
//			attachPart.setFileName("log.txt");
//			multipart.addBodyPart(attachPart);
//
//			msg.setContent(multipart);
//
//			Transport transport = session.getTransport();
//			// 连接邮件服务器
//			transport.connect(sender, password);
//			// 发送邮件
//			transport.sendMessage(msg, new Address[] { new InternetAddress(receiver) });
//			// 关闭连接
//			transport.close();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
}
