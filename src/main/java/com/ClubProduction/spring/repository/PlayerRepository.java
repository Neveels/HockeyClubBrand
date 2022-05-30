package com.ClubProduction.spring.repository;

import com.ClubProduction.spring.models.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, Long> {
}
