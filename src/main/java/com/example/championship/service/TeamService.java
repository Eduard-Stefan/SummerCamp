package com.example.championship.service;

import com.example.championship.model.Team;
import com.example.championship.repository.TeamRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeamService {
    @Autowired
    private TeamRepository teamRepository;

    public @NotNull List<Team> getAllTeams() {
        return teamRepository.findAll();
    }

    public List<Team> findTeamsByName(String name) {
        return teamRepository.findAllByName(name);
    }

    public List<Team> findTeamsByLocation(String location) {
        return teamRepository.findAllByLocation(location);
    }

    public List<Team> findTeamsByCoach(String coach) {
        return teamRepository.findAllByCoach(coach);
    }

    public @NotNull List<Team> sortTeamsByNameAsc() {
        return teamRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));
    }

    public @NotNull List<Team> sortTeamsByNameDesc() {
        return teamRepository.findAll(Sort.by(Sort.Direction.DESC, "name"));
    }

    public @NotNull List<Team> sortTeamsByLocationAsc() {
        return teamRepository.findAll(Sort.by(Sort.Direction.ASC, "location"));
    }

    public @NotNull List<Team> sortTeamsByLocationDesc() {
        return teamRepository.findAll(Sort.by(Sort.Direction.DESC, "location"));
    }

    public @NotNull List<Team> sortTeamsByCoachAsc() {
        return teamRepository.findAll(Sort.by(Sort.Direction.ASC, "coach"));
    }

    public @NotNull List<Team> sortTeamsByCoachDesc() {
        return teamRepository.findAll(Sort.by(Sort.Direction.DESC, "coach"));
    }

    public @NotNull Team save(@NotNull Team newTeam) {
        return teamRepository.save(newTeam);
    }

    public void deleteById(int id) {
        teamRepository.deleteById(id);
    }

    public @NotNull Optional<Team> findById(int id) {
        return teamRepository.findById(id);
    }
}
