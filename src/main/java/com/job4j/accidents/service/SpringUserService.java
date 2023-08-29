package com.job4j.accidents.service;


import com.job4j.accidents.model.User;
import com.job4j.accidents.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class SpringUserService  implements UserService {

    private final UserRepository repository;

    @Override
    public Optional<User> save(User user) {
        return Optional.of(repository.save(user));
    }
}
