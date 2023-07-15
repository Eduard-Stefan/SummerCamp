package com.example.championship.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
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
    @NotBlank(message = "The name is required.")
    private String name;
    @Column(name = "location")
    @NotBlank(message = "The location is required.")
    private String location;
    @Column(name = "coach")
    @NotBlank(message = "The coach is required.")
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
    private Integer totalScore = 0;
    @Column(name = "totalScoreHome")
    private Integer totalScoreHome = 0;
    @Column(name = "totalScoreAway")
    private Integer totalScoreAway = 0;
}
