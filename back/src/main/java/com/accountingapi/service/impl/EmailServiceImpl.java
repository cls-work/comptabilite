package com.accountingapi.service.impl;

import com.accountingapi.security.JWT.JwtTokenProvider;
import com.accountingapi.security.JWT.UserPrincipal;
import com.accountingapi.security.model.PasswordResetToken;
import com.accountingapi.security.model.User;
import com.accountingapi.security.payload.JwtAuthenticationResponse;
import com.accountingapi.security.payload.Mail;
import com.accountingapi.security.service.UserService;
import com.accountingapi.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender javaMailSender;


    @Autowired
    private EmailService emailService;

    @Autowired
    private TokenServiceImpl tokenService;

    @Autowired
    JwtTokenProvider tokenProvider;
    @Autowired
    JwtTokenProvider jwtTokenProvider;
    @Autowired
    UserService userService;
    @Autowired
    private AuthenticationManager authenticationManager;


    @Override
    public void sendEmailWithAttachment(Mail mail, String mailContent) throws MessagingException, IOException {

        MimeMessage msg = javaMailSender.createMimeMessage();


        // true = multipart message
        MimeMessageHelper helper = new MimeMessageHelper(msg, true);

        helper.setTo(mail.getTo());
        User user = (User) mail.getModel().get("user");
        helper.setSubject("Hi Mr/Mrs " + user.getName());

        // default = text/plain
        //helper.setText("Check attachment for image!");


        helper.setText(mailContent, true);

        // hard coded a file path
        //FileSystemResource file = new FileSystemResource(new File("path/android.png"));

        //Add FILE !!
        helper.addAttachment("1.png", new ClassPathResource("1.png"));

        javaMailSender.send(msg);

    }

    @Override
    public void createResetMail(User user) throws IOException, MessagingException {


        //Token Creation-Save
        PasswordResetToken token = tokenService.createRestToken(user);

        //Mail Creation-Save
        Mail mail = new Mail();
        mail.setTo(user.getEmail());


        Map<String, Object> model = new HashMap<>();
        model.put("token", token);
        model.put("user", user);

        configureMail(model, "reset");


        mail.setModel(model);

        // true = text/html
        String message = "\nPlease click on this link to enter your new password . Your current token is\n";
        String mailContent = message + "<br/><a href=\"" + mail.getModel().get("url").toString() + "\">Click here</a>";
        emailService.sendEmailWithAttachment(mail, mailContent);
    }

    @Override
    public void createQuotationMail(User quotationCreator, User admin) throws IOException, MessagingException {

        //Generate admin token
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        "sinda",
                        "testsinda")
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = tokenProvider.generateToken(authentication);


        Mail mail = new Mail();
        mail.setTo(admin.getEmail());
        Map<String, Object> model = new HashMap<>();
        model.put("user", admin);

        configureMail(model, "quotation");

        mail.setModel(model);
        // true = text/html
        String message = "\nUser " + quotationCreator.getName() + " has just added a new quotation. Please check the pdf file and click on the link to confirm or reject it. \n";
        String mailContent = message + "<br/><a href=\"" + mail.getModel().get("url").toString() + "/token=" + jwt + "\">Click here</a>";
        emailService.sendEmailWithAttachment(mail, mailContent);

    }

    @Override
    public void configureMail(Map<String, Object> model, String mailType) {
        String toServer = "localhost";
        String toServerPort = "4200";


        //String appUrl = "http://"+httpServletRequest.getServerName()+":"+httpServletRequest.getServerPort()+httpServletRequest.getContextPath();
        String appUrl = "http://" + toServer + ":" + toServerPort;
        String url = new String();
        if (mailType.equalsIgnoreCase("reset")) {
            url = getResetMailUrl(appUrl, model);
        } else if (mailType.equalsIgnoreCase("quotation")) {
            url = getQuotationMailUrl(appUrl);
        }
        model.put("url", url);
    }

    @Override
    public String getResetMailUrl(String appUrl, Map<String, Object> model) {
        return appUrl + "/reset-password/" + model.get("token");
    }

    @Override
    public String getQuotationMailUrl(String appUrl) {
        return appUrl + "/path";
    }


}