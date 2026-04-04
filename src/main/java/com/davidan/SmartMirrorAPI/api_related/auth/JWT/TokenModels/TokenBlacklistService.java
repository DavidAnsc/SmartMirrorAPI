package com.davidan.SmartMirrorAPI.api_related.auth.JWT.TokenModels;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TokenBlacklistService {
    @Autowired
    private TokenRepo tokenRepo;

    public Token find(String token) {
        return tokenRepo.findByJwtToken(token);
    }

    public Collection<Token> findAll() {
        return tokenRepo.findAll();
    }

    public void blacklistToken(String token, Date expiryDate) {
        Token blacklistedToken = new Token();
        blacklistedToken.setJwtToken(token);
        blacklistedToken.setExpiration(expiryDate);
        tokenRepo.save(blacklistedToken);
    }

    public void removeToken(String token) {
        Token existingToken = tokenRepo.findByJwtToken(token);
        if (existingToken != null) {
            tokenRepo.delete(existingToken);
        }
    }
}
