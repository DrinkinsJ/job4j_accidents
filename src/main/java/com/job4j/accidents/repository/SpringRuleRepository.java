package com.job4j.accidents.repository;

import com.job4j.accidents.model.Rule;
import org.springframework.data.repository.CrudRepository;

public interface SpringRuleRepository extends CrudRepository<Rule, Integer> {
}
