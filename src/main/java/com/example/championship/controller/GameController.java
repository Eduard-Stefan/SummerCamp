package com.example.championship.controller;

import com.example.championship.model.Game;
import com.example.championship.service.GameService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/games")
public class GameController {
    @Autowired
    private GameService gameService;

    @GetMapping(value = "/all")
    public @NotNull List<Game> getAllGames() {
        return gameService.getAllGames();
    }

    @GetMapping(value = "/date/{date}")
    public List<Game> findGamesByDate(@PathVariable(value = "date") Date date) {
        return gameService.findGamesByDate(date);
    }

    @GetMapping(value = "/gameType/{gameType}")
    public List<Game> findGamesByGameType(@PathVariable(value = "gameType") String gameType) {
        return gameService.findGamesByGameType(gameType);
    }

    @GetMapping(value = "/location/{location}")
    public List<Game> findGamesByLocation(@PathVariable(value = "location") String location) {
        return gameService.findGamesByLocation(location);
    }

    @GetMapping(value = "/sort/date/asc")
    public @NotNull List<Game> sortGamesByDateAsc() {
        return gameService.sortGamesByDateAsc();
    }

    @GetMapping(value = "/sort/date/desc")
    public @NotNull List<Game> sortGamesByDateDesc() {
        return gameService.sortGamesByDateDesc();
    }

    @GetMapping(value = "/sort/gameType/asc")
    public @NotNull List<Game> sortGamesByGameTypeAsc() {
        return gameService.sortGamesByGameTypeAsc();
    }

    @GetMapping(value = "/sort/gameType/desc")
    public @NotNull List<Game> sortGamesByGameTypeDesc() {
        return gameService.sortGamesByGameTypeDesc();
    }

    @GetMapping(value = "/sort/location/asc")
    public @NotNull List<Game> sortGamesByLocationAsc() {
        return gameService.sortGamesByLocationAsc();
    }

    @GetMapping(value = "/sort/location/desc")
    public @NotNull List<Game> sortGamesByLocationDesc() {
        return gameService.sortGamesByLocationDesc();
    }

    @PostMapping(value = "/new")
    public @NotNull Game createNewGame(@RequestBody @NotNull Game newGame) {
        return gameService.save(newGame);
    }

    @DeleteMapping(value = "/delete/{id}")
    public void deleteGame(@PathVariable int id) {
        gameService.deleteById(id);
    }

    @PutMapping("/update/{id}")
    public @NotNull Game replaceGame(@RequestBody @NotNull Game newGame, @PathVariable int id) {
        return gameService.replaceGame(newGame, id);
    }
}
