package com.accountingapi.service;

import com.accountingapi.security.model.PasswordResetToken;
import com.accountingapi.security.model.User;

public interface TokenService {

    PasswordResetToken createRestToken(User user);
}
