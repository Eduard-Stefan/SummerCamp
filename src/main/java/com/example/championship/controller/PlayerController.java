package com.example.championship.controller;

import com.example.championship.model.Player;
import com.example.championship.service.PlayerService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/players")
public class PlayerController {
    @Autowired
    private PlayerService playerService;

    @GetMapping(value = "/all")
    public @NotNull List<Player> getAllPlayers() {
        return playerService.getAllPlayers();
    }

    @GetMapping(value = "/name/{name}")
    public List<Player> findPlayersByName(@PathVariable(value = "name") String name) {
        return playerService.findPlayersByName(name);
    }

    @GetMapping(value = "/age/{age}")
    public List<Player> findPlayersByAge(@PathVariable(value = "age") Integer age) {
        return playerService.findPlayersByAge(age);
    }

    @GetMapping(value = "/nationality/{nationality}")
    public List<Player> findPlayersByNationality(@PathVariable(value = "nationality") String nationality) {
        return playerService.findPlayersByNationality(nationality);
    }

    @GetMapping(value = "/sort/name/asc")
    public @NotNull List<Player> sortPlayersByNameAsc() {
        return playerService.sortPlayersByNameAsc();
    }

    @GetMapping(value = "/sort/name/desc")
    public @NotNull List<Player> sortPlayersByNameDesc() {
        return playerService.sortPlayersByNameDesc();
    }

    @GetMapping(value = "/sort/age/asc")
    public @NotNull List<Player> sortPlayersByAgeAsc() {
        return playerService.sortPlayersByAgeAsc();
    }

    @GetMapping(value = "/sort/age/desc")
    public @NotNull List<Player> sortPlayersByAgeDesc() {
        return playerService.sortPlayersByAgeDesc();
    }

    @GetMapping(value = "/sort/nationality/asc")
    public @NotNull List<Player> sortPlayersByNationalityAsc() {
        return playerService.sortPlayersByNationalityAsc();
    }

    @GetMapping(value = "/sort/nationality/desc")
    public @NotNull List<Player> sortPlayersByNationalityDesc() {
        return playerService.sortPlayersByNationalityDesc();
    }

    @PostMapping(value = "/new")
    public @NotNull Player createNewPlayer(@RequestBody @NotNull Player newPlayer) {
        return playerService.save(newPlayer);
    }

    @DeleteMapping(value = "/delete/{id}")
    public void deletePlayer(@PathVariable int id) {
        playerService.deleteById(id);
    }

    @PutMapping("/update/{id}")
    public @NotNull Player replacePlayer(@RequestBody @NotNull Player newPlayer, @PathVariable int id) {
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
