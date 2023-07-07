package com.example.championship.controller;

import com.example.championship.model.Team;
import com.example.championship.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/team")
public class TeamController {
    @Autowired
    private TeamService teamService;

    @GetMapping(value = "/all")
    public List<Team> getAllTeams() {
        return teamService.getAllTeams();
    }

    @PostMapping(value = "/new")
    public Team createNewTeam(@RequestBody Team newTeam) {
        return teamService.save(newTeam);
    }

    @DeleteMapping(value = "/delete/{id}")
    public void deleteTeam(@PathVariable int id) {
        teamService.deleteById(id);
    }

    @PutMapping("/update/{id}")
    public Team replaceTeam(@RequestBody Team newTeam, @PathVariable int id) {
        return teamService.findById(id)
                .map(team -> {
                    team.setName(newTeam.getName());
                    team.setPlayers(newTeam.getPlayers());
                    team.setGamesTeam1(newTeam.getGamesTeam1());
                    team.setGamesTeam2(newTeam.getGamesTeam2());
                    return teamService.save(team);
                })
                .orElseGet(() -> {
                    newTeam.setId(id);
                    return teamService.save(newTeam);
                });
    }
}
