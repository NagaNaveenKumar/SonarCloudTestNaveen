package com.ed.myFirstEdProject.controller;

import com.ed.myFirstEdProject.entity.User;
import com.ed.myFirstEdProject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    private UserService userService;



    @PostMapping("/create-user")
    public void saveUser(@RequestBody User user){
        userService.saveDocument(user);
    }

}
