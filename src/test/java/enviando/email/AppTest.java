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
public class AppTest {
	
	@org.junit.Test
	public void testeEmail()throws Exception {
		
		StringBuilder stringBuilderTextoEmail = new StringBuilder();  
		
		
		stringBuilderTextoEmail.append("Olá, <br/><br/>");
		stringBuilderTextoEmail.append("Você está recebendo o acesso a loja Dinogames.<br/><br/>");
		stringBuilderTextoEmail.append("Para ter acesso clique no botão abaixo.<br/><br/>");
		
		stringBuilderTextoEmail.append("<b>Login:</b> sap@hotmail.com <br/>");
		stringBuilderTextoEmail.append("<b>Senha:</b> 07171717<br/><br/>");
		
		stringBuilderTextoEmail.append("<a target=\"_blank\" href=\"http://projetojavaweb.com/certificado-aluno/login\" style=\"color:#2525a7; padding: 14px 25px; text-align:center; text-decoration: none; display:inline-block; border-radius:30px; font-size:20px; font-family:courier; border : 3px solid green;background-color:#99DA39;\">Acessar Portal do Aluno</a><br/><br/>");
		
		stringBuilderTextoEmail.append("<span style=\"font-size:8px\">Ass.: Saulo Pascon</span>");
		
		ObjetoEnviaEmail enviaEmail = new ObjetoEnviaEmail ("saulopascon@hotmail.com",
															"Saulo A Pascon",
															"Testando e-mail com java",
															stringBuilderTextoEmail.toString());
		
		enviaEmail.EnviarEmail(true);
		
		Thread.sleep(5000); //tempo para enviar
	}
	
	
}