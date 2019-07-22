package com.accountingapi.security.service;

import com.accountingapi.security.model.Role;
import com.accountingapi.security.model.User;
import com.accountingapi.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

public interface UserService {


    void updatePassword(String password, Long userId);

    User findUserById(Long userId);

    List<User> findAllByRoles(Set<Role> roles);

    boolean existsById(Long id);

    User findUserByUsername(String username);
}