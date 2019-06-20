package com.accountingapi.service;

import com.accountingapi.security.model.User;
import com.accountingapi.security.payload.Mail;
import org.springframework.beans.factory.annotation.Autowired;
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
        User user = (User) mail.getModel().get("user");
        helper.setSubject("Hi Mr/Mrs "+user.getName());

        // default = text/plain
        //helper.setText("Check attachment for image!");

        // true = text/html
        String message ="\nPlease click on this link to enter your new password . Your current token is\n";
        helper.setText(message+"<br/><a href=\""+mail.getModel().get("resetUrl").toString()+"\">Click here</a>",true);

        // hard coded a file path
        //FileSystemResource file = new FileSystemResource(new File("path/android.png"));

        //Add FILE !!
        //helper.addAttachment("1.png", new ClassPathResource("1.png"));

        javaMailSender.send(msg);

    }
}
