package com.HockeyClub.spring.services;

import com.HockeyClub.spring.models.Player;
import com.HockeyClub.spring.payload.request.PlayerRequest;
import com.HockeyClub.spring.repository.PlayerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class PlayerService {
    private final PlayerRepository playerRepository;

    public List<Player> listOfPlayers(){
        return playerRepository.findAll();
    }

    public void create(Player player) {
        playerRepository.save(player);
    }

    public void changeGoalAndAssistField(PlayerRequest playerRequest, Long id) {
        Player player = playerRepository.getById(id);
        Integer assists = player.getAssist();
        Integer goals = player.getAssist();
        if(player != null){
            player.setAssist(assists + playerRequest.getAssist());
            player.setGoal(goals + playerRequest.getGoals());
            playerRepository.save(player);
        }
        else log.info("Игрок не найден");

    }
}
