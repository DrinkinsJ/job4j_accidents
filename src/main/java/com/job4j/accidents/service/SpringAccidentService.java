package com.job4j.accidents.service;

import com.job4j.accidents.repository.SpringAccidentRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import com.job4j.accidents.model.Accident;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Primary
public class SpringAccidentService implements AccidentService {
    private final SpringAccidentRepository repository;

    public Accident create(Accident accident) {
        repository.save(accident);
        return accident;
    }

    @Override
    public boolean update(Accident accident) {
        boolean isUpdated = false;
        var optionalAccident = repository.findById(accident.getId());
        if (optionalAccident.isPresent()) {
            repository.save(accident);
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
    public Optional<Accident> findById(int id) {
        return repository.findById(id);
    }

    public List<Accident> findAll() {
        List<Accident> list = new ArrayList<>();
        Iterable<Accident> iterList = repository.findAll();
        iterList.forEach(list::add);
        return list;
    }
}