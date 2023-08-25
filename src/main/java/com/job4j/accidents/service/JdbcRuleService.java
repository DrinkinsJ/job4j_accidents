package com.job4j.accidents.service;

import com.job4j.accidents.model.Rule;
import com.job4j.accidents.repository.JdbcRuleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class JdbcRuleService implements RuleService {

    private final JdbcRuleRepository repository;

    @Override
    public Rule create(Rule rule) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean update(Rule rule) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean deleteById(int id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<Rule> findById(int id) {
        return repository.findById(id);
    }

    @Override
    public Set<Rule> findByIds(int[] ids) {
        return repository.findByIds(ids);
    }

    public Collection<Rule> findAll() {
        return repository.findAll();
    }
}
