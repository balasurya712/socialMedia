package com.application.socialMedia.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.application.socialMedia.model.User;

import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping(path = "/api/auth")
@CrossOrigin(origins = "http://localhost:4200")
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
 
    @RequestMapping(method = RequestMethod.OPTIONS)
    public ResponseEntity<TokenResponse> authentication(@RequestBody LoginRequest request) {
        TokenResponse tr = new TokenResponse();
        tr.setToken(service.authenticate(request));
        System.out.println("option"+request);
        return ResponseEntity.ok(tr);
    }

    @PostMapping
    public ResponseEntity<TokenResponse> postLogin(@RequestBody LoginRequest request) {
        System.out.println("post "+request);
        TokenResponse tr = new TokenResponse();
        tr.setToken(service.authenticate(request));
        return ResponseEntity.ok(tr);
    }
    
}
