package com.HockeyClub.spring.repository;

import com.HockeyClub.spring.models.Player;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlayerRepository extends JpaRepository<Player, Long> {
}
