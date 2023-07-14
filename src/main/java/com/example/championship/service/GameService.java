package com.example.championship.service;

import com.example.championship.model.Game;
import com.example.championship.model.Team;
import com.example.championship.repository.GameRepository;
import com.example.championship.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class GameService {
    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private GameRepository gameRepository;

    public List<Game> getAllGames() {
        return gameRepository.findAll();
    }

    public List<Game> findGamesByGameType(String gameType) {
        return gameRepository.findAllByGameType(gameType);
    }

    public List<Game> findGamesByLocation(String location) {
        return gameRepository.findAllByLocation(location);
    }

    public List<Game> findGamesByDate(Date date) {
        return gameRepository.findAllByDate(date);
    }

    public List<Game> sortGamesByGameTypeAsc() {
        return gameRepository.findAll(Sort.by(Sort.Direction.ASC, "gameType"));
    }

    public List<Game> sortGamesByGameTypeDesc() {
        return gameRepository.findAll(Sort.by(Sort.Direction.DESC, "gameType"));
    }

    public List<Game> sortGamesByLocationAsc() {
        return gameRepository.findAll(Sort.by(Sort.Direction.ASC, "location"));
    }

    public List<Game> sortGamesByLocationDesc() {
        return gameRepository.findAll(Sort.by(Sort.Direction.DESC, "location"));
    }

    public List<Game> sortGamesByDateAsc() {
        return gameRepository.findAll(Sort.by(Sort.Direction.ASC, "date"));
    }

    public List<Game> sortGamesByDateDesc() {
        return gameRepository.findAll(Sort.by(Sort.Direction.DESC, "date"));
    }

    public Game save(Game newGame) {

        Team dbteam1 = teamRepository.findById(newGame.getTeam1().getId()).get();
        if (dbteam1.getTotalScore1() == null) {
            dbteam1.setTotalScore1(newGame.getScore1());
        }
        else {
            dbteam1.setTotalScore1(dbteam1.getTotalScore1()+ newGame.getScore1());
        }
        teamRepository.save(dbteam1);
        newGame.setTeam1(dbteam1);

        Team dbteam2 = teamRepository.findById(newGame.getTeam2().getId()).get();
        if (dbteam2.getTotalScore2() == null) {
            dbteam2.setTotalScore2(newGame.getScore2());
        }
        else {
            dbteam2.setTotalScore2(dbteam2.getTotalScore2()+ newGame.getScore2());
        }
        teamRepository.save(dbteam2);
        newGame.setTeam2(dbteam2);

        return gameRepository.save(newGame);
    }

    public void deleteById(int id) {
        gameRepository.deleteById(id);
    }

    public Optional<Game> findById(int id) {
        return gameRepository.findById(id);
    }
}
