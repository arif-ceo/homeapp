package com.home.service;

import com.home.entity.User;
import com.home.payload.LoginDto;
import com.home.payload.UserDto;

public interface UserService {
    UserDto createUser(User user);

    String verifyLogin(LoginDto loginDto);
}
