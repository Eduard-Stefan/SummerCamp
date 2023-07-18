package com.example.championship.controller;

import com.example.championship.service.TeamService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TeamsViewer {
    @Autowired
    private TeamService teamService;

    @GetMapping("/teams")
    public @NotNull ModelAndView teams() {
        @NotNull ModelAndView mav = new ModelAndView("teams");
        mav.addObject("teams", teamService.getAllTeams());
        return mav;
    }
}
