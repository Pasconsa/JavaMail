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
		
		ObjetoEnviaEmail enviaEmail = new ObjetoEnviaEmail ("saulopascon@hotmail.com",
															"Saulo A Pascon",
															"Testando e-mail com java",
															"Chegou um email feito no java");
		
		enviaEmail.EnviarEmail();
		
		Thread.sleep(5000); //tempo para enviar
	}
	
	
}