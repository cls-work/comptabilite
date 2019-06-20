package com.accountingapi.controller;


import com.accountingapi.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {

    @Autowired
    private JavaMailSender sender;

    @Autowired
    EmailService emailService;

    @RequestMapping("/simpleemail")
    @ResponseBody
    public String home() {
        try {
            emailService.sendEmailWithAttachment();
            return "Email Sent!";
        } catch (Exception ex) {
            return "Error in sending email: " + ex;
        }
    }
}