package com.accountingapi.security.controller;


import com.accountingapi.security.dto.NewPasswordDto;
import com.accountingapi.security.dto.PasswordForgotDto;
import com.accountingapi.security.dto.TokenDto;
import com.accountingapi.security.model.PasswordResetToken;
import com.accountingapi.security.model.User;
import com.accountingapi.security.payload.ApiResponse;
import com.accountingapi.security.payload.Mail;
import com.accountingapi.security.repository.PasswordResetTokenRepository;
import com.accountingapi.security.repository.UserRepository;
import com.accountingapi.security.service.TokenVerificationService;
import com.accountingapi.security.service.UserService;
import com.accountingapi.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    private PasswordResetTokenRepository passwordResetTokenRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private TokenVerificationService tokenVerificationService;

    @Autowired
    PasswordEncoder passwordEncoder;


    @PostMapping(value = "")
    public ResponseEntity<?> processForgotPasswordForm(@Valid @RequestBody PasswordForgotDto form,
                                            BindingResult result,
                                            HttpServletRequest httpServletRequest) throws IOException, MessagingException {

        if (result.hasErrors()){
            return new ResponseEntity(new ApiResponse(false, "Result error."),
                    HttpStatus.BAD_REQUEST);
        }

        User user = userRepository.findByEmail(form.getEmail());
        //User not found
        if (user == null){

            return new ResponseEntity(new ApiResponse(false, "We could not find an account for that e-mail address."),
                    HttpStatus.BAD_REQUEST);
        }

        //Token Creation-Save
        PasswordResetToken token = new PasswordResetToken();
        token.setToken(UUID.randomUUID().toString());
        token.setUser(user);
        token.setExpiryDate(60);
        passwordResetTokenRepository.save(token);


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

        return new ResponseEntity(new ApiResponse(true, "Email successfully sent. "),
                HttpStatus.ACCEPTED);

    }


    @PostMapping(value = "/verify")
    public ResponseEntity<?> tokenVerify(@Valid @RequestBody TokenDto tokenDto){
        String token = tokenDto.getToken();
        return tokenVerificationService.verifyEmail(token);
    }

    @PostMapping(value = "/new-password")
    public ResponseEntity<?> newPassword(@Valid @RequestBody NewPasswordDto newPasswordDto){
        if (tokenVerificationService.verifyEmail(newPasswordDto.getToken()).getStatusCode()==HttpStatus.BAD_REQUEST){
                return tokenVerificationService.verifyEmail(newPasswordDto.getToken());
        }

        PasswordResetToken passwordResetToken = passwordResetTokenRepository.findByToken(newPasswordDto.getToken());
        User user=passwordResetToken.getUser();
        user.setPassword(newPasswordDto.getPassword());

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        User result = userRepository.save(user);
        passwordResetToken.setUsed(true);
        passwordResetTokenRepository.save(passwordResetToken);

        return new ResponseEntity(new ApiResponse(true, "Password changed successfully"),
                HttpStatus.ACCEPTED);    }

}