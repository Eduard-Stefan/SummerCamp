package com.example.championship.controller;

import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeViewer {

    @GetMapping("/home")
    public @NotNull ModelAndView home() {
        return new ModelAndView("index");
    }
}
