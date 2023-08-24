package com.job4j.accidents.service;

import com.job4j.accidents.model.Accident;
import com.job4j.accidents.repository.JdbcAccidentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class SimpleJdbcAccidentService implements JdbcAccidentService {

    private final JdbcAccidentRepository repository;


    @Override
    public Accident create(Accident accident, int[] ids) {
        return repository.create(accident, ids);
    }

    @Override
    public boolean update(Accident accident, int[] ids) {
       return repository.update(accident, ids);
    }

    @Override
    public boolean delete(int id) {
        return repository.delete(id);
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
