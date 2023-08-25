package com.job4j.accidents.repository;

import com.job4j.accidents.model.Rule;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
@AllArgsConstructor
public class JdbcRuleRepository implements RuleRepository {
    private static final String SELECT_ALL_RULE_QUERY = "SELECT id, name FROM Rule";

    private static final String SELECT_BY_ID_RULE_QUERY = "SELECT id, name FROM RULE WHERE id = ?";

    private final JdbcTemplate jdbc;

    @Override
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
        Rule rule = jdbc.queryForObject(SELECT_BY_ID_RULE_QUERY, new Object[]{id}, (rs, rowNum) -> {
            int ruleId = rs.getInt("id");
            String ruleName = rs.getString("name");
            return new Rule(ruleId, ruleName);
        });
        return Optional.ofNullable(rule);
    }

    @Override
    public Set<Rule> findByIds(int[] ids) {
        Set<Rule> rules = new HashSet<>();
        for (int i : ids) {
            findById(i).ifPresent(rules::add);
        }
        return rules;
    }
}
