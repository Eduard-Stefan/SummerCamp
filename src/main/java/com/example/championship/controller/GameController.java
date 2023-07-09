package com.example.championship.controller;

import com.example.championship.model.Game;
import com.example.championship.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/game")
public class GameController {
    @Autowired
    private GameService gameService;

    @GetMapping(value = "/all")
    public List<Game> getAllGames(
            @RequestParam(required = false) String sortBy,
            @RequestParam(required = false) String sortOrder,
            @RequestParam(required = false) String filterValue) {

        List<Game> games = gameService.getAllGames();

        if (sortBy != null && sortOrder != null) {
            Comparator<Game> comparator = null;

            switch (sortBy.toLowerCase()) {
                case "gametype" -> comparator = Comparator.comparing(Game::getGameType, String.CASE_INSENSITIVE_ORDER);
                case "date" -> comparator = Comparator.comparing(Game::getDate);
                case "location" -> comparator = Comparator.comparing(Game::getLocation, String.CASE_INSENSITIVE_ORDER);
                case "score1" -> comparator = Comparator.comparingInt(Game::getScore1);
                case "score2" -> comparator = Comparator.comparingInt(Game::getScore2);
                default -> {
                }
            }

            if (comparator != null) {
                if (sortOrder.equalsIgnoreCase("desc")) {
                    comparator = comparator.reversed();
                }
                games.sort(comparator);
            }
        }

        if (filterValue != null) {
            String filterValueLowerCase = filterValue.toLowerCase();
            games = games.stream()
                    .filter(game ->
                            game.getGameType().toLowerCase().contains(filterValueLowerCase)
                                    || game.getLocation().toLowerCase().contains(filterValueLowerCase)
                                    || (game.getTeam1() != null && game.getTeam1().getName().toLowerCase().contains(filterValueLowerCase))
                                    || (game.getTeam2() != null && game.getTeam2().getName().toLowerCase().contains(filterValueLowerCase)))
                    .collect(Collectors.toList());
        }

        return games;
    }

    @PostMapping(value = "/new")
    public Game createNewGame(@RequestBody Game newGame) {
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
