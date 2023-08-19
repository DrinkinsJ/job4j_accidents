package com.job4j.accidents.repository;

import com.job4j.accidents.model.Accident;
import com.job4j.accidents.model.AccidentType;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class MemAccidentRepository implements AccidentRepository {

    private final AtomicInteger nextId = new AtomicInteger(0);
    private final Map<Integer, Accident> repository = new ConcurrentHashMap<>();

    public MemAccidentRepository() {
        create(new Accident(1, "Name 1", "Text 1", "Address 1", new AccidentType(1, "Test")));
        create(new Accident(1, "Name 2", "Text 2", "Address 2", new AccidentType(1, "Test")));
        create(new Accident(1, "Name 2", "Text 3", "Address 3", new AccidentType(1, "Test")));
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
