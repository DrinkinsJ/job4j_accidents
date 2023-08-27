package com.job4j.accidents.repository;

import com.job4j.accidents.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
}
