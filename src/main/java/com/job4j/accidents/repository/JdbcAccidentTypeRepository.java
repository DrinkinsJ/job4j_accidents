package com.job4j.accidents.repository;

import com.job4j.accidents.model.AccidentType;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Repository
public class JdbcAccidentTypeRepository implements AccidentTypeRepository {

    private static final String SELECT_ALL_ACCIDENT_TYPE_QUERY = "SELECT id, name FROM accident_type";

    private static final String SELECT_BY_ID_ACCIDENT_TYPE_QUERY = "SELECT id, name FROM accident_type WHERE id = ?";

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

    @Override
    public AccidentType create(AccidentType accidentType) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean update(AccidentType accidentType) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean deleteById(int id) {
        throw new UnsupportedOperationException();
    }

    public Optional<AccidentType> findById(int id) {
        AccidentType type = jdbc.queryForObject(SELECT_BY_ID_ACCIDENT_TYPE_QUERY, new Object[]{id}, (rs, rowNum) -> {
            int typeId = rs.getInt("id");
            String typeName = rs.getString("name");
            return new AccidentType(typeId, typeName);
        });
        return Optional.ofNullable(type);
    }
}
