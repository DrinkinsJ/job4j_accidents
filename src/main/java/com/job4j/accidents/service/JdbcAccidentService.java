package com.job4j.accidents.service;

import com.job4j.accidents.model.Accident;
import com.job4j.accidents.repository.JdbcAccidentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class JdbcAccidentService implements AccidentService {

    private final JdbcAccidentRepository repository;

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
    public List<Accident> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Accident> findById(int id) {
        return repository.findById(id);
    }
}
