package com.example.championship.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
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


}
