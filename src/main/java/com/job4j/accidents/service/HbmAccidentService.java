package com.job4j.accidents.service;

import com.job4j.accidents.model.Accident;
import com.job4j.accidents.repository.HbmAccidentRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
@AllArgsConstructor
@Primary
public class HbmAccidentService implements AccidentService {

    private final HbmAccidentRepository repository;

    @Override
    public Accident create(Accident accident) {
        return repository.create(accident);
    }

    @Override
    public boolean update(Accident accident) {
        return repository.update(accident);
    }

    @Override
    public boolean deleteById(int id) {
        return repository.deleteById(id);
    }

    @Override
    public Optional<Accident> findById(int id) {
        return repository.findById(id);
    }

    @Override
    public Collection<Accident> findAll() {
        return repository.findAll();
    }
}
