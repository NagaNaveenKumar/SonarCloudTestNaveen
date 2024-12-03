package com.ed.myFirstEdProject.controller;

import com.ed.myFirstEdProject.entity.User;
import com.ed.myFirstEdProject.repository.UserRepository;
import com.ed.myFirstEdProject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @GetMapping("/all-users")
    public ResponseEntity<?> getAllUsers(){
        List<User> all = userService.getAll();
        if(all!=null && !all.isEmpty()){
            return new ResponseEntity<>(all, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/add-admin-user")
    public ResponseEntity<?> addAdminUser(@RequestBody User user){
        userService.saveAdmin(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
