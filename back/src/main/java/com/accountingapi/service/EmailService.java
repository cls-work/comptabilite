package com.accountingapi.service;

import com.accountingapi.security.model.PasswordResetToken;
import com.accountingapi.security.model.User;
import com.accountingapi.security.payload.Mail;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.Map;

public interface EmailService {

    void sendEmailWithAttachment(Mail mail, String mailContent) throws MessagingException, IOException;

    void createResetMail(User user) throws IOException, MessagingException;

    void createQuotationMail(User quotationCreator, User admin) throws IOException, MessagingException;

    void configureMail(Map<String, Object> model, String mailType);

    String getResetMailUrl(String appUrl, Map<String, Object> model);

    String getQuotationMailUrl(String appUrl);
}
