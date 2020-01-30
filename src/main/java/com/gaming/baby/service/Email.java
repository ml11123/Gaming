package com.gaming.baby.service;

import org.springframework.core.env.Environment;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class Email {


    private Environment env;

    public Email(Environment env) {
        this.env = env;
    }

    public String send(String to, String subject, String body) {
        //TODO write your implementation code here:

        String SMTPHost = env.getProperty("spring.mail.host");
        String SMTPUser = env.getProperty("spring.mail.username");
        String SMTPPassword = env.getProperty("spring.mail.password");
        String SMTPPort = env.getProperty("spring.mail.port");
        String from = env.getProperty("spring.mail.from");

        Properties props = System.getProperties();
        props.put("mail.smtp.starttls.enable", "true");
        assert SMTPHost != null;
        props.put("mail.smtp.host", SMTPHost);
        assert SMTPUser != null;
        props.put("mail.smtp.user", SMTPUser);
        assert SMTPPassword != null;
        props.put("mail.smtp.password", SMTPPassword);
        assert SMTPPort != null;
        props.put("mail.smtp.port", SMTPPort);
        props.put("mail.smtp.auth", "true");

        if(from.contains("@yandex.com")){
            props.put("mail.smtp.ssl.enable", "true");
            props.put("mail.smtp.quitwait", "true");
            props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            props.put("mail.debug", "true");
        }

        Session session = Session.getDefaultInstance(props);

        if(from.contains("@outlook.com") ){
            session = Session.getInstance(props,
                    new javax.mail.Authenticator() {
                        @Override
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(SMTPUser, SMTPPassword);
                        }
                    });
        }

        try {
            session.setDebug(true);

            MimeMessage message = new MimeMessage(session);

            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            message.setHeader("Content-Type", "text/html; charset=UTF-8");
            message.setSubject(subject, "UTF-8");
            message.setContent(body, "text/html; charset=utf-8");

            Transport transport = session.getTransport("smtp");
            transport.connect(SMTPHost, SMTPUser, SMTPPassword);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        } catch (MessagingException ae) {
            ae.printStackTrace();
        }

        return "success";
    }
}
