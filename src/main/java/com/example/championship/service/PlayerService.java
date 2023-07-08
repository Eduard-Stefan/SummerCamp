package com.example.championship.service;

import com.example.championship.model.Player;
import com.example.championship.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlayerService {
    @Autowired
    private PlayerRepository playerRepository;

    public List<Player> getAllPlayers() {
        return playerRepository.findAll();
    }

    public Player save(Player newPlayer) {
        return playerRepository.save(newPlayer);
    }

    public void deleteById(int id) {
        playerRepository.deleteById(id);
    }

    public Optional<Player> findById(int id) {
        return playerRepository.findById(id);
    }

}
