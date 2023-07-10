package com.example.championship.repository;

import com.example.championship.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface GameRepository extends JpaRepository<Game, Integer> {
    List<Game> findAllByGameType(String gameType);

    List<Game> findAllByLocation(String location);

    List<Game> findAllByDate(Date date);
}
