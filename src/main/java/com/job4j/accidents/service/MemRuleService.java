package com.job4j.accidents.service;

import com.job4j.accidents.model.Rule;
import com.job4j.accidents.repository.MemRuleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class MemRuleService implements RuleService {

    private final MemRuleRepository repository;

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
    public Set<Rule> findByIds(int[] ids) {
        return repository.findByIds(ids);
    }

    @Override
    public Collection<Rule> findAll() {
        return repository.findAll();
    }
}
