package com.rhbgroup.dte.pg1enhancement.controller;

import com.rhbgroup.dte.pg1enhancement.security.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JwtController {

  @GetMapping("/generate")
  public ResponseEntity<String> generateToken() {

    String token = JwtUtil.generateToken();
    return ResponseEntity.ok("Generated Token: " + token);
  }
}
