package com.rhbgroup.dte.pg1enhancement.service.impl;

import com.rhbgroup.dte.pg1enhancement.exception.BlogAPIException;
import com.rhbgroup.dte.pg1enhancement.model.LoginDto;
import com.rhbgroup.dte.pg1enhancement.model.RegisterDto;
import com.rhbgroup.dte.pg1enhancement.model.User;
import com.rhbgroup.dte.pg1enhancement.repository.UserRepository;
import com.rhbgroup.dte.pg1enhancement.security.JwtTokenProvider;
import com.rhbgroup.dte.pg1enhancement.service.ifc.AuthServiceifc;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class AuthServiceImpl implements AuthServiceifc {

    private AuthenticationManager authenticationManager;
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private JwtTokenProvider jwtTokenProvider;

    public AuthServiceImpl(AuthenticationManager authenticationManager, UserRepository userRepository, PasswordEncoder passwordEncoder, JwtTokenProvider jwtTokenProvider) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
    }
    @Override
    public String login(LoginDto loginDto) {
       Authentication authentication= authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getUsernameOrEmail(), loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtTokenProvider.generateToken(authentication);

        return token;
    }

    @Override
    public String register(RegisterDto registerDto) {

        //add check for username exists in database
        if (userRepository.existsByUsername(registerDto.getUsername())){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST,"Username is already exists!.");
        }
        // add check for email exists in database
        if (userRepository.existsByUsername(registerDto.getEmail())){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST," Email already exists!.");
        }
        User user = new User();
        user.setName(registerDto.getName());
        user.setUsername(registerDto.getUsername());
        user.setEmail(registerDto.getEmail());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));

        return "User register Successfully.";
    }

    @Override
    public Set<?> name(LoginDto loginDto) {
        return null;
    }
}
