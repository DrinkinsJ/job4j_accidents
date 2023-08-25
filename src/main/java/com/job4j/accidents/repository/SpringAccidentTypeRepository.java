package com.job4j.accidents.repository;

import com.job4j.accidents.model.AccidentType;
import org.springframework.data.repository.CrudRepository;

public interface SpringAccidentTypeRepository extends CrudRepository<AccidentType, Integer> {
}
