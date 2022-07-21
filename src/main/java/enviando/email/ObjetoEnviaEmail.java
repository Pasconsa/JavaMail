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

public class ObjetoEnviaEmail {      // código retirado AppTest

	private String userName = "saulove2029@gmail.com";
	private String senha = "pxyarjvyfphtdpfu";
	
	private String listaDestinatarios = "";
	private String nomeRemetentes = "";
	private String assuntoEmail = "";
	private String textoEmail = "";
	
	
	//Criação de um construtor para  os argumentos genéricos
	public ObjetoEnviaEmail (String listaDestinatarios, String nomeRemetentes, String assuntoEmail, String textoEmail) {
		this.listaDestinatarios = listaDestinatarios;
		this.nomeRemetentes = nomeRemetentes;
		this.assuntoEmail = assuntoEmail;
	
		this.textoEmail = textoEmail;
		
	}

	public void EnviarEmail() throws Exception { // necessario colocar exception

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");/* Autorização */
		props.put("mail.smtp.socketFactory.fallback", "false");
		props.put("mail.smtp.quitwait", "false");
		props.put("mail.smtp.socketFactory.port", "587");
		props.put("mail.host", "smtp.gmail.com");

		props.setProperty("mail.transport.protocol", "smtp");

		props.setProperty("mail.smtp.port", "587");
		props.setProperty("mail.smtp.ssl.trust", "*"); // propriedade de autentificação de segurança ssl, ateristicos
														// todos os valores
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

		Address[] toUser = InternetAddress.parse(listaDestinatarios);

		Message message = new MimeMessage(session);
		message.setFrom(new InternetAddress(userName, nomeRemetentes)); // Quem esta enviando
		message.setRecipients(Message.RecipientType.TO, toUser); // email destino
		message.setSubject(assuntoEmail); // Assunto do email
		message.setText(textoEmail); // descrição do assunto

		Transport.send(message);

	}
}
