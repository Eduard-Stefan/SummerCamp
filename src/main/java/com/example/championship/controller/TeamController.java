package com.example.championship.controller;

import com.example.championship.model.Team;
import com.example.championship.service.TeamService;
import jakarta.validation.Valid;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/teams")
public class TeamController {
    @Autowired
    private TeamService teamService;

    @GetMapping(value = "/all")
    public @NotNull List<Team> getAllTeams() {
        return teamService.getAllTeams();
    }

    @GetMapping(value = "/name/{name}")
    public List<Team> findTeamsByName(@PathVariable(value = "name") String name) {
        return teamService.findTeamsByName(name);
    }

    @GetMapping(value = "/location/{location}")
    public List<Team> findTeamsByLocation(@PathVariable(value = "location") String location) {
        return teamService.findTeamsByLocation(location);
    }

    @GetMapping(value = "/coach/{coach}")
    public List<Team> findTeamsByCoach(@PathVariable(value = "coach") String coach) {
        return teamService.findTeamsByCoach(coach);
    }

    @GetMapping(value = "/sort/name/asc")
    public @NotNull List<Team> sortTeamsByNameAsc() {
        return teamService.sortTeamsByNameAsc();
    }

    @GetMapping(value = "/sort/name/desc")
    public @NotNull List<Team> sortTeamsByNameDesc() {
        return teamService.sortTeamsByNameDesc();
    }

    @GetMapping(value = "/sort/location/asc")
    public @NotNull List<Team> sortTeamsByLocationAsc() {
        return teamService.sortTeamsByLocationAsc();
    }

    @GetMapping(value = "/sort/location/desc")
    public @NotNull List<Team> sortTeamsByLocationDesc() {
        return teamService.sortTeamsByLocationDesc();
    }

    @GetMapping(value = "/sort/coach/asc")
    public @NotNull List<Team> sortTeamsByCoachAsc() {
        return teamService.sortTeamsByCoachAsc();
    }

    @GetMapping(value = "/sort/coach/desc")
    public @NotNull List<Team> sortTeamsByCoachDesc() {
        return teamService.sortTeamsByCoachDesc();
    }

    @PostMapping(value = "/new")
    public @NotNull Team createNewTeam(@Valid @RequestBody @NotNull Team newTeam) {
        return teamService.save(newTeam);
    }

    @DeleteMapping(value = "/delete/{id}")
    public void deleteTeam(@PathVariable int id) {
        teamService.deleteById(id);
    }

    @PutMapping("/update/{id}")
    public @NotNull Team replaceTeam(@RequestBody @NotNull Team newTeam, @PathVariable int id) {
        return teamService.findById(id)
                .map(team -> {
                    team.setName(newTeam.getName());
                    team.setLocation(newTeam.getLocation());
                    team.setCoach(newTeam.getCoach());
                    team.setPlayers(newTeam.getPlayers());
                    team.setGamesTeamHome(newTeam.getGamesTeamHome());
                    team.setGamesTeamAway(newTeam.getGamesTeamAway());
                    return teamService.save(team);
                })
                .orElseGet(() -> {
                    newTeam.setId(id);
                    return teamService.save(newTeam);
                });
    }
}
