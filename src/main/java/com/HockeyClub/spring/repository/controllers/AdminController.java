package com.HockeyClub.spring.repository.controllers;

import com.HockeyClub.spring.models.User;
import com.HockeyClub.spring.security.services.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
    private final AdminService adminService;

    @GetMapping("/users")
    public List<User> admin() {
        return adminService.list();
    }

    @PostMapping("user/ban/{id}")
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String userBan(@PathVariable("id") Long id) {
        adminService.banUser(id);
        return "User active changed";
    }


}


