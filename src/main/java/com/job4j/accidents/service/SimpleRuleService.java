package com.job4j.accidents.service;

import com.job4j.accidents.model.Rule;
import com.job4j.accidents.repository.RuleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SimpleRuleService implements RuleService {

    private final RuleRepository repository;

    @Override
    public Rule create(Rule rule) {
        return repository.create(rule);
    }

    @Override
    public boolean update(Rule rule) {
        return repository.update(rule);
    }

    @Override
    public boolean deleteById(int id) {
        return repository.deleteById(id);
    }

    @Override
    public Optional<Rule> findById(int id) {
        return repository.findById(id);
    }

    @Override
    public Collection<Rule> findAll() {
        return repository.findAll();
    }
}
