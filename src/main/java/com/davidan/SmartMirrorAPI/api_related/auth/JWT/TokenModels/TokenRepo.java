package com.davidan.SmartMirrorAPI.api_related.auth.JWT.TokenModels;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepo extends JpaRepository<Token, Integer> {
  public Token findByJwtToken(String jwtToken);
}
