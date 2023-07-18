package com.example.championship.service;

import com.example.championship.model.Player;
import com.example.championship.repository.PlayerRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlayerService {
    @Autowired
    private PlayerRepository playerRepository;

    public @NotNull List<Player> getAllPlayers() {
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

    public @NotNull List<Player> sortPlayersByNameAsc() {
        return playerRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));
    }

    public @NotNull List<Player> sortPlayersByNameDesc() {
        return playerRepository.findAll(Sort.by(Sort.Direction.DESC, "name"));
    }

    public @NotNull List<Player> sortPlayersByAgeAsc() {
        return playerRepository.findAll(Sort.by(Sort.Direction.ASC, "age"));
    }

    public @NotNull List<Player> sortPlayersByAgeDesc() {
        return playerRepository.findAll(Sort.by(Sort.Direction.DESC, "age"));
    }

    public @NotNull List<Player> sortPlayersByNationalityAsc() {
        return playerRepository.findAll(Sort.by(Sort.Direction.ASC, "nationality"));
    }

    public @NotNull List<Player> sortPlayersByNationalityDesc() {
        return playerRepository.findAll(Sort.by(Sort.Direction.DESC, "nationality"));
    }

    public @NotNull Player save(@NotNull Player newPlayer) {
        return playerRepository.save(newPlayer);
    }

    public void deleteById(int id) {
        playerRepository.deleteById(id);
    }

    public @NotNull Optional<Player> findById(int id) {
        return playerRepository.findById(id);
    }
}
