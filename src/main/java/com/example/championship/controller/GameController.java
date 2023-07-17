package com.example.championship.controller;

import com.example.championship.model.Game;
import com.example.championship.service.GameService;
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
    public List<Game> getAllGames() {
        return gameService.getAllGames();
    }

    @GetMapping(value = "/gameType/{gameType}")
    public List<Game> findGamesByGameType(@PathVariable(value = "gameType") String gameType) {
        return gameService.findGamesByGameType(gameType);
    }

    @GetMapping(value = "/location/{location}")
    public List<Game> findGamesByLocation(@PathVariable(value = "location") String location) {
        return gameService.findGamesByLocation(location);
    }

    @GetMapping(value = "/date/{date}")
    public List<Game> findGamesByDate(@PathVariable(value = "date") Date date) {
        return gameService.findGamesByDate(date);
    }
    //I don't know what URL to use, http://localhost:8080/games/date/2023-07-13 doesn't work for example

    @GetMapping(value = "/sort/gameType/asc")
    public List<Game> sortGamesByGameTypeAsc() {
        return gameService.sortGamesByGameTypeAsc();
    }

    @GetMapping(value = "/sort/gameType/desc")
    public List<Game> sortGamesByGameTypeDesc() {
        return gameService.sortGamesByGameTypeDesc();
    }

    @GetMapping(value = "/sort/location/asc")
    public List<Game> sortGamesByLocationAsc() {
        return gameService.sortGamesByLocationAsc();
    }

    @GetMapping(value = "/sort/location/desc")
    public List<Game> sortGamesByLocationDesc() {
        return gameService.sortGamesByLocationDesc();
    }

    @GetMapping(value = "/sort/date/asc")
    public List<Game> sortGamesByDateAsc() {
        return gameService.sortGamesByDateAsc();
    }

    @GetMapping(value = "/sort/date/desc")
    public List<Game> sortGamesByDateDesc() {
        return gameService.sortGamesByDateDesc();
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
        return gameService.replaceGame(newGame, id);
    }
}
