package com.example.championship.controller;

import com.example.championship.service.GameService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class GamesViewer {
    @Autowired
    private GameService gameService;

    @GetMapping("/games")
    public @NotNull ModelAndView games() {
        @NotNull ModelAndView mav = new ModelAndView("games");
        mav.addObject("games", gameService.getAllGames());
        return mav;
    }
}
