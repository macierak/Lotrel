package com.lotrel.ltserwer.reportsModule.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.*;
import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

@Service
public class SendMailService {

    @Value("${lotrel-mail.address}")
    private String mail;

    @Value("${lotrel-mail.host}")
    private String host;

    @Value("${lotrel-mail.port}")
    private String port;

    @Value("${lotrel-mail.password}")
    private String password;

    public void sendMail(String mailTo, String subject, String content, File attachment) throws MessagingException, IOException {
        final Message message = new MimeMessage(getSession());
        message.setFrom(new InternetAddress(mail));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mailTo));
        message.setSubject(subject);
        message.setContent(getContent(content, attachment));

        Transport.send(message);
    }

    private static Multipart getContent(String content, File attachment) throws MessagingException, IOException {
        final MimeBodyPart mimeBodyPart = new MimeBodyPart();
        mimeBodyPart.setContent(content, "text/html; charset=utf-8");

        final Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(mimeBodyPart);

        if (Objects.nonNull(attachment)) {
            multipart.addBodyPart(getAttachment(attachment));
        }

        return multipart;
    }

    private static MimeBodyPart getAttachment(File attachment) throws MessagingException, IOException {
        final MimeBodyPart mimeBodyPart = new MimeBodyPart();
        mimeBodyPart.attachFile(attachment);
        return mimeBodyPart;
    }

    private Session getSession() {
        return Session.getInstance(getProperties(), new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(mail, password);
            }
        });
    }

    private Properties getProperties() {
        final Properties properties = new Properties();
        properties.put("mail.smtp.auth", true);
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", port);
        properties.put("mail.smtp.ssl.trust", host);
        return properties;
    }

}
