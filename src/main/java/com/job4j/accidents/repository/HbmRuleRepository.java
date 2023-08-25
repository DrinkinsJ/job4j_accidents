package com.job4j.accidents.repository;

import com.job4j.accidents.model.Rule;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
@AllArgsConstructor
@Primary
public class HbmRuleRepository implements RuleRepository {

    private final CrudRepository crudRepository;

    @Override
    public Rule create(Rule rule) {
        crudRepository.run(session -> session.save(rule));
        return rule;
    }

    @Override
    public boolean update(Rule rule) {
        boolean isUpdated = false;
        try {
            crudRepository.run(session -> session.merge(rule));
            isUpdated = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isUpdated;
    }

    @Override
    public boolean deleteById(int id) {
        boolean isDeleted = false;
        try {
            crudRepository.run("DELETE FROM Rule WHERE id = :id", Map.of("id", id));
            isDeleted = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isDeleted;
    }

    @Override
    public Optional<Rule> findById(int id) {
        Optional<Rule> ruleOptional = Optional.empty();
        try {
            ruleOptional = crudRepository.optional(
                    "FROM Rule WHERE id = :id", Rule.class,
                    Map.of("id", id)
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ruleOptional;
    }

    @Override
    public Set<Rule> findByIds(int[] ids) {

        List<Integer> idList = Arrays.asList(Arrays.stream(ids)
                .boxed()
                .toArray(Integer[]::new));

        try {
            return new HashSet<>(crudRepository.query(
                    "SELECT r FROM Rule r WHERE r.id IN :idList",
                    Rule.class,
                    Map.of("idList", idList)
            ));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Collections.emptySet();
    }

    @Override
    public Collection<Rule> findAll() {
        Collection<Rule> rules = new ArrayList<>();
        try {
            rules = crudRepository.query("FROM Rule", Rule.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rules;
    }
}
