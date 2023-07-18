package com.example.championship.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

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
    @Column(name = "location")
    private String location;
    @Column(name = "coach")
    private String coach;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "team")
    @JsonIgnore
    private List<Player> players;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "teamHome")
    private List<Game> gamesTeamHome;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "teamAway")
    private List<Game> gamesTeamAway;
    @Column(name = "totalScore")
    private @NotNull Integer totalScore = 0;
    @Column(name = "totalScoreHome")
    private @NotNull Integer totalScoreHome = 0;
    @Column(name = "totalScoreAway")
    private @NotNull Integer totalScoreAway = 0;
}
