package com.rhbgroup.dte.pg1enhancement.controller;

import com.rhbgroup.dte.pg1enhancement.model.LoginDto;
import com.rhbgroup.dte.pg1enhancement.model.RegisterDto;
import com.rhbgroup.dte.pg1enhancement.security.JWTAuthResponse;
import com.rhbgroup.dte.pg1enhancement.security.JwtTokenProvider;
import com.rhbgroup.dte.pg1enhancement.service.ifc.AuthServiceifc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private AuthServiceifc authService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    //Build login rest api
//    http://localhost:8080/api/auth/login
    @PostMapping(value = {"/login","/signin"})
    public ResponseEntity<JWTAuthResponse> authenticateUser(@RequestBody LoginDto loginDto){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getUsernameOrEmail(),loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtTokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new JWTAuthResponse(token));
    }
//    http://localhost:8080/api/auth/signup
    @PostMapping(value = {"/register","/signup"})
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto){
       String response = authService.register(registerDto);
       return new ResponseEntity(response, HttpStatus.CREATED);
    }
}

