package com.job4j.accidents.service;

import com.job4j.accidents.model.AccidentType;

import java.util.Collection;
import java.util.Optional;

public interface AccidentTypeService {
    AccidentType create(AccidentType accidentType);
    boolean update(AccidentType accidentType);
    boolean deleteById(int id);
    Optional<AccidentType> findById(int id);
    Collection<AccidentType> findAll();
}
