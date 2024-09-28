package com.example.dropdown.service;

import com.example.dropdown.user.User;
import com.example.dropdown.user.UserRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;




    @Transactional
    public User saveUser(User user) {
        try{

            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
            user.setUsername(user.getUsername());
            System.out.println("Attempting to save user: " + user);
            user.setPassword(encoder.encode(user.getPassword()));
            return userRepo.save(user);
        }
        catch(Exception e){
            System.out.println("error saving user"+e.getMessage());
            throw e;

        }
    }

    public Optional<User> findUserByUsername(String username) {
        return Optional.ofNullable(userRepo.findByUsername(username));
    }

    public List<User> findAllUsers() {
        return userRepo.findAll();
    }


}

