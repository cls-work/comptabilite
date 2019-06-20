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

    @PostMapping
    public String processForgotPasswordForm(@Valid @RequestBody PasswordForgotDto form,
                                            BindingResult result,
                                            HttpServletRequest httpServletRequest) throws IOException, MessagingException {

        if (result.hasErrors()){
            return "forgot-password";
        }


        User user = userRepository.findByEmail(form.getEmail());
        if (user == null){
            return "We could not find an account for that e-mail address.";
        }

        PasswordResetToken token = new PasswordResetToken();
        token.setToken(UUID.randomUUID().toString());
        token.setUser(user);
        token.setExpiryDate(30);
        tokenRepository.save(token);

        Mail mail = new Mail();
        mail.setTo(user.getEmail());
        mail.setSubject("Password reset request");

        Map<String, Object> model = new HashMap<>();
        model.put("token", token);
        model.put("user", user);
        String url = httpServletRequest.getScheme() + "://" + httpServletRequest.getServerName() + ":" + httpServletRequest.getServerPort()+httpServletRequest.getContextPath();
        model.put("resetUrl", url + "/reset-password?token=" + token.getToken());
        mail.setModel(model);
        emailService.sendEmailWithAttachment(mail);

        return "redirect:/forgot-password?success";

    }

}