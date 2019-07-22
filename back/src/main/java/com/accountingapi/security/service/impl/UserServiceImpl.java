package com.accountingapi.security.service.impl;

import com.accountingapi.security.model.Role;
import com.accountingapi.security.model.User;
import com.accountingapi.security.repository.UserRepository;
import com.accountingapi.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    public void updatePassword(String password, Long userId) {
        userRepository.updatePassword(password, userId);
    }

    @Override
    public User findUserById(Long userId) {
        return userRepository.findById(userId).get();
    }

    @Override
    public List<User> findAllByRoles(Set<Role> roles) {
        return userRepository.getAllByRoles(roles);
    }

    @Override
    public boolean existsById(Long id) {
        return userRepository.existsById(id);
    }

    @Override
    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username).get();
    }

}
