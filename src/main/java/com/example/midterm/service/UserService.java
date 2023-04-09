package com.example.midterm.service;

import com.example.midterm.model.User;

public interface UserService {
    User findUserById(Long id);
    User saveUser(User user);
}
