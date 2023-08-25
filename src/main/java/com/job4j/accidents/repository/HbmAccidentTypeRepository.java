package com.job4j.accidents.repository;

import com.job4j.accidents.model.AccidentType;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
@Primary
public class HbmAccidentTypeRepository implements AccidentTypeRepository {

    private final CrudRepository crudRepository;

    @Override
    public AccidentType create(AccidentType accidentType) {
        crudRepository.run(session -> session.save(accidentType));
        return accidentType;
    }

    @Override
    public boolean update(AccidentType accidentType) {
        boolean isUpdated = false;
        try {
            crudRepository.run(session -> session.merge(accidentType));
            isUpdated = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isUpdated;
    }

    @Override
    public boolean deleteById(int id) {
        boolean isDeleted = false;
        try {
            crudRepository.run("DELETE FROM AccidentType WHERE id = :id", Map.of("id", id));
            isDeleted = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isDeleted;
    }

    @Override
    public Optional<AccidentType> findById(int id) {
        Optional<AccidentType> accidentTypeOptional = Optional.empty();
        try {
            accidentTypeOptional = crudRepository.optional(
                    "FROM AccidentType WHERE id = :id", AccidentType.class,
                    Map.of("id", id)
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
        return accidentTypeOptional;
    }

    @Override
    public Collection<AccidentType> findAll() {
        Collection<AccidentType> types = new ArrayList<>();
        try {
            types = crudRepository.query("FROM AccidentType", AccidentType.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return types;
    }
}
