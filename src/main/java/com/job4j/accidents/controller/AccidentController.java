package com.job4j.accidents.controller;

import com.job4j.accidents.model.Accident;
import com.job4j.accidents.service.JdbcAccidentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
public class AccidentController {
    private final JdbcAccidentService jdbcAccidentService;

    @GetMapping("/createAccident")
    public String viewCreateAccident(Model model) {
        model.addAttribute("accidentTypes", jdbcAccidentService.findTypes());
        model.addAttribute("rules", jdbcAccidentService.findRules());
        return "createAccident";
    }

    @PostMapping("/saveAccident")
    public String save(@ModelAttribute Accident accident, @RequestParam("rIds") int[] ids) {
        jdbcAccidentService.create(accident, ids);
        return "redirect:/index";
    }

    @GetMapping("/editAccident/{id}")
    public String viewEditAccident(@PathVariable int id, Model model) {
        var optionalAccident = jdbcAccidentService.findById(id);
        if (optionalAccident.isPresent()) {
            model.addAttribute("rules", jdbcAccidentService.findRules());
            model.addAttribute("accidentTypes", jdbcAccidentService.findTypes());
            model.addAttribute("accident", optionalAccident.get());
            return "editAccident";
        }
        model.addAttribute("message", "Accident not found");
        return "404";
    }

    @PostMapping("/editAccident")
    public String update(@ModelAttribute Accident accident, @RequestParam("rIds") int[] ids) {
        jdbcAccidentService.update(accident, ids);
        return "redirect:/";
    }
}