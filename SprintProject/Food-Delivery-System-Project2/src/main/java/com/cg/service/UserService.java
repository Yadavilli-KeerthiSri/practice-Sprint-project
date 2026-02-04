package com.cg.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cg.entity.User;
import com.cg.exception.ResourceNotFound;
import com.cg.iservice.IUserService;
import com.cg.repository.UserRepository;

@Service
public class UserService implements IUserService, UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder encoder;

    /**
     * Required by Spring Security to perform login.
     * It fetches the user from the database and converts it into a Security UserDetails object.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.cg.entity.User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .authorities(user.getRole()) // e.g., "ROLE_USER" or "ROLE_ADMIN"
                .build();
    }

    @Override
    public User registerUser(User user) {
        // Encodes the plain-text password into BCrypt before saving
        user.setPassword(encoder.encode(user.getPassword()));
        
        // Sets default role if none provided
        if (user.getRole() == null || user.getRole().isEmpty()) {
            user.setRole("ROLE_USER");
        }
        return userRepository.save(user);
    }

    @Override
    public User findByUsername(String username) throws ResourceNotFound {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFound("User not found: " + username));
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
