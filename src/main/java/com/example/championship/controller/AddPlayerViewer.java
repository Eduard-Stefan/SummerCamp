package com.example.championship.controller;

import com.example.championship.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AddPlayerViewer {
    @Autowired
    private PlayerService playerService;

    @GetMapping("/add-player")
    public ModelAndView players() {
        ModelAndView mav = new ModelAndView("add-player");
        mav.addObject("add-player", playerService.getAllPlayers());
        return mav;
    }
}
