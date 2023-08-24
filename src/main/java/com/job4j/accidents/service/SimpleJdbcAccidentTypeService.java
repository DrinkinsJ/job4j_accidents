package com.job4j.accidents.service;

import com.job4j.accidents.model.AccidentType;
import com.job4j.accidents.repository.JdbcAccidentTypeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@AllArgsConstructor
public class SimpleJdbcAccidentTypeService {

    private final JdbcAccidentTypeRepository repository;

    public Collection<AccidentType> findAll() {
        return repository.findAll();
    }
}
