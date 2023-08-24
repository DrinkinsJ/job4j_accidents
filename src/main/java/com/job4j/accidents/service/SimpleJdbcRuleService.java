package com.job4j.accidents.service;

import com.job4j.accidents.model.Rule;
import com.job4j.accidents.repository.JdbcRuleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@AllArgsConstructor
public class SimpleJdbcRuleService {

    private final JdbcRuleRepository repository;

    public Collection<Rule> findAll() {
        return repository.findAll();
    }
}
