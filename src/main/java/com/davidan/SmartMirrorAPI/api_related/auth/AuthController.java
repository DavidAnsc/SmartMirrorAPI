package com.davidan.SmartMirrorAPI.api_related.auth;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.davidan.SmartMirrorAPI.api_related.auth.JWT.JWTKit;

@RestController
@RequestMapping("/auth")
public class AuthController {
  @Autowired
  JWTKit jwtKit;

  @Value("${jwt.secret1}")
  private String username;

  @Value("${jwt.secret2}")
  private String password;

  @PostMapping("/login")
  public String login(@RequestBody Map<String, String> body) {
    if (body.get("username").equals(username) && body.get("password").equals(password)) {
      return jwtKit.generateToken(body.get("username"));
    } else {
      return "Invalid credentials";
    }
  }

  // @PostMapping("/refresh")
  // public String refresh(@RequestBody String entity) {
      
  // }
  

}
