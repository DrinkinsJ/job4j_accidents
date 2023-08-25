package com.job4j.accidents.service;

import com.job4j.accidents.model.Accident;
import com.job4j.accidents.model.AccidentType;
import com.job4j.accidents.model.Rule;
import com.job4j.accidents.repository.SpringAccidentTypeRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Primary
public class SpringAccidentTypeService implements AccidentTypeService {

    private final SpringAccidentTypeRepository repository;

    @Override
    public AccidentType create(AccidentType accidentType) {
        repository.save(accidentType);
        return accidentType;
    }

    @Override
    public boolean update(AccidentType accidentType) {
        boolean isUpdated = false;
        var optionalType = repository.findById(accidentType.getId());
        if (optionalType.isPresent()) {
            repository.save(accidentType);
            isUpdated = true;
        }
        return isUpdated;
    }

    @Override
    public boolean deleteById(int id) {
        repository.deleteById(id);
        return !repository.existsById(id);
    }

    @Override
    public Optional<AccidentType> findById(int id) {
        return repository.findById(id);
    }

    @Override
    public Collection<AccidentType> findAll() {
        var iterTypes = repository.findAll();
        List<AccidentType> types = new ArrayList<>();
        iterTypes.forEach(types::add);
        return types;
    }
}
