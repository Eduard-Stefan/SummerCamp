package com.example.championship.controller;

import com.example.championship.model.Player;
import com.example.championship.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/player")
public class PlayerController {
    @Autowired
    private PlayerService playerService;

    @GetMapping(value = "/all")
    public List<Player> getAllPlayers(
            @RequestParam(required = false) String sortBy,
            @RequestParam(required = false) String sortOrder,
            @RequestParam(required = false) String filterValue) {

        List<Player> players = playerService.getAllPlayers();

        if (sortBy != null && sortOrder != null) {
            Comparator<Player> comparator = null;

            switch (sortBy.toLowerCase()) {
                case "name" -> comparator = Comparator.comparing(Player::getName, String.CASE_INSENSITIVE_ORDER);
                case "age" -> comparator = Comparator.comparingInt(Player::getAge);
                case "nationality" ->
                        comparator = Comparator.comparing(Player::getNationality, String.CASE_INSENSITIVE_ORDER);
                case "team" ->
                        comparator = Comparator.comparing(p -> p.getTeam().getName(), String.CASE_INSENSITIVE_ORDER);
                default -> {
                }
            }

            if (comparator != null) {
                if (sortOrder.equalsIgnoreCase("desc")) {
                    comparator = comparator.reversed();
                }
                players.sort(comparator);
            }
        }

        if (filterValue != null) {
            String filterValueLowerCase = filterValue.toLowerCase();
            players = players.stream()
                    .filter(player ->
                            player.getName().toLowerCase().contains(filterValueLowerCase)
                                    || player.getNationality().toLowerCase().contains(filterValueLowerCase)
                                    || (player.getTeam() != null && player.getTeam().getName().toLowerCase().contains(filterValueLowerCase)))
                    .collect(Collectors.toList());
        }

        return players;
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
