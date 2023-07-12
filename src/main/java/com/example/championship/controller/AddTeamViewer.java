package com.example.championship.controller;

import com.example.championship.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AddTeamViewer {
    @Autowired
    private TeamService teamService;

    @GetMapping("/add-team")
    public ModelAndView teams() {
        ModelAndView mav = new ModelAndView("add-team");
        mav.addObject("add-team", teamService.getAllTeams());
        return mav;
    }
}
