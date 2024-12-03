package com.ed.myFirstEdProject.controller;

import com.ed.myFirstEdProject.entity.JournalEntry;
import com.ed.myFirstEdProject.entity.User;
import com.ed.myFirstEdProject.service.JournalEntryService;
import com.ed.myFirstEdProject.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<?> getAllUsers(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName= authentication.getName();
        User user = userService.findByUsername(userName);
        if(user!=null){
            return new ResponseEntity<>(user,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }



    @PutMapping("/update")
    public ResponseEntity<?> updateUser(@RequestBody User user){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User userInDB=userService.findByUsername(userName);
        if(userInDB!=null){
            userInDB.setUserName(user.getUserName()!=null&&!user.getUserName().equals("")?user.getUserName():userInDB.getUserName());
            userInDB.setPassword(user.getPassword()!=null&&!user.getPassword().equals("")?user.getPassword():userInDB.getPassword());
            userService.saveDocument(userInDB);
            return ResponseEntity.status(HttpStatus.OK).body("user updated");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("user not found");
    }

    @DeleteMapping
    public ResponseEntity<?> deleteUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName=authentication.getName();
        userService.deleteByUserName(userName);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
