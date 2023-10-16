// package com.labSoftware.services;

// import org.springframework.boot.rsocket.server.RSocketServer.Transport;
// import org.springframework.stereotype.Service;

// import com.labSoftware.config.Initialize;

// import jakarta.mail.Multipart;
// import jakarta.mail.internet.InternetAddress;
// import jakarta.mail.internet.MimeBodyPart;
// import jakarta.mail.internet.MimeMessage;
// import jakarta.mail.internet.MimeMultipart;

// @Service
// public class MailService {

// public void sendMessage(String destinatario, String mensagem) {

// try {

// Message message = new MimeMessage(Initialize.retornaSession());
// message.setFrom(new InternetAddress("email"));
// message.setRecipients(
// Message.RecipientType.TO, InternetAddress.parse(destinatario));
// message.setSubject("Cupom");

// MimeBodyPart mimeBodyPart = new MimeBodyPart();
// mimeBodyPart.setContent(mensagem, "text/html; charset=utf-8");

// Multipart multipart = new MimeMultipart();
// multipart.addBodyPart(mimeBodyPart);

// message.setContent(multipart);

// Transport.send(message);

// } catch (Exception e) {
// System.out.println("ERRO AO ENVIAR EMAIL: " + e.getMessage());
// }
// }
// }
