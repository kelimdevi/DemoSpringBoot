package com.example.dropdown.user;

import com.example.dropdown.service.JwtService;
import com.example.dropdown.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000",allowedHeaders = "*")
@RequestMapping("/users")

public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtService jwtService;


    @PreAuthorize("hasAuthority('ROLE_ADMIN)")
    @PostMapping("/create")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        System.out.println("Received user registration: " + user);
        User createdUser = userService.saveUser(user);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

//    @GetMapping("/allUsers")
//    public ResponseEntity<User> getUserByUsername(@RequestParam(required = false) String username) {
//        return userService.findUserByUsername(username)
//                .map(user -> new ResponseEntity<>(user, HttpStatus.OK))
//                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
//    }

    @PreAuthorize("hasAuthority('ROLE_USER')")
    @GetMapping("/allUsers")
    public List<User> getAllUsers()
    {
        return userService.findAllUsers();
    }

    @PostMapping("login")
    public String login(@RequestBody User user){

        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));

        if(authentication.isAuthenticated())
            return jwtService.generateToken(user.getUsername());
        else
            return "Login Failed";

    }
}
