package com.ed.myFirstEdProject.service;

import com.ed.myFirstEdProject.entity.User;
import com.ed.myFirstEdProject.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.Arguments;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.ArrayList;

import static org.mockito.Mockito.when;


public class UserDetailsServiceImplTest {

    @InjectMocks
    private UserDetailsServiceImpl userDetailsService;


    @Mock
    private UserRepository userRepository;

    @BeforeEach
    @Disabled
    void initializeMocks(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @Disabled
    public void loadByUserNameTest(){
        when(userRepository.findByUserName(ArgumentMatchers.anyString())).thenReturn(User.builder().userName("praneeth").password("eqff").roles(new ArrayList<>()).build());
        UserDetails user=userDetailsService.loadUserByUsername("naveen");
    }

}
