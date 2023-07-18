package com.example.championship.controller;

import com.example.championship.service.PlayerService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PlayersViewer {
    @Autowired
    private PlayerService playerService;

    @GetMapping("/players")
    public @NotNull ModelAndView players() {
        @NotNull ModelAndView mav = new ModelAndView("players");
        mav.addObject("players", playerService.getAllPlayers());
        return mav;
    }
}
