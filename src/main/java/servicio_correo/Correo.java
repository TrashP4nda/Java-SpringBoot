package servicio_correo;


import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Correo {
	
	public static void main(String[] args) {
		

        final String username = "traficojoseba@gmail.com";
        final String password = "";

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        
        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("tuCorreo@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse("ikcdd@plaiaundi.net"));
            message.setSubject("Verificación de Correo Electrónico");

            message.setContent("<h1>Verifica tu correo electrónico</h1>"
                    + "<p>Gracias por registrarte. Por favor, haz clic en el enlace de abajo para verificar tu correo electrónico:</p>"
                    + "<a href='http://localhost:3000/allincidentes'>Verificar Correo Electrónico</a>",
                    "text/html; charset=utf-8");

            Transport.send(message);

            System.out.println("Correo de Verificación Enviado!");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
        
	}
    }