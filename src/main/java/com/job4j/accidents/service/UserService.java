package com.job4j.accidents.service;

import com.job4j.accidents.model.User;

import java.util.Optional;

public interface UserService {
    Optional<User> save(User user);
}
