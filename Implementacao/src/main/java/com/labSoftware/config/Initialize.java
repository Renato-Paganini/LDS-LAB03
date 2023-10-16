// package com.labSoftware.config;

// import org.springframework.boot.context.event.ApplicationStartedEvent;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.context.event.EventListener;
// import jakarta.mail.Authenticator;
// import jakarta.mail.PasswordAuthentication;
// import jakarta.mail.Session;

// import java.util.Properties;

// @Configuration
// public class Initialize {

// private static Properties properties;

// @EventListener(ApplicationStartedEvent.class)
// public void defineProperties(){

// Properties prop = new Properties();
// prop.put("mail.smtp.auth", "true");
// prop.put("mail.smtp.starttls.enable", "true");
// prop.put("mail.smtp.host", "smtp-mail.outlook.com");
// prop.put("mail.smtp.port", "587");

// properties = prop;

// }

// public static Session retornaSession(){

// return Session.getInstance(properties, new Authenticator() {
// @Override
// protected PasswordAuthentication getPasswordAuthentication() {
// return new PasswordAuthentication(
// "email",
// "senha"
// );
// }
// });

// }

// }
