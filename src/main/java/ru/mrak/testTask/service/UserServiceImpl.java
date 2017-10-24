package ru.mrak.testTask.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.mrak.testTask.dao.UserDao;
import ru.mrak.testTask.model.User;
import ru.mrak.testTask.model.UserFields;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    @Transactional
    public void addUser(User user) {
        userDao.addUser(user);
    }

    @Override
    @Transactional
    public void updateUser(User user) {
        userDao.updateUser(user);
    }

    @Override
    @Transactional
    public void removeUser(int id) {
        userDao.removeUser(id);
    }

    @Override
    @Transactional
    public User getUserById(int id) {
        return userDao.getUserById(id);
    }

    @Override
    @Transactional
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    @Transactional
    public List<User> getAllUsers(UserFields sortBy) {
        return userDao.getAllUsers(sortBy);
    }

    @Override
    @Transactional
    public List<User> getUsers(UserFields sortBy, int startLimit, int rowsLimit) {
        return userDao.getUsers(sortBy, startLimit, rowsLimit);
    }

    @Override
    @Transactional
    public List<User> getUsers(UserFields sortBy, int startLimit, int rowsLimit, User findUser) {
        return userDao.getUsers(sortBy, startLimit, rowsLimit, findUser);
    }

    @Override
    @Transactional
    public int getNumberOfRows() {
        return userDao.getNumberOfRows();
    }

    @Override
    @Transactional
    public int getNumberOfFindRows(User findUser) {
        return userDao.getNumberOfFindRows(findUser);
    }
}
