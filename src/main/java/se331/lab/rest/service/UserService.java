package se331.lab.rest.service;

import se331.lab.rest.security.entity.User;

import java.util.Optional;

public interface UserService {
    Optional<User> findById(Long id);

    User save(User newUser);
}
