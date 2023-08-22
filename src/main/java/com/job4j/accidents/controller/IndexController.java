package com.job4j.accidents.controller;

import com.job4j.accidents.service.AccidentService;
import com.job4j.accidents.service.JdbcAccidentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class IndexController {

    private final JdbcAccidentService jdbcAccidentService;

    @GetMapping({"/", "/index"})
    public String getIndex(Model model) {
        model.addAttribute("user", "Petr Arsentev");
        model.addAttribute("accidents", jdbcAccidentService.findAll());
        return "index";
    }
}
