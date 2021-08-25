package ru.russeva.web.service;

import ru.russeva.web.model.User;

import java.util.List;

public interface UserService {
    void add(User user);

    void edit(User user);

    void delete(long id);

    User getUserById(long id);

    List<User> listUsers();
}
