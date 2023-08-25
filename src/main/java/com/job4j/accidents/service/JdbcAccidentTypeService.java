package com.job4j.accidents.service;

import com.job4j.accidents.model.AccidentType;
import com.job4j.accidents.repository.JdbcAccidentTypeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
@AllArgsConstructor
public class JdbcAccidentTypeService implements AccidentTypeService {

    private final JdbcAccidentTypeRepository repository;

    @Override
    public AccidentType create(AccidentType accidentType) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean update(AccidentType accidentType) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean deleteById(int id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<AccidentType> findById(int id) {
        return repository.findById(id);
    }

    public Collection<AccidentType> findAll() {
        return repository.findAll();
    }
}
