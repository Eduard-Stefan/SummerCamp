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
    public List<Player> getAllPlayers(
            @RequestParam(required = false) String sortBy,
            @RequestParam(required = false) String sortOrder,
            @RequestParam(required = false) String filterValue) {

        List<Player> players = null;

        if (sortBy != null && sortOrder != null) {

            switch (sortBy.toLowerCase()) {
                case "name" -> {
                    if (sortOrder.equalsIgnoreCase("asc"))
                        players = playerService.sortByNameAsc();
                    else
                        players = playerService.sortByNameDesc();
                }
                case "age" -> {
                    if (sortOrder.equalsIgnoreCase("asc"))
                        players = playerService.sortByAgeAsc();
                    else
                        players = playerService.sortByAgeDesc();
                }
                case "nationality" -> {
                    if (sortOrder.equalsIgnoreCase("asc"))
                        players = playerService.sortByNationalityAsc();
                    else
                        players = playerService.sortByNationalityDesc();
                }
                //case "team" -> {}
                default -> {
                }
            }
        } else
            players = playerService.getAllPlayers();

        /*if (filterValue != null) {
            String filterValueLowerCase = filterValue.toLowerCase();
            players = players.stream()
                    .filter(player ->
                            player.getName().toLowerCase().contains(filterValueLowerCase)
                                    || player.getNationality().toLowerCase().contains(filterValueLowerCase)
                                    || (player.getTeam() != null && player.getTeam().getName().toLowerCase().contains(filterValueLowerCase)))
                    .collect(Collectors.toList());
        }*/

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
