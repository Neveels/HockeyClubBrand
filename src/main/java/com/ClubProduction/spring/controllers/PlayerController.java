package com.ClubProduction.spring.controllers;

import com.ClubProduction.spring.models.Player;
import com.ClubProduction.spring.payload.request.PlayerRequest;
import com.ClubProduction.spring.services.PlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/player")
@CrossOrigin("http://localhost:3000")
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

    @PutMapping("/changeStatistic/{id}")
    public String changeGoalAndAssistField(@RequestBody PlayerRequest playerRequest, @PathVariable Long id){
        playerService.changeGoalAndAssistField(playerRequest, id);
        return "Players fields successfully changed";
    }

    @GetMapping("/get/{id}")
    public Player getPlayerById(@PathVariable Long id){
        return playerService.getPlayerById(id);
    }
}
