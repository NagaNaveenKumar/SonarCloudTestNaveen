package com.ed.myFirstEdProject.service;

import com.ed.myFirstEdProject.entity.User;
import com.ed.myFirstEdProject.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@Component
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private static final PasswordEncoder passwordEncoder=new BCryptPasswordEncoder();




    public boolean saveDocument(User user){
        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRoles(Arrays.asList("USER"));
            userRepository.save(user);
            return true;
        }
        catch(Exception e){
            log.error("error log check");
            log.info("info log check");
            log.warn("warn log check");
            log.debug("debug log check");
            log.trace("trace log check");
            return false;
        }
    }

    public void saveAdmin(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("USER","ADMIN"));
        userRepository.save(user);
    }

    public List<User> getAll(){
        return userRepository.findAll();
    }

    public Optional<User> findById(ObjectId id){
        return userRepository.findById(id);
    }

    public boolean deleteEntry(ObjectId id){
        userRepository.deleteById(id);
        return true;
    }

    public User findByUsername(String userName){
        return userRepository.findByUserName(userName);
    }

    public boolean deleteByUserName(String userName){
         userRepository.deleteByUserName(userName);
         return true;
    }


}