package com.example.championship.repository;

import com.example.championship.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Integer> {
    List<Player> findAllByName(String name);

    List<Player> findAllByAge(Integer age);

    List<Player> findAllByNationality(String nationality);
}
