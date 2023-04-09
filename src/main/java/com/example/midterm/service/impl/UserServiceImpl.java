package com.example.midterm.service.impl;

import com.example.midterm.model.Brand;
import com.example.midterm.model.User;
import com.example.midterm.repository.UserRepository;
import com.example.midterm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;


    @Override
    public User findUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()){
            return user.get();
        }
        return null;
    }

    @Override
    public User saveUser(User user) {
        user.setId(0L);
        return userRepository.save(user);
    }
}
