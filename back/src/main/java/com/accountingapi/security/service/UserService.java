package com.accountingapi.security.service;

import com.accountingapi.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void updatePassword(String password, Long userId) {
        userRepository.updatePassword(password, userId);
    }
}