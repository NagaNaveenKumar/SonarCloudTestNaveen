package com.ed.myFirstEdProject.service;

import com.ed.myFirstEdProject.UserArgumentsProvider;
import com.ed.myFirstEdProject.entity.User;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.util.AssertionErrors;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    @Disabled
    public void test(){
        assertEquals(4,2+2);
        assertNotNull(userService.findByUsername("satish"));
    }

    @Test
    @Disabled
    public void test2(){
        User user=userService.findByUsername("satish");
        assertFalse(!user.getJournal_entries().isEmpty());
    }

    @ParameterizedTest
    @CsvSource({
            "3,1,4",
            "1,2,3"
    })
    @Disabled
    public void test3(int a,int b,int result){
        assertEquals(result,a+b);
    }

    @ParameterizedTest
    @ValueSource(strings={"naveen","satish"})
    @Disabled
    public void test4(String name){
        assertNotNull(userService.findByUsername(name),"user not found with name : "+name);
    }

    @ParameterizedTest
    @ArgumentsSource(UserArgumentsProvider.class)
    @Disabled
    public void test5(User user){
        assertTrue(userService.saveDocument(user));
    }

}
