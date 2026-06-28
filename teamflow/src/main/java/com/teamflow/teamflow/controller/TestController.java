package com.teamflow.teamflow.controller;

import com.teamflow.teamflow.model.User;
import com.teamflow.teamflow.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Map;

@RestController
public class TestController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")
    public Map<String, String> home() {
        return Map.of(
                "app", "TeamFlow",
                "status", "running"
        );
    }

//    @GetMapping("/test-db")
//    public List<User> testDb() {
//        User user = User.builder()
//                .name("Test User")
//                .email("test@teamflow.com")
//                .password("password123")
//                .build();

//        userRepository.save(user);
//        return userRepository.findAll();
//    }
}
