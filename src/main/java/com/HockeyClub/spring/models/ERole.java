package com.HockeyClub.spring.models;

import org.springframework.security.core.GrantedAuthority;

public enum ERole implements GrantedAuthority {
  ROLE_USER,
  ROLE_ADMIN;

  @Override
  public String getAuthority() {
    return name();
  }
}
