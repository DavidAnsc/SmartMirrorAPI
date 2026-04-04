package com.davidan.SmartMirrorAPI.api_related.auth.RefreshToken;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RefreshTokenRepo extends JpaRepository<RefreshToken, Integer> {
  public RefreshToken findByToken(String token);
  
}
