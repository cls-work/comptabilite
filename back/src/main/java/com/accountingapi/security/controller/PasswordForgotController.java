package com.accountingapi.security.controller;


import com.accountingapi.security.dto.PasswordForgotDto;
import com.accountingapi.security.model.PasswordResetToken;
import com.accountingapi.security.model.User;
import com.accountingapi.security.payload.Mail;
import com.accountingapi.security.repository.PasswordResetTokenRepository;
import com.accountingapi.security.repository.UserRepository;
import com.accountingapi.security.service.UserService;
import com.accountingapi.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/forgot-password")
public class PasswordForgotController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordResetTokenRepository tokenRepository;

    @Autowired
    private EmailService emailService;

    /*@GetMapping
    public String displayForgotPasswordPage() {
        return "forgot-password";
    }*/

    @PostMapping(value = "")
    public String processForgotPasswordForm(@Valid @RequestBody PasswordForgotDto form,
                                            BindingResult result,
                                            HttpServletRequest httpServletRequest) throws IOException, MessagingException {

        if (result.hasErrors()){
            return "forgot-password";
        }


        User user = userRepository.findByEmail(form.getEmail());
        //User not found
        if (user == null){
            return "We could not find an account for that e-mail address.";
        }

        //Token Creation-Save
        PasswordResetToken token = new PasswordResetToken();
        token.setToken(UUID.randomUUID().toString());
        token.setUser(user);
        token.setExpiryDate(30);
        tokenRepository.save(token);


        //Mail Creation-Save
        Mail mail = new Mail();
        mail.setTo(user.getEmail());

        String toServer="localhost";
        String toServerPort="4200";

        Map<String, Object> model = new HashMap<>();
        model.put("token", token);
        model.put("user", user);
        //String appUrl = "http://"+httpServletRequest.getServerName()+":"+httpServletRequest.getServerPort()+httpServletRequest.getContextPath();
        String appUrl = "http://"+toServer+":"+toServerPort;
        String url = appUrl +"/reset-password/"+token.getToken();
        model.put("resetUrl", url );


        mail.setModel(model);
        emailService.sendEmailWithAttachment(mail);

        return "your password has been successfully sent";

    }

}