package com.ClubProduction.spring.services;

import com.ClubProduction.spring.models.Role;
import com.ClubProduction.spring.models.User;
import com.ClubProduction.spring.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class AdminService {
    private final UserRepository userRepository;

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public boolean banUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("User Not Found"));
        if (user.getRoles().equals(Role.ROLE_ADMIN)) {
            return false;
        }

        if (user != null) {
            if (user.getActive()) {
                user.setActive(false);
            } else {
                user.setActive(true);
            }
        }
        userRepository.save(user);
        return true;
    }
}
