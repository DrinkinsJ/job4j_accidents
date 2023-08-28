package com.job4j.accidents.repository;

import com.job4j.accidents.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Integer> {
    Optional<User> findUserByUsername(String name);
}