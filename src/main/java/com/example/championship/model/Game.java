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
    @Column(name = "date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date date;
    @Column(name = "location")
    private String location;
    @Column(name = "score1")
    private int score1;
    @Column(name = "score2")
    private int score2;
    @ManyToOne
    @JoinColumn(name = "team1_id")
    private Team team1;
    @ManyToOne
    @JoinColumn(name = "team2_id")
    private Team team2;

}
