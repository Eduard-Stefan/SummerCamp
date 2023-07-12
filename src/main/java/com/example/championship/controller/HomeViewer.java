package com.example.championship.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeViewer {

    @GetMapping("/home")
    public ModelAndView home() {
        ModelAndView mav = new ModelAndView("index");
        return mav;
    }
}
