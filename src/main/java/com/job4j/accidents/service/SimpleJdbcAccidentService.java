package com.job4j.accidents.service;

import com.job4j.accidents.model.Accident;
import com.job4j.accidents.model.AccidentType;
import com.job4j.accidents.model.Rule;
import com.job4j.accidents.repository.JdbcAccidentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Repository
public class SimpleJdbcAccidentService implements JdbcAccidentService {

    JdbcAccidentRepository repository;


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
    public List<AccidentType> findTypes() {
        return repository.findTypes();
    }

    @Override
    public List<Rule> findRules() {
        return repository.findRules();
    }

    @Override
    public Optional<Accident> findById(int id) {
        return repository.findById(id);
    }
}
