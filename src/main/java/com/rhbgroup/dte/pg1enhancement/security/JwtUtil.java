package com.rhbgroup.dte.pg1enhancement.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;

public class JwtUtil {

  private static final String SECRET_KEY =
      "88fffe73860aa634f7c0ceae0a782427cd1e7bbeb3d88302ba24c6c8cd0aaca2";
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
}
