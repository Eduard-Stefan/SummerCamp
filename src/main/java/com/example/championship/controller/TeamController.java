package com.example.championship.controller;

import com.example.championship.model.Team;
import com.example.championship.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/teams")
public class TeamController {
    @Autowired
    private TeamService teamService;

    @GetMapping(value = "/all")
    public List<Team> getAllTeams() {
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
    public List<Team> sortTeamsByNameAsc() {
        return teamService.sortTeamsByNameAsc();
    }

    @GetMapping(value = "/sort/name/desc")
    public List<Team> sortTeamsByNameDesc() {
        return teamService.sortTeamsByNameDesc();
    }

    @GetMapping(value = "/sort/location/asc")
    public List<Team> sortTeamsByLocationAsc() {
        return teamService.sortTeamsByLocationAsc();
    }

    @GetMapping(value = "/sort/location/desc")
    public List<Team> sortTeamsByLocationDesc() {
        return teamService.sortTeamsByLocationDesc();
    }

    @GetMapping(value = "/sort/coach/asc")
    public List<Team> sortTeamsByCoachAsc() {
        return teamService.sortTeamsByCoachAsc();
    }

    @GetMapping(value = "/sort/coach/desc")
    public List<Team> sortTeamsByCoachDesc() {
        return teamService.sortTeamsByCoachDesc();
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
