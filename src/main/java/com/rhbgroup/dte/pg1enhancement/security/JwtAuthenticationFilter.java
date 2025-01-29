package com.rhbgroup.dte.pg1enhancement.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
  @Override
  protected void doFilterInternal(
      HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {
    String requestPath = request.getServletPath();

    // Skip JWT authentication for generate
    if (requestPath.equals("/generate")) {
      filterChain.doFilter(request, response);
      return;
    }

    final String authorizationHeader = request.getHeader("Authorization");
    if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
      String token = authorizationHeader.substring(7);
      try {
        Claims claims = JwtUtil.validateToken(token);
        String username = claims.getSubject();
        String password = (String) claims.get("password");

      } catch (ExpiredJwtException | MalformedJwtException | SignatureException e) {
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid or Expired JWT");
        return;
      }
    } else {
      response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Missing JWT Token");
      return;
    }
    filterChain.doFilter(request, response);
  }
}
