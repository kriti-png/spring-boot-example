package com.example.springboot;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class LoginController {

    ArrayList<User> acceptedUsers = new ArrayList<>();
    public LoginController() {
        acceptedUsers.add(new User("kriti", "kritispassword"));
        acceptedUsers.add(new User("teddy", "teddyspassword"));
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody User user) {
        for(int i = 0; i < acceptedUsers.size(); i++) {
            if(user.equals(acceptedUsers.get(i))) {
                return ResponseEntity.status(HttpStatus.OK).body("User Authenticated");
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("invalid username and/or password");
    }
}
