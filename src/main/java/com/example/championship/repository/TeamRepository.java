package com.example.championship.repository;

import com.example.championship.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamRepository extends JpaRepository<Team, Integer> {
    List<Team> findAllByName(String name);

    List<Team> findAllByLocation(String location);

    List<Team> findAllByCoach(String coach);
}
