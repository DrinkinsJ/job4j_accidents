package com.job4j.accidents.service;

import com.job4j.accidents.model.AccidentType;
import com.job4j.accidents.repository.AccidentTypeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SimpleAccidentTypeService implements AccidentTypeService {

    private final AccidentTypeRepository repository;

    @Override
    public AccidentType create(AccidentType accidentType) {
        return repository.create(accidentType);
    }

    @Override
    public boolean update(AccidentType accidentType) {
        return repository.update(accidentType);
    }

    @Override
    public boolean deleteById(int id) {
        return repository.deleteById(id);
    }

    @Override
    public Optional<AccidentType> findById(int id) {
        return repository.findById(id);
    }

    @Override
    public Collection<AccidentType> findAll() {
        return repository.findAll();
    }
}
