package com.job4j.accidents.repository;

import com.job4j.accidents.model.Accident;
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
public class HbmAccidentRepository implements AccidentRepository {

    private final CrudRepository crudRepository;

    @Override
    public Accident create(Accident accident) {
        crudRepository.run(session -> session.save(accident));
        return accident;
    }

    @Override
    public boolean update(Accident accident) {
        boolean isUpdated = false;
        try {
            crudRepository.run(session -> session.merge(accident));
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
            crudRepository.run(
                    "DELETE FROM Accident WHERE id = :id", Map.of("id", id)
            );
            isDeleted = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isDeleted;
    }

    @Override
    public Optional<Accident> findById(int id) {
        Optional<Accident> accidentOptional = Optional.empty();
        try {
            accidentOptional = crudRepository.optional(
                    "SELECT a FROM Accident a LEFT JOIN FETCH a.type WHERE a.id = :id", Accident.class,
                    Map.of("id", id)
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
        return accidentOptional;
    }

    @Override
    public Collection<Accident> findAll() {
        Collection<Accident> accidents = new ArrayList<>();
        try {
            accidents = crudRepository.query("SELECT a FROM Accident a LEFT JOIN FETCH a.type", Accident.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return accidents;
    }
}
