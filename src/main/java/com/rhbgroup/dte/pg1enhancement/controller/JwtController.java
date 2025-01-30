package com.rhbgroup.dte.pg1enhancement.controller;

import com.rhbgroup.dte.pg1enhancement.api.JwtApiDelegate;
import com.rhbgroup.dte.pg1enhancement.model.JwtTokenResponse;
import com.rhbgroup.dte.pg1enhancement.security.JwtUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class JwtController implements JwtApiDelegate {

  @Override
  public ResponseEntity<JwtTokenResponse> generateToken() {
    log.info("API: getJwtToken");
    String token = JwtUtil.generateToken();
    JwtTokenResponse jwtResponse = new JwtTokenResponse();
    jwtResponse.setToken(token);
    return ResponseEntity.ok(jwtResponse);
  }
}
