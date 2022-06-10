package com.ClubProduction.spring.controllers;

import com.ClubProduction.spring.models.Player;
import com.ClubProduction.spring.payload.request.ChangePlayerStatisticRequest;
import com.ClubProduction.spring.services.PlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/players")
@CrossOrigin("http://localhost:3000")
@RequiredArgsConstructor
public class PlayerController {

    private final PlayerService playerService;

    @PostMapping("/add")
    public Player addPlayer(@RequestBody Player player) {
        playerService.create(player);
        return player;
    }

    @DeleteMapping("/{id}")
    public void deletePlayer(@PathVariable Long id) {
        playerService.deleteById(id);
    }

    @GetMapping("")
    public List<Player> getAllPlayers() {
        return playerService.findAllPlayers();
    }

    @GetMapping("/{id}")
    public Player getPlayerById(@PathVariable Long id) {
        return playerService.findPlayerById(id);
    }

    @PutMapping("/{id}")
    public ChangePlayerStatisticRequest changeGoalAndAssistField(@RequestBody ChangePlayerStatisticRequest playerRequest, @PathVariable Long id) {
        playerService.changeGoalAndAssistField(playerRequest, id);
        return playerRequest;
    }


}
