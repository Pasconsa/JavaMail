package enviando.email;

import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Unit test for simple App.
 */
public class AppTestPrimeiraExplicação {
	
	private String userName = "saulove2029@gmail.com";
	private String senha = "pxyarjvyfphtdpfu";
	
	@org.junit.Test

	public void testeEmail() {
		try {
	
	/* Erro ssl feito tls
	 Properties properties = new Properties();

	properties.put("mail.smtp.auth", "true");  //autorização
	properties.put("mail.smtp.starttls", "true"); //autentificação parte de segurança
	properties.put("mail.smtp.host", "smtp.gmail.com"); // Servidor Gmail
	properties.put("mail.smtp.port", "465");  //Porta servidor
	properties.put("mail.smtp.socketFactory.port", "465"); // especifica a porta a ser conectada pelo socket
	properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SLLSocketFactory"); // Classe socket de conexão ao smtp */
			
			Properties props = new Properties();
			props.put("mail.smtp.auth", "true");/* Autorização */
			props.put("mail.smtp.socketFactory.fallback", "false");
			props.put("mail.smtp.quitwait", "false");
			props.put("mail.smtp.socketFactory.port", "587");
			props.put("mail.host", "smtp.gmail.com");

			props.setProperty("mail.transport.protocol", "smtp");

			props.setProperty("mail.smtp.port", "587");
			props.setProperty("mail.smtp.ssl.trust", "*"); //propriedade de autentificação de segurança ssl, ateristicos todos os valores
			props.setProperty("mail.smtp.starttls.enable", String.valueOf("True"));// True or False
			props.setProperty("mail.smtp.ssl.protocols", "TLSv1.2");
			props.setProperty("mail.smtp.timeout", "300000");
			props.setProperty("mail.smtp.connectiontimeout", "300000");
			props.setProperty("mail.smtp.writetimeout", "300000");
	
	Session session = Session.getInstance(props, new Authenticator() {
		@Override
		protected PasswordAuthentication getPasswordAuthentication() {
			return new PasswordAuthentication(userName, senha);
		}

	});
	
	Address[] toUser = InternetAddress.parse("saulopascon@hotmail.com , saulove2029@gmail.com");
	
	Message message = new MimeMessage(session);
	message.setFrom(new InternetAddress(userName, "Saulo Pascon"));  //Quem esta enviando
	message.setRecipients(Message.RecipientType.TO, toUser); //email destino
	message.setSubject("Chegou e-mail enviado por Java"); //Assunto do email
	message.setText("Ola programador, você acabou de receber um e-mail por Java"); //descrição do assunto
	
	
	Transport.send(message);
	
	
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}
}
