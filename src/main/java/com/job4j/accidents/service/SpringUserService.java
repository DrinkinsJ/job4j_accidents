package com.job4j.accidents.service;


import ch.qos.logback.classic.Logger;
import com.job4j.accidents.model.User;
import com.job4j.accidents.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class SpringUserService  implements UserService {

    private final UserRepository repository;

    @Override
    public Optional<User> save(User user) {
        try {
            return Optional.of(repository.save(user));
        } catch (Exception e) {
            log.error("try reg already exist user", e);
        }
        return Optional.empty();
    }
}
