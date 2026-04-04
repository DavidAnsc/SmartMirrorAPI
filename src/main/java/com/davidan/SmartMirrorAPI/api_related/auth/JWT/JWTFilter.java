package com.davidan.SmartMirrorAPI.api_related.auth.JWT;

import java.io.IOException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JWTFilter extends OncePerRequestFilter {

  @Autowired
  private JWTKit jwtKit;
  // @Autowired

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
    String header = request.getHeader("Authorization");

    if (header.startsWith("Bearer ")) {
      String jwtToken = header.substring(7);
      if (jwtToken.length() > 5) {
        String username = jwtKit.extractUsername(jwtToken);
        boolean isExpired = jwtKit.isTokenExpired(jwtToken);
        if (username != null && !isExpired) {
          // Set authentication in the security context if needed
          //TODO: implement the auth token once the user database is created
          // UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(jwtToken, username)
        }
      }
    }
    
  }

}
