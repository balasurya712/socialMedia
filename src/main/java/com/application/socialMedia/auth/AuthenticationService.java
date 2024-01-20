package com.application.socialMedia.auth;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.application.socialMedia.model.FollowRequest;
import com.application.socialMedia.model.Page;
import com.application.socialMedia.model.Role;
import com.application.socialMedia.model.User;
import com.application.socialMedia.repository.FollowRequestRepository;
import com.application.socialMedia.repository.PageRepository;
import com.application.socialMedia.repository.UserRepository;
import com.application.socialMedia.service.JwtService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    @Autowired
    UserRepository userRepo;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    JwtService jwtService;

    @Autowired
    PageRepository pageRepo;
    @Autowired
    FollowRequestRepository followRepo;
    
    private final AuthenticationManager authenticationManager;

    public String register(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        if (userRepo.existsByName(user.getName())) {
            throw new RuntimeException("Email already exists");
        }
        if (user.getEmail().endsWith("socialmedia.com")) {
            if (user.getEmail().equals("admin@socialmedia.com"))
                user.setRole(Role.ADMIN);
            else
                user.setRole(Role.CONTENT_MODERATOR);
        } else {
            user.setRole(Role.USER);
        }
        userRepo.save(user);
        Page page = new Page();
        page.setName(user.getName());
        page = pageRepo.save(page);
        FollowRequest req = new FollowRequest();
        req.setPageId(page.get_id());
        req.setRequestedId(new ArrayList<>());
        followRepo.save(req);
        String jwt = jwtService.generateToken(user);
        return jwt;
    }


    public String authenticate(LoginRequest request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getName(), request.getPassword()));
        User user = userRepo.findByName(request.getName()).orElseThrow(()-> new UsernameNotFoundException("User not Found"));
        String jwt = jwtService.generateToken(user);
        return jwt;

    }

}