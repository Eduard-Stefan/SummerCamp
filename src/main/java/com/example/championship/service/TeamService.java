package com.example.championship.service;

import com.example.championship.model.Team;
import com.example.championship.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeamService {
    @Autowired
    private TeamRepository teamRepository;

    public List<Team> getAllTeams() {
        return teamRepository.findAll();
    }

    public Team save(Team newTeam) {
        return teamRepository.save(newTeam);
    }

    public void deleteById(int id) {
        teamRepository.deleteById(id);
    }

    public Optional<Team> findById(int id) {
        return teamRepository.findById(id);
    }

}
