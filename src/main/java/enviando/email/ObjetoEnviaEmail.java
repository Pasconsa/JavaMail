package enviando.email;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

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

	public void EnviarEmail(boolean envioHtml)  throws Exception { // necessario colocar exception

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
		
		if (envioHtml) {                        //condição para enviar por html
			message.setContent(textoEmail, "text/html; charset=utf-8");
		}else {
			message.setText(textoEmail);
		}
		

		Transport.send(message);

	}
	
	//---------------- Enviar email com anexo------------------------------------
	
	public void EnviarEmailAnexo(boolean envioHtml)  throws Exception { // necessario colocar exception

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
		
		
		
		// Parte 1 do Email texto e descrição do email
		
		MimeBodyPart corpoEmail = new MimeBodyPart();
		
		if (envioHtml) {                        //condição para enviar por html
			corpoEmail.setContent(textoEmail, "text/html; charset=utf-8");
		}else {
			corpoEmail.setText(textoEmail);
		}
		

		List <FileInputStream> arquivos = new ArrayList<FileInputStream>();
		arquivos.add(simuladorPdf());/*Certificado*/
		arquivos.add(simuladorPdf());/*nota fiscal*/
		arquivos.add(simuladorPdf());/*documento texto*/
		arquivos.add(simuladorPdf()); /*Imagem*/
		
		
		Multipart multipart = new MimeMultipart();
		multipart.addBodyPart(corpoEmail);
		
		int index = 0;
		for (FileInputStream fileInputStream : arquivos) {
		
		// Parte 2 do email que são os anexos do email
		MimeBodyPart anexoEmail = new MimeBodyPart();
		
		/*Onde é passado o simuladorDePDf você passa o seu arquivo gravado no banco e dados*/
		anexoEmail.setDataHandler(new DataHandler(new ByteArrayDataSource(simuladorPdf(),"application/pdf")));
		anexoEmail.setFileName("anexoEmail" + index + ".pdf");
		
		multipart.addBodyPart(anexoEmail);
		
		index++;
	}
	
	
	message.setContent(multipart);
		
		

		Transport.send(message);

	}
	
//Metodo que simula Pdf ou qualquer arquivo que possa ser enviado por email
	//Pode pegar o arquivo de seu banco de dados base 64, byte[], Stream de arquivos
	//Pode  estar em um banco de dados ou pasta
	//Retorna um Pdf em branco com texto neste paragrafo
	
	private FileInputStream simuladorPdf() throws Exception{
		
		Document document = new Document();
		File file = new File("fileanexo.pdf");
		file.createNewFile();
		PdfWriter.getInstance(document, new FileOutputStream(file)); //escrever o documento dentro deste conteudo file
		document.open();
		document.add(new Paragraph("Conteudo do Pdf anexo com Java Mail, esse texto é do pdf"));
		document.close();
		
		return new FileInputStream(file);
	}
	
}
