package com.job4j.accidents.repository;

import com.job4j.accidents.model.Accident;
import com.job4j.accidents.model.AccidentType;
import com.job4j.accidents.model.Rule;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

@AllArgsConstructor
@Repository
public class JdbcAccidentRepository {

    private static final String INSERT_ACCIDENT_QUERY = "INSERT INTO accident (name, text, address, accident_type_id) VALUES (?, ?, ?, ?)";
    private static final String INSERT_RULE_QUERY = "INSERT INTO accident_rule (accident_id, rule_id) VALUES (?, ?)";
    private static final String DELETE_RULE_QUERY = "DELETE FROM accident_rule where accident_id = ?";

    private static final String DELETE_ACCIDENT_BY_ID_QUERY = "DELETE FROM accident WHERE id = ?";

    private static final String UPDATE_ACCIDENT_QUERY = """
            update accident set name = ?, text = ?,
             address = ?, accident_type_id = ? where id = ?
                            """;

    private static final String SELECT_ALL_ACCIDENT_QUERY = """
                   SELECT a.id,
                   a.name  AS a_name,
                   a.text,
                   a.address,
                   at.id   AS at_id,
                   at.name AS at_name,
                   r.id    AS r_id,
                   r.name  AS r_name
            FROM accident a
                     JOIN accident_type at ON a.accident_type_id = at.id
                     LEFT JOIN accident_rule ar ON a.id = ar.accident_id
                     LEFT JOIN rule r ON ar.rule_id = r.id
                            """;

    private static final String SELECT_ALL_RULE_QUERY = "SELECT id, name FROM Rule";

    private static final String SELECT_ALL_ACCIDENT_TYPE_QUERY = "SELECT id, name FROM accident_type";
    private static final String SELECT_ACCIDENT_RULE_QUERY = """
            SELECT r.id AS rule_id, r.name AS r_name
            FROM rule r
                     JOIN accident_rule ar ON r.id = ar.rule_id
            WHERE ar.accident_id = ?
            """;
    private static final String SELECT_ACCIDENT_BY_ID_QUERY = """
            SELECT a.id, a.name AS a_name, a.text, a.address, at.id AS at_id, at.name AS at_name
            FROM accident a
                     JOIN accident_type at ON a.accident_type_id = at.id
            WHERE a.id = ?
            """;

    private final JdbcTemplate jdbc;

    public Accident create(Accident accident, int[] ids) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbc.update(con -> {
            PreparedStatement preparedStatement = con.prepareStatement(INSERT_ACCIDENT_QUERY, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, accident.getName());
            preparedStatement.setString(2, accident.getText());
            preparedStatement.setString(3, accident.getAddress());
            preparedStatement.setInt(4, accident.getAccidentType().getId());
            return preparedStatement;
        }, keyHolder);
        var keys = keyHolder.getKeyList();
        int generatedId = (int) keys.get(0).get("id");
        accident.setId(generatedId);

        Arrays.stream(ids).forEach(i -> jdbc.update(INSERT_RULE_QUERY, accident.getId(), i));
        return accident;
    }

    public boolean update(Accident accident, int[] ids) {
        jdbc.update(DELETE_RULE_QUERY, accident.getId());
        boolean isUpdated = jdbc.update(UPDATE_ACCIDENT_QUERY,
                accident.getName(),
                accident.getText(),
                accident.getAddress(),
                accident.getAccidentType().getId(),
                accident.getId()) > 0;
        Arrays.stream(ids).forEach(i -> jdbc.update(INSERT_RULE_QUERY, accident.getId(), i));
        return isUpdated;
    }

    public boolean delete(int id) {
        jdbc.update(DELETE_RULE_QUERY, id);
        return jdbc.update(DELETE_ACCIDENT_BY_ID_QUERY, id) > 0;
    }


    public List<Accident> findAll() {
        Map<Integer, Accident> accidentsMap = new HashMap<>();
        jdbc.query(SELECT_ALL_ACCIDENT_QUERY, (resultSet) -> {
            while (resultSet.next()) {
                int accidentId = resultSet.getInt("id");
                Accident accident = accidentsMap.computeIfAbsent(accidentId, key -> {
                    Accident newAccident = new Accident();
                    try {
                        newAccident.setId(resultSet.getInt("id"));
                        newAccident.setName(resultSet.getString("a_name"));
                        newAccident.setText(resultSet.getString("text"));
                        newAccident.setAddress(resultSet.getString("address"));
                        AccidentType accidentType = new AccidentType();
                        accidentType.setId(resultSet.getInt("at_id"));
                        accidentType.setName(resultSet.getString("at_name"));
                        newAccident.setAccidentType(accidentType);
                        newAccident.setRules(new HashSet<>());
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    return newAccident;
                });
                int ruleId = resultSet.getInt("r_id");
                if (ruleId != 0) {
                    Rule rule = new Rule();
                    rule.setId(ruleId);
                    rule.setName(resultSet.getString("r_name"));
                    accident.getRules().add(rule);
                }
            }

        });
        return new ArrayList<>(accidentsMap.values());
    }

    public Optional<Accident> findById(int id) {
        List<Accident> accidents = jdbc.query(SELECT_ACCIDENT_BY_ID_QUERY, ps -> ps.setInt(1, id),
                (rs, rowNum) -> {
                    Accident accident = new Accident();
                    accident.setId(rs.getInt("id"));
                    accident.setName(rs.getString("a_name"));
                    accident.setText(rs.getString("text"));
                    accident.setAddress(rs.getString("address"));
                    AccidentType type = new AccidentType();
                    type.setId(rs.getInt("at_id"));
                    type.setName(rs.getString("at_name"));
                    accident.setAccidentType(type);
                    return accident;
                });

        Accident accident = accidents.get(0);
        List<Rule> rules = jdbc.query(SELECT_ACCIDENT_RULE_QUERY, ps -> ps.setInt(1, accident.getId()),
                (rs, rowNum) -> {
                    var rule = new Rule();
                    rule.setId(rs.getInt("rule_id"));
                    rule.setName(rs.getString("r_name"));
                    return rule;
                });
        accident.setRules(new HashSet<>(rules));


        return Optional.of(accident);
    }

    public List<Rule> findRules() {
        List<Rule> rules = new ArrayList<>();
        jdbc.query(SELECT_ALL_RULE_QUERY, resultSet -> {
            do {
                Rule rule = new Rule();
                rule.setId(resultSet.getInt("id"));
                rule.setName(resultSet.getString("name"));
                rules.add(rule);
            } while (resultSet.next());
        });
        return rules;
    }

    public List<AccidentType> findTypes() {
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
