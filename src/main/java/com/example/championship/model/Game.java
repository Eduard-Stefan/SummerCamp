package com.example.championship.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "game_type")
    private String gameType;
    @Column(name = "location")
    private String location;
    @Column(name = "date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date date;
    @Column(name = "score_home")
    private int scoreHome;
    @Column(name = "score_away")
    private int scoreAway;
    @ManyToOne
    @JoinColumn(name = "team_home_id")
    private Team teamHome;
    @ManyToOne
    @JoinColumn(name = "team_away_id")
    private Team teamAway;
}
