package com.ClubProduction.spring.services;

import com.ClubProduction.spring.models.Player;
import com.ClubProduction.spring.payload.request.ChangePlayerStatisticRequest;
import com.ClubProduction.spring.repository.PlayerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class PlayerService {
    private final PlayerRepository playerRepository;

    public List<Player> findAllPlayers() {
        return playerRepository.findAll();
    }

    public void create(Player player) {
        playerRepository.save(player);
    }

    public void changeGoalAndAssistField(ChangePlayerStatisticRequest playerRequest, Long id) {
        Player player = playerRepository.getById(id);
        Integer assists = player.getAssist();
        Integer goals = player.getGoal();
        if (player != null) {
            player.setAssist(assists + playerRequest.getAssist());
            player.setGoal(goals + playerRequest.getGoals());
            playerRepository.save(player);
        } else log.info("Игрок не найден");

    }

    public Player findPlayerById(Long id) {
        return playerRepository.findById(id).orElse(null);
    }

    public void deleteById(Long id) {
        playerRepository.deleteById(id);
    }
}
