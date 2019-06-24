package com.accountingapi.security.service;


import com.accountingapi.security.model.PasswordResetToken;
import com.accountingapi.security.payload.ApiResponse;
import com.accountingapi.security.repository.PasswordResetTokenRepository;
import com.accountingapi.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Calendar;

@Service
public class TokenVerificationService {

    @Autowired
    PasswordResetTokenRepository passwordResetTokenRepository;

    @Autowired
    UserRepository userRepository;

    public ResponseEntity<?> verifyEmail(String token) {

        PasswordResetToken passwordResetToken = passwordResetTokenRepository.findByToken(token);
        if (passwordResetToken == null) {
            return new ResponseEntity(new ApiResponse(false, "Invalid token. "),
                    HttpStatus.BAD_REQUEST);
        }


        if (passwordResetToken.getExpiryDate().before(Calendar.getInstance().getTime())) {
            return new ResponseEntity(new ApiResponse(false, "Expired token "),
                    HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(new ApiResponse(true, "Valid token "),
                HttpStatus.ACCEPTED);

    }


}