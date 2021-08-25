package ru.russeva.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.russeva.web.dao.UserDao;
import ru.russeva.web.model.User;

import java.util.List;

@Service
public class UserServiceImpl  implements UserService{

    @Autowired
    private UserDao userDao;

    @Transactional
    @Override
    public void add(User user) {
        userDao.add(user);
    }
    @Transactional
    @Override
    public void edit(User user) {
        userDao.edit(user);

    }
    @Transactional
    @Override
    public void delete(long id) {
        userDao.delete(id);
    }
    @Transactional(readOnly = true)
    @Override
    public User getUserById(long id) {
        return userDao.getUserById(id);
    }
    @Transactional
    @Override
    public List<User> listUsers() {
        return userDao.listUsers();
    }
}
