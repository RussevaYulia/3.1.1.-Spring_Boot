package ru.russeva.web.dao;

import ru.russeva.web.model.Role;

import java.util.List;

public interface RoleDao {
    List<Role> getAllRoles();

    Role getRole(long id);
}
