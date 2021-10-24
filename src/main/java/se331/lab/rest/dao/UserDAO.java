package se331.lab.rest.dao;

import se331.lab.rest.security.entity.User;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface UserDAO {
    Optional<User> findById(Long id);

    User save(User newUser);
}
