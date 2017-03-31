package com.homedirect.ins;

/**
 * @author dodongloc
 *Dec 15, 2016
 */
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class MailService {

  private MailSender mailSender = new JavaMailSenderImpl();

  private Session session;

  private final String username = "noreply.baohiem@vndirect.com.vn";
  private final String password = "Homedirect@1234";

  private Properties props = new Properties();

  public MailService() {
    super();
    boolean auth = true;
    String host = "mail.vndirect.com.vn";
    String port = "25";
    props.put("mail.smtp.auth", "true");
//    props.put("mail.smtp.starttls.enable", "false");
    props.put("mail.smtp.host", host);
    props.put("mail.smtp.port", port);

    System.out.println("SMTP config: ");
    System.out.println("-- smtp.auth: " + auth);
    System.out.println("-- smtp.host: " + host);
    System.out.println("-- smtp.port: " + port);

    session = Session.getInstance(props,
            new javax.mail.Authenticator() {
              protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
              }
            });
  }

  public void sendMailDefaultFrom(String to,String[] cc, String subject, String msg) {
    System.out.println("Send mail: ");
    System.out.println("-- user    : " + username);
    System.out.println("-- pass    : " + password);
    System.out.println("-- to      : " + to);
    System.out.println("-- subject : " + subject);
    System.out.println("-- msg     : " + msg);

    sendMail(username, to, cc, subject, msg);
  }

  private void sendMail(String from, String to,String[] cc, String subject, String msg) {
    try {
      InternetAddress[] ccAddresses = new InternetAddress[cc.length];
      for (int j = 0; j < cc.length; j++) {
        ccAddresses[j] = new InternetAddress(cc[j]);
      }
      Message message = new MimeMessage(session);
      message.setFrom(new InternetAddress(from));
      message.setRecipients(Message.RecipientType.TO,
              InternetAddress.parse(to));
      message.setRecipients(Message.RecipientType.CC, ccAddresses);
      message.setSubject(subject);
      message.setContent(msg, "text/html; charset=UTF-8");
      Transport.send(message);

    } catch (MessagingException e) {
      throw new RuntimeException(e);
    }
  }

  public MailSender getMailSender() {
    return mailSender;
  }

  public void setMailSender(MailSender mailSender) {
    this.mailSender = mailSender;
  }

  public Session getSession() {
    return session;
  }

  public void setSession(Session session) {
    this.session = session;
  }
}