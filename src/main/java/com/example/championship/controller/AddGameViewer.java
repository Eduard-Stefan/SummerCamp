package com.example.championship.controller;

import com.example.championship.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AddGameViewer {
    @Autowired
    private GameService gameService;

    @GetMapping("/add-game")
    public ModelAndView games() {
        ModelAndView mav = new ModelAndView("add-game");
        mav.addObject("add-game", gameService.getAllGames());
        return mav;
    }
}
