package com.job4j.accidents.repository;

import com.job4j.accidents.model.Rule;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;

public interface RuleRepository {
    Rule create(Rule rule);
    boolean update(Rule rule);
    boolean deleteById(int id);
    Optional<Rule> findById(int id);
    Set<Rule> findByIds(int[] ids);
    Collection<Rule> findAll();
}
