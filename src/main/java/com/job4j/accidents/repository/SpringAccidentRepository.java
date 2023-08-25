package com.job4j.accidents.repository;

import com.job4j.accidents.model.Accident;
import org.springframework.data.repository.CrudRepository;

public interface SpringAccidentRepository extends CrudRepository<Accident, Integer>{
}
