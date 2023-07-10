package com.example.championship.service;

import com.example.championship.model.Player;
import com.example.championship.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
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

    public List<Player> findPlayersByName(String name) {
        return playerRepository.findAllByName(name);
    }

    public List<Player> findPlayersByAge(Integer age) {
        return playerRepository.findAllByAge(age);
    }

    public List<Player> findPlayersByNationality(String nationality) {
        return playerRepository.findAllByNationality(nationality);
    }

    public List<Player> sortPlayersByNameAsc() {
        return playerRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));
    }

    public List<Player> sortPlayersByNameDesc() {
        return playerRepository.findAll(Sort.by(Sort.Direction.DESC, "name"));
    }

    public List<Player> sortPlayersByAgeAsc() {
        return playerRepository.findAll(Sort.by(Sort.Direction.ASC, "age"));
    }

    public List<Player> sortPlayersByAgeDesc() {
        return playerRepository.findAll(Sort.by(Sort.Direction.DESC, "age"));
    }

    public List<Player> sortPlayersByNationalityAsc() {
        return playerRepository.findAll(Sort.by(Sort.Direction.ASC, "nationality"));
    }

    public List<Player> sortPlayersByNationalityDesc() {
        return playerRepository.findAll(Sort.by(Sort.Direction.DESC, "nationality"));
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
