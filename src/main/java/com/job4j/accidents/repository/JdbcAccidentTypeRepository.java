package com.job4j.accidents.repository;

import com.job4j.accidents.model.AccidentType;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Repository
public class JdbcAccidentTypeRepository {

    private static final String SELECT_ALL_ACCIDENT_TYPE_QUERY = "SELECT id, name FROM accident_type";

    private final JdbcTemplate jdbc;

    public List<AccidentType> findAll() {
        List<AccidentType> types = new ArrayList<>();
        jdbc.query(SELECT_ALL_ACCIDENT_TYPE_QUERY, resultSet -> {
            do {
                AccidentType type = new AccidentType();
                type.setId(resultSet.getInt("id"));
                type.setName(resultSet.getString("name"));
                types.add(type);
            } while (resultSet.next());
        });
        return types;
    }
}
