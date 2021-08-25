package ru.russeva.web.dao;

import org.springframework.stereotype.Repository;
import ru.russeva.web.model.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class RoleDaoImpl implements RoleDao{

    @PersistenceContext
    EntityManager em;

    @Override
    public List<Role> getAllRoles() {
        return em.createQuery("select role from Role role", Role.class)
                .getResultList();
    }

    @Override
    public Role getRole(long id) {
        return em.find(Role.class, id);
    }
}
