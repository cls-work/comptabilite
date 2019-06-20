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

import javax.validation.Valid;
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

    @ModelAttribute("forgotPasswordForm")
    public PasswordForgotDto forgotPasswordDto() {
        return new PasswordForgotDto();
    }

    /*@GetMapping
    public String displayForgotPasswordPage() {
        return "forgot-password";
    }*/

    @PostMapping
    public String processForgotPasswordForm(@Valid @RequestBody PasswordForgotDto form,
                                            BindingResult result) {

        if (result.hasErrors()){
            return "forgot-password";
        }


        User user = userRepository.findByEmail(form.getEmail());
        if (user == null){
            result.rejectValue("email", null, "We could not find an account for that e-mail address.");
            return "forgot-password";
        }

        PasswordResetToken token = new PasswordResetToken();
        token.setToken(UUID.randomUUID().toString());
        token.setUser(user);
        token.setExpiryDate(30);
        tokenRepository.save(token);

        Mail mail = new Mail();
        mail.setTo(user.getEmail());
        mail.setSubject("Password reset request");

       /* Map<String, Object> model = new HashMap<>();
        model.put("token", token);
        model.put("user", user);
        model.put("signature", "https://memorynotfound.com");
        String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
        model.put("resetUrl", url + "/reset-password?token=" + token.getToken());
        mail.setModel(model);
        emailService.sendEmail(mail);*/

        return "redirect:/forgot-password?success";

    }

}