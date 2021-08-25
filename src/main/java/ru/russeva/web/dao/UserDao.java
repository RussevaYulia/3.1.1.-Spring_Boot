package ru.russeva.web.dao;

import org.springframework.security.core.userdetails.UserDetails;
import ru.russeva.web.model.User;

import java.util.List;

public interface UserDao {
    void add(User user);

    void edit(User user);

    void delete(long id);

    User getUserById(long id);

    List<User> listUsers();

    UserDetails getUserByName(String name);
}
