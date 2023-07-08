package com.example.championship.service;

import com.example.championship.model.Game;
import com.example.championship.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GameService {
    @Autowired
    private GameRepository gameRepository;

    public List<Game> getAllGames() {
        return gameRepository.findAll();
    }

    public Game save(Game newGame) {
        return gameRepository.save(newGame);
    }

    public void deleteById(int id) {
        gameRepository.deleteById(id);
    }

    public Optional<Game> findById(int id) {
        return gameRepository.findById(id);
    }

}
