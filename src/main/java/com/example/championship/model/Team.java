package com.example.championship.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@Entity
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    private String name;
    @OneToMany(mappedBy = "team")
    private List<Player> players;
    @OneToMany(mappedBy = "team1")
    private List<Game> gamesTeam1;
    @OneToMany(mappedBy = "team2")
    private List<Game> gamesTeam2;

    /*
    public Team(String name, List<Player> players, List<Game> gamesTeam1, List<Game> gamesTeam2) {
        this.name = name;
        this.players = players;
        this.gamesTeam1 = gamesTeam1;
        this.gamesTeam2 = gamesTeam2;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public List<Game> getGamesTeam1() {
        return gamesTeam1;
    }

    public void setGamesTeam1(List<Game> gamesTeam1) {
        this.gamesTeam1 = gamesTeam1;
    }

    public List<Game> getGamesTeam2() {
        return gamesTeam2;
    }

    public void setGamesTeam2(List<Game> gamesTeam2) {
        this.gamesTeam2 = gamesTeam2;
    }
    */
}
