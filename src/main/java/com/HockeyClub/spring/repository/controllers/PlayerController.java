package com.HockeyClub.spring.repository.controllers;

import com.HockeyClub.spring.models.Player;
import com.HockeyClub.spring.payload.request.PlayerRequest;
import com.HockeyClub.spring.security.services.PlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/player")
@CrossOrigin("*")
@RequiredArgsConstructor
public class PlayerController {

    private final PlayerService playerService;

    @GetMapping("/allPlayers")
    public List<Player> listPlayers(){
        return playerService.listOfPlayers();
    }

    @PostMapping("/add")
    public String addPlayer(@RequestBody Player player){
        playerService.create(player);
        return "Players successfully added";
    }

    @PostMapping("/changeStatistic/{id}")
    public String changeGoalAndAssistField(@RequestBody PlayerRequest playerRequest, @PathVariable Long id){
        playerService.changeGoalAndAssistField(playerRequest, id);
        return "Players fields successfully changed";
    }
}
