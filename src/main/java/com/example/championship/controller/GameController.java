package com.example.championship.controller;

import com.example.championship.model.Game;
import com.example.championship.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/game")
public class GameController {
    @Autowired
    private GameService gameService;

    @GetMapping(value = "/all")
    public List<Game> getAllGames() {
        return gameService.getAllGames();
    }

    @PostMapping(value = "/new")
    public Game createNewCame(@RequestBody Game newGame) {
        return gameService.save(newGame);
    }

    @DeleteMapping(value = "/delete/{id}")
    public void deleteGame(@PathVariable int id) {
        gameService.deleteById(id);
    }

    @PutMapping("/update/{id}")
    public Game replaceGame(@RequestBody Game newGame, @PathVariable int id) {
        return gameService.findById(id)
                .map(game -> {
                    game.setGameType(newGame.getGameType());
                    game.setDate(newGame.getDate());
                    game.setLocation(newGame.getLocation());
                    game.setScore1(newGame.getScore1());
                    game.setScore2(newGame.getScore2());
                    game.setTeam1(newGame.getTeam1());
                    game.setTeam2(newGame.getTeam2());
                    return gameService.save(game);
                })
                .orElseGet(() -> {
                    newGame.setId(id);
                    return gameService.save(newGame);
                });
    }

}
