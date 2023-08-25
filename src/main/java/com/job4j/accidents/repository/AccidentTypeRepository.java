package com.job4j.accidents.repository;

import com.job4j.accidents.model.AccidentType;

import java.util.Collection;
import java.util.Optional;

public interface AccidentTypeRepository {
    AccidentType create(AccidentType accidentType);
    boolean update(AccidentType accidentType);
    boolean deleteById(int id);
    Optional<AccidentType> findById(int id);
    Collection<AccidentType> findAll();
}
