package com.backend.user.infraestructure.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.backend.user.application.auth.AuthenticationService;
import com.backend.user.domain.User;
import com.backend.user.domain.dto.auth.AuthenticationRequest;
import com.backend.user.domain.dto.auth.AuthenticationResponse;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationService authenticationService;

    @GetMapping("/validate-token")
    public ResponseEntity<Boolean> validate(@RequestParam String jwt){
        boolean isTokenValid = authenticationService.validateToken(jwt);
        return ResponseEntity.ok(isTokenValid);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody @Valid AuthenticationRequest authenticationRequest){

        AuthenticationResponse rsp = authenticationService.login(authenticationRequest);
        return ResponseEntity.ok(rsp);

    }
    
    @GetMapping("/profile")
    public ResponseEntity<User> findMyProfile(){
        User user = authenticationService.findLoggedInUser();
        return ResponseEntity.ok(user);
    }
}
