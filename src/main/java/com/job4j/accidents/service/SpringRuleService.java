package com.job4j.accidents.service;

import com.job4j.accidents.model.Rule;
import com.job4j.accidents.repository.SpringRuleRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.*;

@AllArgsConstructor
@Service
@Primary
public class SpringRuleService implements RuleService {

    private final SpringRuleRepository repository;

    @Override
    public Rule create(Rule rule) {
        repository.save(rule);
        return rule;
    }

    @Override
    public boolean update(Rule rule) {
        boolean isUpdated = false;
        var optionalRule = repository.findById(rule.getId());
        if (optionalRule.isPresent()) {
            repository.save(rule);
            isUpdated = true;
        }
        return isUpdated;
    }

    @Override
    public boolean deleteById(int id) {
        repository.deleteById(id);
        return !repository.existsById(id);
    }

    @Override
    public Optional<Rule> findById(int id) {
        return repository.findById(id);
    }

    @Override
    public Set<Rule> findByIds(int[] ids) {
        var IterRules = repository.findAllById(Arrays.asList(Arrays.stream(ids)
                .boxed()
                .toArray(Integer[]::new)));
        Set<Rule> rules = new HashSet<>();
        IterRules.forEach(rules::add);
        return rules;
    }

    @Override
    public Collection<Rule> findAll() {
        var iterRules = repository.findAll();
        List<Rule> rules = new ArrayList<>();
        iterRules.forEach(rules::add);
        return rules;
    }
}
