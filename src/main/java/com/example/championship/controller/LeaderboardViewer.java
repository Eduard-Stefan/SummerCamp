package com.example.championship.controller;

import com.example.championship.service.TeamService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LeaderboardViewer {
    @Autowired
    private TeamService teamService;

    @GetMapping("/leaderboard")
    public @NotNull ModelAndView teams() {
        @NotNull ModelAndView mav = new ModelAndView("leaderboard");
        mav.addObject("leaderboard", teamService.getAllTeams());
        return mav;
    }
}
