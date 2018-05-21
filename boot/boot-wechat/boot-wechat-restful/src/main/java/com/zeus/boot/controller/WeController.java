package com.zeus.boot.controller;

import com.zeus.boot.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class WeController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users")
    public List findUserList(){
        return userRepository.findAll();
    }

}
