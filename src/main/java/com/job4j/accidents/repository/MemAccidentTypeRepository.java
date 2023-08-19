package com.job4j.accidents.repository;

import com.job4j.accidents.model.AccidentType;
import org.springframework.stereotype.Controller;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Controller
public class MemAccidentTypeRepository implements AccidentTypeRepository {

    private final AtomicInteger nextId = new AtomicInteger(0);
    private final Map<Integer, AccidentType> repository = new ConcurrentHashMap<>();


    public MemAccidentTypeRepository() {
        create(new AccidentType(1, "Two cars"));
        create(new AccidentType(1, "Machine and man"));
        create(new AccidentType(1, "Car and bicycle"));
    }

    @Override
    public AccidentType create(AccidentType accidentType) {
        int id = nextId.incrementAndGet();
        accidentType.setId(id);
        return repository.put(id, accidentType);
    }

    @Override
    public boolean update(AccidentType accidentType) {
        return repository.replace(accidentType.getId(), accidentType) != null;
    }

    @Override
    public boolean deleteById(int id) {
        return repository.remove(id) != null;
    }

    @Override
    public Optional<AccidentType> findById(int id) {
        return Optional.of(repository.get(id));
    }

    @Override
    public Collection<AccidentType> findAll() {
        return repository.values();
    }
}
