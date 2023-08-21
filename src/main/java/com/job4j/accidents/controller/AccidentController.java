package com.job4j.accidents.controller;

import com.job4j.accidents.model.Accident;
import com.job4j.accidents.service.AccidentService;
import com.job4j.accidents.service.AccidentTypeService;
import com.job4j.accidents.service.RuleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
public class AccidentController {
    private final AccidentService accidentService;
    private final AccidentTypeService accidentTypeService;
    private final RuleService ruleService;

    @GetMapping("/createAccident")
    public String viewCreateAccident(Model model) {
        model.addAttribute("accidentTypes", accidentTypeService.findAll());
        model.addAttribute("rules", ruleService.findAll());
        return "createAccident";
    }

    @PostMapping("/saveAccident")
    public String save(@ModelAttribute Accident accident, @RequestParam("rIds") int[] ids) {
        int typeId = accident.getAccidentType().getId();
        var optionalAccidentType = accidentTypeService.findById(typeId);
        if (optionalAccidentType.isPresent()) {
            accident.setRules(ruleService.findByIds(ids));
            accident.setAccidentType(optionalAccidentType.get());
            accidentService.create(accident);
        }
        return "redirect:/index";
    }

    @GetMapping("/editAccident/{id}")
    public String viewEditAccident(@PathVariable int id, Model model) {
        var optionalAccident = accidentService.findById(id);
        if (optionalAccident.isPresent()) {
            model.addAttribute("rules", ruleService.findAll());
            model.addAttribute("accidentTypes", accidentTypeService.findAll());
            model.addAttribute("accident", optionalAccident.get());
            return "editAccident";
        }
        model.addAttribute("message", "Accident not found");
        return "404";
    }

    @PostMapping("/editAccident")
    public String update(@ModelAttribute Accident accident, @RequestParam("rIds") int[] ids) {
        int typeId = accident.getAccidentType().getId();
        accident.setAccidentType(accidentTypeService.findById(typeId).orElseThrow());
        accident.setRules(ruleService.findByIds(ids));
        accidentService.update(accident);
        return "redirect:/";
    }
}