package com.learning.springboot.services;

import com.learning.springboot.dto.Response;
import com.learning.springboot.dto.User;
import com.learning.springboot.dto.UserRequest;
import com.learning.springboot.exceptions.ResourceNotFoundException;
import com.learning.springboot.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private List<User> userList = new ArrayList<>();

    public Response createNewUser(UserRequest request) {

        // anyMatch is enough; !userList.isEmpty() is redundant because anyMatch returns false on empty lists
//        boolean emailExists = userList.stream()
//                .anyMatch(u -> u.getEmail().equalsIgnoreCase(request.getEmail()));
//
//        if (emailExists) {
//            throw new IllegalArgumentException("User already exists with this email: " + request.getEmail());
//        }

        User new_user = new User();
        new_user.setName(request.getName());
        new_user.setEmail(request.getEmail());
        userRepository.save(new_user);

        return new Response(true, "User created successfully", new_user);
    }

    public Response fetchUserById(Integer id) {


//        if (id < 0 || id >= userList.size()) {
//            throw new ResourceNotFoundException("User not found with ID: " + id);
//        }
        User user = userRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("User not found with ID: " + id));
        return new Response(true, "User fetched successfully", user);
    }

    public Response fetchUserByName(String name) {
        // Simplified: Find the user first. If present, return; if not, throw.
        User user = userList.stream()
                .filter(u -> u.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("User not found with name: " + name));

        return new Response(true, "User found", user);
    }

    public Response fetchUserByEmail(String email) {
        // FIXED: Changed u.getName() to u.getEmail()
        User user = userList.stream()
                .filter(u -> u.getEmail().equalsIgnoreCase(email))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("User not found with email: " + email));

        return new Response(true, "User found", user);
    }
}