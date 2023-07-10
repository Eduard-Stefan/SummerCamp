package com.example.championship.repository;

import com.example.championship.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Integer> {
    List<Player> findByOrderByNameAsc();
    List<Player> findByOrderByNameDesc();
    List<Player> findByOrderByAgeAsc();
    List<Player> findByOrderByAgeDesc();
    List<Player> findByOrderByNationalityAsc();
    List<Player> findByOrderByNationalityDesc();

}
