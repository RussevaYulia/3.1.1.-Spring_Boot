package ru.russeva.web.service;

import ru.russeva.web.model.Role;

import java.util.List;

public interface RoleService {
    List<Role> getAllRoles();
    Role getRoleById(long id);
}
