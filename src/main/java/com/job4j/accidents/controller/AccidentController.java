package com.job4j.accidents.controller;

import com.job4j.accidents.model.Accident;
import com.job4j.accidents.service.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
public class AccidentController {
    private final JdbcAccidentService jdbcAccidentService;
    private final SimpleJdbcRuleService simpleJdbcRuleService;
    private final SimpleAccidentTypeService simpleAccidentTypeService;
    private final AccidentService accidentService;
    private final AccidentTypeService accidentTypeService;
    private final RuleService ruleService;

    @GetMapping("/createAccident")
    public String viewCreateAccident(Model model) {
        model.addAttribute("types", accidentTypeService.findAll());
        model.addAttribute("rules", ruleService.findAll());
        return "createAccident";
    }

    @PostMapping("/saveAccident")
    public String save(@ModelAttribute Accident accident, @RequestParam("rIds") int[] ids) {
        accidentService.create(accident, ids);
        return "redirect:/index";
    }

    @GetMapping("/editAccident/{id}")
    public String viewEditAccident(@PathVariable int id, Model model) {
        var optionalAccident = jdbcAccidentService.findById(id);
        if (optionalAccident.isPresent()) {
            model.addAttribute("rules", simpleJdbcRuleService.findAll());
            model.addAttribute("accidentTypes", simpleAccidentTypeService.findAll());
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