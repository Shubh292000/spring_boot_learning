package com.learning.springboot.controllers;

import com.learning.springboot.dto.Response;
import com.learning.springboot.dto.UserRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.learning.springboot.services.UserService;


@RestController
@RequestMapping("/api") // Good practice to version or prefix your routes
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/addUser")
    public ResponseEntity<Response> createNewUser(@Valid @RequestBody UserRequest userRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createNewUser(userRequest));
    }

    @GetMapping("/getUser/{id}")
    public ResponseEntity<Response> fetchUserById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(userService.fetchUserById(id));
    }

    @GetMapping("/getUser")
    public ResponseEntity<Response> getUserByField(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "email", required = false) String email) {

        // Check name first
        if (name != null && !name.isBlank()) {
            return ResponseEntity.ok(userService.fetchUserByName(name));
        }

        // Then check email
        if (email != null && !email.isBlank()) {
            return ResponseEntity.ok(userService.fetchUserByEmail(email));
        }

        // If neither is provided, throw an error
        throw new IllegalArgumentException("Please provide either a name or an email for the search.");
    }
}