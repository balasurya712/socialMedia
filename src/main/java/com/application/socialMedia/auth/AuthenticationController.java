package com.application.socialMedia.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.application.socialMedia.model.User;

import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping(path = "/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    @Autowired
    AuthenticationService service;
    //Register Method call
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User request) {
        
        
        return ResponseEntity.ok(service.register(request));
    }
    

    //Validation Method call or authentication method call
      @PostMapping("/authenticate")
    public ResponseEntity<String> authentication(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(service.authenticate(request));
    }
    
}
