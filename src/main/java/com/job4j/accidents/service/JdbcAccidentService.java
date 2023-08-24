package com.job4j.accidents.service;

import com.job4j.accidents.model.Accident;
import com.job4j.accidents.model.AccidentType;
import com.job4j.accidents.model.Rule;

import java.util.List;
import java.util.Optional;

public interface JdbcAccidentService {

    Accident create(Accident accident, int[] ids);
    boolean update(Accident accident, int[] ids);
    boolean delete(int id);
    List<Accident> findAll();
    Optional<Accident> findById(int id);

}
