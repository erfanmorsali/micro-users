package com.example.microusers.modules.users.controllers;

import com.example.microusers.modules.users.dtos.UserInput;
import com.example.microusers.modules.users.dtos.UserOutput;
import com.example.microusers.modules.users.exceptions.AuthenticationFailedException;
import com.example.microusers.modules.users.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1/users")
public class UserController {


    private final UserService userService;

    @Value("${api-key}")
    private String apiKey;

    @Autowired
    public UserController(UserService service) {
        this.userService = service;
    }

    public void checkApiKey(HttpServletRequest request) throws Exception {
        String headerApiKey = request.getHeader("X-API-KEY");
        if (headerApiKey == null || !headerApiKey.equals(apiKey)) {
            throw new AuthenticationFailedException("authorization failed");
        }
    }


    @GetMapping("/")
    public ResponseEntity<List<UserOutput>> getAllUsers(HttpServletRequest request) throws Exception {
        checkApiKey(request);
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserOutput> getUserById(@PathVariable(name = "userId") Long userId, HttpServletRequest request) throws Exception {
        checkApiKey(request);
        return new ResponseEntity<>(userService.getUserById(userId), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<UserOutput> createUser(@Valid @RequestBody UserInput userInput, HttpServletRequest request) throws Exception {
        checkApiKey(request);
        return new ResponseEntity<>(userService.createUser(userInput), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable(name = "id") Long user_id, HttpServletRequest request) throws Exception {
        checkApiKey(request);
        userService.deleteUserById(user_id);
        return new ResponseEntity<>("", HttpStatus.NO_CONTENT);
    }
}
