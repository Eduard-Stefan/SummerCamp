package com.example.championship.service;

import com.example.championship.model.Game;
import com.example.championship.model.Team;
import com.example.championship.repository.GameRepository;
import com.example.championship.repository.TeamRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Date;
import java.util.List;

@Service
public class GameService {
    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private GameRepository gameRepository;

    public @NotNull List<Game> getAllGames() {
        return gameRepository.findAll();
    }

    public List<Game> findGamesByDate(Date date) {
        return gameRepository.findAllByDate(date);
    }

    public List<Game> findGamesByGameType(String gameType) {
        return gameRepository.findAllByGameType(gameType);
    }

    public List<Game> findGamesByLocation(String location) {
        return gameRepository.findAllByLocation(location);
    }

    public @NotNull List<Game> sortGamesByDateAsc() {
        return gameRepository.findAll(Sort.by(Sort.Direction.ASC, "date"));
    }

    public @NotNull List<Game> sortGamesByDateDesc() {
        return gameRepository.findAll(Sort.by(Sort.Direction.DESC, "date"));
    }

    public @NotNull List<Game> sortGamesByGameTypeAsc() {
        return gameRepository.findAll(Sort.by(Sort.Direction.ASC, "gameType"));
    }

    public @NotNull List<Game> sortGamesByGameTypeDesc() {
        return gameRepository.findAll(Sort.by(Sort.Direction.DESC, "gameType"));
    }

    public @NotNull List<Game> sortGamesByLocationAsc() {
        return gameRepository.findAll(Sort.by(Sort.Direction.ASC, "location"));
    }

    public @NotNull List<Game> sortGamesByLocationDesc() {
        return gameRepository.findAll(Sort.by(Sort.Direction.DESC, "location"));
    }

    public @NotNull Game save(@NotNull Game newGame) {

        @NotNull Team dbTeamHome = teamRepository.findById(newGame.getTeamHome().getId()).get();
        if (dbTeamHome.getTotalScoreHome() == null) {
            dbTeamHome.setTotalScoreHome(newGame.getScoreHome());
        } else {
            dbTeamHome.setTotalScoreHome(dbTeamHome.getTotalScoreHome() + newGame.getScoreHome());
        }
        dbTeamHome.setTotalScore(dbTeamHome.getTotalScoreHome() + dbTeamHome.getTotalScoreAway());
        teamRepository.save(dbTeamHome);
        newGame.setTeamHome(dbTeamHome);

        @NotNull Team dbTeamAway = teamRepository.findById(newGame.getTeamAway().getId()).get();
        if (dbTeamAway.getTotalScoreAway() == null) {
            dbTeamAway.setTotalScoreAway(newGame.getScoreAway());
        } else {
            dbTeamAway.setTotalScoreAway(dbTeamAway.getTotalScoreAway() + newGame.getScoreAway());
        }
        dbTeamAway.setTotalScore(dbTeamAway.getTotalScoreHome() + dbTeamAway.getTotalScoreAway());
        teamRepository.save(dbTeamAway);
        newGame.setTeamAway(dbTeamAway);

        return gameRepository.save(newGame);
    }

    public void deleteById(int id) {

        Team dbTeamHome = gameRepository.getReferenceById(id).getTeamHome();
        dbTeamHome.setTotalScoreHome(dbTeamHome.getTotalScoreHome() - gameRepository.getReferenceById(id).getScoreHome());
        dbTeamHome.setTotalScore(dbTeamHome.getTotalScoreHome() + dbTeamHome.getTotalScoreAway());
        teamRepository.save(dbTeamHome);
        gameRepository.getReferenceById(id).setTeamHome(dbTeamHome);

        Team dbTeamAway = gameRepository.getReferenceById(id).getTeamAway();
        dbTeamAway.setTotalScoreAway(dbTeamAway.getTotalScoreAway() - gameRepository.getReferenceById(id).getScoreAway());
        dbTeamAway.setTotalScore(dbTeamAway.getTotalScoreHome() + dbTeamAway.getTotalScoreAway());
        teamRepository.save(dbTeamAway);
        gameRepository.getReferenceById(id).setTeamAway(dbTeamAway);

        gameRepository.deleteById(id);
    }

    public @NotNull Game replaceGame(@RequestBody @NotNull Game newGame, @PathVariable int id) {

        Team dbTeamHome = gameRepository.getReferenceById(id).getTeamHome();
        dbTeamHome.setTotalScoreHome(dbTeamHome.getTotalScoreHome() - gameRepository.getReferenceById(id).getScoreHome());
        dbTeamHome.setTotalScore(dbTeamHome.getTotalScoreHome() + dbTeamHome.getTotalScoreAway());
        teamRepository.save(dbTeamHome);
        gameRepository.getReferenceById(id).setTeamHome(dbTeamHome);

        Team dbTeamAway = gameRepository.getReferenceById(id).getTeamAway();
        dbTeamAway.setTotalScoreAway(dbTeamAway.getTotalScoreAway() - gameRepository.getReferenceById(id).getScoreAway());
        dbTeamAway.setTotalScore(dbTeamAway.getTotalScoreHome() + dbTeamAway.getTotalScoreAway());
        teamRepository.save(dbTeamAway);
        gameRepository.getReferenceById(id).setTeamAway(dbTeamAway);

        return gameRepository.findById(id)
                .map(game -> {
                    game.setDate(newGame.getDate());
                    game.setGameType(newGame.getGameType());
                    game.setLocation(newGame.getLocation());
                    game.setScoreHome(newGame.getScoreHome());
                    game.setScoreAway(newGame.getScoreAway());
                    game.setTeamHome(newGame.getTeamHome());
                    game.setTeamAway(newGame.getTeamAway());

                    @NotNull Team dbTeamHomeAux = teamRepository.findById(newGame.getTeamHome().getId()).get();
                    if (dbTeamHomeAux.getTotalScoreHome() == null) {
                        dbTeamHomeAux.setTotalScoreHome(newGame.getScoreHome());
                    } else {
                        dbTeamHomeAux.setTotalScoreHome(dbTeamHomeAux.getTotalScoreHome() + newGame.getScoreHome());
                    }
                    dbTeamHomeAux.setTotalScore(dbTeamHomeAux.getTotalScoreHome() + dbTeamHomeAux.getTotalScoreAway());
                    teamRepository.save(dbTeamHomeAux);
                    newGame.setTeamHome(dbTeamHomeAux);

                    @NotNull Team dbTeamAwayAux = teamRepository.findById(newGame.getTeamAway().getId()).get();
                    if (dbTeamAwayAux.getTotalScoreAway() == null) {
                        dbTeamAwayAux.setTotalScoreAway(newGame.getScoreAway());
                    } else {
                        dbTeamAwayAux.setTotalScoreAway(dbTeamAwayAux.getTotalScoreAway() + newGame.getScoreAway());
                    }
                    dbTeamAwayAux.setTotalScore(dbTeamAwayAux.getTotalScoreHome() + dbTeamAwayAux.getTotalScoreAway());
                    teamRepository.save(dbTeamAwayAux);
                    newGame.setTeamAway(dbTeamAwayAux);

                    return gameRepository.save(game);
                })
                .orElseGet(() -> {
                    newGame.setId(id);

                    @NotNull Team dbTeamHomeAux = teamRepository.findById(newGame.getTeamHome().getId()).get();
                    if (dbTeamHomeAux.getTotalScoreHome() == null) {
                        dbTeamHomeAux.setTotalScoreHome(newGame.getScoreHome());
                    } else {
                        dbTeamHomeAux.setTotalScoreHome(dbTeamHomeAux.getTotalScoreHome() + newGame.getScoreHome());
                    }
                    dbTeamHomeAux.setTotalScore(dbTeamHomeAux.getTotalScoreHome() + dbTeamHomeAux.getTotalScoreAway());
                    teamRepository.save(dbTeamHomeAux);
                    newGame.setTeamHome(dbTeamHomeAux);

                    @NotNull Team dbTeamAwayAux = teamRepository.findById(newGame.getTeamAway().getId()).get();
                    if (dbTeamAwayAux.getTotalScoreAway() == null) {
                        dbTeamAwayAux.setTotalScoreAway(newGame.getScoreAway());
                    } else {
                        dbTeamAwayAux.setTotalScoreAway(dbTeamAwayAux.getTotalScoreAway() + newGame.getScoreAway());
                    }
                    dbTeamAwayAux.setTotalScore(dbTeamAwayAux.getTotalScoreHome() + dbTeamAwayAux.getTotalScoreAway());
                    teamRepository.save(dbTeamAwayAux);
                    newGame.setTeamAway(dbTeamAwayAux);

                    return gameRepository.save(newGame);
                });
    }
}
