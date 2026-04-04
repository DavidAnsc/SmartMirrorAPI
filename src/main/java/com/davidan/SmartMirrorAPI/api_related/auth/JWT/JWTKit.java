package com.davidan.SmartMirrorAPI.api_related.auth.JWT;

import java.util.Date;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.davidan.SmartMirrorAPI.api_related.auth.JWT.TokenModels.Token;
import com.davidan.SmartMirrorAPI.api_related.auth.JWT.TokenModels.TokenBlacklistService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Service
public class JWTKit {
  KeyGenerator keyGen;
  SecretKey secretKey;
  Claims claims;

  @Value("${jwt.expiration}")
  private int jwtExpiration; // in millie

  @Autowired
  private TokenBlacklistService tokenService;

  public JWTKit() throws Exception {
    this.keyGen = KeyGenerator.getInstance("HmacSHA256");
    this.secretKey = keyGen.generateKey();
  }

  public String generateToken(String username) {
    return Jwts.builder()
      .claims()
        // .add(claims)
        .subject(username)
        .expiration(new Date(System.currentTimeMillis() + jwtExpiration))
        .issuedAt(new Date())
      .and()
        .signWith(secretKey)
      .compact();
  }

  public void blacklistToken(String jwtToken) {
      Token token = new Token();
      System.out.println(jwtToken);
      Date expiry = Jwts.parser()
      .verifyWith(secretKey)
      .build()
      .parseSignedClaims(jwtToken)
      .getPayload()
      .getExpiration();
  
      token.setJwtToken(jwtToken);
      token.setExpiration(expiry);
      
      tokenService.blacklistToken(token.getJwtToken(), token.getExpiration());
  }
  
  public void refreshTokenList() {
      tokenService.findAll().forEach(token -> {
          if (token.getExpiration().before(new Date())) {
              tokenService.removeToken(token.getJwtToken());
          }
      });
  }
  
  public boolean isBlacklisted(String jwtToken) {
      Token token = tokenService.find(jwtToken);
      if (token != null) {
          if (token.getExpiration().before(new Date())) {
              tokenService.removeToken(jwtToken);
              return false;
          }
          return true;
      }
      return false;
  }
  
  public String extractUsername(String jwtToken) {
      return extractAllClaims(jwtToken).getSubject();
  }
  
  public boolean isUserValid(String jwtToken, UserDetails userDetails) {
      String username = extractUsername(jwtToken);
      return username != null
              && username.equals(userDetails.getUsername());
  }
  
  public boolean isTokenExpired(String jwtToken) {
      Date expiration = extractAllClaims(jwtToken).getExpiration();
      return expiration.before(new Date());
  }
  
  private Claims extractAllClaims(String jwtToken) {
      return Jwts.parser()
              .verifyWith(secretKey)
              .build()
              .parseSignedClaims(jwtToken)
              .getPayload();
  }
}
