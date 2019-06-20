package com.accountingapi.service;

import com.accountingapi.security.payload.Mail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendEmailWithAttachment(Mail mail) throws MessagingException, IOException {

        MimeMessage msg = javaMailSender.createMimeMessage();


        // true = multipart message
        MimeMessageHelper helper = new MimeMessageHelper(msg, true);

        helper.setTo(mail.getTo());

        helper.setSubject(mail.getSubject());

        // default = text/plain
        //helper.setText("Check attachment for image!");

        // true = text/html
        helper.setText(mail.getModel().get("resetUrl").toString(), true);

        // hard coded a file path
        //FileSystemResource file = new FileSystemResource(new File("path/android.png"));

        helper.addAttachment("1.png", new ClassPathResource("1.png"));

        javaMailSender.send(msg);

    }
}
