package com.job4j.accidents.repository;

import com.job4j.accidents.model.Rule;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@AllArgsConstructor
public class JdbcRuleRepository {
    private static final String SELECT_ALL_RULE_QUERY = "SELECT id, name FROM Rule";

    private final JdbcTemplate jdbc;

    public List<Rule> findAll() {
        List<Rule> rules = new ArrayList<>();
        jdbc.query(SELECT_ALL_RULE_QUERY, resultSet -> {
            do {
                Rule rule = new Rule();
                rule.setId(resultSet.getInt("id"));
                rule.setName(resultSet.getString("name"));
                rules.add(rule);
            } while (resultSet.next());
        });
        return rules;
    }
}
