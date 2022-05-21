package com.HockeyClub.spring.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users", 
    uniqueConstraints = { 
      @UniqueConstraint(columnNames = "username"),
      @UniqueConstraint(columnNames = "email") 
    })
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements UserDetails {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

//Name may not be blank
  private String username;

  private String email;

  private String password;

  private String phoneNumber;

  private Boolean active;

  private String activateCode;

//  @ManyToMany(fetch = FetchType.LAZY)
//  @JoinTable(  name = "user_roles",
//        joinColumns = @JoinColumn(name = "user_id"),
//        inverseJoinColumns = @JoinColumn(name = "role_id"))
  @ElementCollection(targetClass = ERole.class, fetch = FetchType.EAGER)
  @CollectionTable(name = "user_role",
  joinColumns = @JoinColumn(name = "user_id"))
  @Enumerated(EnumType.STRING)
  private Set<ERole> roles = new HashSet<>();

  private LocalDateTime dateOfCreated;

  @PrePersist
  private void init(){
    dateOfCreated = LocalDateTime.now();
  }

  public boolean isAdmin(){
    return roles.contains(ERole.ROLE_ADMIN);
  }

  public Set<ERole> getRoles() {
    return roles;
  }

  public User(String username, String email, String password, String phoneNumber) {
    this.username = username;
    this.email = email;
    this.password = password;
    this.phoneNumber = phoneNumber;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return active;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return roles;
  }

}
