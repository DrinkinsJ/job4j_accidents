package com.job4j.accidents.repository;

import com.job4j.accidents.model.Accident;
import com.job4j.accidents.model.AccidentType;
import com.job4j.accidents.model.Rule;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class MemAccidentRepository implements AccidentRepository {

    private final AtomicInteger nextId = new AtomicInteger(0);
    private final Map<Integer, Accident> repository = new ConcurrentHashMap<>();

    public MemAccidentRepository() {

        Rule rule1 = new Rule(1, "Rule 1");
        Rule rule2 = new Rule(2, "Rule 2");
        AccidentType type = new AccidentType(1, "Type");

        create(Accident.builder()
                .id(1)
                .name("Name 1")
                .text("Text 1")
                .address("Address 1")
                .accidentType(type)
                .rules(Set.of(rule1, rule2))
                .build());

        create(Accident.builder()
                .id(1)
                .name("Name 2")
                .text("Text 2")
                .address("Address 2")
                .accidentType(type)
                .rules(Set.of(rule1, rule2))
                .build());

        create(Accident.builder()
                .id(1)
                .name("Name 3")
                .text("Text 3")
                .address("Address 3")
                .accidentType(type)
                .rules(Set.of(rule1, rule2))
                .build());
    }

    @Override
    public Accident create(Accident accident) {
        int id = nextId.incrementAndGet();
        accident.setId(id);
        return repository.put(id, accident);
    }

    @Override
    public boolean update(Accident accident) {
        return repository.replace(accident.getId(), accident) != null;
    }

    @Override
    public boolean deleteById(int id) {
        return repository.remove(id) != null;
    }

    @Override
    public Optional<Accident> findById(int id) {
        return Optional.of(repository.get(id));
    }

    @Override
    public Collection<Accident> findAll() {
        return repository.values();
    }
}
