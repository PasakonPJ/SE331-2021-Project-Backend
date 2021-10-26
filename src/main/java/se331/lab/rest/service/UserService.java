package se331.lab.rest.service;

import org.springframework.data.domain.Page;
import se331.lab.rest.security.entity.User;

import java.util.Optional;

public interface UserService {
    Optional<User> findById(Long id);

    User save(User newUser);

    Page<User> getWaitUsers(Integer perPage, Integer page);
}
