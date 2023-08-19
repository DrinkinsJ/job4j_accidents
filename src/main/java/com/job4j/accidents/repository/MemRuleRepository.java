package com.job4j.accidents.repository;

import com.job4j.accidents.model.Rule;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class MemRuleRepository implements RuleRepository {

    private final Set<Rule> repository = new HashSet<>();

    private final AtomicInteger nextId = new AtomicInteger(0);

    public MemRuleRepository() {
        create(new Rule(1, "Rule 1"));
        create(new Rule(1, "Rule 2"));
        create(new Rule(1, "Rule 3"));
    }

    @Override
    public Rule create(Rule rule) {
        int id = nextId.incrementAndGet();
        rule.setId(id);
        repository.add(rule);
        return rule;
    }

    @Override
    public boolean update(Rule rule) {
        Optional<Rule> existingRule = findById(rule.getId());
        boolean isUpdated = false;
        if (existingRule.isPresent()) {
            repository.remove(existingRule.get());
            repository.add(rule);
            isUpdated = true;
        }
        return isUpdated;
    }

    @Override
    public boolean deleteById(int id) {
        boolean isDeleted = false;
        for (Rule rule : repository) {
            if (rule.getId() == id) {
                isDeleted = repository.remove(rule);
                break;
            }
        }
        return isDeleted;
    }

    @Override
    public Optional<Rule> findById(int id) {
        Optional<Rule> optionalRule = Optional.empty();
        for (Rule rule : repository) {
            if (rule.getId() == id) {
                optionalRule = Optional.of(rule);
                break;
            }
        }
        return optionalRule;
    }

    @Override
    public Collection<Rule> findAll() {
        return repository.stream().toList();
    }
}
