package wschun.shop.utils;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

public class MailUtils {
	public static void sendMail(String address, String subject, String msg) {
		try {
			// 1、读取邮件配置
			final Properties props = new Properties();
			// 使用类加载器读取配置文件
			ClassLoader loader = MailUtils.class.getClassLoader();
			props.load(loader.getResourceAsStream("mail.properties"));

			// 2、创建验证器
			Authenticator auth = new Authenticator() {
				public PasswordAuthentication getPasswordAuthentication() {
					String username = props.getProperty("username");
					String password = props.getProperty("password");
					return new PasswordAuthentication(username, password);
				}
			};

			// 3、创建邮件服务器会话对象Session
			Session session = Session.getInstance(props, auth);

			// 4、邮件信息对象
			Message message = new MimeMessage(session);
			// 发送者
			String from = props.getProperty("from");
			message.setFrom(new InternetAddress(from));

			// 发送方式与接收者
			message.setRecipient(RecipientType.TO, new InternetAddress(address));
			// 主题
			message.setSubject(subject);
			// 内容及发送格式
			message.setContent(msg, "text/html;charset=UTF-8");

			// 5、发送邮件
			Transport.send(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}