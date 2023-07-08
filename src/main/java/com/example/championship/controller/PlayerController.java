package com.example.championship.controller;

import com.example.championship.model.Player;
import com.example.championship.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/player")
public class PlayerController {
    @Autowired
    private PlayerService playerService;

    @GetMapping(value = "/all")
    public List<Player> getAllPlayers() {
        return playerService.getAllPlayers();
    }

    @PostMapping(value = "/new")
    public Player createNewPlayer(@RequestBody Player newPlayer) {
        return playerService.save(newPlayer);
    }

    @DeleteMapping(value = "/delete/{id}")
    public void deletePlayer(@PathVariable int id) {
        playerService.deleteById(id);
    }

    @PutMapping("/update/{id}")
    public Player replacePlayer(@RequestBody Player newPlayer, @PathVariable int id) {
        return playerService.findById(id)
                .map(player -> {
                    player.setName(newPlayer.getName());
                    player.setAge(newPlayer.getAge());
                    player.setNationality(newPlayer.getNationality());
                    player.setTeam(newPlayer.getTeam());
                    return playerService.save(player);
                })
                .orElseGet(() -> {
                    newPlayer.setId(id);
                    return playerService.save(newPlayer);
                });
    }

}
