package com.davidan.SmartMirrorAPI.api_related.auth.RefreshToken;

import java.util.Date;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;

@Entity
public class RefreshToken {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "refresh_token_seq")
  @SequenceGenerator(name = "refresh_token_seq", sequenceName = "refresh_token_seq", allocationSize = 1)
  private long id;

  private String token;

  private Date expiryDate;

  public RefreshToken() {
    this.token = UUID.randomUUID().toString();
  }

  public long getId() {
    return id;
  }
  public void setId(long id) {
    this.id = id;
  }
  public String getToken() {
    return token;
  }
  public void setToken(String token) {
    this.token = token;
  }
  public Date getExpiryDate() {
    return expiryDate;
  }
  public void setExpiryDate(Date expiryDate) {
    this.expiryDate = expiryDate;
  }
}
