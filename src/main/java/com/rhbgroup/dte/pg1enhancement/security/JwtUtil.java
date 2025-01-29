package com.rhbgroup.dte.pg1enhancement.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;

public class JwtUtil {

  private static final String SECRET_KEY = "ThisIsA256BitSecureKeyForJWTsExamplehihivuj";
  private static final long EXPIRATION_TIME = 3600000; // 1 hour
  private static final String STATIC_USERNAME = "TaiNgonheng";
  private static final String STATIC_PASSWORD = "heng";

  public static String generateToken() {
    return Jwts.builder()
        .setSubject(STATIC_USERNAME)
        .claim("password", STATIC_PASSWORD)
        .setIssuedAt(new Date())
        .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
        .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
        .compact();
  }

  public static Claims validateToken(String token) {
    return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
  }

  public static boolean isValidCredentials(String username, String password) {
    return STATIC_USERNAME.equals(username) && STATIC_PASSWORD.equals(password);
  }
}
