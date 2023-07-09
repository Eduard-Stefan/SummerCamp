package com.example.championship.controller;

import com.example.championship.model.Team;
import com.example.championship.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/team")
public class TeamController {
    @Autowired
    private TeamService teamService;

    @GetMapping(value = "/all")
    public List<Team> getAllTeams(
            @RequestParam(required = false) String sortBy,
            @RequestParam(required = false) String sortOrder,
            @RequestParam(required = false) String filterValue) {

        List<Team> teams = teamService.getAllTeams();

        if (sortBy != null && sortOrder != null) {
            Comparator<Team> comparator = null;

            switch (sortBy.toLowerCase()) {
                case "name" -> comparator = Comparator.comparing(Team::getName, String.CASE_INSENSITIVE_ORDER);
                case "location" -> comparator = Comparator.comparing(Team::getLocation, String.CASE_INSENSITIVE_ORDER);
                case "coach" -> comparator = Comparator.comparing(Team::getCoach, String.CASE_INSENSITIVE_ORDER);
                default -> {
                }
            }

            if (comparator != null) {
                if (sortOrder.equalsIgnoreCase("desc")) {
                    comparator = comparator.reversed();
                }
                teams.sort(comparator);
            }
        }

        if (filterValue != null) {
            String filterValueLowerCase = filterValue.toLowerCase();
            teams = teams.stream()
                    .filter(team -> team.getName().toLowerCase().contains(filterValueLowerCase)
                            || team.getLocation().toLowerCase().contains(filterValueLowerCase)
                            || team.getCoach().toLowerCase().contains(filterValueLowerCase))
                    .collect(Collectors.toList());
        }

        return teams;
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
                    team.setLocation(newTeam.getLocation());
                    team.setCoach(newTeam.getCoach());
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
